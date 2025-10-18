import java.util.*;

class Main {
    static int n;
    static int[][] map;
    
    static Queue<Pipe> q = new LinkedList<>();
    
    static int answer = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        map = new int[n][n];
        
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                map[i][j] = sc.nextInt();
                
        if(map[n-1][n-1] == 1) {
            System.out.println(0);
            return;
        }
                
        q.add(new Pipe(1, 0, 1));
        BFS();
        
        System.out.println(answer);
    }
    
    private static void BFS() {
        while(!q.isEmpty()) {
            Pipe p = q.poll();
            
            if(p.x == n-1 && p.y == n-1) answer++;
            
            insertPipe(p);
        }
    }
    
    private static void insertPipe(Pipe p) {
        int type = p.type;
        int x = p.x;
        int y = p.y;
        
        if(type == 1) {
            if(inRange(x, y+1) && map[x][y+1] == 0)
                q.add(new Pipe(1, x, y+1));
            
            if(inRange(x+1, y+1) && map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0)
                q.add(new Pipe(3, x+1, y+1));
        }
        else if(type == 2) {
            if(inRange(x+1, y) && map[x+1][y] == 0)
                q.add(new Pipe(2, x+1, y));
            
            if(inRange(x+1, y+1) && map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0)
                q.add(new Pipe(3, x+1, y+1));
            
        }
        else {
            if(inRange(x, y+1) && map[x][y+1] == 0)
                q.add(new Pipe(1, x, y+1));
            
            if(inRange(x+1, y) && map[x+1][y] == 0)
                q.add(new Pipe(2, x+1, y));
            
            if(inRange(x+1, y+1) && map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0)
                q.add(new Pipe(3, x+1, y+1));
        }
    }
    
    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}

class Pipe {
    int type;
    int x;
    int y;
    
    Pipe(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}