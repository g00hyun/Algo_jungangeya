#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n;
vector<int> v[100001];
bool visited[100001];
queue<int> q;
int result[100001];

void BFS() {
    while(!q.empty()) {
        int cur = q.front();
        q.pop();

        for(int i = 0; i < v[cur].size(); i++) {
            int next = v[cur][i];

            if(!visited[next]) {
                q.push(next);
                visited[next] = true;

                result[next] = cur;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    cin >> n;
    for(int i = 0; i<n-1; i++) {
        int n1, n2;
        cin >> n1 >> n2;

        v[n1].push_back(n2);
        v[n2].push_back(n1);
    }

    q.push(1);
    visited[1] = true;
    BFS();

    for(int i = 2; i<=n; i++) {
        cout << result[i] << '\n';
    }
}