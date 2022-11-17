package Minesweeper.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static Minesweeper.MineSweeperMain.menuFrame;

public class GameSetup extends JFrame implements Runnable {
    static JSlider slider;
    static JComboBox comboBox;
    String[] difficulties = {"Easy", "Medium", "Hard"};
    public GameSetup(){
        super("Setup");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 350);

        JTextField size = new JTextField("Pálya mérete:");
        slider = new JSlider(JSlider.HORIZONTAL, 5, 17, 10);
        JTextField difficulty = new JTextField("Nehézség:");
        comboBox = new JComboBox(difficulties);
        JButton button = new JButton("Start Game");

        setLayout(new GridLayout(5,1));

        size.setHorizontalAlignment(JTextField.CENTER);
        difficulty.setHorizontalAlignment(JTextField.CENTER);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);

        button.addActionListener(actionListener);

        add(size);
        add(slider);
        add(difficulty);
        add(comboBox);
        add(button);

        setLocationRelativeTo(null);
        setResizable(false);
    }

    ActionListener actionListener = e -> {
        if (e.getActionCommand().equals("Start Game")) {
            try {
                GameMainFrame gameMainFrame = new GameMainFrame(slider.getValue(), comboBox.getSelectedIndex());
                Thread gameMainFrameThread = new Thread(gameMainFrame);
                gameMainFrameThread.start();
                menuFrame.disableFrame();
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };

    @Override
    public void run() {
        GameSetup gameSetup = new GameSetup();
        gameSetup.setVisible(true);
    }
}

