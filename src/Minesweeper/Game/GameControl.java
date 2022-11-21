package Minesweeper.Game;

import Minesweeper.Game.Fields.*;
import Minesweeper.Interface.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameControl {
    static int mineChance;
    static int healChance;
    static int crossChance;

    static int lives;

    static GameMainFrame gameMainFrame;

    public static Empty fields[][];

    static int score=1000;

    static int time;

    static Thread timerThread;


    public void setDifficultyValues(int difficulty){
        //set chances based on difficulty
        switch (difficulty) {
            case 0 -> {
                mineChance = 10;
                healChance = 7;
                crossChance = 3;
            }
            case 1 -> {
                mineChance = 15;
                healChance = 5;
                crossChance = 2;
            }
            default -> {
                mineChance = 25;
                healChance = 2;
                crossChance = 1;
            }
        }
    }

    public GameControl(int size, int difficulty){
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

    public static GameMainFrame getGameMainFrame(){
        return gameMainFrame;
    }

    public static void hit(){
        lives = lives-1;
        score = score -100;
        if(lives <= 0){
            timerThread.interrupt();
            Thread gameOver = new Thread(new GameOverFrame(score));
            gameOver.start();
            return;
        }
        gameMainFrame.setLivesField(lives);
    }

    public static void heal(){
        lives = lives+1;
        gameMainFrame.setLivesField(lives);
    }

    public static void stopTimer(){
        timerThread.interrupt();
    }

    static class Timer implements Runnable{
        boolean running = true;
        @Override
        public void run() {
            while (running){
                try {
                    Thread.sleep(1000);
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
