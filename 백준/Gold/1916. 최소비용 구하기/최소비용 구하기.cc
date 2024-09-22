#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <climits>
#include <queue>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int n, m;
vector<pair<int,int>> v[1001];
int start, finish;

int minDist[1001];

void Dijkstra(int start) {
    minDist[start] = 0;
    priority_queue<pair<int,int>> pq;
    pq.push({start, 0});

    while(!pq.empty()) {
        int current = pq.top().first;
        int distance = -pq.top().second;
        pq.pop();

        if(minDist[current] < distance) continue;

        for(int i = 0; i < v[current].size(); i++) {
            int next = v[current][i].first;
            int nextDistance = distance + v[current][i].second;

            if(nextDistance < minDist[next]) {
                minDist[next] = nextDistance;
                pq.push({next, -nextDistance});
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    cin >> n >> m;

    while(m--) {
        int city1, city2, cost;
        cin >> city1 >> city2 >> cost;

        v[city1].push_back({city2, cost});
    }
    cin >> start >> finish;

    for(int i = 1; i<=n; i++)
        minDist[i] = INT_MAX;

    Dijkstra(start);
    
    cout << minDist[finish];
}