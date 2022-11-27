package Minesweeper.Interface;

import Minesweeper.LeaderBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static Minesweeper.FrameController.*;
import static Minesweeper.Game.GameControl.*;
import static Minesweeper.LeaderBoard.writeLeaderBoardToFile;


/**
 * Class for the Game Over Frame
 */
public class GameOverFrame extends JFrame implements Runnable{

    /**
     * Constructor for the Game Over Frame
     */
    public GameOverFrame(int score, String difficulty){
        super("Game Over");
        setSize(200, 150);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                menuFrame.enableFrame();
                getGameMainFrame().dispose();
                writeLeaderBoardToFile();
                dispose();
            }
        });
        setLayout(new FlowLayout());
        JLabel gameOver = new JLabel("Game Over!\n Your score: " + score);
        JButton leaderBoardButton = new JButton("Add to leaderBoard");
        JButton backToMenu = new JButton("Exit to menu");
        leaderBoardButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    String name = JOptionPane.showInputDialog("Enter your name");
                                                    getLeaderBoard().addEntry(new LeaderBoard.LeaderBoardEntry(name, score, difficulty));
                                                }
                                            });
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGameMainFrame().dispose();
                menuFrame.enableFrame();
                dispose();
            }
        });
        add(gameOver);
        add(leaderBoardButton);
        add(backToMenu);
    }

    /**
     * Method to run the frame as thread
     */
    public void run(){
        this.setVisible(true);
    }
}
