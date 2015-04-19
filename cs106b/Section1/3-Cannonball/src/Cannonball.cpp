#include <iostream>
#include "hello.h"
#include "console.h"
using namespace std;

int cannonBalls(int height);

int main() {
    cout << cannonBalls(4) << endl;
    return 0;
}

int cannonBalls(int height) {
    if (height < 1) {
        return 0;
    } else {
        return height*height + cannonBalls(height-1);
    }
}
