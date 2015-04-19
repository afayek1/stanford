/*
 * File: Obenglobish.java
 * ----------------------
 * Name: [TODO: enter name here]
 * Section: [TODO: enter section leader here]
 * This file is the starter project for the Obenglobish problem.
 * [TODO: rewrite the documentation]
 */

#include <iostream>
#include <string>
#include "console.h"
#include "simpio.h"
#include "strlib.h"
using namespace std;

/* Main program */

string translateToObenglish(string word);

int main() {
    while (true) {
       string word = getLine("Please enter a word: ");
       if (word == "") break;
       string translatedWord = translateToObenglish(word);
       cout << word << " --> " << translatedWord << endl;
   }
   return 0;
}

string translateToObenglish(string word) {
    string translation = "";
    string vowels = "aeiou";
    string prefix = "ob";

    for (int i=0; i < word.length(); i++) {
        // Rule 1: If the last letter is an "e" ignore prefix
        if (i==word.length()-1 && word[i] == 'e') {
            translation += "e";
        // Rule 2: If the current letter is a vowel and the previous letter isn't, add prefix
        } else if ( vowels.find(word[i]) != string::npos && (vowels.find(word[i-1]) == string::npos || i == 0)) {
            translation += (prefix + word[i]);
        } else {
            translation += word[i];
        }
    }
    return translation;
}
