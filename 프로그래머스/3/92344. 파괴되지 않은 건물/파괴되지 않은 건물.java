class Solution {
    int[][] board;
    int[][] skill;
    int[][] prefixSum;
    
    public int solution(int[][] board, int[][] skill) {
        this.board = board;
        this.skill = skill;
        
        prefixSum = new int[board.length][board[0].length];
        
        for(int i = 0; i<skill.length; i++) {
            int[] s = skill[i];
            
            int type = s[0];
            int r1 = s[1]; int c1 = s[2];
            int r2 = s[3]; int c2 = s[4];
            int degree = s[5];
            
            if(type == 1) {
                prefixSum[r1][c1] -= degree;
                
                if(c2+1 < board[0].length)
                    prefixSum[r1][c2+1] += degree;
                if(r2+1 < board.length)
                    prefixSum[r2+1][c1] += degree;
                if(c2+1 < board[0].length && r2+1 < board.length)
                    prefixSum[r2+1][c2+1] -= degree;
            }
            else {
                prefixSum[r1][c1] += degree;
                
                if(c2+1 < board[0].length)
                    prefixSum[r1][c2+1] -= degree;
                if(r2+1 < board.length)
                    prefixSum[r2+1][c1] -= degree;
                if(c2+1 < board[0].length && r2+1 < board.length)
                    prefixSum[r2+1][c2+1] += degree;
            }
        }
        
        // right
        for(int i = 0; i<board.length; i++) {
            for(int j = 1; j<board[0].length; j++) {
                prefixSum[i][j] += prefixSum[i][j-1];
            }
        }
        
        // down
        for(int j = 0; j<board[0].length; j++) {
            for(int i = 1; i<board.length; i++) {
                prefixSum[i][j] += prefixSum[i-1][j];
            }
        }
        
        // for(int[] b : prefixSum) {
        //     for(int i : b)
        //         System.out.print(i + " ");
        //         // if(i > 0) answer++;
        //     System.out.println();
        // }
        
        for(int i = 0; i<board.length; i++)
            for(int j = 0; j<board[0].length; j++)
                board[i][j] += prefixSum[i][j];
        
        int answer = 0;
        
        for(int[] b : board)
            for(int i : b)
                if(i > 0) answer++;
                    
        
        return answer;
    }
}