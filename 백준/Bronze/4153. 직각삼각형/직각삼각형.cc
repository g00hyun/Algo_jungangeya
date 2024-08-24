#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    while(1) {
        int length[3];
        cin >> length[0] >> length[1] >> length[2];

        if(length[0] == 0 && length[1] == 0 && length[2] == 0)
            break;

        sort(length, length+3);

        pow(length[0], 2) + pow(length[1], 2) == pow(length[2], 2) ? cout << "right" : cout << "wrong";
        cout << '\n';
    }
}