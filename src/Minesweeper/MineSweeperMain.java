package Minesweeper;

public class MineSweeperMain {
    public static Thread menuThread;
    public static MineSweeperMenuFrame menuFrame;
    public static void main(String[] args) {
        menuFrame = new MineSweeperMenuFrame();
        menuThread = new Thread(menuFrame);
        menuThread.start();
    }
}
