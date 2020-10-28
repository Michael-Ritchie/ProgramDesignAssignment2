/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Database {

    Connection conn = null;
    String url = "jdbc:derby:PlayerDB;create=true";  //url of the DB host
    String dbusername = "pdc";  //your DB username
    String dbpassword = "pdc";   //your DB password
    String tableName = "ChessGames";

    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (GAMENAME VARCHAR(20), TURN CHAR,"
                        + " A1 VARCHAR(5), A2 VARCHAR(5), A3 VARCHAR(5), A4 VARCHAR(5), A5 VARCHAR(5), A6 VARCHAR(5), A7 VARCHAR(5), A8 VARCHAR(5),"
                        + " B1 VARCHAR(5), B2 VARCHAR(5), B3 VARCHAR(5), B4 VARCHAR(5), B5 VARCHAR(5), B6 VARCHAR(5), B7 VARCHAR(5), B8 VARCHAR(5),"
                        + " C1 VARCHAR(5), C2 VARCHAR(5), C3 VARCHAR(5), C4 VARCHAR(5), C5 VARCHAR(5), C6 VARCHAR(5), C7 VARCHAR(5), C8 VARCHAR(5),"
                        + " D1 VARCHAR(5), D2 VARCHAR(5), D3 VARCHAR(5), D4 VARCHAR(5), D5 VARCHAR(5), D6 VARCHAR(5), D7 VARCHAR(5), D8 VARCHAR(5),"
                        + " E1 VARCHAR(5), E2 VARCHAR(5), E3 VARCHAR(5), E4 VARCHAR(5), E5 VARCHAR(5), E6 VARCHAR(5), E7 VARCHAR(5), E8 VARCHAR(5),"
                        + " F1 VARCHAR(5), F2 VARCHAR(5), F3 VARCHAR(5), F4 VARCHAR(5), F5 VARCHAR(5), F6 VARCHAR(5), F7 VARCHAR(5), F8 VARCHAR(5),"
                        + " G1 VARCHAR(5), G2 VARCHAR(5), G3 VARCHAR(5), G4 VARCHAR(5), G5 VARCHAR(5), G6 VARCHAR(5), G7 VARCHAR(5), G8 VARCHAR(5),"
                        + " H1 VARCHAR(5), H2 VARCHAR(5), H3 VARCHAR(5), H4 VARCHAR(5), H5 VARCHAR(5), H6 VARCHAR(5), H7 VARCHAR(5), H8 VARCHAR(5))");
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Unable to create table");

        }
    }

    public String[] getSaveFiles()
    {
        String[] saveFiles = new String[]{"No Save Files Found"};;
        int index = 0;
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT GAMENAME FROM ChessGames");
            if(rs.first())
            {
                rs.last();
                saveFiles = new String[rs.getRow()];
                rs.first();
                while(rs.next())
                {
                    System.out.println("SAVE FILE FOUND");
                    saveFiles[index] = rs.getString(index);
                    index++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Select all gamefiles, add to savefiles string;
        return saveFiles;
    }
    
    public Data checkSaveFile(String gameName) {
        Data data = new Data();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ChessGames WHERE GAMENAME = '" + gameName + "'");

            if (rs.next()) {
                Board board = new Board(false);

                board.whiteTurn = ("W".equals(rs.getString("TURN")));
                for (int i = 2; i < 66; i++) {
                    String line = rs.getString(i);
                    board.loadBoard(line);
                }
                data.currentBoard = board;
            } else {
                System.out.println("Save file not found, loading new board");
                Board board = new Board(true);
                statement.executeUpdate("INSERT INTO " + tableName + "(GAMENAME, TURN) VALUES('" + gameName + "', 'W')");
                data.currentBoard = board;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.boardName = gameName;
        data.startFlag = true;
        data.quitFlag = false;
        return data; //Back to checkName() of Model.java.
    }

    public void saveGame(Data data) {
        Statement statement;
        try {
            statement = conn.createStatement();
            for (int i = 0; i < 8; i++) {
                char col = (char) (65 + i);
                for (int j = 0; j < 8; j++) {
                    ChessPiece piece = data.currentBoard.board[i][j].getPiece();
                    String cell = Character.toString(col) + Integer.toString(j + 1);
                    String values = Integer.toString(j) + Integer.toString(i);
                    if (piece != null) {
                        values += ((piece.isWhite) ? "W" : "B") + piece.letter + ((piece.moved) ? "T" : "F");
                    }
                    statement.executeUpdate("INSERT INTO " + tableName + "(" + cell + ") VALUES('" + values + "') WHERE GAMENAME = '" + data.boardName + "'");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void quitGame(Data data) {
        Statement statement;
        try {
            statement = conn.createStatement();
            saveGame(data);
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
}
