package Minesweeper.Interface;

import Minesweeper.Game.GameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static Minesweeper.FrameController.*;

/**
 * Class for the Game Setup Frame
 */
public class GameSetup extends JFrame implements Runnable {

    /**
     * Slider to select the size
     */
    static JSlider slider;

    /**
     * Slider to select the difficulty
     */
    static JComboBox comboBox;

    /**
     * This array contains the difficulties
     */
    String[] difficulties = {"Easy", "Medium", "Hard"};

    /**
     * Constructor for the Game Setup Frame
     */
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

    /**
     * ActionListener for the button
     */
    ActionListener actionListener = e -> {
        if (e.getActionCommand().equals("Start Game")) {
            try {
                GameControl gameControl = new GameControl(slider.getValue(), comboBox.getSelectedIndex());
                menuFrame.disableFrame();
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    };

    /**
     * Method to run the frame
     */
    @Override
    public void run() {
        GameSetup gameSetup = new GameSetup();
        gameSetup.setVisible(true);
    }
}

