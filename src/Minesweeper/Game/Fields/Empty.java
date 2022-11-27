package Minesweeper.Game.Fields;

import javax.swing.*;
import java.awt.event.MouseListener;

import static Minesweeper.Game.GameControl.*;

/**
 * Class for the empty fields
 */
public class Empty extends JButton {

    /**
     * stores the numbor of bombs around
     */
    public int bombsAround;

    /**
     * stores the state of the field (revealed or not)
     */
    public int location[];

    /**
     * stores if the field is flagged
     */
    boolean flagged = false;

    /**
     * Constructor for the empty fields
     */
    public Empty() {
        super();
        setSize(15, 15);
        setVisible(true);
        addMouseListener(mouseListener);
        setIcon(new ImageIcon("src/Minesweeper/Game/Fields/empty.png"));
    }

    /**
     * returns the object
     */
    public Object getThis() {
        return this;
    }

    /**
     * reveals the field and the others around it if there are no bombs around
     * @param field
     */
    public void revealField(Empty field){
        if(field.bombsAround == 0 && field.isEnabled() && bombsAround != -1){
            field.setEnabled(false);
            if(field.location[0] != 0 && field.location[1] != 0){
                revealField(fields[field.location[0]-1][field.location[1]]);
                revealField(fields[field.location[0]-1][field.location[1]-1]);
            }
            if(field.location[0] != fields[0].length-1 && field.location[1] != fields[0].length-1){
                revealField(fields[field.location[0]+1][field.location[1]]);
                revealField(fields[field.location[0]+1][field.location[1]+1]);
            }
            if(field.location[1] != 0 && field.location[0] != fields[0].length-1){
                revealField(fields[field.location[0]][field.location[1]-1]);
                revealField(fields[field.location[0]+1][field.location[1]-1]);
            }
            if(field.location[1] != fields[1].length-1 && field.location[0] != 0){
                revealField(fields[field.location[0]][field.location[1]+1]);
                revealField(fields[field.location[0]-1][field.location[1]+1]);
            }
        }else{
            field.setEnabled(false);
        }
        checkWin();
    }


    /**
     * MouseListener for the fields
     */
    MouseListener mouseListener = new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) throws StackOverflowError{
            if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3 && isEnabled()) {
                if (flagged) {
                    setIcon(new ImageIcon("src/Minesweeper/Game/Fields/empty.png"));
                    flagged = false;
                } else{
                    setIcon(new ImageIcon("src/Minesweeper/Game/Fields/flag.png"));
                    flagged = true;
                }
            }else if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1 && isEnabled() && !flagged){
                revealField((Empty) getThis());
            }
        }
    };




    /**
     * reveals the field and starts revealing others to the left for the cross pattern
     * @param field
     */
    public void popLeft(Empty field){
        if(field.bombsAround ==-1 ){
            field.setIcon(new ImageIcon("src/Minesweeper/Game/Fields/flag.png"));
            field.flagged = true;
        }else
            field.setEnabled(false);
        if(field.location[1] == 0){
            return;
        }
        popLeft(fields[field.location[0]][field.location[1]-1]);

    }

    /**
     * reveals the field and starts revealing others to the right for the cross pattern
     * @param field
     */
    public void popRight(Empty field){
        if(field.bombsAround ==-1 ){
            field.setIcon(new ImageIcon("src/Minesweeper/Game/Fields/flag.png"));
            field.flagged = true;
        }else
            field.setEnabled(false);
        if(field.location[1] == fields[0].length-1){
            return;
        }
        popRight(fields[field.location[0]][field.location[1]+1]);
    }

    /**
     * reveals the field and starts revealing others to the top for the cross pattern
     * @param field
     */
    public void popUp(Empty field){
        if(field.bombsAround ==-1 ){
            field.setIcon(new ImageIcon("src/Minesweeper/Game/Fields/flag.png"));
            field.flagged = true;
        }else
            field.setEnabled(false);
        if(field.location[0] == 0){
            return;
        }
        popUp(fields[field.location[0]-1][field.location[1]]);
    }

    /**
     * reveals the field and starts revealing others to the bottom for the cross pattern
     * @param field
     */
    public void popDown(Empty field){
        if(field.bombsAround ==-1 ){
            field.setIcon(new ImageIcon("src/Minesweeper/Game/Fields/flag.png"));
            field.flagged = true;
        }else
            field.setEnabled(false);
        if(field.location[0] == fields[0].length-1){
            return;
        }
        popDown(fields[field.location[0]+1][field.location[1]]);
    }


    /**
     * returns if a field is flagged
     * @return flagged the flag state of a field
     */
    public boolean getFlagged(){
        return flagged;
    }

}



