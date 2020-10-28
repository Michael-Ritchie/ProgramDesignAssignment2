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
