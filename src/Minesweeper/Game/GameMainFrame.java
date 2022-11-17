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
    static Empty[][] fields;
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
            mineChance = 20;
            healChance = 10;
            crossChance = 5;
        }
        case 1 -> {
            mineChance = 25;
            healChance = 7;
            crossChance = 2;
        }
        default -> {
            mineChance = 30;
            healChance = 5;
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
                gamePanel.add(fields[i][j]);
            }
        }

    add(livesField);
    add(gamePanel);

    setResizable(false);

    }

    @Override
    public void run() {
        setVisible(true);
    }


}
