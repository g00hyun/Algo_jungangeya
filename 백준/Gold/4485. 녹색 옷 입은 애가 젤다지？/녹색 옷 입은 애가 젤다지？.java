import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int testcase = 1;
        while(n != 0) {
            // input
            int[][] map = new int[n][n];
            boolean[][] visited = new boolean[n][n];
            for(int i = 0; i<n; i++)
                for(int j = 0; j<n; j++)
                    map[i][j] = sc.nextInt();
            
            int answer = BFS(n, map, visited);
            System.out.println("Problem " + (testcase++) + ": " + answer);
            
            n = sc.nextInt();
        }
    }
    
    private static int BFS(int n, int[][] map, boolean[][] visited) {
        int result = Integer.MAX_VALUE;
        
        PriorityQueue<Pos> pq = new PriorityQueue<>((a,b) -> a.prefixSum - b.prefixSum);
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,1,-1};
        
        pq.add(new Pos(0,0,map[0][0]));
        visited[0][0] = true;
        
        while(!pq.isEmpty()) {
            Pos cur = pq.poll();
            
            if(cur.x == n-1 && cur.y == n-1) return cur.prefixSum;
            
            for(int i = 0; i<4; i++) {
                int nx = cur.x + dx[i]; int ny = cur.y + dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<n && !visited[nx][ny]) {
                    pq.add(new Pos(nx, ny, cur.prefixSum + map[nx][ny]));
                    visited[nx][ny] = true;
                }
            }
        }
        
        return result;
    }
}

class Pos {
    int x;
    int y;
    int prefixSum;
    
    Pos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.prefixSum = z;
    }
}