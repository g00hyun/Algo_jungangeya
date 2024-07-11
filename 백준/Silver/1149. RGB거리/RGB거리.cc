#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n;
int costs[1001][3];
int dp[1001][3];

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    cin >> n;
    for(int i = 1; i<=n; i++) {
        int r,g,b;
        cin >> r >> g >> b;

        costs[i][0] = r;
        costs[i][1] = g;
        costs[i][2] = b;
    }

    for(int i = 1; i<=n; i++) {
        dp[i][0] = costs[i][0] + min(dp[i-1][1], dp[i-1][2]);
        dp[i][1] = costs[i][1] + min(dp[i-1][0], dp[i-1][2]);
        dp[i][2] = costs[i][2] + min(dp[i-1][0], dp[i-1][1]);
    }

    cout << min({dp[n][0], dp[n][1], dp[n][2]});
}