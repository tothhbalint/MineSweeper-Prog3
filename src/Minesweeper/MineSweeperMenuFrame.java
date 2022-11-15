package Minesweeper;

import javax.swing.*;
import java.awt.*;

public class MineSweeperMenuFrame extends JFrame {
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
            //add an image to the label
            label.setIcon(new ImageIcon("src/Minesweeper/Minesweeper.png"));
            label.setSize(250,250);
            panel.add(label);
            buttons.add(new JLabel());
            JButton button = new JButton("Start Game");
            buttons.add(button, BorderLayout.NORTH);
            //add button to show highscore
            buttons.add(new JLabel());
            JButton button2 = new JButton("Highscore");
            buttons.add(button2);
            //add button to show about
            buttons.add(new JLabel());
            JButton button3 = new JButton("About");
            buttons.add(button3, BorderLayout.SOUTH);

            panel.add(buttons);


            add(panel);
        }
}
