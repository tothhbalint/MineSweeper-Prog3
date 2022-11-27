package Minesweeper.Game.Fields;

import javax.swing.*;

import static Minesweeper.Game.GameControl.checkWin;
import static Minesweeper.Game.GameControl.hit;


/**
 * Class for the bombs
 */
public class Bomb extends Empty {

    /**
     * stores the numbor of bombs around, here its -1, so its easy to check if its a bomb
     */
    int bombsAround = -1;

    /**
     * Constructor for the bomb
     */
    public Bomb() {
        super();
        setDisabledIcon(new ImageIcon("src/Minesweeper/Game/Fields/bomb.jpg"));
        setSize(15,15);
        setVisible(true);
    }


    /**
     * returns the object
     */
    public Object getThis() {
        return this;
    }

    /**
     * reveals the field
     * @param field
     */
    public void revealField(Empty field){
        hit();
        super.revealField(field);
        checkWin();
    }
}

