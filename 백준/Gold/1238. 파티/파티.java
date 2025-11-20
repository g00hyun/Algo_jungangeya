import java.util.*;
import java.io.*;

class Main {
    static int n,m,t;
    static List<Pos>[] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1]; t = line[2];
        graph = new ArrayList[n+1];
        for(int i = 1; i<=n; i++)
            graph[i] = new ArrayList<>();
        
        for(int i = 0; i<m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            
            graph[line[0]].add(new Pos(line[1], line[2]));
        }
        
        int answer = -1;
        for(int i = 1; i<=n; i++) {
            int htop = BFS(i,t);
            int ptoh = BFS(t,i);
            
            answer = Math.max(answer, htop + ptoh);
        }
        System.out.println(answer);
    }
    
    private static int BFS(int start, int end) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, 100_000_000);
        
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(start, 0));
        dist[start] = 0;
        
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            int prefixDist = cur.weight;
            
            if(prefixDist > dist[cur.to]) continue;
            
            for(int i = 0; i<graph[cur.to].size(); i++) {
                Pos next = graph[cur.to].get(i);
                if(prefixDist + next.weight < dist[next.to]) {
                    dist[next.to] = prefixDist + next.weight;
                    q.add(new Pos(next.to, prefixDist + next.weight));
                }
            }
        }
        
        return dist[end];
    }
}

class Pos {
    int to;
    int weight;
    Pos(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}