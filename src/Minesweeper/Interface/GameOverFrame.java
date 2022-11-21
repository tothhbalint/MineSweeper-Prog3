package Minesweeper.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static Minesweeper.MineSweeperMain.menuFrame;

import static Minesweeper.Game.GameControl.*;

public class GameOverFrame extends JFrame implements Runnable{
    public GameOverFrame(){
        super("Game Over");
        setSize(200, 100);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                menuFrame.enableFrame();
                gameMainFrame.dispose();
            }
        });
        setLayout(new FlowLayout());
        JLabel gameOver = new JLabel("VESZTETTÉL!");
        add(gameOver);
    }

    public void run(){
        GameOverFrame gameOverFrame = new GameOverFrame();
        gameOverFrame.setVisible(true);
    }
}
