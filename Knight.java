package Chess;

import java.util.ArrayList;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class Knight extends ChessPiece {

    /**
     * Initializes the Knight by calling its super method and setting its name.
     * @param isWhite is a Boolean to determine the color of the piece.
     * @param square is the square that this piece will be placed on.
    **/
    public Knight(boolean isWhite, Square square) {
        super(isWhite, 'N', square);
        name = "Knight";
    }
    
    /**
     * Returns a list of squares that this piece is able to move to, the knight
     * can move in an L-shaped manner, two squares in a straight line, then an 
     * additional square in a direction perpendicular to the previous.
    **/
    @Override
    public ArrayList<Square> findMoves(Board board) {
        ArrayList<Square> possibleMoves = new ArrayList();
        int radius = 2;
        Square newSquare;

        if ((newSquare = board.moveToSquare(this.square, radius, 1)) != null) {
            if (newSquare.getPiece() != null && newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            } else {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, radius, -1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, -radius, 1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, -radius, -1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, 1, radius)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, -1, radius)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, 1, -radius)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, -1, -radius)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        return possibleMoves;
    }
}
