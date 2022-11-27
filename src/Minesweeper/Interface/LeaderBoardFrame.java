package Minesweeper.Interface;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;

import static Minesweeper.FrameController.*;
import static Minesweeper.LeaderBoard.writeLeaderBoardToFile;


/**
 * Class for the Leaderboards Frame
 */
public class LeaderBoardFrame extends JFrame implements Runnable {

    /**
     * Constructor for the Leaderboards Frame
     */
    public LeaderBoardFrame() {
        super("Leaderboard");
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                writeLeaderBoardToFile();
                dispose();
            }
        });
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600,200));
        setResizable(false);
        JTable table = new JTable(getLeaderBoard());
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
    }


    /**
     * Method to disable the Leaderboards Frame
     */
    public void disableFrame() {
        this.setVisible(false);
    }

    /**
     * Method to enable the Leaderboards Frame
     */
    public void enableFrame() {
        this.setVisible(true);
    }

    /**
     * Method to run the Leaderboards Frame as a thread
     */
    @Override
    public void run() {
        this.setVisible(true);
    }
}

