package Minesweeper.Interface;

import Minesweeper.LeaderBoard;

import javax.swing.*;

import static Minesweeper.LeaderBoard.*;
public class LeaderBoardFrame extends JFrame implements Runnable {

    LeaderBoard leaderBoard = new LeaderBoard();
    public LeaderBoardFrame() {
        super("Leaderboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        addEntry(new LeaderBoardEntry("Test", 100));
        JTable table = new JTable(leaderBoard);

        add(table);
    }

    @Override
    public void run() {
        LeaderBoardFrame leaderBoardFrame = new LeaderBoardFrame();
        leaderBoardFrame.setVisible(true);
    }
}

