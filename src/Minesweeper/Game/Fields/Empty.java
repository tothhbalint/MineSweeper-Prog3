package Minesweeper.Game.Fields;

import javax.swing.*;
import java.awt.event.MouseListener;

import static Minesweeper.Game.GameMainFrame.lives;
import static Minesweeper.Game.GameMainFrame.livesField;

public class Empty extends JButton {

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
                setEnabled(false);
            }
            }
    };
}

