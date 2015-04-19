#include <iostream>
#include <fstream>
#include <string>
#include "hello.h"
#include "console.h"
#include "filelib.h"
using namespace std;

void readStats(string fname, int &min, int &max, double &mean);

int main() {
    int min = 0;
    int max = 0;
    double mean = 0.0;
    string filename = "scores.txt";
    readStats(filename, min, max, mean);
    cout << "The min is: " << min << endl;
    cout << "The max is: " << max << endl;
    cout << "The mean is: " << mean << endl;
    return 0;
}

void readStats(string fname, int &min, int &max, double &mean) {
    ifstream infile;
    if (!openFile(infile, fname)) {
        error("Can't open " + fname);
    }
    double total = 0;
    int count = 0;
    int score;
    while (infile >> score) {
        if (score < 0 || score > 100) error("Score out of range");
        if (count == 0 || score < min) min = score;
        if (count == 0 || score > max) max = score;
        total += score;
        count++;
    }
    mean = total/count;
    infile.close();
}
