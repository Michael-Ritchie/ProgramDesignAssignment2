package Chess;

import java.util.Scanner;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class ChessMain {
    /**
     * Initializes a new ChessMain, asking whether a game should be loaded or a
     * new one started. Then lists the appropriate game files and asks user to
     * select one. Otherwise a new board is created to begin play. The Game
     * object's playChess() method is called. If the chess game ends, the save
     * file is deleted.
     */
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        model.addObserver(view); // Build connection between the view and the model.
    }
}        
//        ChessMain cm = new ChessMain();
//        Scanner input = new Scanner(System.in);
//        System.out.println("Welcome to Chess");
//        System.out.println("Would you like to load an existing game or start a new one? (Input a number to select an option)");
//        System.out.println("1. Load game");
//        System.out.println("2. New game");
//        int selection = input.nextInt();
//        if (selection == 1) {
//            System.out.println("Saved Games: ");
//            File directory = new File("test");
//            int count = 0;
//            ArrayList<File> games = new ArrayList();
//            for (File file : directory.listFiles()) {
//                if (file.getName().endsWith(".txt")) {
//                    count++;
//                    games.add(file);
//                    System.out.println(count + ". " + file.getName().substring(0, file.getName().length() - 4));
//                }
//            }
//            if (count > 0) {
//                int fileNum = 0;
//                boolean valid = false;
//                do {
//                    try {
//                        fileNum = Integer.parseInt(input.nextLine());
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Please input an number to select a file");
//                    }
//                } while (fileNum > count || fileNum < 1);
//                cm.saveFile = games.get(fileNum - 1).toString();
//            }
//            cm.getBoard();
//        } else if (selection == 2) {
//            System.out.println("Starting a new game");
//            cm.board.initialiseBoard(true);
//        }
//        Game game = new Game(cm.board);
//        boolean finished = game.playChess();
//        if (finished == true) {
//            File gameFile = new File(cm.saveFile);
//            gameFile.delete();
//        }
//    }

    /**
     * Attempts to open a file with the name specified by the saveFile String.
     * A board is generated through each line in the save file, each line represents
     * the information needed for one square on the board and the piece, if any,
     * that it contains.
     * If the file does not exist, a message is displayed and a new game is
     * started.
    **/
//    private void getBoard() {
//        File file = new File(saveFile);
//        if (!file.exists()) {
//            System.out.println("No save file found, starting a new game");
//            board.initialiseBoard(true);
//            return;
//        }
//        try {
//            System.out.println("Loading " + file.getName().substring(0, file.getName().length()));
//            FileInputStream fIn = new FileInputStream(file);
//            Scanner fileScanner = new Scanner(fIn);
//            board.whiteTurn = (fileScanner.nextLine().matches("T"));
//            while (fileScanner.hasNextLine()) {
//                String line = fileScanner.nextLine();
//                board.loadBoard(line);
//            }
//            fIn.close();
//        } catch (FileNotFoundException ex) {
//            System.err.print("File unable to be opened");
//        } catch (IOException ex) {
//            System.err.println("Stream exception occurred");
//        }
//    }
//}
