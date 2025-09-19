import java.util.*;
import java.io.*;

/**
 * 1. 장애물 배치(3중 for문)
 * 2. 선생님 순찰
 */

class Main {
    static int n;
    static char[][] map = new char[6][6];
    static boolean isCatch = true;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i<n; i++) {
            char[] line = br.readLine().replace(" ", "").toCharArray();
            
            for(int j = 0; j<n; j++)
                map[i][j] = line[j];
        }
        
        for(int i = 0; i<n*n; i++) {
            int x1 = i / n;
            int y1 = i % n;
            if(map[x1][y1] != 'X') continue;
            map[x1][y1] = 'O';
            
            for(int j = 0; j<n*n; j++) {
                int x2 = j / n;
                int y2 = j % n;
                if(map[x2][y2] != 'X') continue;
                map[x2][y2] = 'O';
                
                for(int k = 0; k<n*n; k++) {
                    int x3 = k / n;
                    int y3 = k % n;
                    if(map[x3][y3] != 'X') continue;
                    map[x3][y3] = 'O';
                    
                    // T 순찰 로직
                    if(!gacha()) isCatch = false;
                    
                    // for(int x = 0; x<n; x++) {
                    //     for(int y = 0; y<n; y++)
                    //         System.out.print(map[x][y] + " ");
                    //     System.out.println();
                    // }
                    // System.out.println();
                    
                    map[x3][y3] = 'X';
                }
                
                map[x2][y2] = 'X';
            }
            
            map[x1][y1] = 'X';
        }
        
        if(!isCatch) System.out.println("YES");
        else System.out.println("NO");
        
        // for(int i = 0; i<n; i++) {
        //     for(int j = 0; j<n; j++)
        //         System.out.print(map[i][j] + " ");
        //     System.out.println();
        // }
                
    }
    
    private static boolean gacha() {
        boolean result = false;
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(map[i][j] == 'T') 
                    if(patrol(i,j)) result = true;
        
        return result;
    }
    
    private static boolean patrol(int x, int y) {
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        
        for(int i = 0; i<4; i++) {
            int nx = x;
            int ny = y;
            while(true) {
                nx += dx[i];
                ny += dy[i];
                
                if(!(0 <= nx && nx < n && 0 <= ny && ny < n)) break;
                if(map[nx][ny] == 'O') break;
                
                if(map[nx][ny] == 'S') return true;
            }
        }
        
        return false;
    }
}