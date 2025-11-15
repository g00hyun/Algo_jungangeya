import java.util.*;

class Main {
    static int n;
    static List<Node>[] graph;
    static boolean[] visited;
    static int answer = 0;
    
    static int now;
    static Set<Integer> list = new HashSet<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        
        for(int i = 0; i<=n; i++)
            graph[i] = new ArrayList<>();
        
        for(int i = 0; i<n-1; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int dist = sc.nextInt();
            
            graph[from].add(new Node(to,dist));
            graph[to].add(new Node(from,dist));
        }
        
        now = 1;
        while(!list.contains(now)) {
            int prev = now;
            list.add(prev);
            
            visited[prev] = true;
            Backtracking(prev, 0);
            visited[prev] = false;
        }
        
        System.out.println(answer);
    }
    
    private static void Backtracking(int cur, int dist) {
        // leaf
        if(graph[cur].size() == 1) {
            if(answer < dist) {
                answer = dist;
                now = cur;
            }
        }
        
        for(int i = 0; i<graph[cur].size(); i++) {
            int next = graph[cur].get(i).to;
            int ndist = graph[cur].get(i).dist;
            
            if(visited[next]) continue;
            
            visited[next] = true;
            Backtracking(next, dist + ndist);
            visited[next] = false;
        }
    }
}

class Node {
    int to, dist;
    
    Node(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
}