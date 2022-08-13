import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Monster extends Enemy {

    private int visionRange;

    public Monster(char tile, String Name, Health health, int visionRange, int experience, int Defense, int Attack) {
        super(tile, Name, health, experience, Defense, Attack);
        this.visionRange = visionRange;
    }

    public void travel() {
        Player p = (Player) board.getBoard().stream().filter(q -> q.getTile()=='@' /*instanceof Player*/).collect(Collectors.toList()).get(0);
        if (Range.range(this, p) < visionRange) {
            int dx = this.getX() - p.getX();
            int dy = this.getY() - p.getY();
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    moveLeft();
                } else {
                    moveRight();
                }
            } else {
                if (dy > 0) {
                    moveUp();
                } else {
                    moveDown();
                }
            }
            Hit();
        } else {
            Random rn=new Random();;
            int movement = rn.nextInt(5);
            //System.out.println(movement);
            //movement=0 =>left
            if (movement == 0) {
                moveLeft();
            }
            //movement=1 =>right
            if (movement == 1) {
                moveRight();
            }
            //movement=2 =>up
            if (movement == 2) {
                moveUp();
            }
            //movement=3 =>down
            if (movement == 3) {
                moveDown();
            }
            //movement=4 =>stay
            if (movement == 4) {
                return;
            }
        }
    }

    public void moveLeft() {
        if (this.getY() != 0) {
            List<Tile> t = board.getBoard().stream().filter(q -> q.getY() == this.getY() - 1 && q.getX() == this.getX()).collect(Collectors.toList());
            if (t.size() == 0) {
                setY(getY() - 1);
            }
        }
    }

    public void moveRight() {
        if (this.getY() != board.getBoard().getLast().getY()) {
            List<Tile> t = board.getBoard().stream().filter(q -> q.getY() == this.getY() + 1 && q.getX() == this.getX()).collect(Collectors.toList());
            if (t.size() == 0) {
                setY(getY() + 1);
            }
        }
    }

    public void moveUp() {
        if (this.getX() != 1) {
            List<Tile> t = board.getBoard().stream().filter(q -> q.getY() == this.getY() && q.getX() == this.getX() - 1).collect(Collectors.toList());
            if (t.size() == 0) {
                setX(getX() - 1);
            }
        }
    }

    public void moveDown() {
        if (this.getX() != board.getBoard().getLast().getX()) {
            List<Tile> t = board.getBoard().stream().filter(q -> q.getY() == this.getY() && q.getX() == this.getX() + 1).collect(Collectors.toList());
            if (t.size() == 0) {
                setX(getX() + 1);
            }
        }
    }

    public int getVisionRange() {
        return visionRange;
    }

    @Override
    public void gameTick() {
        travel();
    }

    @Override
    public void Hit() {
        Player p = (Player) board.getBoard().stream().filter(q -> q.getTile()=='@' /*instanceof Player*/).collect(Collectors.toList()).get(0);
        p.attacked(getAttack());
    }

    @Override
    public String description() {
        return getName()+"      "+"Health: " + getHealth().getAmount() + "/" + getHealth().getPool();
    }
}
