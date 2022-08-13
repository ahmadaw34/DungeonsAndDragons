import java.util.List;
import java.util.stream.Collectors;

public class Mage extends Player {

    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    public Mage(String Name, Health health, int manaPool, int spellPower, int Defnse, int Attack) {
        super(Name, health, Defnse, Attack);
        this.manaPool = manaPool;
        currentMana = manaPool / 4;
        this.spellPower = spellPower;
        setAbilityName("Blizzard");
    }


    @Override
    public void CastSpecialAbility() {
        if (currentMana < manaCost) return;
        currentMana -= manaCost;
        Hit();
    }

    @Override
    public void Hit() {
        int hits = 0;
        while (hits < hitsCount) {
            List<Tile> filteredBoard = board.getBoard().stream().filter(q -> q.getTile() != '.' && q.getTile() != '#' && q.getTile() != '@' /*instanceof Enemy*/ && Range.range(q, this) <= abilityRange).collect(Collectors.toList());
            if (filteredBoard.size() != 0 && ((Unit) filteredBoard.get(0)).getHealth().getAmount() > 0) {
                if (filteredBoard.get(0).getTile() == 'B' || filteredBoard.get(0).getTile() == 'Q' || filteredBoard.get(0).getTile() == 'D' /*instanceof Trap*/) {
                    if (((Trap) filteredBoard.get(0)).isVisible()) {
                        System.out.println(((Enemy) filteredBoard.get(0)).description());
                        ((Enemy) filteredBoard.get(0)).attacked(spellPower);
                        hits++;
                    }
                } else {
                    System.out.println(((Enemy) filteredBoard.get(0)).description());
                    ((Enemy) filteredBoard.get(0)).attacked(spellPower);
                    hits++;
                }
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
                "Mana: " + currentMana + "/" + manaPool + "              " +
                "Spell Power: " + spellPower + "              ";
        return str;
    }


    @Override
    public void levelUp() {
        increasePlayerLevel();
        manaPool += 25 * getPlayerLevel();
        currentMana = Math.min(currentMana + (manaPool / 4), manaPool);
        spellPower += getPlayerLevel() * 10;
    }

    @Override
    public void gameTick() {
        currentMana = Math.min(manaPool, currentMana + getPlayerLevel());
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setHitsCount(int hitsCount) {
        this.hitsCount = hitsCount;
    }

    public void setAbilityRange(int abilityRange) {
        this.abilityRange = abilityRange;
    }
}
