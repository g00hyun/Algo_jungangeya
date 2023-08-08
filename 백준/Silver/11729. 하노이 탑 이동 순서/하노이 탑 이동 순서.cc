#include <iostream>

using namespace std;

void hanoi(int n, int left, int mid, int right)
{
    if (n == 1)
    {
        cout << left << ' ' << right << "\n";
    }
    else
    {
        hanoi(n - 1, left, right, mid);
        cout << left << ' ' << right << "\n";
        hanoi(n - 1, mid, left, right);
    }
}

int main()
{
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    int n;
    cin >> n;

    int result = (1<<n) - 1;
    cout << result << "\n";
    hanoi(n, 1, 2, 3);
}