/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.Arrays;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		checkCard = new int[nPlayers+1][N_CATEGORIES+1];
		scoreCard = new int[nPlayers+1][N_CATEGORIES+1];
	    for (int round=1; round<=N_ROUNDS; round++) {
		    for (int currentPlayer=1; currentPlayer<=nPlayers; currentPlayer++) {		        		        
		        firstRoll(currentPlayer);		        		        
		        rollAgain();		        
		        rollAgain();
	            getCategoryScore(currentPlayer);
		    }
		}
	    displayFinalScores();
	    declareWinner();
	}
	
	
	/**
	 * First roll for each player for each round.
	 * @param currentPlayer player whose current turn it is.
	 */
	private void firstRoll(int currentPlayer) {
        display.printMessage(playerNames[currentPlayer-1] + "'s turn! 'Roll Dice' button to roll the dice.");
	    display.waitForPlayerToClickRoll(currentPlayer);
	    for (int die=0; die<N_DICE; die++) {
	        currentDiceState[die] = rgen.nextInt(1, 6);
	    }
	    display.displayDice(currentDiceState);
	}
	
	/**
	 * Second and third roll for each player for each round.
	 */
	private void rollAgain() {
	    display.printMessage("Select the dice you wish to re-roll and click 'Roll Again.'");
	    display.waitForPlayerToSelectDice();
	    for (int k=0; k<N_DICE; k++) {
            if (display.isDieSelected(k)==true) {
                currentDiceState[k]=rgen.nextInt(1,6);
            }
        }
        display.displayDice(currentDiceState);
	}

	/**
     * Player selects a category every round that he wants his dice to be considered for.
     * @param currentPlayer player whose current turn it is.
     * @return category that the player has picked that has not already been used
     * by that player.
     */
    private int selectCategory (int currentPlayer) {
        while (true) {
            int category = display.waitForPlayerToSelectCategory();
            if (checkCard[currentPlayer][category]==0) {
                checkCard[currentPlayer][category]=1;
                return category;
            } else {
                display.printMessage("Category has already been used. Pick another.");          
            }
        }
    }	

    /**
	 * If the dice state matches the category chosen, the player receives the corresponding score,
	 * otherwise they recieve a score of 0. The display is then updated to reflect these changes.
	 * @param currentPlayer of the round.
	 */
    private void getCategoryScore(int currentPlayer){
        display.printMessage("Select a category for this roll.");
        int category = selectCategory(currentPlayer);
        if (checkDiceAgainstCategory(category)==true) {
            display.updateScorecard(category, currentPlayer, calculateCategoryScore(currentPlayer, category));
            calculateTotalScore(currentPlayer);
        } else {
            display.updateScorecard(category, currentPlayer, 0);
        }
    }			
	
    /**
     * Checks if the dice state matches the category the player picked at the end of each round.
     * @param category a player has chosen that has not already been used.
     * @return true if the dice state matches the selected category, false otherwise.
     */
	private boolean checkDiceAgainstCategory(int category) {
	    int[] dieFrequency = new int[N_DICE_FACES+1];    
	    for (int die: currentDiceState) {
	        dieFrequency[die]+=1;
	    }

	    Arrays.sort(dieFrequency);
	    
	    if (category>=ONES && category<= SIXES || category == CHANCE) {
            return true;
	    } else if (category==THREE_OF_A_KIND) {
	        for (int die: dieFrequency) {
	            if (die>=3) return true;
	        }
	    } else if (category==FOUR_OF_A_KIND) {
	        for (int die: dieFrequency) {
	            if (die>=4) return true;
	        }
	    } else if (category==FULL_HOUSE) {
	        if(dieFrequency[N_DICE_FACES]==3 && dieFrequency[N_DICE_FACES-1]==2) return true;
	    } else if (category==SMALL_STRAIGHT) {
	        if (dieFrequency[2]==1 && dieFrequency[6]==1) return true;
	        if (dieFrequency[6]>=1 && dieFrequency[5]==1) return true;        
	    } else if (category==LARGE_STRAIGHT) {
            if (dieFrequency[6]==1) return true;      	    	    
	    } else if (category==YAHTZEE) {
	        for (int die: dieFrequency) {
	            if (die==5) return true;
	        }
	    }	    
	    return false;
	}
	
	/**
	 * Calculates the player's score at the end of each round given the category they selected.
	 * @param currentPlayer player whose current turn it is.
	 * @param category the player has selected that has previously been checked for validity.
	 * @return score corresponding to the given category.
	 */
    private int calculateCategoryScore(int currentPlayer, int category) {
        int score = 0;
        switch (category) {
            case ONES: case TWOS: case THREES: case FOURS: case FIVES: case SIXES:
                for (int die = 0; die<N_DICE; die++) {
                    if (currentDiceState[die]==category)
                        score += category;
                }
                break;
            case FULL_HOUSE:
                score = 25;
                break;
            case SMALL_STRAIGHT:
                score = 30;
                break;
            case LARGE_STRAIGHT:
                score = 40;
                break;
            case YAHTZEE:
                score = 50;
                break;
            case CHANCE: case FOUR_OF_A_KIND: case THREE_OF_A_KIND:
                for (int die = 0; die<N_DICE; die++) {
                    score+=currentDiceState[die];
                }
                break;
            default:
                score = 0;
        }
        scoreCard[currentPlayer][category] = score;
        return score;       
    }
                    	
	/**
	 * Calculates and updates the display of the total score after the end of each player's turn. 
	 * @param currentPlayer player whose current turn it is.
	 */
	private void calculateTotalScore(int currentPlayer){
        calculateLowerScore(currentPlayer);
        calculateUpperScore(currentPlayer);
        scoreCard[currentPlayer][TOTAL] = scoreCard[currentPlayer][UPPER_SCORE]+scoreCard[currentPlayer][LOWER_SCORE];
        display.updateScorecard(TOTAL, currentPlayer, scoreCard[currentPlayer][TOTAL]);
	}
	
    /**
     * Helper method of calculateTotal score that calculates the combined score of the upper categories,
     * determines if the player has met the bonus requirements, and updates the scoreCard state.
     * @param currentPlayer
     */
    private void calculateUpperScore(int currentPlayer) {
        int total = 0;
        for (int category = ONES; category<=SIXES; category++) {
            total += scoreCard[currentPlayer][category];
        }
        
        if (total>=63) scoreCard[currentPlayer][UPPER_BONUS]=35;
                  
        scoreCard[currentPlayer][UPPER_SCORE] = total;                     
    }
    
    /**
     * Helper method of calculateTotalScore that calculates the combined score of the lower categories
     * and updates the scoreCard state. 
     * @param currentPlayer player whose current turn it is.
     */
    private void calculateLowerScore(int currentPlayer) {
        int total = 0;
        for (int category = THREE_OF_A_KIND; category<=CHANCE; category++) {
            total += scoreCard[currentPlayer][category];
        }
        scoreCard[currentPlayer][LOWER_SCORE] = total;
    }
    
    /**
     * Display scores at the end of the game that were previously not seen.
     */
    private void displayFinalScores(){
        for (int player=1; player<=nPlayers; player++) {
            display.updateScorecard(UPPER_BONUS, player, scoreCard[player][UPPER_BONUS]);
            display.updateScorecard(UPPER_SCORE, player, scoreCard[player][UPPER_SCORE]);
            display.updateScorecard(LOWER_SCORE, player, scoreCard[player][LOWER_SCORE]);
        }
    }
    
	/**
	 * Determines and prints the winner at the end of the game.
	 */
    private void declareWinner(){
        int winningScore = 0;
        int winner = 0;
        for (int player=1; player<=nPlayers; player++) {
            if (scoreCard[player][TOTAL]>winningScore) {
                winningScore = scoreCard[player][TOTAL];
                winner = player-1;
            }
        }
        display.printMessage("Congratulations, " + playerNames[winner] + ", you're the winner with a total score of " + winningScore + "!");
    }
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	//State of the dice on the board.
	private int[] currentDiceState = new int[N_DICE];
	
	//State of the scores in each category for each player.
	private int[][] scoreCard;
	
	//State of categories each player has selected.
	private int[][] checkCard;
}
