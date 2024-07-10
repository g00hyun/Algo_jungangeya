#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int n,m;
vector<int> v;
// bool visited[9];

void Backtracking(int s) {
    if(v.size() == m) {
        int prev = 0;
        for(auto i : v) {
            if(prev > i) return;

            prev = i;
        }

        for(auto i : v)
            cout << i << ' ';
        cout << endl;

        return;
    }

    for(int i = 1; i<=n; i++) {
        // if(visited[i]) continue;

        // visited[i] = true;
        v.push_back(i);
        Backtracking(s+1);
        v.pop_back();
        // visited[i] = false;
    }
}

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n >> m;

    Backtracking(1);
}