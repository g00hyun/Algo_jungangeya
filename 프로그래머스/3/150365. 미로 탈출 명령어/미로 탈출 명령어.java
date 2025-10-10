import java.util.*;

class Solution {
    int n,m;
    Pos start;
    Pos end;
    int moveCnt;
    // d l r u
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    char[] dirs = new char[4];
    Set<String> visited = new HashSet<>();
    String answer;
    
    public String solution(int N, int M, int x, int y, int r, int c, int k) {
        n = N;
        m = M;
        start = new Pos(x,y);
        end = new Pos(r,c);
        moveCnt = k;
        
        dirs[0] = 'd';
        dirs[1] = 'l';
        dirs[2] = 'r';
        dirs[3] = 'u';
        
        if(!impossible()) return "impossible";
        
        return DFS(start, 0, "") ? answer : "impossible";
    }
    
    private boolean impossible() {
        int dist = Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
        
        if(dist > moveCnt) return false;
        if((moveCnt - dist) % 2 == 1) return false;
        
        return true;
    }
    
    private boolean DFS(Pos pos, int cnt, String route) {
        if(cnt == moveCnt) {
            return pos.x == end.x && pos.y == end.y;
        }
        
        int remain = moveCnt - cnt;
        int dist = Math.abs(end.x - pos.x) + Math.abs(end.y - pos.y);
        if(remain < dist) return false;
        
        for(int i = 0; i<4; i++) {
            int nx = pos.x + dx[i];
            int ny = pos.y + dy[i];
            
            if(inRange(nx,ny)) {
                if(DFS(new Pos(nx,ny), cnt+1, route+dirs[i])) {
                    if(cnt+1 == moveCnt) answer = route + dirs[i];
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= m;
    }
}

class Pos {
    int x;
    int y;
    
    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}