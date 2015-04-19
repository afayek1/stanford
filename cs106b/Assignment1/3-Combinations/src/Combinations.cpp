/*
 * File: Combinations.cpp
 * ----------------------
 * Name: [TODO: enter name here]
 * Section: [TODO: enter section leader here]
 * This file is the starter project for the recursive combinations problem.
 * [TODO: rewrite the documentation]
 */

#include <iostream>
#include "console.h"
#include "simpio.h"
using namespace std;

int pascalSum(int n, int k);

int main() {
   int n = getInteger("Which row?") - 1;
   int k = getInteger("Which column?") - 1;
   cout << "The pascal sum is " << pascalSum(n, k);
   return 0;
}

int pascalSum(int n, int k) {
    if (k==0 || k == n) {
        return 1;
    } else {
        return pascalSum(n-1, k) + pascalSum(n-1, k-1);
    }
}

