/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
   
    //Parameters in pixels of each circle in the target 
    private static final double LARGE_RADIUS = 72;
    private static final double MEDIUM_RADIUS = 72*0.65;
    private static final double SMALL_RADIUS = 72*0.3;
    
    public void run() {
        addTarget();
	}
    
    //Creates a target by implementing addCircle three times
    private void addTarget() {
        addCircle(LARGE_RADIUS, Color.RED);
        addCircle(MEDIUM_RADIUS, Color.WHITE);
        addCircle(SMALL_RADIUS, Color.RED);
    }
    
    //Creates a circle in the middle of the canvas
    private void addCircle(double radius, Color color) {
        double diameter = radius*2;
        double canvasCenterX = getWidth()/2;
        double canvasCenterY = getHeight()/2;
        GOval circle = new GOval(canvasCenterX-radius, canvasCenterY-radius, diameter, diameter);
        circle.setFilled(true);
        circle.setColor(color);
        add(circle);
    }
    

}
