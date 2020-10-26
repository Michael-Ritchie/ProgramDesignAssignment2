package Chess;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class Game {

    Board board;

    /**
     * If a board has not been parsed, create a new board to play on.
     * Otherwise, use the board that has been parsed.
     * @param board is a board to use if one has been loaded from a save file.
    **/
    public Game(Board board) {
        if (board == null) {
            this.board = new Board(true);
        } else {
            this.board = board;
        }
    }
    /**
     * Firstly displays board state, then checks if the current player is in check and outputs if so, 
     * displays current players turn, and how to select a piece. Takes input from
     * the user, piece selection, and makes them repeat if invalid or not in the correct format.
     * User can also save the game at this point by typing 'save', this creates a save file,
     * asking for the user to name it, and exits the program once save has been completed.
     * Once a piece has been selected, all valid moves are displayed, the user must enter
     * one of the displayed square codes to continue, else type 'x' to return to piece selection.
     * The input is then converted into a square to move to and the pieces are swapped accordingly
     * If the player is still in check at the end of their turn, the game ends.
     * This loops until the game is saved or checkmate occurs.
    **/
    public boolean playChess() {
        Scanner scanner = new Scanner(System.in);
        Square selectedSquare;
        Square moveToSquare = null;
        boolean validSquare = false;
        boolean changeTurn = true;
        ArrayList<Square> possibleMoves;
        while (true) {
            System.out.println(board.toString());
            System.out.println((board.whiteTurn) ? "Whites Turn" : "Blacks Turn");
            if (board.whiteTurn) {
                for (int i = 0; i < board.whiteCount; i++) {
                    if (board.whitePieces[i].letter == 'K') {
                        if (board.inCheck(board.whitePieces[i])) {
                            System.out.println("White is in check");
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < board.blackCount; i++) {
                    if (board.blackPieces[i].letter == 'K') {
                        if (board.inCheck(board.blackPieces[i])) {
                            System.out.println("Black is in check");
                            break;
                        }
                    }
                }
            }
            System.out.println("Select a piece by entering its column letter followed by its row number");
            System.out.println("Type \'save\' to save this game");
            do {
                String input = scanner.nextLine();
                if (input.matches("save")) {
                    System.out.print("Name the saved game : ");
                    String saveName;
                    do {
                        saveName = scanner.nextLine();
                        saveName = saveName.replaceAll("[\\\\/:*?\"<>|]", "");
                    } while (saveName.length() < 1);
                    board.saveBoard("test/" + saveName + ".txt");
                    return false;
                }
                selectedSquare = board.selectSquare(input);
            } while (!this.validSquare(selectedSquare));
            possibleMoves = selectedSquare.getPiece().findMoves();
            if (possibleMoves.isEmpty()) {
                System.out.println("No moves possible for " + selectedSquare.getPiece().name + " at " + selectedSquare.toString());
                continue;
            }
            System.out.println("Choose a square to move to : (Type \'x\' to return to piece selection)");
            for (int i = 0; i < possibleMoves.size(); i++) {
                System.out.print(possibleMoves.get(i).toString() + (i < possibleMoves.size() - 1 ? ", " : "\n"));
            }
            do {
                validSquare = false;
                String input = scanner.nextLine();
                if (input.matches("x")) {
                    changeTurn = false;
                    break;
                }
                moveToSquare = board.selectSquare(input);
                if (moveToSquare == null) {
                    continue;
                }
                for (int i = 0; i < possibleMoves.size(); i++) {
                    if (moveToSquare.getRow() == possibleMoves.get(i).getRow()
                            && moveToSquare.getCol() == possibleMoves.get(i).getCol()) {
                        validSquare = true;
                    }
                }
                if (!validSquare) {
                    System.out.println("Please select a valid move");
                }
            } while (validSquare == false);
            if (changeTurn == true) {
                selectedSquare.getPiece().moved = true;
                moveToSquare.setPiece(selectedSquare.getPiece());
                moveToSquare.getPiece().square = moveToSquare;
                selectedSquare.setPiece(null);

                if (board.whiteTurn) {
                    for (int i = 0; i < board.whiteCount; i++) {
                        if (board.whitePieces[i].letter == 'K') {
                            if (board.inCheck(board.whitePieces[i])) {
                                System.out.println("White has been checkmated. Black Wins!");
                                return true;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < board.blackCount; i++) {
                        if (board.blackPieces[i].letter == 'K') {
                            if (board.inCheck(board.blackPieces[i])) {
                                System.out.println("Black has been checkmated. White Wins!");
                                return true;
                            }
                        }
                    }
                }
                board.whiteTurn = !board.whiteTurn;
            }
        }
    }
    /**
     * Method returns false if the selected square does not exist, or if the selected square
     * does not have a piece on it, or if it is the other players turn. Verification that
     * the square is complete.
     * @param square is the square to validate, checking whether it exists and holds a piece.
    **/
    private boolean validSquare(Square square) {
        if (square == null) {
            return false;
        }
        if (square.getPiece() == null) {
            System.out.println("Invalid Input. Please enter a valid square");
            return false;
        } else if (board.whiteTurn != square.getPiece().isWhite) {
            System.out.println("It is " + ((board.whiteTurn) ? "white's " : "black's ") + "turn");
            return false;
        }
        return true;
    }

}
