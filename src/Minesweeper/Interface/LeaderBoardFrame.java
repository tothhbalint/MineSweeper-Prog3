package Minesweeper.Interface;

import javax.swing.*;

import java.awt.*;

import static Minesweeper.FrameController.getLeaderBoard;
import static Minesweeper.FrameController.leaderBoardFrame;

public class LeaderBoardFrame extends JFrame implements Runnable {

    public LeaderBoardFrame() {
        super("Leaderboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400,200));
        setResizable(false);
        JTable table = new JTable(getLeaderBoard());
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
    }

    @Override
    public void run() {
        this.setVisible(true);
    }
}

