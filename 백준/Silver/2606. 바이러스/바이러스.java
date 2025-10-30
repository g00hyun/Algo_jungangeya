import java.util.*;

class Main {
    static int n, e;
    static List<Integer>[] graph;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        e = sc.nextInt();
        
        graph = new ArrayList[n+1];
        for(int i = 0; i<=n; i++)
            graph[i] = new ArrayList<>();
        
        while(e-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        visited = new boolean[n+1];
        
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int i = 0; i<graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                
                if(visited[next]) continue;
                
                q.add(next);
                visited[next] = true;
            }
        }
        
        int answer = 0;
        for(int i = 2; i<=n; i++)
            if(visited[i]) answer++;
            
        System.out.print(answer);
        
    }
}