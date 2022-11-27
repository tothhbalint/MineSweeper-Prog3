package Minesweeper;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;

/**
 * This class manages the leaderboard.
 */
public class LeaderBoard extends AbstractTableModel implements Serializable {

    /**
     * This variable stores the leaderboard.
     */
    static ArrayList<LeaderBoardEntry> leaderBoard = new ArrayList<>();

    /**
     * Constructor that reads the leaderboard from file.
     */
    public LeaderBoard(){
        try {
            readLeaderBoardFromFile();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * This method adds an entry to the leaderboard.
     */
    static public void addEntry(LeaderBoardEntry entry) {
        leaderBoard.add(entry);
    }

    /**
     * This method removes an entry from the leaderboard.
     */
    static public void removeEntry(LeaderBoardEntry entry) {
        leaderBoard.remove(entry);
    }

    /**
     * This method returns the leaderboard.
     */
    public static ArrayList<LeaderBoardEntry> getLeaderBoard() {
        return leaderBoard;
    }

    /**
     * This method writes the leaderboard to file.
     */
    public static void writeLeaderBoardToFile() {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("src/Minesweeper/leaderBoard.save");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(leaderBoard);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method reads the leaderboard from file.
     */
    public static void readLeaderBoardFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/Minesweeper/leaderBoard.save");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            leaderBoard = (ArrayList<LeaderBoardEntry>) objectInputStream.readObject();
            objectInputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method returns the number of rows in the leaderboard.
     */
    @Override
    public int getRowCount() {
        return leaderBoard.size();
    }

    /**
     * This method returns the number of columns in the leaderboard.
     */
    @Override
    public int getColumnCount() {
        return 3;
    }

    /**
     * This method returns the value at a given row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch( columnIndex ){
            case 0 -> {
                return leaderBoard.get(rowIndex).getName();
            }
            case 1 -> {
                return leaderBoard.get(rowIndex).getScore();
            }
            case 2 -> {
                return leaderBoard.get(rowIndex).getDifficulty();
            }
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
    }

    /**
     * This is a class for a leaderboard entry.
     */
    static public class LeaderBoardEntry implements Serializable {

        /**
         * This variable stores the name of the player.
         */
        private String name;

        /**
         * This variable stores the score of the player.
         */
        private int score;


        /**
         * This variable stores the difficulty of the game.
         */
        private String difficulty;

        /**
         * Constructor for a leaderboard entry.
         */
        public LeaderBoardEntry(String name, int score, String difficulty) {
            this.name = name;
            this.score = score;
            this.difficulty = difficulty;
        }

        /**
         * This method returns the name of the player.
         */
        public String getName() {
            return name;
        }

        /**
         * This method returns the score of the player.
         */
        public int getScore() {
            return score;
        }

        /**
         * This method returns the difficulty of the game.
         */
        public String getDifficulty() {return difficulty;}
    }
}

