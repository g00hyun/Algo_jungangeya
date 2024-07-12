#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

long long int a,b,c;

long long recursive(long long b) {
    if(b == 0) return 1;
    if(b == 1) return a%c;

    long long k = recursive(b / 2)%c;

    if(b & 1) return (k * k % c) * (a % c);
    else return k * k%c;
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    cin >> a >> b >> c;

    cout << recursive(b) % c;
}