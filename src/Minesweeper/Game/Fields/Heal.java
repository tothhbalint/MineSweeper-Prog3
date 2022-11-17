package Minesweeper.Game.Fields;

public class Heal extends Empty {
    public Heal() {
        super();
        addActionListener(actionListener);
    }


    public Object getThis() {
        return this;
    }

}

