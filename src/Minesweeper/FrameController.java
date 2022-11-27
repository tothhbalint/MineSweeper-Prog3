package Minesweeper;

import Minesweeper.Interface.*;

/**
 * This is the frame controller class for the game.
 */
public class FrameController{
    /**
     * different frames related to the game
     */
    public static LeaderBoardFrame leaderBoardFrame;
    public static MineSweeperMenuFrame menuFrame;
    public static Thread menuThread;

    /**
     * This is the leaderboards thread
     */
    public static Thread leaderBoardThread;

    /**
     * This stores the leaderBoard
     */
    static LeaderBoard leaderBoard;

    /**
     * This is returns the leaderBoard
     */
    public static LeaderBoard getLeaderBoard(){
        return leaderBoard;
    }

    /**
     * This is the constructor for the frame controller class
     */
    public FrameController(){
        leaderBoard = new LeaderBoard();
        leaderBoardFrame = new LeaderBoardFrame();
        leaderBoardThread = new Thread(leaderBoardFrame);
        menuFrame = new MineSweeperMenuFrame();

        menuThread = new Thread(menuFrame);
        menuThread.start();
    }
}
