import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Warrior JonSnow = new Warrior("Jon Snow", new Health(300), 3, 4, 30);

    @Test
    void increasePlayerLevelTest(){
        JonSnow.increasePlayerLevel();
        assertEquals(JonSnow.getPlayerLevel(),2);
    }

    @Test
    void attackedTest(){
        JonSnow.attacked(3);
        assertEquals(JonSnow.getHealth().getAmount(),JonSnow.getHealth().getPool());
    }
}
