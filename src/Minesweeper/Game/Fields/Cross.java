package Minesweeper.Game.Fields;

import javax.swing.*;

import static Minesweeper.Game.GameControl.checkWin;

/**
 * Class for the cross fields
 */
public class Cross extends Empty {

    /**
     * Constructor for the cross fields
     */
    public Cross() {
        super();
        setSize(15,15);
        setVisible(true);
        setText("X");
    }


    /**
     * returns the object
     */
    public Object getThis() {
        return this;
    }

    /**
     * reveals the field and starts revealing others in a cross pattern
     * @param field
     */
    public void revealField(Empty field){
        popDown(field);
        popUp(field);
        popLeft(field);
        popRight(field);
        checkWin();
    };


}
