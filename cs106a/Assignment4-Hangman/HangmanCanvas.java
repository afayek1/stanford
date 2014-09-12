/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
	    drawScaffold();
		incorrectGuesses = "";
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
	    double x = getWidth()/2-BEAM_LENGTH;
		double y = 60+SCAFFOLD_HEIGHT;
	    GLabel hiddenWordLabel = new GLabel(word, x, y);
		hiddenWordLabel.setFont("monospaced-24");
		if (getElementAt(x,y) != null){
            remove(getElementAt(x,y));
        }
		add(hiddenWordLabel);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {		
		incorrectGuesses+=letter; 
	    double x = getWidth()/2-BEAM_LENGTH;
	    double y = 80+SCAFFOLD_HEIGHT;
	    GLabel incorrectGuessesLabel = new GLabel(incorrectGuesses, x, y);
	    incorrectGuessesLabel.setFont("monospaced-24");
	    add(incorrectGuessesLabel);
		if (incorrectGuesses.length()==1) drawHead();
		else if (incorrectGuesses.length()==2) drawBody();
		else if (incorrectGuesses.length()==3) drawLeftArm();
		else if (incorrectGuesses.length()==4) drawRightArm();
		else if (incorrectGuesses.length()==5) drawLeftLeg();
		else if (incorrectGuesses.length()==6) drawRightLeg();
        else if (incorrectGuesses.length()==7) drawLeftFoot();
        else if (incorrectGuesses.length()==8) drawRightFoot();
	}
	
	private void drawScaffold(){
	    GLine pole = new GLine(getWidth()/2-BEAM_LENGTH, 20, getWidth()/2-BEAM_LENGTH, 20+SCAFFOLD_HEIGHT);
	    GLine beam = new GLine(getWidth()/2-BEAM_LENGTH, 20, getWidth()/2, 20);
	    GLine rope = new GLine(getWidth()/2, 20, getWidth()/2, 20+ROPE_LENGTH);
	    add(pole);
	    add(beam);
	    add(rope);
	}
	
	private void drawHead(){
	    double x = getWidth()/2-HEAD_RADIUS;
	    double y = 20+ROPE_LENGTH;
	    GOval head = new GOval(x, y, HEAD_RADIUS*2, HEAD_RADIUS*2);
	    add(head);
	}
	
	private void drawBody() {
	    GLine body = new GLine(getWidth()/2, 20+ROPE_LENGTH+HEAD_RADIUS*2, getWidth()/2, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
	    GLine hip = new GLine(getWidth()/2-HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, getWidth()/2+HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
	    add(hip);
	    add(body);
	}
	
	private void drawLeftArm(){
	    GLine leftUpperArm = new GLine(getWidth()/2-UPPER_ARM_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
	    GLine leftLowerArm = new GLine(getWidth()/2-UPPER_ARM_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2-UPPER_ARM_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
	    add(leftUpperArm);
	    add(leftLowerArm);
	}
	
    private void drawRightArm(){
        GLine rightUpperArm = new GLine(getWidth()/2, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2+UPPER_ARM_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
        GLine rightLowerArm = new GLine(getWidth()/2+UPPER_ARM_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, getWidth()/2+UPPER_ARM_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
        add(rightUpperArm);
        add(rightLowerArm);
    }
    
    private void drawLeftLeg(){
        GLine leftLeg = new GLine(getWidth()/2-HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, getWidth()/2-HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
        add(leftLeg);
    }
	
    private void drawRightLeg(){
        GLine rightLeg = new GLine(getWidth()/2+HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, getWidth()/2+HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
        add(rightLeg);
    }
	
    private void drawRightFoot() {
        GLine rightFoot = new GLine(getWidth()/2+HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, getWidth()/2+HIP_WIDTH+FOOT_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
        add(rightFoot);    
	}
    
    private void drawLeftFoot(){
        GLine leftFoot = new GLine(getWidth()/2-HIP_WIDTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, getWidth()/2-HIP_WIDTH-FOOT_LENGTH, 20+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
        add(leftFoot);        
    }

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private String incorrectGuesses;
}
