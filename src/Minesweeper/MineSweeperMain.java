package Minesweeper;

import Minesweeper.Interface.MineSweeperMenuFrame;

/**
 * This is the main class for the game.
 */
public class MineSweeperMain {
    public static Thread menuThread;
    public static MineSweeperMenuFrame menuFrame;
    public static void main(String[] args) {
        FrameController mainControl = new FrameController();
    }
}
