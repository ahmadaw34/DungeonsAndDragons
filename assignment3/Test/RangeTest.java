import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    @Test
    void rangeTest(){
        Tile t1=new Tile('1');
        t1.setX(0);t1.setY(2);
        Tile t2=new Tile('2');
        t2.setX(0);t2.setY(0);
        assertEquals(Range.range(t1,t2),2);
    }
}
