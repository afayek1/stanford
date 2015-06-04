/*
 * File: WordLadder.cpp
 * --------------------
 * Name: [TODO: enter name here]
 * Section: [TODO: enter section leader here]
 * This file is the starter project for the word ladder problem
 * on Assignment #2.
 * [TODO: extend the documentation]
 */

#include <iostream>
#include "console.h"
#include "lexicon.h"
#include "queue.h"
#include "simpio.h"
#include "vector.h"
using namespace std;

void printIntroduction();
string getStartWord();
string getDestinationWord();
void printLadder(vector<string> ladder);

queue<vector<string> > toVisit;
vector<string> ladder;
set<string> visited;
string start, destination;

int main() {
    Lexicon english("EnglishWords.dat");
    printIntroduction();
    start = getStartWord();
    destination = getDestinationWord();

    visited.insert(start);
    ladder.push_back(start);
    toVisit.push(ladder);

    while (!toVisit.empty()) {
        ladder = toVisit.front();
        toVisit.pop();
        if (ladder.back() == destination) {
            printLadder(ladder);
            return 0;
        }

        string word = ladder.back();
        for (int i=0; i<start.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                string potentialWord = word;
                potentialWord[i] = c;
                if (english.contains(potentialWord) && !(visited.find(potentialWord) != visited.end())){
                    visited.insert(potentialWord);
                    vector<string> duplicateLadder = ladder;
                    duplicateLadder.push_back(potentialWord);
                    toVisit.push(duplicateLadder);
                }
            }
        }
    }
    cout << "NO LADDER" << endl;
	return 0;
}

void printIntroduction() {
    cout << "Welcome to word ladder. Your start and destination words must be the same length. RETURN to quit." << endl;
}

string getStartWord() {
    return getLine("Please enter start word: ");
}

string getDestinationWord() {
    return getLine("Please enter destination word: ");
}

void printLadder(vector<string> ladder) {
    if (ladder.size() == 1) {
        cout << ladder.front();
        return;
    }

    string printedLadder = "";
    for (int i=0; i < ladder.size() - 1; i++) {
        printedLadder += ladder[i] + " --> ";
    }

    printedLadder += ladder.back();
    cout << printedLadder << endl;
}
