public abstract class Player extends Unit implements HeroicUnit {

    private int Level;
    private String abilityName;

    public Player(String Name, Health health, int Defense, int Attack) {
        super('@', Name, health, 0, Defense, Attack);
        Level = 1;
    }

    public int getPlayerLevel() {
        return Level;
    }

    public void increasePlayerLevel() {
        setExperience(getExperience() - 50 * Level);
        Level++;
        getHealth().setPool(getHealth().getPool() + 10 * Level);
        getHealth().setAmount(getHealth().getPool());
        setAttack(getAttack() + Level * 4);
        setDefense(getDefense() + Level);
    }

    public abstract void levelUp();

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public void moveUp() {
        setX(getX() - 1);
    }

    public void moveDown() {
        setX(getX() + 1);
    }

    public void moveLeft() {
        setY(getY() - 1);
    }

    public void moveRight() {
        setY(getY() + 1);
    }

    @Override
    public void attacked(int attackPower) {
        if (attackPower > getDefense()) {
            getHealth().setAmount(getHealth().getAmount() - (attackPower - getDefense()));
        }
        if (getHealth().getAmount() <= 0) {
            try {
                getHealth().setAmount(0);
                setTile('X');
                GAMEOVER();
            } catch (Exception e) {
            }
        }
    }

    public void GAMEOVER() {
        System.out.println(board);
        System.out.println(this.description());
        System.out.println("GAME OVER!");
    }

    @Override
    public String toString() {
        return getTile() + "";
    }
}
