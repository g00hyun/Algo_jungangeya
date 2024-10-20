#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n, k;
vector<pair<int,int>> knapsack;
int dp[101][100001];

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n >> k;
    for(int i = 0; i<n; i++) {
        int w, v;
        cin >> w >> v;

        knapsack.push_back({w,v});
    }

    for(int weight = 1; weight<=k; weight++) {
        for(int obj = 1; obj<=n; obj++) {
            if(knapsack[obj-1].first > weight)
                dp[obj][weight] = dp[obj-1][weight];
            else
                dp[obj][weight] = max(dp[obj-1][weight - knapsack[obj-1].first] + knapsack[obj-1].second ,  dp[obj-1][weight]);

        }
    }

    cout << dp[n][k];
}