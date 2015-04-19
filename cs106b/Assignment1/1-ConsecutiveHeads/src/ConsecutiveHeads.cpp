/*
 * File: ConsecutiveHeads.cpp
 * --------------------------
 * Name: [TODO: enter name here]
 * Section: [TODO: enter section leader here]
 * This file is the starter project for the coin-flipping problem.
 * [TODO: rewrite the documentation]
 */

#include <iostream>
#include <string>
#include "console.h"
#include "random.h"
using namespace std;



string flipCoin();
void flipCoinUntil(int target, string side);

const int TARGET_AMOUNT = 3;
const string TARGET_SIDE = "heads";
const string HEADS = "heads";
const string TAILS = "tails";

int main() {
  flipCoinUntil(TARGET_AMOUNT, TARGET_SIDE);
  return 0;
}

string flipCoin() {
    int n = randomInteger(0,1);
    if (n==0) {
        return HEADS;
    } else {
        return TAILS;
    }
}

void flipCoinUntil(int targetAmount, string targetSide) {
    int currentStreak = 0;
    int flipCount = 0;
    while (true) {
        if (currentStreak == targetAmount) {
            cout << "It took " << flipCount << " flips to get to get " << targetAmount << " consecutive " << targetSide << "." << endl;
            return;
        }

        string currentFlip = flipCoin();
        flipCount += 1;
        cout << currentFlip << endl;

        if (currentFlip == targetSide) {
            currentStreak += 1;
        } else {
            currentStreak = 0;
        }
    }

}
