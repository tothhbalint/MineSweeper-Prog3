package Minesweeper.Interface;

import Minesweeper.LeaderBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static Minesweeper.LeaderBoard.addEntry;
import static Minesweeper.MineSweeperMain.menuFrame;


public class GameOverFrame extends JFrame implements Runnable{
    public GameOverFrame(int score){
        super("Game Over");
        setSize(200, 100);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                menuFrame.enableFrame();
            }
        });
        setLayout(new FlowLayout());
        JLabel gameOver = new JLabel("You lost!\n Your score: " + score);
        JButton leaderBoardButton = new JButton("Add to leaderBoard");
        leaderBoardButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    String name = JOptionPane.showInputDialog("Enter your name");
                                                    addEntry(new LeaderBoard.LeaderBoardEntry(name, score));
                                                }
                                            });
        add(gameOver);
        add(leaderBoardButton);
    }

    public void run(){
        this.setVisible(true);
    }
}
