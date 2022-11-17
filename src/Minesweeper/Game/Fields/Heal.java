package Minesweeper.Game.Fields;

import Minesweeper.Game.GameMainFrame;

import javax.swing.*;
import java.awt.event.ActionListener;

import static Minesweeper.Game.GameMainFrame.lives;
import static Minesweeper.Game.GameMainFrame.livesField;

public class Heal extends Empty {
    public Heal() {
        super();
        addActionListener(actionListener);
    }


    public Object getThis() {
        return this;
    }

}

