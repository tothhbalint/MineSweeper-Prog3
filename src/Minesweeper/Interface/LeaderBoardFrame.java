package Minesweeper.Interface;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;

import static Minesweeper.FrameController.*;

public class LeaderBoardFrame extends JFrame implements Runnable {

    public LeaderBoardFrame() {
        super("Leaderboard");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400,200));
        setResizable(false);
        JTable table = new JTable(getLeaderBoard());
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
    }


    public void disableFrame() {
        this.setVisible(false);
    }

    public void enableFrame() {
        this.setVisible(true);
    }

    @Override
    public void run() {
        this.setVisible(true);
    }
}

