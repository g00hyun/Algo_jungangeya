#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>

using namespace std;

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)

int r,c;
char map[20][20];
bool visited[28];
queue<pair<int,int>> q;
int maxcnt;

int dx[4] = {1,-1,0,0};
int dy[4] = {0,0,1,-1};

int alphaToIndex(char a) {
    return a - 'A';
}

bool inRange(int x, int y) {
    return 0 <= x && x < r && 0 <= y && y < c;
}

bool CanGo(int x, int y) {
    if(!inRange(x,y)) return false;
    if(visited[alphaToIndex(map[x][y])]) return false;
    return true;
}

void DFS(int x, int y, int cnt) {
    maxcnt = max(cnt, maxcnt);

    for(int i = 0; i<4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if(CanGo(nx,ny)) {
            visited[alphaToIndex(map[nx][ny])] = true;
            DFS(nx, ny, cnt+1);
            visited[alphaToIndex(map[nx][ny])] = false;
        }
    }
    
}

int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    cin >> r >> c;

    for(int i = 0; i<r; i++)
        for (int j = 0; j < c; j++)
            cin >> map[i][j];

    visited[alphaToIndex(map[0][0])] = true;
    DFS(0,0,1);

    cout << maxcnt;

}