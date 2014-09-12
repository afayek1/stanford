/*
 * File: ConsecutiveHeads.cpp
 * --------------------------
 * Name: [TODO: enter name here]
 * Section: [TODO: enter section leader here]
 * This file is the starter project for the Consecutive Heads problem.
 * [TODO: rewrite the documentation]
 */

#include <iostream>
#include <cstdlib>
#include "console.h"
#include "random.h"

using namespace std;

string flipCoin();


int main() {
    while (true) {
        if (flipCoin()) == "Heads";
    }
    return 0;
}


string flipCoin(){
    int side = randomInteger(0,1);
    if (side==0) {
        return "Heads";
    } else {
        return "Tails";
    }
}