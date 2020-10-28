package Chess;

import java.util.ArrayList;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class Bishop extends ChessPiece {

    /**
     * Initializes the Bishop by calling its super method and setting its name.
     * @param isWhite is a Boolean to determine the color of the piece.
     * @param square is the square that this piece will be placed on.
    **/
    public Bishop(boolean isWhite, Square square) {
        super(isWhite, 'B', square);
        name = "Bishop";
    }
    
    /**
     * Returns a list of squares that this piece is able to move to, the Bishop
     * can move in a diagonal line for as far as possible across the board. If
     * a piece is not in the way, it will be allowed to move to the edge of the 
     * board.
    **/
    @Override
    public ArrayList<Square> findMoves(Board board) {
        ArrayList<Square> possibleMoves = new ArrayList();
        for (int i = 0; i < 4; i++) {
            boolean availableSquare = true;
            int distance = 0;
            while (availableSquare == true) {
                Square newSquare = null;
                distance += 1;
                switch (i) {
                    case 0:
                        newSquare = board.moveToSquare(this.square, distance, distance);
                        break;
                    case 1:
                        newSquare = board.moveToSquare(this.square, distance, -distance);
                        break;
                    case 2:
                        newSquare = board.moveToSquare(this.square, -distance, distance);
                        break;
                    case 3:
                        newSquare = board.moveToSquare(this.square, -distance, -distance);
                        break;
                }
                if (newSquare != null) {
                    if (newSquare.getPiece() != null) {
                        availableSquare = false;
                        if (newSquare.getPiece().isWhite == this.isWhite) {
                            break;
                        }
                    }
                    possibleMoves.add(newSquare);
                } else {
                    availableSquare = false;
                }
            }
        }
        return possibleMoves;
    }
}
