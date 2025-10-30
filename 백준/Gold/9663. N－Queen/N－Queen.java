import java.util.*;

class Main {
    static int n;
    static int[][] available;
    static int answer = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        available = new int[n][n];
        
        Backtracking(0);
        System.out.println(answer);
        
    }
    
    private static void Backtracking(int row) {
        if(row == n) {
            answer++;
            // for(boolean[] b : available) {
            //     for(boolean i : b)
            //         System.out.print(i + " ");
            //     System.out.println();
            // }
            // System.out.println();
            return;
        }
        
        for(int j = 0; j<n; j++) {
            if(available[row][j] > 0) continue;
            
            masking(row, j);
            Backtracking(row + 1);
            unmasking(row, j);
        }
    }
    
    private static void masking(int x, int y) {
        available[x][y]--;
        // row
        for(int j = 0; j<n; j++)
            available[x][j]++;
        
        // col
        for(int i = 0; i<n; i++)
            available[i][y]++;
            
        // diagonal
        int cx = x; int cy = y;
        while(true) {
            cx += -1; cy += -1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]++;
        }
        
        cx = x; cy = y;
        while(true) {
            cx += -1; cy += 1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]++;
        }
        
        cx = x; cy = y;
        while(true) {
            cx += 1; cy += -1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]++;
        }
        
        cx = x; cy = y;
        while(true) {
            cx += 1; cy += 1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]++;
        }
    }
    
    private static void unmasking(int x, int y) {
        available[x][y]++;
        // row
        for(int j = 0; j<n; j++)
            available[x][j]--;
        
        // col
        for(int i = 0; i<n; i++)
            available[i][y]--;
            
        // diagonal
        int cx = x; int cy = y;
        while(true) {
            cx += -1; cy += -1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]--;
        }
        
        cx = x; cy = y;
        while(true) {
            cx += -1; cy += 1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]--;
        }
        
        cx = x; cy = y;
        while(true) {
            cx += 1; cy += -1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]--;
        }
        
        cx = x; cy = y;
        while(true) {
            cx += 1; cy += 1;
            
            if(!(0 <= cx && cx < n && 0 <= cy && cy < n)) break;
            
            available[cx][cy]--;
        }
    }
}