package Chess;

import java.util.ArrayList;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class Pawn extends ChessPiece {

    /**
     * Initializes the Pawn by calling its super method and setting its name.
     * @param isWhite is a Boolean to determine the color of the piece.
     * @param square is the square that this piece will be placed on.
    **/
    public Pawn(boolean isWhite, Square square) {
        super(isWhite, 'P', square);
        name = "Pawn";
    }

    /**
     * Returns a list of squares that this piece is able to move to, the Pawn
     * can only move forwards, down a column. If it has not yet moved, it is allowed
     * to move to squares. Pawns can only capture pieces that are in front of them
     * on a diagonal. If a pawn reaches the oppositions back line, it is promoted to
     * a piece of the players choosing.
    **/
    @Override
    public ArrayList<Square> findMoves(Board board) {
        ArrayList<Square> possibleMoves = new ArrayList();

        for (int i = 0; i < 4; i++) {
            Square newSquare = null;
            switch (i) {
                case 0:
                    if (this.moved == false) {
                        newSquare = board.moveToSquare(this.square, 2, 0);
                        possibleMoves.add(newSquare);
                    }
                    break;
                case 1:
                    newSquare = board.moveToSquare(this.square, 1, 0);
                    if (newSquare != null && newSquare.getPiece() == null) {
                        possibleMoves.add(newSquare);
                    }
                    break;
                case 2:
                    newSquare = board.moveToSquare(this.square, 1, 1);
                    if (newSquare != null && newSquare.getPiece() != null && newSquare.getPiece().isWhite != this.isWhite) {
                        possibleMoves.add(newSquare);
                    }
                    break;
                case 3:
                    newSquare = board.moveToSquare(this.square, 1, -1);
                    if (newSquare != null && newSquare.getPiece() != null && newSquare.getPiece().isWhite != this.isWhite) {
                        possibleMoves.add(newSquare);
                    }
                    break;
            }
        }
        possibleMoves.removeIf(s -> (s == null));
        return possibleMoves;
    }
}
