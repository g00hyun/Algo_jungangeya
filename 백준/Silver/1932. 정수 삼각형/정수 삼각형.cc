#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n;
int arr[500][500];
int dp[500][500];

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    cin >> n;
    for(int i = 0; i<n; i++)
        for(int j = 0; j<=i; j++)
            cin >> arr[i][j];

    for(int i = 1; i<n; i++)
        for(int j = 0; j<=i; j++) {
            if(j == 0)
                arr[i][j] += arr[i-1][j];
            else if(j == i)
                arr[i][j] += arr[i-1][j-1];
            else
                arr[i][j] += max(arr[i-1][j], arr[i-1][j-1]);
        }

    cout << *max_element(arr[n-1], arr[n-1]+n);
}