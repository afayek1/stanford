/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
    public void run() {
	    int n = readInt("Enter a number: ");
	    hailstone(n);
        println("The process took " + count + " to reach 1");
	}
	
	public int hailstone(int n) {
	    if (n==1) {
	        return 1;
	    } else if (n%2==0) {
	        println(n + " is even so I take half: " + (n/2));
	        count++;
	        return hailstone(n/2);
	    } else {
	        println(n + " is odd so I make 3n+1: " + (3*n+1));
	        count++;
	        return hailstone(n*3 + 1);
	    }
	}
	
	/*Instance variable keeps track of number of steps taken to complete Hailstone*/
	private int count;	
}

