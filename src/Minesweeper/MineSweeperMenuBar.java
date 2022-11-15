package Minesweeper;

import javax.swing.*;

public class MineSweeperMenuBar extends JMenuBar {
    public MineSweeperMenuBar(){
        JMenu file = new JMenu("File");
        JMenu leaderboard = new JMenu("Leaderboard");
        JMenu help = new JMenu("Help");
        add(file);
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit To Menu");
        JMenuItem close = new JMenuItem("Close Game");
        file.add(save);
        file.add(exit);
        file.add(close);
        add(leaderboard);
        add(help);
        setVisible(true);
    }
}
