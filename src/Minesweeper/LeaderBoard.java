package Minesweeper;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.ArrayList;

public class LeaderBoard extends AbstractTableModel implements Serializable {

    static ArrayList<LeaderBoardEntry> leaderBoard = new ArrayList<>();

    public LeaderBoard() {
    }

    static public void addEntry(LeaderBoardEntry entry) {
        leaderBoard.add(entry);
    }

    static public void removeEntry(LeaderBoardEntry entry) {
        leaderBoard.remove(entry);
    }

    static void clearLeaderBoard() {
        leaderBoard.clear();
    }

    public static ArrayList<LeaderBoardEntry> getLeaderBoard() {
        return leaderBoard;
    }

    @Override
    public int getRowCount() {
        return leaderBoard.size()/2;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch( columnIndex ){
            case 0 -> {
                return leaderBoard.get(rowIndex).getName();
            }
            case 1 -> {
                return leaderBoard.get(rowIndex).getScore();
            }
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
    }

    static public class LeaderBoardEntry implements Serializable {
        private String name;
        private int score;

        public LeaderBoardEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}

