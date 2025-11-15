import java.util.*;

class Main {
    static int v,e;
    static int[] dist;
    static List<Node>[] graph;
    static int start;
    static PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.value - b.value);
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt(); e = sc.nextInt();
        dist = new int[v+1];
        graph = new ArrayList[v+1];
        
        for(int i = 1; i<=v; i++)
            graph[i] = new ArrayList<>();
        
        start = sc.nextInt();
        
        for(int i = 0; i<e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int value = sc.nextInt();
            
            graph[from].add(new Node(to, value));
        }
        
        Arrays.fill(dist, 100_000_000);
        
        dist[start] = 0;
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for(int i = 0; i<graph[cur.to].size(); i++) {
                Node next = graph[cur.to].get(i);
                if(cur.value + next.value < dist[next.to]) {
                    dist[next.to] = cur.value + next.value;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
        
        for(int i = 1; i<=v; i++)
            System.out.println(dist[i] == 100_000_000 ? "INF" : dist[i]);
    }
}

class Node {
    int to;
    int value;
    
    Node(int to, int value) {
        this.to = to;
        this.value = value;
    }
}