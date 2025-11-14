class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        // [][][0] : 왼->오 / [][][1] : 상->하
        int[][][] dp = new int[m][n][2];
        
        for(int j = 1; j<n; j++) {
            if(cityMap[0][j] == 1) break;
            dp[0][j][0] = 1;            
        }
        for(int i = 1; i<m; i++) {
            if(cityMap[i][0] == 1) break;
            dp[i][0][1] = 1;
        }
        
        for(int i = 1; i<m; i++) {
            for(int j = 1; j<n; j++) {
                if(cityMap[i][j] == 1) {
                    // dp[i][j][0] = 0;
                    // dp[i][j][1] = 0;
                    continue;
                }
                
                // 왼->오
                dp[i][j][0] += dp[i][j-1][0];
                if(cityMap[i][j-1] == 0)
                    dp[i][j][0] = (dp[i][j][0] + dp[i][j-1][1]) % MOD;
                
                // 상->하
                dp[i][j][1] += dp[i-1][j][1];
                if(cityMap[i-1][j] == 0)
                    dp[i][j][1] = (dp[i][j][1] + dp[i-1][j][0]) % MOD;                
            }
        }
        
        // for(long[][] c : dp) {
        //     for(int[] i : c)
        //         System.out.print((i[0] + i[1]) + " ");
        //     System.out.println();
        // }
        return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;
    }
}