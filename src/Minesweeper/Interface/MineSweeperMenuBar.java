package Minesweeper.Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Minesweeper.MineSweeperMain.menuFrame;
import static Minesweeper.Interface.LeaderBoardFrame.*;

public class MineSweeperMenuBar extends JMenuBar {
    public MineSweeperMenuBar(){
        JMenu file = new JMenu("File");
        JMenu leaderboard = new JMenu("Leaderboard");
        leaderboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        JMenu help = new JMenu("Help");
        add(file);
        JMenuItem exit = new JMenuItem("Exit To Menu");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread.currentThread().interrupt();
                menuFrame.enableFrame();
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
