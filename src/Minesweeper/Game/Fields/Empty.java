package Minesweeper.Game.Fields;

import javax.swing.*;
import java.awt.event.MouseListener;

import static Minesweeper.Game.GameMainFrame.*;

public class Empty extends JButton {

    public int bombsAround;
    public int location[];

    boolean flagged = false;

    public Empty() {
        super();
        setSize(15, 15);
        setVisible(true);
        addActionListener(actionListener);
        addMouseListener(mouseListener);
        setIcon(new ImageIcon("src/Minesweeper/Game/Fields/empty.png"));
    }

    public Object getThis() {
        return this;
    }

    public void popFieldsAround(Empty field){
        if(field instanceof Cross){
            Crosspop(field);
            return;
        }
        if(field.bombsAround == 0 && field.isEnabled() && bombsAround != -1){
            field.setEnabled(false);
            if(field.location[0] != 0 && field.location[1] != 0){
                popFieldsAround(fields[field.location[0]-1][field.location[1]]);
                popFieldsAround(fields[field.location[0]-1][field.location[1]-1]);
            }
            if(field.location[0] != fields[0].length-1 && field.location[1] != fields[0].length-1){
                popFieldsAround(fields[field.location[0]+1][field.location[1]]);
                popFieldsAround(fields[field.location[0]+1][field.location[1]+1]);
            }
            if(field.location[1] != 0 && field.location[0] != fields[0].length-1){
                popFieldsAround(fields[field.location[0]][field.location[1]-1]);
                popFieldsAround(fields[field.location[0]+1][field.location[1]-1]);
            }
            if(field.location[1] != fields[1].length-1 && field.location[0] != 0){
                popFieldsAround(fields[field.location[0]][field.location[1]+1]);
                popFieldsAround(fields[field.location[0]-1][field.location[1]+1]);
            }
        }else {
            field.setEnabled(false);
        }
    }

    MouseListener mouseListener = new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3 && isEnabled()) {
                if (flagged) {
                    setIcon(new ImageIcon("src/Minesweeper/Game/Fields/empty.png"));
                    flagged = false;
                } else if (!flagged) {
                    setIcon(new ImageIcon("src/Minesweeper/Game/Fields/flag.png"));
                    flagged = true;
                }
            }else if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1 && isEnabled()){
                if(getThis() instanceof Heal){
                    livesField.setText("Lives: " + ++lives);
                    livesField.paintImmediately(livesField.getVisibleRect());
                }
                else if(getThis() instanceof Bomb){
                    livesField.setText("Lives: " + --lives);
                    livesField.paintImmediately(livesField.getVisibleRect());
                    setEnabled(false);
                }else{
                    popFieldsAround((Empty) getThis());
                }
            }
            }
    };

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

    public void Crosspop(Empty field){
        popDown(field);
        popUp(field);
        popLeft(field);
        popRight(field);
    };
}

