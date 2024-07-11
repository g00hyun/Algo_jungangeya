#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <set>

using namespace std;

int n, m;
vector<int> v;
bool visited[8];
set<int> s;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

void Backtracking(int prev, const vector<int>& arr) {
    if(v.size() == m) {
        for(int i : v)
            cout << i << ' ';
        cout << '\n';

        return;
    }

    for(int i = 0; i<arr.size(); i++) {
        if(prev > arr[i]) continue;

        v.push_back(arr[i]);
        Backtracking(arr[i], arr);
        v.pop_back();
    }
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n >> m;
    for(int i = 0; i<n; i++) {
        int tmp;
        cin >> tmp;

        s.insert(tmp);
    }

    vector<int> arr(s.begin(), s.end());

    sort(arr.begin(), arr.end());

    Backtracking(-1, arr);  
}