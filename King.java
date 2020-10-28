package Chess;

import java.util.ArrayList;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class King extends ChessPiece {

    /**
     * Initializes the King by calling its super method and setting its name.
     * @param isWhite is a Boolean to determine the color of the piece.
     * @param square is the square that this piece will be placed on.
    **/
    public King(boolean isWhite, Square square) {
        super(isWhite, 'K', square);
        name = "King";
    }
    
    /**
     * Returns a list of squares that this piece is able to move to, the King
     * can move one square in any of the eight directions. This piece is the only
     * one that is unable to be captured.
    **/
    @Override
    public ArrayList<Square> findMoves(Board board) {
        ArrayList<Square> possibleMoves = new ArrayList();
        Square newSquare;

        if ((newSquare = board.moveToSquare(this.square, 1, 0)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, 1, 1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, 0, 1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, -1, 1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, -1, 0)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, -1, -1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, 0, -1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        if ((newSquare = board.moveToSquare(this.square, 1, -1)) != null) {
            if (newSquare.getPiece() == null) {
                possibleMoves.add(newSquare);
            } else if (newSquare.getPiece().isWhite != this.isWhite) {
                possibleMoves.add(newSquare);
            }
        }
        return possibleMoves;
    }
}
