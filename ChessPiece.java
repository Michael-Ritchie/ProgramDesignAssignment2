package Chess;

import java.util.ArrayList;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public abstract class ChessPiece {

    protected boolean isWhite;
    protected boolean moved;
    protected char letter;
    protected String name;
    protected Square square;

    /**
     * Initializes variables default values to be used for chess pieces
     * @param isWhite is a Boolean to determine what color the piece is.
     * @param letter is the character value denoted to the piece.
     * @param square is the square from the board that the piece is assigned to.
    **/
    public ChessPiece(boolean isWhite, char letter, Square square) {
        this.isWhite = isWhite;
        this.moved = false;
        this.letter = letter;
        this.square = square;
    }

    public abstract ArrayList<Square> findMoves(Board board);

    /**
     * Returns a string two characters long, one for the color of the piece and
     * the other being the letter denoted to the chess piece.
    **/
    public String toString() {
        String code = (isWhite == true) ? "W" : "B";
        code += letter;
        return String.valueOf(code);
    }
}
