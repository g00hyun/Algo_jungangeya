import java.util.*;

class Main {
    static int n;
    static int[][] arr;
    static int[][] dp;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][3];
        dp = new int[n][3];
        
        for(int i = 0; i<n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];
        
        for(int i = 1; i<n; i++) {
            for(int j = 0; j<3; j++) {
                int minval = Integer.MAX_VALUE;
                for(int a = 0; a<3; a++)
                    if(a != j) minval = Math.min(minval, dp[i-1][a]);
                
                dp[i][j] = arr[i][j] + minval;
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i<3; i++)
            answer = Math.min(answer, dp[n-1][i]);
            
        System.out.println(answer);
    }
}