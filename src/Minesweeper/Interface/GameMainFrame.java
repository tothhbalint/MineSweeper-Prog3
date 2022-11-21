package Minesweeper.Interface;

import Minesweeper.Game.Fields.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static Minesweeper.MineSweeperMain.menuFrame;

public class GameMainFrame extends JFrame implements Runnable{
    static JLabel livesField;

    static JLabel timeField;

public GameMainFrame(int n, Empty[][] fields){
    super("Minesweeper");
    setSize(n*50 + 50 , n*50 + 100);
    addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            menuFrame.enableFrame();
        }
    });
    setLayout(new FlowLayout());
    livesField = new JLabel("Lives: " + 3);
    timeField = new JLabel("Time: " + 0);
    JPanel gamePanel = new JPanel();

    gamePanel.setSize(n*50+50, n*50+50);
    gamePanel.setLayout(new GridLayout(n,n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            gamePanel.add(fields[i][j]);
        }
    }

    add(livesField);
    add(timeField);
    add(gamePanel);

    setResizable(false);
    }

    public static void setLivesField(int lives) {
        livesField.setText("Lives: " + lives);
    }

    public static void setTimeField(int time) {
        timeField.setText("Time: " + time);
    }

    @Override
    public void run() {
        setVisible(true);
    }




}
