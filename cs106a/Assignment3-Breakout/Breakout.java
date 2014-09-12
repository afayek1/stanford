/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Starting position of the paddle */
    private static final int PADDLE_Y_START = HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 2;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 2;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	private static final int BALL_CIRCUM = BALL_RADIUS*2;

/**Starting x-position and y-position of the ball */
	private static final int BALL_START_X = (WIDTH-BALL_RADIUS*2)/2;
	private static final int BALL_START_Y = (HEIGHT-BALL_RADIUS*2)/2;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

	
    public void init(){
        addMouseListeners();
    }
    
/** Runs the Breakout program. */
	public void run() {
	    setUp();
	    play();
	    endGame();
	}
	
/** Sets up the game board. */
	private void setUp(){
	    createLevel();
	    createPaddle();
	    createBall();	    
	}


/** Creates the layout of the bricks. */
    private void createLevel(){        
        Color color = null;
        for (int row = 0; row<NBRICK_ROWS; row++) {            
            for (int column = 0; column < NBRICKS_PER_ROW; column++) {
                int x = column*BRICK_WIDTH + column*BRICK_SEP;
                int y = BRICK_Y_OFFSET + (BRICK_HEIGHT*row) + (BRICK_SEP*row);
                switch (row){
                case 0: case 1: color = Color.red;
                break;
                case 2: case 3: color = Color.orange;
                break;
                case 4: case 5: color = Color.yellow;
                break;
                case 6: case 7: color = Color.green;
                break;
                case 8: case 9: color = Color.cyan;
                break;
                }
                createBrick(x,y,color);                
            }
        }
    }

/** Creates a single brick. */
    private void createBrick(double x, double y, Color color){
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        add(brick);
        brick.setFilled(true);
        brick.setColor(color);
    }

/** Creates the paddle. */
    private void createPaddle(){
        double x = (WIDTH-PADDLE_WIDTH)/2;
        paddle = new GRect(x, PADDLE_Y_START, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

/** Mouse controls paddle in x-directions. */
    public void mouseMoved(MouseEvent e){
        if (e.getX()>=0 && e.getX()+PADDLE_WIDTH<=WIDTH) {
            paddle.setLocation(e.getX(), HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
    }

/** Creates the ball. */
    private void createBall(){
        ball = new GOval(BALL_START_X, BALL_START_Y, BALL_CIRCUM, BALL_CIRCUM);
        ball.setFilled(true);
        add(ball);
    }
    
    /** Sets up the game play. */
    private void play(){
        waitForClick();
        ballVelocity();
        while(true){
            ballMovement();
            //If ball hits bottom wall ends turn.
            if (ball.getY()+BALL_CIRCUM>=HEIGHT) {
                turns--;
                if (turns == 0) {
                    break;
                }
                else {
                    remove(ball);
                    createBall();
                    waitForClick();
                }
            }
            if (bricksRemaining == 0) break;
        }       
    }
    
    

/** Sets the velocity of the ball */
    private void ballVelocity(){     
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx;
        vy = velocity_y;
    }
    
/** Defines what a ball does when it collides with boundaries or other objects. */
    private void ballMovement(){
        //Moves the ball in a given direction.
        ball.move(vx,vy);
        
        //Slows the movement of the ball by pausing 10 milliseconds between movements.
        pause(10);
        
        //Reverses direction of ball when it hits the side walls.
        if (ball.getX()<=0 || ball.getX()+BALL_RADIUS*2>=WIDTH) vx = -vx;
            
        //Reverses direction of ball when it hits the top wall.
        if (ball.getY()<=0) vy = -vy;
              
        GObject collider = getCollidingObject();        
        
        //If the ball hits the paddle it reverses directions. Otherwise
        //the only other object it can hit is a brick. When it does,
        //it removes the brick then reverses the direction of the ball.
        if (collider == paddle) {
            vy = -vy;
        } else if (collider != null) {
            remove(collider);
            bricksRemaining--;
            vy = -vy;
        }

    }

/** Checks each corner of the ball and returns the object it collides with if it does. */
    private GObject getCollidingObject(){
        //Top left corner.
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        }
        //Top right corner.
        else if (getElementAt(ball.getX()+BALL_CIRCUM, ball.getY()) != null) {
            return getElementAt(ball.getX()+BALL_CIRCUM, ball.getY());
        }
        //Bottom left corner.
        else if (getElementAt(ball.getX(), ball.getY()+BALL_CIRCUM) != null) {
            return getElementAt(ball.getX(), ball.getY()+BALL_CIRCUM);
        }
        //Bottom right corner.
        else if (getElementAt(ball.getX()+BALL_CIRCUM, ball.getY()) != null) {
            return getElementAt(ball.getX()+BALL_CIRCUM, ball.getY());
        }
        else {
            return null;
        }
    }
    private void endGame() {
        removeAll();
        if (bricksRemaining == 0) {
            printWinner();
        } else {
            printGameOver();
        }
        
    }
    private void printWinner() {
        GLabel winner = new GLabel("CONGRATULATIONS! You have just completed the level!", WIDTH/2, HEIGHT/2);
        add(winner);
    }
    
    private void printGameOver() {
        GLabel gameOver = new GLabel("GAME OVER.", WIDTH/2, HEIGHT/2);
        add(gameOver);
    }
    
    private GRect paddle;
    private GOval ball;
    private double vx, vy;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private int delay = 10;
    private int bricksRemaining = NBRICK_ROWS*NBRICKS_PER_ROW;
    private int turns = NTURNS;
    private int velocity_y = 4;
}
