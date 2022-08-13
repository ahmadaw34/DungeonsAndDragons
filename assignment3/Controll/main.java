import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                Player player = null;
                boolean flag = true;
                File levelsPath = new File(args[0]);
                File myLevel = new File(String.valueOf(levelsPath.getCanonicalFile()));
                File[] files = myLevel.listFiles();
                for (int filenum = 0; filenum < files.length && flag; filenum++) {
                    File myObj = files[filenum];
                    //File myObj = new File(args[0].toString()+"level" + filenum + ".txt");
                    //File myObj = new File("levels_dir/level" + filenum + ".txt");
                    Scanner myReader = new Scanner(myObj);
                    LinkedList<String[]> datalist = new LinkedList<>();
                    while (myReader.hasNextLine()) {
                        String str = myReader.nextLine();
                        String[] line = str.split("");
                        datalist.add(line);
                    }
                    myReader.close();
                    String[][] data = new String[datalist.size()][datalist.get(0).length];
                    for (int i = 0; i < data.length; i++) {
                        for (int j = 0; j < data[i].length; j++) {
                            data[i][j] = datalist.get(i)[j];
                        }
                    }
                    Controller controller = new Controller(data, player);
                    //if (player != null) controller.setPlayer(player);
                    player = controller.play();
                    if (player.getHealth().getAmount() <= 0) {
                        flag = false;
                    }
                }
            }
            System.out.println("congratulations you finished the game :)");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
