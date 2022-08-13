import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void toStringTest(){
        Board board=new Board(new LinkedList<>());
        assertEquals(board.toString(),"");
    }
}
