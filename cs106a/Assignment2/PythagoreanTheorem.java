/*
 * File: PythagoreanTheorem.java
 * Name: Amir Fayek
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
	    double a, b, c;
	    println("Enter value to complete the Pythagorean Thereom");
	    a = readDouble("a: ");
	    b = readDouble("b: ");
	    c = Math.sqrt(a*a+b*b);
	    println("c = " + c);
	}
}
