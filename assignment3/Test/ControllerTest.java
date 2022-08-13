import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;

    public ControllerTest(){
        String[][] data=new String[4][4];
        for(int i=0;i< data.length;i++){
            for(int j=0;j<data[i].length;j++){
                data[i][j]=".";
            }
        }
        controller=new Controller(data,null);
    }

    @Test
    void isNumericTest(){
        assertTrue(Controller.isNumeric("123"));
        assertFalse(Controller.isNumeric("a"));
    }

    @Test
    void buildTest(){
        assertEquals(controller.getBoard().getBoard().size(),0);
    }

    @Test
    void playTest(){
        Warrior JonSnow = new Warrior("Jon Snow", new Health(300), 3, 4, 30);
        controller.getBoard().getBoard().add(JonSnow);
        assertEquals(controller.getBoard().getBoard().getFirst(),JonSnow);
    }
}
