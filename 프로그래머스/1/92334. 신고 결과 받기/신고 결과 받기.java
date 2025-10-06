import java.util.*;

class Solution {
    int n;
    int[][] users;
    Map<String, Integer> userToIdx = new HashMap<>();
    int[] counts;
    int[] mails;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        n = id_list.length;
        users = new int[n][n];
        counts = new int[n];
        mails = new int[n];
        
        for(int i = 0; i<n; i++)
            userToIdx.put(id_list[i], i);
        
        for(String s : report) {
            String[] line = s.split(" ");
            int from = userToIdx.get(line[0]);
            int to = userToIdx.get(line[1]);
            
            users[to][from] = 1;
        }
        
        int idx = 0;
        for(int[] u : users) {
            int count = Arrays.stream(u).filter(v -> v == 1).toArray().length;
            counts[idx++] = count;
        }
        
        for(int i = 0; i<n; i++) 
            if(counts[i] >= k) 
                for(int j = 0; j<n; j++)
                    if(users[i][j] == 1)
                        mails[j]++;
        
        return mails;
    }
}