package Minesweeper.Game.Fields;

import javax.swing.*;

public class Cross extends Empty {
    public Cross() {
        super();
        setSize(15,15);
        setVisible(true);
        setText("X");
    }


    public Object getThis() {
        return this;
    }

    public void revealField(Empty field){
        popDown(field);
        popUp(field);
        popLeft(field);
        popRight(field);
    };


}
