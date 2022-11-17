package Minesweeper.Game;

import Minesweeper.Game.Fields.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Random;

import static Minesweeper.MineSweeperMain.menuFrame;

public class GameMainFrame extends JFrame implements Runnable{

    static Empty[][] fields;
public GameMainFrame(int n, int difficulty) {
    super("Minesweeper");
    setSize(n*50 + 50 , n*50 + 80);
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
    switch (difficulty){
        case 0:
            mineChance = 20;
            healChance = 10;
            crossChance = 5;
            break;
        case 1:
            mineChance = 25;
            healChance = 7;
            crossChance = 2;
            break;
        default:
            mineChance = 30;
            healChance = 5;
            crossChance = 1;
            break;
    }

    fields = new Empty[n][n];
    setLayout(new FlowLayout());
    JTextField lives = new JTextField("Lives: 3");
    JPanel gamePanel = new JPanel();

    lives.setEditable(false);

    Random randI = new Random();

    gamePanel.setSize(n*50, n*50);
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

    add(lives);
    add(gamePanel);

    setLocationRelativeTo(null);
    setResizable(true);

    }

    @Override
    public void run() {
        setVisible(true);
    }


}
