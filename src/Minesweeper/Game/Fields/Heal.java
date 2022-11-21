package Minesweeper.Game.Fields;

import static Minesweeper.Game.GameControl.heal;

public class Heal extends Empty {
    public Heal() {
        super();
        addActionListener(actionListener);
    }


    public Object getThis() {
        return this;
    }
    public void revealField(Empty field){
        heal();
        super.revealField(field);
    }

}

