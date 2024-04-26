package Übung7.Snake;

import java.awt.event.*;

public class Controls implements KeyListener { // Keyboard controls for the game
    private final Game game;
    private Direction next = Direction.RIGHT;
    /*
     * To implement the controls of our game we use an interface found in java.awt to easily capture and evaluate key presses.
     * We then map certain functionality to keys.
     */

    public Controls(Game game){
        this.game = game;
    }

    public Direction getNextDirection(){ return next;}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            // Handle special keys
            case KeyEvent.VK_Q, KeyEvent.VK_ESCAPE  -> game.exitGame();
            // map Move Inputs to Direction
            case KeyEvent.VK_W, KeyEvent.VK_UP      -> next = Direction.UP;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT    -> next = Direction.LEFT;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN    -> next = Direction.DOWN;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT   -> next = Direction.RIGHT;
        };
    }

    // These methods are called when other actions are performed by the user, but we don't need to implement anything here.
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}

