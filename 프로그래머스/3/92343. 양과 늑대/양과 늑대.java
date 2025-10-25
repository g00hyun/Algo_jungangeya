import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] info;
    int answer = 1;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        initAdj(edges);
        
        // 0 삽입
        int next = 0;
        List<Integer> nextList = new ArrayList<>();
        for(int i : adj[next])
            nextList.add(i);
        Backtracking(0, 1, 0, nextList);
        
        return answer;
    }
    
    private void Backtracking(int cur, int sheep, int wolf, List<Integer> nextList) {
        for(int next : nextList) {
            int s = sheep;
            int w = wolf;
            
            if(info[next] == 0) s++;
            else w++;
            
            if(s <= w) continue;
            
            if(s > answer) answer = s;
            
            List<Integer> nnextList = new ArrayList<>();
            for(int n : nextList)
                if(n != next)
                    nnextList.add(n);
            
            for(int n : adj[next])
                nnextList.add(n);
            
            Backtracking(next, s, w, nnextList);
        }
    }
    
    private void initAdj(int[][] edges) {
        adj = new ArrayList[info.length];
        for(int i = 0; i<info.length; i++)
            adj[i] = new ArrayList<>();
        
        for(int[] e : edges) {
            int n1 = e[0]; int n2 = e[1];
            
            adj[n1].add(n2);
        }
    }
}