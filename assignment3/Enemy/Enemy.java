import java.util.stream.Collectors;

public abstract class Enemy extends Unit {

    public Enemy(char tile, String Name, Health health, int experience, int Defense, int Attack) {
        super(tile, Name, health, experience, Defense, Attack);
    }

    @Override
    public void attacked(int attackPower) {
        if (attackPower > getDefense()) {
            getHealth().setAmount(getHealth().getAmount() - (attackPower - getDefense()));
        }
        if (getHealth().getAmount() <= 0) {
            Player p = (Player) board.getBoard().stream().filter(q -> q.getTile() == '@' /*instanceof Player*/).collect(Collectors.toList()).get(0);
            p.setExperience(getExperience() + p.getExperience());
            if (p.getExperience() >= 50 * p.getPlayerLevel()) {
                p.levelUp();
            }
            board.getBoard().remove(this);
        }
    }

    @Override
    public String toString() {
        return getTile() + "";
    }
}
