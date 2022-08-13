import java.util.List;
import java.util.stream.Collectors;

public class Hunter extends Player {

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(String Name, Health health, int Defense, int Attack, int range) {
        super(Name, health, Defense, Attack);
        this.range = range;
        arrowsCount = 10;
        ticksCount = 0;
        setAbilityName("Shoot");
    }

    @Override
    public void CastSpecialAbility() {
        List<Tile> filteredBoard = board.getBoard().stream().filter(q -> q.getTile() != '#' && q.getTile() != '.' && q.getTile() != '@' /*instanceof Enemy*/ && Range.range(this, q) <= range).collect(Collectors.toList());
        if (arrowsCount != 0 && filteredBoard.size() != 0) {
            if (filteredBoard.get(0).getTile() == 'B' || filteredBoard.get(0).getTile() == 'Q' || filteredBoard.get(0).getTile() == 'D' /*instanceof Trap*/) {
                if (((Trap) filteredBoard.get(0)).isVisible()) {
                    Hit();
                }
            } else {
                Hit();
            }
        }
    }

    @Override
    public void levelUp() {
        increasePlayerLevel();
        arrowsCount += (10 * getPlayerLevel());
        setAttack(getAttack() + (2 * getPlayerLevel()));
        setDefense(getDefense() + getPlayerLevel());
    }

    @Override
    public void gameTick() {
        if (ticksCount == 10) {
            arrowsCount += getPlayerLevel();
            ticksCount = 0;
        } else {
            ticksCount++;
        }
    }

    @Override
    public void Hit() {
        arrowsCount--;
        List<Tile> filteredBoard = board.getBoard().stream().filter(q -> q.getTile() != '.' && q.getTile() != '#' && q.getTile() != '@' /*instanceof Enemy*/ && Range.range(this, q) <= range).collect(Collectors.toList());
        if (filteredBoard.size() != 0) {
            Enemy colsestEnemy = ((Enemy) filteredBoard.get(0));
            for (int i = 0; i < filteredBoard.size(); i++) {
                if (Range.range(colsestEnemy, this) > Range.range(this, filteredBoard.get(i))) {
                    colsestEnemy = ((Enemy) filteredBoard.get(i));
                }
            }
            colsestEnemy.attacked(this.getAttack());
        }
    }

    @Override
    public String description() {
        String str = "";
        str += getName() + "              " +
                "Health: " + getHealth().getAmount() + "/" + getHealth().getPool() + "              " +
                "Attack: " + getAttack() + "              " +
                "Defense: " + getDefense() + "              " +
                "Arrows: " + arrowsCount + "              " +
                "Range: " + range + "              ";
        return str;
    }
}
