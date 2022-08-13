import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private Monster monster;

    public EnemyTest(){
        monster = new Monster('s', "Lannister Solider", new Health(80), 3, 25, 3, 8);
    }

    @Test
    void attackedTest(){
        monster.attacked(2);
        assertEquals(monster.getHealth().getAmount(),80);
    }

}
