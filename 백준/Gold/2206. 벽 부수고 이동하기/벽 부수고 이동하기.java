import java.util.*;

class Main {
    static int n,m;
    static int[][] smap;
    static int[][] scount;
    static Queue<Integer> xq = new LinkedList<>();
    static Queue<Integer> yq = new LinkedList<>();
    static int[][] emap;
    static int[][] ecount;
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        
        smap = new int[n][m];
        scount = new int[n][m];
        emap = new int[n][m];
        ecount = new int[n][m];
        
        for(int i = 0; i<n; i++) {
            char[] line = sc.next().toCharArray();
            for(int j = 0; j<m; j++) {
                smap[i][j] = line[j] - '0';
                emap[i][j] = line[j] - '0';
            }
        }
        
        stowBFS(0,0);
        etowBFS(n-1,m-1);
        
        int answer;
        if(scount[n-1][m-1] == 0) answer = 100_000_000;
        else answer = scount[n-1][m-1];
        
        for(int i = 0; i<n; i++)
            for(int j = 0; j<m; j++)
                if(smap[i][j] == 1 && emap[i][j] == 1 && scount[i][j] > 0 && ecount[i][j] > 0)
                    answer = Math.min(answer, scount[i][j] + ecount[i][j] - 1);
        
        if(answer == 100_000_000) System.out.println(-1);
        else System.out.println(answer);
    }
    
    private static void stowBFS(int x, int y) {
        xq.add(x); yq.add(y);
        scount[x][y]++;
        
        while(!xq.isEmpty() && !yq.isEmpty()) {
            int cx = xq.poll(); int cy = yq.poll();
            
            if(smap[cx][cy] == 1) continue;
            
            for(int i = 0; i<4; i++) {
                int nx = cx + dx[i]; int ny = cy + dy[i];
                
                if(InRange(nx,ny) && scount[nx][ny] == 0) {
                    xq.add(nx); yq.add(ny);
                    scount[nx][ny] = scount[cx][cy] + 1;
                }
            }
        }
    }
    
    private static void etowBFS(int x, int y) {
        xq.add(x); yq.add(y);
        ecount[x][y]++;
        
        while(!xq.isEmpty() && !yq.isEmpty()) {
            int cx = xq.poll(); int cy = yq.poll();
            
            if(emap[cx][cy] == 1) continue;
            
            for(int i = 0; i<4; i++) {
                int nx = cx + dx[i]; int ny = cy + dy[i];
                
                if(InRange(nx,ny) && ecount[nx][ny] == 0) {
                    xq.add(nx); yq.add(ny);
                    ecount[nx][ny] = ecount[cx][cy] + 1;
                }
            }
        }
    }
    
    private static boolean InRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}