package Minesweeper;

import javax.swing.*;

public class LeaderBoard extends JFrame implements Runnable {
    public LeaderBoard() {
        super("Leaderboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void run() {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.setVisible(true);
    }
}

