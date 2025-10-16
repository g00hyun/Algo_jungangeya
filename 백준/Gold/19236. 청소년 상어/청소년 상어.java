import java.util.*;

class Main {
    static ArrayDeque<Integer> ad = new ArrayDeque<>();
    
    // u, ul, l, ld, d, dr, r, ru
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    
    static int answer = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Block[][] map = new Block[4][4];
        
        for(int i = 0; i<4; i++)
            for(int j = 0; j<4; j++)
                map[i][j] = new Block(sc.nextInt(), sc.nextInt() - 1);
                
        answer = map[0][0].type;
        map[0][0].type = -1;
        ad.add(answer);
        
        DFS(0, 0, answer, map);
        
        System.out.println(answer);
    }
    
    private static void DFS(int x, int y, int sum, Block[][] oldMap) {
        Block[][] map = new Block[4][4];
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                map[i][j] = new Block(oldMap[i][j].type, oldMap[i][j].dir);
        
        fishMove(map);
        
        if(!canSharkMove(x,y,map)) {
            answer = Math.max(answer, sum);
            return;
        }
        
        int nx = x + dx[map[x][y].dir];
        int ny = y + dy[map[x][y].dir];
        while(inRange(nx,ny)) {
            int fishPoint = map[nx][ny].type;
            
            if(fishPoint <= 0) {
                nx += dx[map[x][y].dir];
                ny += dy[map[x][y].dir];
                continue;
            }
            
            map[x][y].type = 0;
            map[nx][ny].type = -1;
            
            ad.add(fishPoint);
            DFS(nx,ny,sum + fishPoint, map);
            
            // map 복구 로직
            map[nx][ny].type = fishPoint;
            map[x][y].type = -1;
            ad.removeLast();
            
            nx += dx[map[x][y].dir];
            ny += dy[map[x][y].dir];
        }
    }
    
    private static void fishMove(Block[][] map) {
        for(int fish = 1; fish<=16; fish++) {
            int x = -1; int y = -1;
            
            for(int i = 0; i<4; i++)    
                for(int j = 0; j<4; j++)
                    if(map[i][j].type == fish) {
                        x = i; y = j;
                    }
            
            if(x == -1 && y == -1) continue;
            
            int nx = x + dx[map[x][y].dir];
            int ny = y + dy[map[x][y].dir];
            
            // 무조건 탈출???
            // 다음 공간 찾기용 회전
            while(!inRange(nx,ny) || map[nx][ny].type == -1) {
                map[x][y].dir = (map[x][y].dir + 1) % 8;
                nx = x + dx[map[x][y].dir];
                ny = y + dy[map[x][y].dir];
            }
            
            // 물고기 이동
            if(map[nx][ny].type == 0) {
                map[nx][ny].type = map[x][y].type;
                map[nx][ny].dir = map[x][y].dir;
                map[x][y].type = 0;
            }
            else if(map[nx][ny].type > 0) {
                int type = map[x][y].type;
                int dir = map[x][y].dir;
                map[x][y].type = map[nx][ny].type;
                map[x][y].dir = map[nx][ny].dir;
                map[nx][ny].type = type;
                map[nx][ny].dir = dir;
            }
            
        }
    }
    
    private static boolean inRange(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
    
    private static boolean canSharkMove(int x, int y, Block[][] map) {
        int nx = x;
        int ny = y;
        
        while(inRange(nx,ny)) {
            if(map[nx][ny].type > 0)
                return true;
               
            nx += dx[map[x][y].dir]; 
            ny += dy[map[x][y].dir];
        }
        
        return false;
    }
}

class Block {
    int type; // 0 == 공백, -1 == 상어, 자연수 == 물고기
    int dir;
    
    Block(int type, int dir) {
        this.type = type;
        this.dir = dir;
    }
}