import java.util.List;
import java.util.stream.Collectors;

public class Rogue extends Player {

    private int cost;
    private int currentEnergy;

    public Rogue(String Name, Health health, int cost, int Defense, int Attack) {
        super(Name, health, Defense, Attack);
        this.cost = cost;
        currentEnergy = 100;
        setAbilityName("Fan of Knives");
    }

    @Override
    public void CastSpecialAbility() {
        List<Tile> filteredBoard = board.getBoard().stream().filter(q -> q.getTile() != '.' && q.getTile() != '#' && q.getTile() != '@' /*instanceof Enemy*/ && Range.range(q, this) < 2).collect(Collectors.toList());
        if (filteredBoard.size() != 0 && ((Unit) filteredBoard.get(0)).getHealth().getAmount() > 0) {
            if (filteredBoard.get(0).getTile() == 'B' || filteredBoard.get(0).getTile() == 'Q' || filteredBoard.get(0).getTile() == 'D' /*instanceof Trap*/) {
                //System.out.println("is trap visible? "+((Trap) filteredBoard.get(0)).isVisible());
                if (((Trap) filteredBoard.get(0)).isVisible()) {
                    System.out.println(((Enemy) filteredBoard.get(0)).description());
                    Hit();
                    ((Enemy) filteredBoard.get(0)).attacked(getAttack());
                }
            } else {
                System.out.println(((Enemy) filteredBoard.get(0)).description());
                Hit();
                ((Enemy) filteredBoard.get(0)).attacked(getAttack());
            }
        }
    }

    @Override
    public void Hit() {
        //nothing special
    }

    @Override
    public void levelUp() {
        increasePlayerLevel();
        currentEnergy = 100;
        setAttack(getAttack() + getPlayerLevel() * 3);
    }

    @Override
    public void gameTick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public String description() {
        String str = "";
        str += getName() + "              " +
                "Health: " + getHealth().getAmount() + "/" + getHealth().getPool() + "              " +
                "Attack: " + getAttack() + "              " +
                "Defense: " + getDefense() + "              " +
                "Energy: " + currentEnergy + "/100" + "              ";
        return str;
    }
}
