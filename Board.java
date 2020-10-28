package Chess;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @id 18024641
 * @author Michael Ritchie
 */
public class Board {

    public Square board[][];
    boolean whiteTurn = true;
    ChessPiece[] whitePieces = new ChessPiece[16];
    ChessPiece[] blackPieces = new ChessPiece[16];
    int whiteCount = 0;
    int blackCount = 0;

    /**
     * Initialize the board as an 8 by 8 grid, call the initialiseBoard method.
     * @param addPieces is a reference Boolean to parse to the initialiseBoard method.
    **/
    public Board(boolean addPieces) {
        board = new Square[8][8];
        initialiseBoard(addPieces);
    }

    /**
     * Add Squares to each of the grid positions on the board, if addPieces is true,
     * create a new board state, otherwise allow ChessMain to read in piece positions
     * from file. Add each piece to its respective array, being whitePieces or blackPieces
     * set each new piece to its appropriate square.
     * @param addPieces if true, will add the pieces to the chess board in their
     *                  starting positions, creating a new game.
    **/
    public void initialiseBoard(boolean addPieces) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square((i + 1), (char) (j + 65));
                if (addPieces == true) {
                    ChessPiece piece = addPiece(i, j);
                    if(piece == null)
                    {
                        continue;
                    }
                    else if (piece.isWhite) {
                        whitePieces[whiteCount] = piece;
                        whiteCount++;
                    } else {
                        blackPieces[blackCount] = piece;
                        blackCount++;
                    }
                    board[i][j].setPiece(piece);
                }
            }
        }
    }

    /**
     * Store the relevant information inside temporary variables. If a square
     * holds a piece, create a new piece to the specifications of the save file,
     * otherwise return. Add the piece to the appropriate chess piece array depending
     * on its color.
     * @param cell is a string taken from a line in the save file, contains information
     * on the current square, and the chess piece it holds.
    **/
    public void loadBoard(String cell) {
        int row = (int) (cell.charAt(1)) - 48;
        int col = (int) (cell.charAt(0)) - 48;
        if (cell.length() < 3) {
            return;
        }
        char pieceColour = cell.charAt(2);
        char pieceType = cell.charAt(3);
        char pieceMoved = cell.charAt(4);
        ChessPiece newPiece = null;
        switch (pieceType) {
            case 'K':
                newPiece = new King((pieceColour == 'W'), board[row][col]);
                board[row][col].setPiece(newPiece);
                break;
            case 'Q':
                newPiece = new Queen((pieceColour == 'W'), board[row][col]);
                board[row][col].setPiece(newPiece);
                break;
            case 'B':
                newPiece = new Bishop((pieceColour == 'W'), board[row][col]);
                board[row][col].setPiece(newPiece);
                break;
            case 'N':
                newPiece = new Knight((pieceColour == 'W'), board[row][col]);
                board[row][col].setPiece(newPiece);
                break;
            case 'R':
                newPiece = new Rook((pieceColour == 'W'), board[row][col]);
                board[row][col].setPiece(newPiece);
                break;
            case 'P':
                newPiece = new Pawn((pieceColour == 'W'), board[row][col]);
                board[row][col].setPiece(newPiece);
                break;
        }
        if (newPiece != null) {
            newPiece.moved = (pieceMoved == 'T');
            if (newPiece.isWhite) {
                this.whitePieces[whiteCount] = newPiece;
                whiteCount++;
            } else {
                this.blackPieces[blackCount] = newPiece;
                blackCount++;
            }
        }
    }

    /**
     * Depending on the inputs i and j, a piece will be generated for the appropriate
     * squares to achieve a new board state to play chess on.
     * @param i the index for the row of the piece.
     * @param j the index for the column of the piece.
    **/
    private ChessPiece addPiece(int i, int j) {
        switch (i) {
            case 0:
            case 7:
                switch (j) {
                    case 0:
                        return new Rook((i != 0), board[i][j]);
                    case 1:
                        return new Knight((i != 0), board[i][j]);
                    case 2:
                        return new Bishop((i != 0), board[i][j]);
                    case 3:
                        return new Queen((i != 0), board[i][j]);
                    case 4:
                        return new King((i != 0), board[i][j]);
                    case 5:
                        return new Bishop((i != 0), board[i][j]);
                    case 6:
                        return new Knight((i != 0), board[i][j]);
                    case 7:
                        return new Rook((i != 0), board[i][j]);
                }
                break;
            case 1:
                return new Pawn(false, board[i][j]);
            case 6:
                return new Pawn(true, board[i][j]);
            default:
                break;
        }
        return null;
    }

    /**
     * Determines the cell described by the save file, then if a piece is on this cell
     * and sets attributes for it based on the characters defined in the cell String.
     * @param cell is the string sent from the save file, representing one cell of the board
    **/
    public Square selectSquare(String cell) {
        String cellPattern = "^[a-hA-H]{1}[0-9]{1}";
        if (cell.matches(cellPattern) == true) {

            int col = (int) (cell.charAt(0)) - 65;
            if (Character.isLowerCase(cell.charAt(0))) {
                col -= 32;
            }
            int row = (int) (cell.charAt(1)) - 49;
            return board[row][col];
        }
        System.out.println("Please enter a valid square");
        return null;
    }

    /**
     * Determines whether the square that the piece is trying to move to exists within
     * the boundaries of the board space, if not, it will return null, otherwise the
     * square at the given row and column indexes will be returned.
     * @param current is the reference square that the piece in question is currently on.
     * @param rowMove is the number of squares to move between rows.
     * @param colMove is the number of squares to move between columns.
    **/
    public Square moveToSquare(Square current, int rowMove, int colMove) {
        if (current.getPiece().isWhite == true) {
            rowMove = -rowMove;
        }
        int newRow = current.getRow() - 1 + rowMove;
        //System.out.println("newRow: " + newRow);
        int newCol = (int) (current.getCol()) - 65 + colMove;
        //System.out.println("newCol: " + newCol);
        if (newRow > 7 || newRow < 0) {
            return null;
        }
        if (newCol > 7 || newCol < 0) {
            return null;
        }
        return board[newRow][newCol];
    }

    /**
     * Loops through each of the opposing pieces and checks to see whether they are
     * able to take the king piece, if so, the king is in check.
     * @param king is the king piece that is being checked whether it is in check.
    **/
    public boolean inCheck(ChessPiece king, Board board) {
        ChessPiece[] pieces = (king.isWhite) ? this.blackPieces : this.whitePieces;
        ArrayList<Square> possibleMoves;
        for (int i = 0; i < 16; i++) {
            if (pieces[i] == null) {
                continue;
            }
            possibleMoves = pieces[i].findMoves(board);
            for (Square possibleMove : possibleMoves) {
                if (possibleMove.equals(king.square)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prints the full board state, prints the row numbers and column letters of the board.
     * Then the squares are printed, if a piece exists on a square, its denotation will be
     * printed, the letter of its color and letter of its piece.
    **/
    public String toString() {
        String output = "  ";
        for (int i = -1; i < 8; i++) {
            if (i == -1) {
                for (int j = 0; j < 8; j++) {
                    output += " " + (char) (65 + j) + "  ";
                }
                output += "\n";
                continue;
            }
            for (int j = -1; j < 8; j++) {
                if (j == -1) {
                    output += (i + 1) + " ";
                    continue;
                }
                output += "[" + ((board[i][j].getPiece() != null) ? board[i][j].getPiece().toString() : "  ") + "]";
            }
            output += "\n";
        }
        return output;
    }
}