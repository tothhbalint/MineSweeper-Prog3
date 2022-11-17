package Minesweeper.Game.Fields;

import javax.swing.*;

public class Bomb extends Empty {
    public Bomb() {
        super();
        setDisabledIcon(new ImageIcon("src/Minesweeper/Game/Fields/bomb.jpg"));

        setSize(15,15);
        setVisible(true);
    }


    public Object getThis() {
        return this;
    }
}

