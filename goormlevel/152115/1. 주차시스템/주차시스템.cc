#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <climits>

using namespace std;

int n,m;
queue<pair<int,int>> q;
int MAP[2000][2000];
bool visited[2000][2000];

int dx[4] = {0,0,-1,1};
int dy[4] = {1,-1,0,0};

bool inRange(int x, int y) {
	return 0 <= x && x < n && 0 <= y && y < m;
}

bool CanGo(int x, int y) {
	if(!inRange(x,y)) return false;
	if(visited[x][y]) return false;
	return true;
}

int BFS(int initCnt) {
	int cnt = initCnt;
	while(!q.empty()) {
		auto [x,y] = q.front();
		q.pop();
		
		for(int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(CanGo(nx, ny)) {
				q.push({nx,ny});
				visited[nx][ny] = true;
				cnt += MAP[nx][ny] == 0 ? 1 : -2;
			}
		}
	}
	return cnt;
}

int main() {
	cin >> n >> m;
	for(int i = 0; i<n; i++)
		for(int j = 0; j<m; j++)
			cin >> MAP[i][j];
	
	for(int i = 0; i<n; i++)
		for(int j = 0; j<m; j++)
			if(MAP[i][j] == 1)
				visited[i][j] = true;
	
	int result = INT_MIN;
	for(int i = 0; i<n; i++)
		for(int j = 0; j<m; j++)
			if(!visited[i][j]) {
				q.push({i,j});
				visited[i][j] = true;
				int initCnt = MAP[i][j] == 0 ? 1 : -2;
				int cnt = BFS(initCnt);
				
				result = max(result, cnt);
			}
	
	result == INT_MIN || result < 0 ? cout << 0 : cout << result;
	return 0;
}