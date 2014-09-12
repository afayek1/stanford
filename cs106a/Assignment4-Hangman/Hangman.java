/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {
    
    //Total number of guesses player has per round.
    private static final int MAX_GUESSES = 8;
    
    
    public void init(){
        canvas = new HangmanCanvas();
        add(canvas);
    }
    
    public void run() {
        printIntro();
        playGame();        
    }
    
    private void printIntro() {
        println("Welcome to Hangman!");
        println("");
    }
            
    private void setUpGame(){
        canvas.reset();        
        randomWord();
        hideWord();
        guessesRemaining = MAX_GUESSES;
        incorrectGuesses = "";
    }
    
    private void playGame() {
        while (true) {                   
            setUpGame();
            
            while (guessesRemaining>0) {
                println("The word now looks like this: " + String.valueOf(hiddenWord));    
                canvas.displayWord(String.valueOf(hiddenWord));
                getGuess();
                checkGuess();
                checkGuessesRemaining();
                if (didPlayerWin()) break;                                                                                
            }
            
            //Asks if player wants to play again.
            if (playAgain()) break;              
        }
    }
    
    private boolean didPlayerWin() {
        if (secretWord.equals(String.valueOf(hiddenWord))) {
            canvas.displayWord(String.valueOf(hiddenWord));
            println("You guessed the word: " + secretWord);
            println("You win.");
            return true;
        }
        return false;
    }
    
    private void checkGuessesRemaining() {
        if (guessesRemaining == 1) {
            println("You only have one guess left");
        } else if (guessesRemaining == 0) {
            println("You're completely hung");
            println("The word was: " + secretWord);
            println("You lose.");           
        } else {
            println("You have " + (guessesRemaining) + " guesses left");
            println("");
        }
    }
    
    //Checks guess and updates hidden word.
    private void checkGuess() {
        boolean found = false;
        for (int i = 0; i<secretWord.length(); i++) {                
            if (guess==secretWord.charAt(i)) {                   
                hiddenWord[i] = guess;
                found = true;
            }
        }
        
        if (found) {
            println("That guess is correct");
        } else {
            println("There are no " + guess + "'s in the word");
            incorrectGuesses+=guess;
            canvas.noteIncorrectGuess(guess);
            guessesRemaining-=1;
        }
    }
            
    //Gets the user's guess.
    private char getGuess() {        
        while(true) {            
            String getGuess = readLine("Your guess: ").toUpperCase();
            if (getGuess instanceof java.lang.String && getGuess.length()==1) {
                guess = getGuess.charAt(0);
                return guess;
            } else if (getGuess instanceof java.lang.String && getGuess.length()!=1){
                println("You can only guess one letter at a time!");
            } else {
                println("Your guess must be a single letter!");
            }
        }
    }
                
    //Prints the hidden word as a series of dashes (char[]).
    private char[] hideWord() {
        hiddenWord = new char[secretWord.length()];
        for (int i=0; i<secretWord.length(); i++) {            
            hiddenWord[i]='-';
        }
        return hiddenWord;
    }
    
    /**
     * Returns a random secret word from our library.
    */
    private void randomWord(){
        int r = rgen.nextInt(hangmanDictionary.getWordCount());
        secretWord = hangmanDictionary.getWord(r);
    }
    
    private boolean playAgain() {
        String playAgain = readLine("Would you like to play again? (Y/N)").toUpperCase();
        if (playAgain == "N" || playAgain == "NO") return false;
        return true;
    }
    
    private RandomGenerator rgen = new RandomGenerator();
    private HangmanCanvas canvas;
    
    //Loads library of words.
    private HangmanLexicon hangmanDictionary = new HangmanLexicon();
    
    //Random secret word player must try and solve.
    private String secretWord;
    
    //Shows portion of the secret word player has correctly guessed so far.
    private char[] hiddenWord;
    
    //Number of remaining guesses.
    private int guessesRemaining;
        
    //List of player's incorrect guesses.
    private String incorrectGuesses;
    
    //Player's current guess.
    private char guess;
}
