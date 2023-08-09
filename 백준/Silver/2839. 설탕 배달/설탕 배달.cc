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
    int arr[5001] = {
        0,
    };
    cin >> n;

    arr[3] = arr[5] = 1;

    for (int i = 6; i <= n; i++)
    {
        if (arr[i - 3] == 0 && arr[i - 5] == 0)
            continue;
        else if (arr[i - 3] == 0)
            arr[i] = arr[i - 5] + 1;
        else if (arr[i - 5] == 0)
            arr[i] = arr[i - 3] + 1;
        else
            arr[i] = arr[i - 5] + 1;
    }

    if (arr[n] == 0)
        cout << -1;
    else
        cout << arr[n];
}