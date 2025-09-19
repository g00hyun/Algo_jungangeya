import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int[][] map = new int[1000][1000];
    static int[][] result = new int[1000][1000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i<n; i++) {
            char[] line = br.readLine().toCharArray();
            
            for(int j = 0; j<n; j++)
                map[i][j] = (line[j] == '.') ? 0 : Character.getNumericValue(line[j]);
        }
        
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                if(map[i][j] == 0)
                    result[i][j] = mineCount(i,j);
                else
                    result[i][j] = -1;
            }
        }
        
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                if(result[i][j] == -1)
                    System.out.print('*');
                else if(result[i][j] >= 10)
                    System.out.print('M');
                else
                    System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }
    
    private static int mineCount(int x, int y) {
        int[] dx = {0,0,1,-1,1,1,-1,-1};
        int[] dy = {1,-1,0,0,1,-1,1,-1};
        int count = 0;
        
        for(int d = 0; d<8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(0 <= nx && nx < n && 0 <= ny && ny < n)
                count += map[nx][ny];
        }
        
        return count;
    }
}