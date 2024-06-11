#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

// left => -- / right => ++
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};
bool visited[500][500][4];
vector<string> map;




int UpdateDir(int x, int y, int dir, vector<string> grid) {
    if(grid[x][y] == 'L')
        dir = (dir - 1) >= 0 ? dir-1 : 3;
    else if(grid[x][y] == 'R')
        dir = (dir+1)%4;
    else
        dir = dir;
    
    return dir;
}

void NextStep(int *x, int *y, int dir, int max_x, int max_y) {
    *x += dx[dir], *y += dy[dir];
    
    if(0 > *x)
        *x = max_x - 1;
        
    if(*x >= max_x)
        *x = 0;
        
    if(0 > *y)
        *y = max_y - 1;
        
    if(*y >= max_y)
        *y = 0;  
}

vector<int> solution(vector<string> grid) {
    vector<int> answer;    
    
    for(int init_x = 0; init_x < grid.size(); init_x++) {
        for(int init_y = 0; init_y < grid[0].size(); init_y++) {
            for(int init_dir = 0; init_dir < 4; init_dir++) {
                if(!visited[init_x][init_y][init_dir]) {
                
                    int x,y,dir;
                    x = init_x, y = init_y, dir = init_dir;
                    // cout << '(' << x << ',' << y << ") => " << grid[x][y] << endl;
                    
                    int cnt = 0;                    
                    while(!visited[x][y][dir]) {
                        cnt++;
                        visited[x][y][dir] = true;

                        // dir = UpdateDir(x, y, dir, grid);
                        if(grid[x][y] == 'L')
                            dir = (dir - 1) >= 0 ? dir-1 : 3;
                        else if(grid[x][y] == 'R')
                            dir = (dir+1)%4;
                        else
                            dir = dir;
                        
                        // NextStep(&x, &y, dir, grid.size(), grid[0].size());
                        x = (x + dx[dir] + grid.size()) % grid.size();
                        y = (y + dy[dir] + grid[0].size()) % grid[0].size();

                        // cout << '(' << x << ',' << y << ") => " << grid[x][y] << endl;
                    }
                    // cout << cnt << endl;
                    if(init_dir == dir && init_x == x && init_y == y)
                        answer.push_back(cnt);
                }
            }

        }
    }
    
    sort(answer.begin(), answer.end());
    
    
    return answer;
}