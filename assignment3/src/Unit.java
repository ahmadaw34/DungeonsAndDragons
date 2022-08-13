import java.util.logging.Level;

public abstract class Unit extends Tile {

    protected Board board;
    private String Name;
    private Health health;
    private int Attack;
    private int Defense;
    private int Experience;

    public Unit(char tile, String Name, Health health, int experience, int Defense, int Attack) {
        super(tile);
        this.Attack = Attack;
        this.health = health;
        this.Defense = Defense;
        this.Name = Name;
        this.Experience = experience;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getName() {
        return Name;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attackPoints) {
        Attack = attackPoints;
    }

    public int getDefense() {
        return Defense;
    }

    public void setDefense(int defensePoints) {
        Defense = defensePoints;
    }

    public abstract void gameTick();

    public abstract void Hit();

    public abstract void attacked(int attackPower);

    public abstract String toString();

    public abstract String description();

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }


}
