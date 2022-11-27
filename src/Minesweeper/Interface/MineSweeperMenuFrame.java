package Minesweeper.Interface;

import Minesweeper.Interface.LeaderBoardFrame.*;

import static Minesweeper.FrameController.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Class for the Menu Frame
 */
public class MineSweeperMenuFrame extends JFrame implements Runnable {

    /**
     * Constructor for the Menu Frame
     */
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

    /**
     * Method to run the Menu Frame
     */
    @Override
        public void run() {
            this.setVisible(true);
        }

    /**
     * Method to disable the Menu Frame
     */
    public void disableFrame() {
            this.setVisible(false);
        }

    /**
     * Method to enable the Menu Frame
    */
    public void enableFrame() {
        this.setVisible(true);
    }

    /**
     * ActionListener for the Menu Frame
     */
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
            leaderBoardFrame.enableFrame();
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
