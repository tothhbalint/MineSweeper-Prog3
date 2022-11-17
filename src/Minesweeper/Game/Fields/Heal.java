package Minesweeper.Game.Fields;

import javax.swing.*;

public class Heal extends Empty {
    public Heal() {
        super();
        setIcon(new ImageIcon("src/Minesweeper/Game/Fields/heal.png"));
        setSize(15,15);
        setVisible(true);
    }
}

