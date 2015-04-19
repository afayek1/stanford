/*
 * File: NumericConversion.cpp
 * ---------------------------
 * Name: [TODO: enter name here]
 * Section: [TODO: enter section leader here]
 * This file is the starter project for the numeric-conversion problem
 * in which you implement the functions intToString and stringToInt.
 * [TODO: rewrite the documentation]
 */

#include <iostream>
#include <string>
#include "console.h"
using namespace std;

/* Function prototypes */

string intToString(int n);
int stringToInt(string str);

/* Main program */

int main() {
  cout << intToString(-158) << endl;
  cout << intToString(-1) << endl;
  cout << intToString(0) << endl;
  cout << intToString(1) << endl;
  cout << intToString(158) << endl;
  return 0;
}

// TODO: refactor
string intToString(int n) {
    // handle negative inputs
    if (n < 0) {
        return "-" + (intToString(n*-1));
    }

    if (n/10 == 0)  {
        return string() + char(n%10 + '0');
    } else {
        return intToString(n/10) + string() + char(n%10 + '0');
    }
}

