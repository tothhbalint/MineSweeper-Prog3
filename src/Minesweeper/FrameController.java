package Minesweeper;

import Minesweeper.Interface.*;

public class FrameController{
    //different frames related to the game
    public static LeaderBoardFrame leaderBoardFrame;
    public static MineSweeperMenuFrame menuFrame;

    static Thread menuThread;
    static LeaderBoard leaderBoard;

    public static LeaderBoard getLeaderBoard(){
        return leaderBoard;
    }

    public FrameController(){
        leaderBoard = new LeaderBoard();
        leaderBoardFrame = new LeaderBoardFrame();
        menuFrame = new MineSweeperMenuFrame();

        menuThread = new Thread(menuFrame);
        menuThread.start();
    }
}
