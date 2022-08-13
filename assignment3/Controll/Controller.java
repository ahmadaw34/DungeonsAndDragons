import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {
    private Board board;
    private Player p;


    public Controller(String[][] data, Player p) {
        this.p = p;
        build(data);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Board getBoard() {
        return board;
    }

    public void setPlayer(Player player) {
        p = player;
    }

    public void build(String[][] data) {
        Warrior JonSnow = new Warrior("Jon Snow", new Health(300), 3, 4, 30);
        Warrior TheHound = new Warrior("The Hound", new Health(400), 5, 6, 20);
        Mage Melisandre = new Mage("Melisandre", new Health(100), 300, 15, 1, 5);
        Mage ThorosOfMyr = new Mage("Thoros of Myr", new Health(250), 150, 20, 4, 25);
        Rogue AryaStarck = new Rogue("Arya Starck", new Health(150), 2, 2, 40);
        Rogue Bronn = new Rogue("Bronn", new Health(250), 50, 3, 35);
        Hunter Ygritte = new Hunter("Ygritte", new Health(220), 2, 30, 6);
        LinkedList<Player> players = new LinkedList<>();
        players.add(JonSnow);
        players.add(TheHound);
        players.add(Melisandre);
        players.add(ThorosOfMyr);
        players.add(AryaStarck);
        players.add(Bronn);
        players.add(Ygritte);
        if (p == null) selectPlayer(players);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            if (p == null) {
                input = reader.readLine();
                while (!isNumeric(input) || !(Integer.parseInt(input) - 1 >= 0 && Integer.parseInt(input) - 1 < players.size())) {
                    if (!isNumeric(input)) System.out.println("Not a number");
                    selectPlayer(players);
                    input = reader.readLine();
                }
                System.out.println("You have selected: " + "\n" + players.get(Integer.parseInt(input) - 1).getName());
            }
            LinkedList<Tile> boards = new LinkedList<>();
            Board board = new Board(boards);
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j].charAt(0) == '#') {
                        Tile wall = new Tile('#');
                        wall.setX(i);
                        wall.setY(j);
                        boards.add(wall);
                    } else if (data[i][j].charAt(0) == '@') {
                        if (p == null) {
                            players.get(Integer.parseInt(input) - 1).setX(i);
                            players.get(Integer.parseInt(input) - 1).setY(j);
                            this.p = players.get(Integer.parseInt(input) - 1);
                        } else {
                            p.setX(i);
                            p.setY(j);
                        }
                        p.setBoard(board);
                        boards.add(p);
                    } else if (data[i][j].charAt(0) == 's') {
                        Monster LannisterSolider = new Monster('s', "Lannister Solider", new Health(80), 3, 25, 3, 8);
                        LannisterSolider.setX(i);
                        LannisterSolider.setY(j);
                        LannisterSolider.setBoard(board);
                        boards.add(LannisterSolider);
                    } else if (data[i][j].charAt(0) == 'k') {
                        Monster LannisterKnight = new Monster('k', "Lannister Knight", new Health(200), 4, 25, 8, 14);
                        LannisterKnight.setX(i);
                        LannisterKnight.setY(j);
                        LannisterKnight.setBoard(board);
                        boards.add(LannisterKnight);
                    } else if (data[i][j].charAt(0) == 'q') {
                        Monster QueensGuard = new Monster('q', "Queen's Guard", new Health(400), 5, 25, 15, 20);
                        QueensGuard.setX(i);
                        QueensGuard.setY(j);
                        QueensGuard.setBoard(board);
                        boards.add(QueensGuard);
                    } else if (data[i][j].charAt(0) == 'z') {
                        Monster Wright = new Monster('z', "Wright", new Health(600), 3, 25, 15, 30);
                        Wright.setX(i);
                        Wright.setY(j);
                        Wright.setBoard(board);
                        boards.add(Wright);
                    } else if (data[i][j].charAt(0) == 'b') {
                        Monster BearWright = new Monster('b', "Bear-Wright", new Health(1000), 4, 250, 30, 75);
                        BearWright.setX(i);
                        BearWright.setY(j);
                        BearWright.setBoard(board);
                        boards.add(BearWright);
                    } else if (data[i][j].charAt(0) == 'g') {
                        Monster GiantWright = new Monster('g', "Giant-Wright", new Health(1500), 5, 500, 40, 100);
                        GiantWright.setX(i);
                        GiantWright.setY(j);
                        GiantWright.setBoard(board);
                        boards.add(GiantWright);
                    } else if (data[i][j].charAt(0) == 'w') {
                        Monster WhiteWalker = new Monster('w', "White Walker", new Health(2000), 6, 1000, 50, 150);
                        WhiteWalker.setX(i);
                        WhiteWalker.setY(j);
                        WhiteWalker.setBoard(board);
                        boards.add(WhiteWalker);
                    } else if (data[i][j].charAt(0) == 'M') {
                        //Monster TheMountain = new Monster('M', "The Mountain", new Health(1000), 6, 500, 25, 60);
                        Boss TheMountain = new Boss('M', "The Mountain", new Health(1000), 500, 25, 60, 6, 5);
                        TheMountain.setX(i);
                        TheMountain.setY(j);
                        TheMountain.setBoard(board);
                        boards.add(TheMountain);
                    } else if (data[i][j].charAt(0) == 'C') {
                        //Monster QueenCersei = new Monster('C', "Queen Cersei", new Health(100), 1, 1000, 10, 10);
                        Boss QueenCersei = new Boss('C', "Queen Cersei", new Health(100), 1000, 10, 10, 1, 8);
                        QueenCersei.setX(i);
                        QueenCersei.setY(j);
                        QueenCersei.setBoard(board);
                        boards.add(QueenCersei);
                    } else if (data[i][j].charAt(0) == 'K') {
                        //Monster NightsKing = new Monster('K', "Night's King", new Health(5000), 8, 5000, 150, 300);
                        Boss NightsKing = new Boss('K', "Night's King", new Health(5000), 5000, 150, 300, 8, 3);
                        NightsKing.setX(i);
                        NightsKing.setY(j);
                        NightsKing.setBoard(board);
                        boards.add(NightsKing);
                    } else if (data[i][j].charAt(0) == 'B') {
                        Trap BonusTrap = new Trap('B', "Bonus Trap", new Health(1), 2, 5, 250, 1, 1);
                        BonusTrap.setX(i);
                        BonusTrap.setY(j);
                        BonusTrap.setBoard(board);
                        boards.add(BonusTrap);
                    } else if (data[i][j].charAt(0) == 'Q') {
                        Trap QueensTrap = new Trap('Q', "Queen's Trap", new Health(250), 3, 7, 100, 10, 50);
                        QueensTrap.setX(i);
                        QueensTrap.setY(j);
                        QueensTrap.setBoard(board);
                        boards.add(QueensTrap);
                    } else if (data[i][j].charAt(0) == 'D') {
                        Trap DeathTrap = new Trap('D', "Death Trap", new Health(500), 2, 10, 250, 20, 100);
                        DeathTrap.setX(i);
                        DeathTrap.setY(j);
                        DeathTrap.setBoard(board);
                        boards.add(DeathTrap);
                    }
                }
            }
            this.board = board;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player play() {
        boolean allEnemiesDied = false;
        while (p.getHealth().getAmount() > 0 && !allEnemiesDied) {
            System.out.println(board);
            System.out.println(p.description() +
                    "Level: " + p.getPlayerLevel() + "              " +
                    "Experience: " + p.getExperience() + "/" + 50 * p.getPlayerLevel() + "\n");

            for (int i = 0; i < board.getBoard().size(); i++) {
                if (board.getBoard().get(i).getTile() != '#' && board.getBoard().get(i).getTile() != '.') {//unit
                    // System.out.println(board.getBoard().get(i).getTile()+":   "+board.getBoard().get(i).getX()+","+board.getBoard().get(i).getY());
                    ((Unit) board.getBoard().get(i)).gameTick();
                }
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = null;
            try {
                input = reader.readLine();
                while (!input.equals("w") && !input.equals("s") && !input.equals("d") && !input.equals("a") && !input.equals("q") && !input.equals("e")) {
                    input = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            char act = input.charAt(0);
            if (act == 'w' && p.getX() != 0) {
                List<Tile> t = board.getBoard().stream().filter(q -> q.getX() == p.getX() - 1 && q.getY() == p.getY()).collect(Collectors.toList());
                if (t.size() == 0) {
                    p.moveUp();
                }
            }
            if (act == 's' && p.getX() != board.getBoard().getLast().getX()) {
                List<Tile> t = board.getBoard().stream().filter(q -> q.getX() == p.getX() + 1 && q.getY() == p.getY()).collect(Collectors.toList());
                if (t.size() == 0) {
                    p.moveDown();
                }
            }
            if (act == 'a' && p.getY() != 0) {
                List<Tile> t = board.getBoard().stream().filter(q -> q.getY() == p.getY() - 1 && q.getX() == p.getX()).collect(Collectors.toList());
                if (t.size() == 0) {
                    p.moveLeft();
                }
            }
            if (act == 'd' && p.getY() != board.getBoard().getLast().getY()) {
                List<Tile> t = board.getBoard().stream().filter(q -> q.getY() == p.getY() + 1 && q.getX() == p.getX()).collect(Collectors.toList());
                if (t.size() == 0) {
                    p.moveRight();
                }
            }
            if (act == 'e') {
                p.CastSpecialAbility();
            }
            if (act == 'q') {
                //do nothing
            }
            allEnemiesDied = true;
            for (int i = 0; i < board.getBoard().size() && allEnemiesDied; i++) {
                if (!Character.toString(board.getBoard().get(i).getTile()).equals("#")
                        && !Character.toString(board.getBoard().get(i).getTile()).equals("@")
                        && !Character.toString(board.getBoard().get(i).getTile()).equals(".")) {
                    allEnemiesDied = false;
                }
            }
        }
        return p;
    }

    public void selectPlayer(LinkedList<Player> players) {
        System.out.println("Select player:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + 1 + ". " + players.get(i).description() +
                    "Level: " + players.get(i).getPlayerLevel() + "              " +
                    "Experience: " + players.get(i).getExperience() + "/" + 50 * players.get(i).getPlayerLevel() + "\n");
        }
    }
}
