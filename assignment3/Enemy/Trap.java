import java.util.stream.Collectors;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tile, String Name, Health health, int visibilityTime, int invisibilityTime, int experience, int Defense, int Attack) {
        super(tile, Name, health, experience, Defense, Attack);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        ticksCount = 0;
        visible = true;
    }

    @Override
    public void gameTick() {
        visible = ticksCount < visibilityTime;
        if (ticksCount == visibilityTime + invisibilityTime) {
            ticksCount = 0;
        } else {
            ticksCount++;
        }
        Hit();
    }

    @Override
    public void Hit() {
        Player p = (Player) board.getBoard().stream().filter(q -> q.getTile() == '@' /*instanceof Player*/).collect(Collectors.toList()).get(0);
        if (Range.range(this, p) < 2 && visible) {
            p.attacked(getAttack());
        }
    }

    public int getVisibilityTime() {
        return visibilityTime;
    }

    public int getInvisibilityTime() {
        return invisibilityTime;
    }

    public boolean isVisible() {
        return visible;
    }

    public String description() {
        /*String str = "";
        str += "Name: " + getName() + "\n";
        str += "Health amount: " + getHealth().getAmount() + "\n";
        str += "Health pool: " + getHealth().getPool() + "\n";
        str += "Attack: " + getAttack() + "\n";
        str += "Defense: " + getDefense() + "\n";
        str += "Experience: " + getExperience() + "\n";
        str += "Visible: " + visible + "\n";
        str += "................................." + "\n";
        return str;*/
        return getName() + "      " + "Health: " + getHealth().getAmount() + "/" + getHealth().getPool();
    }
}
