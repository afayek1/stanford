#include <iostream>
#include "hello.h"
#include "console.h"
using namespace std;

string censorString1(string text, string remove);
void censorString2(string &text, string remove);

int main() {
    string school = "Stanford University";
    censorString2(school, "nt");
    cout << school << endl;
    return 0;
}


string censorString1(string text, string remove) {
    string censored = "";
    for (int i=0; i<text.length(); i++) {
        if (remove.find(text[i]) == string::npos ) {
            censored+=text[i];
        }
    }
    return censored;
}

void censorString2(string &text, string remove) {
    for (int i=0; i < text.length(); i++) {
        if (remove.find(text[i]) != string::npos) {
            text.replace(i, 1, "");
            i--;
        }
    }
}
