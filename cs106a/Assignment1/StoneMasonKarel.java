/*
 * File: StoneMasonKarel.java

 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

    public void run(){
        checkColumn();
        while (frontIsClear()) {
            moveFourBlocks();
            checkColumn();
        }
        
    }
    /*
     * Ascends and fixes column, then descends column. Calls fixColumn
     * at each step of ascend to check and fix column if needed.
     * 
     * Precondition: Karel is facing east.
     * Postcondition: Karel returns to starting position facing east
     * with column now fixed.   
     */
    private void checkColumn() {
        turnLeft();
        fixColumn();
        while (frontIsClear()) {
            move();
            fixColumn();
        }
        turnAround();
        while (frontIsClear()) {
            move();
        }
        turnLeft();
    }
    /*
     * Fixes column by checking if beeper is present at Karel's location.
     * If no beeper is present, Karel places beeper at current location.
     * 
     */
    private void fixColumn() {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }
    
    /*
     * Karel travels four blocks as stated in the problem.
     * 
     */
    private void moveFourBlocks() {
        move();
        move();
        move();
        move();
    }
    
}
