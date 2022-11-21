package Minesweeper.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Minesweeper.FrameController.*;
import static Minesweeper.Game.GameControl.*;

public class MineSweeperMenuBar extends JMenuBar {
    public MineSweeperMenuBar(){
        JMenu file = new JMenu("File");
        JMenuItem leaderboard = new JMenuItem("Leaderboard");
        leaderboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leaderBoardFrame.enableFrame();
            }
        });
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        add(file);

        JMenuItem exit = new JMenuItem("Exit To Menu");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.enableFrame();
                getGameMainFrame().dispose();
            }
        });
        JMenuItem close = new JMenuItem("Close Game");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        file.add(exit);
        file.add(close);
        add(leaderboard);
        add(help);
        setVisible(true);
    }
}
