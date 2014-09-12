/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
	    createPyramid();
	}
	
	/**
	 * Creates a pyramid centered in the middle of the canvas.
	 */
    private void createPyramid(){
        double x = (getWidth() - (BRICK_WIDTH*BRICKS_IN_BASE))/2;
        double y = getHeight()-BRICK_HEIGHT;
        for (int row = 0; row < BRICKS_IN_BASE; row++) {
            createRow(x, y, BRICKS_IN_BASE - row);
            y-=BRICK_HEIGHT;
            x+=BRICK_WIDTH/2;
        }
    }

    /**
     * Creates a row of bricks.
     * @param x x-position of canvas in pixels
     * @param y y-position of canvas in pixels
     * @param numBricks number of bricks in row
     */
    private void createRow(double x, double y, int numBricks){
        for (int i = 0; i < numBricks; i++) {
            createBrick(x, y);
            x+=BRICK_WIDTH;
        }
    }
	
    /**
     * Creates a brick whose top-left position is at (x,y) on the canvas.
     * @param x x-position of canvas in pixels
     * @param y y-position of canvas in pixels
     */
	private void createBrick(double x, double y){
	    GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
	    add(brick);	    
	}
		
}

