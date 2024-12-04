/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class Model extends Observable {

    public Database db;
    public Data data;
    public Board board;

    public Model() {
        this.db = new Database();
        this.db.dbsetup();
    }

    public void loadBoard(String game)
    {
        this.data = this.db.checkSaveFile(game);
        this.notifyObservers(this.data);
    }
    
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
                        if (board.inCheck(board.whitePieces[i], data.currentBoard)) {
                            System.out.println("White is in check");
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < board.blackCount; i++) {
                    if (board.blackPieces[i].letter == 'K') {
                        if (board.inCheck(board.blackPieces[i], data.currentBoard)) {
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
                    db.saveGame(data);
                    return false;
                }
                selectedSquare = board.selectSquare(input);
            } while (!this.validSquare(selectedSquare));
            possibleMoves = selectedSquare.getPiece().findMoves(data.currentBoard);
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
                            if (board.inCheck(board.whitePieces[i], data.currentBoard)) {
                                System.out.println("White has been checkmated. Black Wins!");
                                return true;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < board.blackCount; i++) {
                        if (board.blackPieces[i].letter == 'K') {
                            if (board.inCheck(board.blackPieces[i], data.currentBoard)) {
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
     * Method returns false if the selected square does not exist, or if the
     * selected square does not have a piece on it, or if it is the other
     * players turn. Verification that the square is complete.
     *
     * @param square is the square to validate, checking whether it exists and
     * holds a piece.
    *
     */
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
