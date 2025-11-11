import java.util.*;

class Main {
    static int n,m;
    static List<Integer>[] graph;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited;
    static int answer = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        
        for(int i = 0; i<=n; i++)
            graph[i] = new ArrayList<>();
            
        while(m-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        for(int i = 1; i<=n; i++) {
            if(!visited[i]){
                BFS(i);
                answer++;
            }
        }
        
        System.out.println(answer);
    }
    
    private static void BFS(int node) {
        visited[node] = true;
        q.add(node);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int next : graph[cur]) {
                if(visited[next]) continue;
                
                visited[next] = true;
                q.add(next);
            }
        }
    }
}