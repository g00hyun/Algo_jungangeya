#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <map>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n,k;
int arr[1025][1025];
int prefixSum[1025][1025];

int Calculation(int x, int y) {
    int sum = 0;

    for(int i = 1; i<=x; i++)
        for(int j = 1; j<=y; j++)
            sum += arr[i][j];

    return sum;
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n >> k;

    for(int i = 1; i<=n; i++)
        for(int j = 1; j<=n; j++)
            cin >> arr[i][j];

    // 여기도 시간초과..?
    for(int i = 1; i<=n; i++)
        for(int j = 1; j<=n; j++)
            prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + arr[i][j];

    while(k--) {
        int x1,y1,x2,y2;
        cin >> x1 >> y1 >> x2 >> y2;

        cout << prefixSum[x2][y2] - prefixSum[x1-1][y2] - prefixSum[x2][y1-1] + prefixSum[x1-1][y1-1] << '\n';

        
    }
}