package Minesweeper.Game;

import Minesweeper.Game.Fields.*;
import Minesweeper.Interface.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


/**
 * This is the main class for the game logic managing the game related frames and functions/variables.
 */
public class GameControl {
    /**
     * This variable stores the probability of a field being a bomb.
     */
    static int mineChance;
    /**
     * This variable stores the probability of a field being a heal one.
     */
    static int healChance;
    /**
     * This variable stores the probability of a field being a cross one.
     */
    static int crossChance;

    /**
     * This variable stores the lives left of the player.
     */
    static int lives;

    /**
     * This variable stores the main game frame.
     */
    static GameMainFrame gameMainFrame;

    /**
     * This variable stores the fields.
     */
    public static Empty fields[][];

    /**
     * This variable stores the reached score.
     */
    static int score=1000;

    /**
     * This variable stores the time elapsed since launch.
     */
    static int time;

    /**
     * This variable manages the times thread.
     */
    static Thread timerThread;

    /**
     * This variable stores the games state (Over/Still going).
     */
    static boolean gameOver;

    /**
     * This variable stores the stringified version of the difficulty of the game.
     */
    static String difficulty;

    /**
     * This function sets the values related to difficulty.
     * @param difficulty The difficulty of the game.
     */
    public void setDifficultyValues(int difficulty){
        //set chances based on difficulty
        switch (difficulty) {
            case 0 -> {
                this.difficulty = "Easy";
                mineChance = 10;
                healChance = 7;
                crossChance = 3;
            }
            case 1 -> {
                this.difficulty = "Medium";
                mineChance = 15;
                healChance = 5;
                crossChance = 2;
            }
            default -> {
                this.difficulty = "Hard";
                mineChance = 25;
                healChance = 2;
                crossChance = 1;
            }
        }
    }

    /**
     * This Constructor for the game that starts the game, sets up the board and other important handles.
     * @param size size of the board.
    * @param difficulty difficulty of the game.
     */
    public GameControl(int size, int difficulty){
        gameOver = false;
        time = 0;
        lives = 3;
        timerThread = new Thread(new Timer());
        timerThread.start();
        fields = new Empty[size][size];
        setDifficultyValues(difficulty);

        Random randI = new Random();


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int rand = randI.nextInt(100);
                if(rand < crossChance){
                    fields[i][j] = new Cross();
                } else if (rand < (crossChance + healChance)){
                    fields[i][j] = new Heal();
                } else if (rand < (crossChance+ healChance + mineChance)){
                    fields[i][j] = new Bomb();
                } else {
                    fields[i][j] = new Empty();
                }
                fields[i][j].setPreferredSize(new Dimension(50,50));
                fields[i][j].location = new int[]{i,j};
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j].bombsAround = getBombsAround(i,j);
                if(fields[i][j].bombsAround > 0){
                    fields[i][j].setDisabledIcon(new ImageIcon("src/Minesweeper/Game/Fields/Icons/Minesweeper_" + fields[i][j].bombsAround + ".png"));
                }else if(fields[i][j].bombsAround == -1){
                    fields[i][j].setDisabledIcon(new ImageIcon("src/Minesweeper/Game/Fields/bomb.jpg"));
                }

            }
        }

        gameMainFrame = new GameMainFrame(size, fields);
        gameMainFrame.setVisible(true);
    }

    /**
     * This function returns the number of bombs around a field.
     * @param i x coordinate of the field.
     * @param j y coordinate of the field.
     * @return number of bombs around the field.
     */
    public int getBombsAround(int i, int j){
        int bombsAround = 0;
        if(fields[i][j] instanceof Bomb){
            return -1;
        }else{
            if(i > 0 && j > 0 && fields[i-1][j-1] instanceof Bomb){
                bombsAround++;
            }
            if(i > 0 && fields[i-1][j] instanceof Bomb){
                bombsAround++;
            }
            if(i > 0 && j < fields.length-1 && fields[i-1][j+1] instanceof Bomb){
                bombsAround++;
            }
            if(j > 0 && fields[i][j-1] instanceof Bomb){
                bombsAround++;
            }
            if(j < fields.length-1 && fields[i][j+1] instanceof Bomb){
                bombsAround++;
            }
            if(i < fields.length-1 && j > 0 && fields[i+1][j-1] instanceof Bomb){
                bombsAround++;
            }
            if(i < fields.length-1 && fields[i+1][j] instanceof Bomb){
                bombsAround++;
            }
            if(i < fields.length-1 && j < fields.length-1 && fields[i+1][j+1] instanceof Bomb){
                bombsAround++;
            }
        }
        return bombsAround;
    }

    /**
     * This function returns the handle for the main frame.
     * @return handle for the main frame.
     */
    public static GameMainFrame getGameMainFrame(){
        return gameMainFrame;
    }

    /**
     * This function manages what happens when a bomb is revealed.
     */
    public static void hit(){
        lives = lives-1;
        score = score -100;
        if(lives <= 0){
            timerThread.interrupt();
            gameMainFrame.setEnabled(false);
            Thread gameOver = new Thread(new GameOverFrame(score,difficulty));
            gameOver.start();
            return;
        }
        gameMainFrame.setLivesField(lives);
    }

    /**
     * This function manages what happens when a heal is revealed.
     */
    public static void heal(){
        lives = lives+1;
        gameMainFrame.setLivesField(lives);
    }

    /**
     * This function stops the timer.
     */
    public static void stopTimer(){
        timerThread.interrupt();
    }

    /**
     * This function checks if the game is won.
     */
    public static void checkWin(){
        for (Empty[] e : fields) {
            for (Empty field : e) {
                if(field.isEnabled()){
                    if(field.bombsAround==-1 && field.getFlagged()){
                        continue;
                    }
                    return;
                }
            }
        }
        gameOver=true;
    }

    /**
     * This is a class to manage time.
     */
    static class Timer implements Runnable{

        /**
         * This variable stores the state of the timer.
         */
        boolean running = true;

        /**
         * This function runs the timer.
         */
        @Override
        public void run() {
            while (running){
                if(gameOver){
                    timerThread.interrupt();
                    score = score + 1500;
                    Thread gameOver = new Thread(new GameOverFrame(score,difficulty));
                    gameOver.start();
                    gameMainFrame.setEnabled(false);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
                time++;
                score = score-10;
                gameMainFrame.setTimeField(time);
            }
        }
    }
}
