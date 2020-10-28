package Chess;

import java.util.ArrayList;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class Rook extends ChessPiece {

    /**
     * Initializes the Rook by calling its super method and setting its name.
     * @param isWhite is a Boolean to determine the color of the piece.
     * @param square is the square that this piece will be placed on.
    **/
    public Rook(boolean isWhite, Square square) {
        super(isWhite, 'R', square);
        name = "Rook";
    }

    /**
     * Returns a list of squares that this piece is able to move to, the Rook
     * can move in a straight line for as far as possible across the board. If
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
                        newSquare = board.moveToSquare(this.square, distance, 0);
                        break;
                    case 1:
                        newSquare = board.moveToSquare(this.square, 0, -distance);
                        break;
                    case 2:
                        newSquare = board.moveToSquare(this.square, -distance, 0);
                        break;
                    case 3:
                        newSquare = board.moveToSquare(this.square, 0, distance);
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
