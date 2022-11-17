package Minesweeper.Game.Fields;

public class Cross extends Empty {
    public Cross() {
        super();
        setSize(15,15);
        setVisible(true);
    }


    public Object getThis() {
        return this;
    }
}
