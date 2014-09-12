/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
    public void run(){
        checkStart();
        while (frontIsClear()) {
            everyOtherBeeper();
            turnAndFace();
        }
    }
    
    private void checkStart(){
        while (frontIsBlocked()) {
            turnLeft();
        }
    }
    
    private void everyOtherBeeper(){
        while (frontIsClear()) {
            putBeeper();
            move();
            if (frontIsClear()) {
                move();
            }
        }
    }
    
    private void turnAndFace(){
        if (facingEast() && leftIsClear()) {
            turnLeft();
            move();
            turnLeft();
        } else if (facingWest() && rightIsClear()) {
            turnRight();
            move();
            turnRight();
        }
    }

}
