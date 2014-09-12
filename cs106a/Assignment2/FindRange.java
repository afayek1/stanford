/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
    /*
     * Reads integer input from user and returns the smallest and largest integer.
     */
	
    /* Class constant. */
    private static final int SENTINEL = 0; 
    
    public void run() {
        welcomeMessage();
        getValues();
        printResults();
	}
    
    /* Prints a welcome message to the screen. */
    private void welcomeMessage() {
        println("This program finds the largest and smallest numbers. Press 0 to end the program.");
    }
    
    /* Gets values from user. */
    private void getValues() {
        int num = smallest = biggest = readInt("? ");
        while (num!=SENTINEL) {
            num = readInt("? ");
            if (num==SENTINEL) {
                break;
            } else if (num<smallest) {
                smallest = num;
            } else if (num>biggest) {
                biggest = num;
            } 
        }
    }
    
    /* Prints results.*/
    private void printResults() {
        if (smallest == SENTINEL && biggest == SENTINEL) {
            println("You exited before inputting any values!");
        } else {
            println("smallest: " + smallest);
            println("biggest: " + biggest);
        }
    }
    
    private static int smallest;
    private static int biggest;
     
}

