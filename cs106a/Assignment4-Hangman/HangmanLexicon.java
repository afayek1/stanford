/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import acm.util.*;

public class HangmanLexicon {
    public HangmanLexicon() {
        
        try {
            BufferedReader hangmanFile = new BufferedReader(new FileReader("HangmanLexicon.txt"));
            while (true) {
                String word = hangmanFile.readLine();
                if (word==null) break;
                hangmanDictionary.add(word);                
            }
            hangmanFile.close();
        } catch (IOException ex) {
            throw new ErrorException(ex);
        }
    }
         
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return hangmanDictionary.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return hangmanDictionary.get(index);	
	}
	
	private ArrayList<String> hangmanDictionary = new ArrayList<String>();
}
