package Minesweeper.Game.Fields;

import javax.swing.*;

import static Minesweeper.Game.GameControl.hit;

public class Bomb extends Empty {

    int bombsAround = -1;

    public Bomb() {
        super();
        setDisabledIcon(new ImageIcon("src/Minesweeper/Game/Fields/bomb.jpg"));
        setSize(15,15);
        setVisible(true);
    }


    public Object getThis() {
        return this;
    }

    public void revealField(Empty field){
        hit();
        super.revealField(field);
    }
}

