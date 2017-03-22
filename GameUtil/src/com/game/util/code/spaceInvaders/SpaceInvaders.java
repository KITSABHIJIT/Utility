package com.game.util.code.spaceInvaders;

import javax.swing.JFrame;
/**
 * 
 * http://zetcode.com/tutorials/javagamestutorial/spaceinvaders/
 *
 */
public class SpaceInvaders extends JFrame implements Commons {

    public SpaceInvaders()
    {
        add(new Board());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new SpaceInvaders();
    }
}
