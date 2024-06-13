#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <unordered_set>

using namespace std;

#define MAP_MAX_N 100

int n, l;
int MAP[MAP_MAX_N][MAP_MAX_N];

bool inRange(int x, int y) {
    return 0 <= x && x < n && 0 <= y && y < n;
}

// 개행문자는 "\n" 사용하기 (endl사용할 시 버퍼와 줄바꿈을 동시에 처리하므로 매우 느림)
int main() {
    ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

    cin >> n >> l;
    for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
            cin >> MAP[i][j];

    int answer = 0;

    vector<int> r;

    // 가로
    for(int row = 0; row<n; row++) {
        int visited[n] = {0,};

        int col = 0;
        while(col < n) {
            int ncol = col + 1;

            if(inRange(row,ncol) && MAP[row][col] == MAP[row][ncol])
                col++;
            else if(MAP[row][col] == MAP[row][ncol] - 1) {
                int len = l;
                bool isInstall = true;
                while(len--)
                    if(visited[col - len] == 1)
                        isInstall = false;
                
                if(!isInstall) break;

                bool isStep = true;
                len = l;
                while(len--) {
                    int rcol = col - len;
                    if(!(inRange(row,rcol) && MAP[row][col] == MAP[row][rcol])) {
                        isStep = false;
                        break;
                    }
                }

                if(isStep) {
                    int len = l;
                    while(len--)
                        visited[col - len] = 1;
                    col++;
                }
                else break;
            }
            else if(MAP[row][col] == MAP[row][ncol] + 1) {
                int len = l;
                bool isInstall = true;
                while(len--)
                    if(visited[col + len + 1] == 1)
                        isInstall = false;
                
                if(!isInstall) break;
                
                bool isStep = true;
                len = l;
                while(len--) {
                    int rcol = col + len + 1;
                    if(!(inRange(row,rcol) && MAP[row][ncol] == MAP[row][rcol])) {
                        isStep = false;
                        break;
                    }
                }

                if(isStep) {
                    int len = l;
                    while(len--)
                        visited[col + len + 1] = 1;
                    col++;
                }
                else break;
            }
            else
                break;
        }

        if(col == n-1) answer++, r.push_back(row);
    }

    vector<int> c;
    // 세로
    for(int col = 0; col<n; col++) {
        int visited[n] = {0,};

        int row = 0;
        while(row < n) {
            int nrow = row + 1;

            if(inRange(nrow,col) && MAP[row][col] == MAP[nrow][col])
                row++;
            else if(MAP[row][col] == MAP[nrow][col] - 1) {
                int len = l;
                bool isInstall = true;
                while(len--)
                    if(visited[row - len] == 1)
                        isInstall = false;
                
                if(!isInstall) break;

                bool isStep = true;
                len = l;
                while(len--) {
                    int rrow = row - len;
                    if(!(inRange(rrow,col) && MAP[row][col] == MAP[rrow][col])) {
                        isStep = false;
                        break;
                    }
                }

                if(isStep) {
                    int len = l;
                    while(len--)
                        visited[row - len] = 1;
                    row++;
                }
                else break;
            }
            else if(MAP[row][col] == MAP[nrow][col] + 1) {
                int len = l;
                bool isInstall = true;
                while(len--)
                    if(visited[row + len + 1] == 1)
                        isInstall = false;
                
                if(!isInstall) break;

                bool isStep = true;
                len = l;
                while(len--) {
                    int rrow = row + len + 1;
                    if(!(inRange(rrow,col) && MAP[nrow][col] == MAP[rrow][col])) {
                        isStep = false;
                        break;
                    }
                }

                if(isStep) {
                    int len = l;
                    while(len--)
                        visited[row + len + 1] = 1;
                    row++;
                }
                else break;
            }
            else
                break;
        }

        if(row == n-1) answer++, c.push_back(col);
    }

    cout << answer;
}