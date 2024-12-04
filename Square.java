package Chess;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class Square {

    private int row;
    private char col;
    private ChessPiece piece;

    /**
     * Initializes the square variables
     * @param row the row number of the square. 
     * @param col the column letter of the square
    **/
    public Square(int row, char col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    public int getRow() {
        return this.row;
    }

    public char getCol() {
        return this.col;
    }

    public ChessPiece getPiece() {
        return this.piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    /**
     * returns a string which represents the squares location on the board
     * using its column letter and row number.
    **/
    public String toString() {
        return String.valueOf(col) + row;
    }
}
