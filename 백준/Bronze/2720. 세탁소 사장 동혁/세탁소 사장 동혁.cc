#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int n;
    int arr[4] = {0,};
    cin >> n;
    while (n--)
    {
        fill(arr, arr+4, 0);
        int num;
        cin >> num;
        
        arr[0] = num / 25;
        num %= 25;
        arr[1] = num / 10;
        num %= 10;
        arr[2] = num / 5;
        num %= 5;
        arr[3] = num / 1;

        for (auto &&i : arr)
            cout << i << ' ';
        cout << endl;
    }
}