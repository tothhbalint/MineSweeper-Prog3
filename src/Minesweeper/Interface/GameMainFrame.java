package Minesweeper.Interface;

import Minesweeper.Game.Fields.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static Minesweeper.FrameController.*;
import static Minesweeper.Game.GameControl.*;

/**
 * Class for the Game Main Frame
 */
public class GameMainFrame extends JFrame implements Runnable{
    /**
     * field for the lives
     */
    static JLabel livesField;

    /**
     * field for time
     */
    static JLabel timeField;


    /**
     * Constructor for the Game Main Frame
      * @param n number of rows and columns
     * @param fields array of fields
     */
    public GameMainFrame(int n, Empty[][] fields){
        super("Minesweeper");
        setSize(n*50 + 50 , n*50 + 100);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                menuFrame.enableFrame();
                stopTimer();

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

        MineSweeperMenuBar menuBar = new MineSweeperMenuBar();

        this.setJMenuBar(menuBar);

        add(livesField);
        add(timeField);
        add(gamePanel);

        setResizable(false);
        }

    /**
     * Method for setting the lives
     * @param lives
     */
    public static void setLivesField(int lives) {
        livesField.setText("Lives: " + lives);
    }

    /**
     * Method for setting the time
     * @param time
     */
    public static void setTimeField(int time) {
        timeField.setText("Time: " + time);
    }

    /**
     * Method for enabling the frame
     */
    @Override
    public void run() {
        setVisible(true);
    }




}
