package Minesweeper.Game;

import Minesweeper.Game.Fields.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Random;

import static Minesweeper.MineSweeperMain.menuFrame;

public class GameMainFrame extends JFrame implements Runnable{
    public static int lives=3;
    public static JLabel livesField;
    static public Empty[][] fields;
public GameMainFrame(int n, int difficulty) {
    super("Minesweeper");
    setSize(n*50 + 50 , n*50 + 100);
    addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            menuFrame.enableFrame();
        }
    });

    int mineChance;
    int healChance;
    int crossChance;


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

    fields = new Empty[n][n];
    setLayout(new FlowLayout());
    livesField = new JLabel("Lives: " + lives);
    JPanel gamePanel = new JPanel();


    Random randI = new Random();

    gamePanel.setSize(n*50+50, n*50+50);
    gamePanel.setLayout(new GridLayout(n,n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
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
                gamePanel.add(fields[i][j]);
            }
        }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            fields[i][j].bombsAround = getBombsAround(i,j);
            if(fields[i][j].bombsAround > 0){
                fields[i][j].setDisabledIcon(new ImageIcon("src/Minesweeper/Game/Fields/Icons/Minesweeper_" + fields[i][j].bombsAround + ".png"));
            }else if(fields[i][j].bombsAround == -1){
                fields[i][j].setDisabledIcon(new ImageIcon("src/Minesweeper/Game/Fields/bomb.jpg"));
            }

        }
    }

    add(livesField);
    add(gamePanel);

    setResizable(false);

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



    @Override
    public void run() {
        setVisible(true);
    }




}
