#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n;
pair<int,int> work[15 + 1];
int dp[15 + 1];


int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    
    cin >> n;
    for(int i = 1; i<=n; i++) {
        int t, p;
        cin >> t >> p;
        work[i] = make_pair(t,p);
    }

    for (int i = n; i > 0; i--)
    {
        if(i + work[i].first <= n + 1) {
            dp[i] = max(dp[i+1], dp[i + work[i].first] + work[i].second);
        }
        else {
            dp[i] = dp[i+1];
        }
    }
    
    cout << dp[1];
}