import java.util.LinkedList;

public class Board {

    private LinkedList<Tile> board;

    public Board(LinkedList<Tile> board) {
        this.board = board;
    }

    public LinkedList<Tile> getBoard() {
        return board;
    }

    public String toString() {
        char[][] board = new char[getBoard().getLast().getX() + 1][getBoard().getLast().getY() + 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '.';
            }
        }
        for (int i = 0; i < getBoard().size(); i++) {
            board[getBoard().get(i).getX()][getBoard().get(i).getY()] = getBoard().get(i).getTile();
            if ((getBoard().get(i).getTile() == 'B' || getBoard().get(i).getTile() == 'Q' || getBoard().get(i).getTile() == 'D') /*instanceof Trap*/ && !((Trap) getBoard().get(i)).isVisible()) {
                board[getBoard().get(i).getX()][getBoard().get(i).getY()] = '.';
            }
        }
        String str = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                str += board[i][j];
            }
            str += "\n";
        }
        return str;
    }
}
