package Minesweeper.Interface;

import Minesweeper.Interface.LeaderBoardFrame.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MineSweeperMenuFrame extends JFrame implements Runnable {
        public MineSweeperMenuFrame() {
            setTitle("Menü");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(300, 500);
            setLocationRelativeTo(null);
            setResizable(false);
            JPanel panel = new JPanel();
            MineSweeperMenuBar menu = new MineSweeperMenuBar();
            this.setJMenuBar(menu);
            panel.setLayout(new FlowLayout());
            JPanel buttons = new JPanel(new GridLayout(6,1));
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/Minesweeper/Interface/Minesweeper.png"));
            label.setSize(250,250);
            panel.add(label);
            buttons.add(new JLabel());
            JButton button = new JButton("Start Game");
            buttons.add(button, BorderLayout.NORTH);
            buttons.add(new JLabel());
            JButton button2 = new JButton("Highscore");
            buttons.add(button2);
            buttons.add(new JLabel());
            JButton button3 = new JButton("About");
            buttons.add(button3, BorderLayout.SOUTH);
            button.addActionListener(actionListener);
            button2.addActionListener(actionListener);
            button3.addActionListener(actionListener);
            panel.add(buttons);
            add(panel);
        }

        @Override
        public void run() {
            this.setVisible(true);
        }

        public void disableFrame() {
            this.setVisible(false);
        }

        public void enableFrame() {
            this.setVisible(true);
        }

        ActionListener actionListener = e -> {
            if (e.getActionCommand().equals("Start Game")) {
                try {
                    GameSetup gameSetup = new GameSetup();
                    Thread gameSetupThread = new Thread(gameSetup);
                    gameSetupThread.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (e.getActionCommand().equals("Highscore")) {
                LeaderBoardFrame highscore = new LeaderBoardFrame();
                Thread thread = new Thread(highscore);
                thread.start();
            }
            if (e.getActionCommand().equals("About")) {
                JFrame about = new JFrame("About");
                about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                about.setSize(300, 300);
                about.add(new JTextField("IDE JONNEK A SZABALLYOKKK"));
                about.setVisible(true);
                this.setVisible(false);
            }
        };
}
