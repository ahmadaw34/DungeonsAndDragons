import java.util.List;
import java.util.stream.Collectors;

public class Warrior extends Player {
    private int abilityCooldown;
    private int remainingCooldown;

    public Warrior(String Name, Health health, int abilityCooldown, int Defense, int Attack) {
        super(Name, health, Defense, Attack);
        this.abilityCooldown = abilityCooldown;
        remainingCooldown = 0;
        setAbilityName("Avenger’s Shield");
    }

    @Override
    public void levelUp() {
        increasePlayerLevel();
        remainingCooldown = 0;
        getHealth().setPool(getHealth().getPool() + getPlayerLevel() * 5);
        setAttack(getAttack() + getPlayerLevel() * 2);
        setDefense(getDefense() + getPlayerLevel());
    }

    @Override
    public void gameTick() {
        if (remainingCooldown > 0) remainingCooldown--;
    }

    @Override
    public void CastSpecialAbility() {
        if (remainingCooldown == 0) {
            Hit();
            getHealth().setAmount(Math.min(getHealth().getAmount() + getDefense() * 10, getHealth().getPool()));
            remainingCooldown = abilityCooldown;
        }
    }

    @Override
    public void Hit() {
        List<Tile> filteredBoard = board.getBoard().stream().filter(q -> q.getTile() != '.' && q.getTile() != '#' && q.getTile() != '@' /*instanceof Enemy*/ && Range.range(this, q) < 3).collect(Collectors.toList());
        if (filteredBoard.size() != 0 && ((Unit) filteredBoard.get(0)).getHealth().getAmount() > 0) {
            if (filteredBoard.get(0).getTile() == 'B' || filteredBoard.get(0).getTile() == 'Q' || filteredBoard.get(0).getTile() == 'D' /*instanceof Trap*/) {
                if (((Trap) filteredBoard.get(0)).isVisible()) {
                    System.out.println(((Enemy) filteredBoard.get(0)).description());
                    setAttack(getHealth().getPool() / 10);
                    ((Enemy) filteredBoard.get(0)).attacked(getAttack());
                    return;
                }
            } else {
                System.out.println(((Enemy) filteredBoard.get(0)).description());
                setAttack(getHealth().getPool() / 10);
                ((Enemy) filteredBoard.get(0)).attacked(getAttack());
                return;
            }
        }
    }

    @Override
    public String description() {
        String str = "";
        str += getName() + "              " +
                "Health: " + getHealth().getAmount() + "/" + getHealth().getPool() + "              " +
                "Attack: " + getAttack() + "              " +
                "Defense: " + getDefense() + "              " +
                "Cooldown: " + getRemainingCooldown() + "/" + getAbilityCooldown() + "              ";
        return str;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }
}
