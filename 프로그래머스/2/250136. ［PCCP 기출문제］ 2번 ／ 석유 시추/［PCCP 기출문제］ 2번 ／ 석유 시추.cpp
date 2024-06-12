#include <string>
#include <vector>
#include <queue>
#include <iostream>
#include <set>
#include <algorithm>

using namespace std;

int n,m;
int result[500];

vector<vector<int>> MAP;
bool visited[500][500];

set<int> available;

int dx[4] = {0,0,1,-1};
int dy[4] = {1,-1,0,0};

queue<pair<int,int>> q;

bool inRange(int x, int y) {
    return 0 <= x && x < n && 0 <= y && y < m;
}

bool CanGo(int x, int y) {
    if(!inRange(x,y)) return false;
    if(visited[x][y]) return false;
    if(MAP[x][y] == 0) return false;
    return true;
}

int BFS() {
    int cnt = 1;
    available.clear();
    
    while(!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        
        available.insert(y);
        
        for(int i = 0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(CanGo(nx, ny)) {
                q.push(make_pair(nx,ny));
                visited[nx][ny] = true;
                cnt++;
            }
        }
    }
    
    return cnt;
}

int solution(vector<vector<int>> land) {
    int answer = 0;
    
    MAP = land;
    
    n = land.size(), m = land[0].size();
    
    for(int i = 0; i<n; i++)
        for(int j = 0; j<m; j++)
            if(MAP[i][j] == 0)
                visited[i][j] = true;
    
    for(int i = 0; i<n; i++) {
        for(int j = 0; j<m; j++) {
            if(!visited[i][j]) {
                q.push(make_pair(i,j));
                visited[i][j] = true;
                int value = BFS();
                for(auto it : available)
                    result[it] += value;
            }
        }
    }
    
    answer = *max_element(result, result+m);
    
    return answer;
}