#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;
    int arr[1001] = {0,};
    int dp[1001] = {0,};

    for (int i = 1; i <= n; i++)
        cin >> arr[i];

    int Answer = 0;
    for (int i = 1; i <= n; i++)
    {
        dp[i] = 1;
        for (int j = i - 1; j >= 1; j--)
        {
            if (arr[i] > arr[j])
            {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        Answer = max(dp[i], Answer);
    }
 
    cout << Answer << endl;
}