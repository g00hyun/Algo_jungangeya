#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)


int n;
int arr[100000][3];
int dp[2][3];

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n;
    for(int i = 0; i<n; i++)
        cin >> arr[i][0] >> arr[i][1] >> arr[i][2];

    dp[1][0] = dp[0][0] = arr[0][0];
    dp[1][1] = dp[0][1] = arr[0][1];
    dp[1][2] = dp[0][2] = arr[0][2];

    for(int i = 1; i<n; i++) {
        dp[1][0] = arr[i][0] + max(dp[0][0], dp[0][1]);
        dp[1][1] = arr[i][1] + max({dp[0][0], dp[0][1], dp[0][2]});
        dp[1][2] = arr[i][2] + max(dp[0][2], dp[0][1]);

        dp[0][0] = dp[1][0];
        dp[0][1] = dp[1][1];
        dp[0][2] = dp[1][2];
    }
    int result_max = max({dp[1][0], dp[1][1], dp[1][2]});

    dp[1][0] = dp[0][0] = arr[0][0];
    dp[1][1] = dp[0][1] = arr[0][1];
    dp[1][2] = dp[0][2] = arr[0][2];

    for(int i = 1; i<n; i++) {
        dp[1][0] = arr[i][0] + min(dp[0][0], dp[0][1]);
        dp[1][1] = arr[i][1] + min({dp[0][0], dp[0][1], dp[0][2]});
        dp[1][2] = arr[i][2] + min(dp[0][2], dp[0][1]);

        dp[0][0] = dp[1][0];
        dp[0][1] = dp[1][1];
        dp[0][2] = dp[1][2];
    }
    int result_min = min({dp[1][0], dp[1][1], dp[1][2]});
    cout << result_max << ' ' << result_min;

}