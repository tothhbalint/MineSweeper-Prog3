package Minesweeper;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;

public class LeaderBoard extends AbstractTableModel implements Serializable {

    static ArrayList<LeaderBoardEntry> leaderBoard = new ArrayList<>();

    public LeaderBoard(){
        try {
            readLeaderBoardFromFile();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static public void addEntry(LeaderBoardEntry entry) {
        leaderBoard.add(entry);
    }

    static public void removeEntry(LeaderBoardEntry entry) {
        leaderBoard.remove(entry);
    }

    public static ArrayList<LeaderBoardEntry> getLeaderBoard() {
        return leaderBoard;
    }

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

    @Override
    public int getRowCount() {
        return leaderBoard.size();
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

