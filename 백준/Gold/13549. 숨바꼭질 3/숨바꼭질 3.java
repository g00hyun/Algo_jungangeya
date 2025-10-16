import java.util.*;

class Main {
    static int n,k;
    static Queue<Integer> q = new LinkedList<>();
    static int[] count = new int[100001];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        k = sc.nextInt();
        
        Arrays.fill(count, -1);
        
        count[n] = 0;
        int a = 0;
        while(count[k] == -1) {
            for(int i = 0; i<=100000; i++)
                if(count[i] != -1)
                    q.add(i);
            BFS();
        }
        
        System.out.println(count[k]);
    }
    
    private static void BFS() {
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            int tmp = cur;
            while(tmp != 0 && tmp * 2 <= 100000) {
                tmp *= 2;
                if(count[tmp] == -1)
                    count[tmp] = count[cur];
                
                if(count[tmp] > count[cur])
                    count[tmp] = count[cur];
            }
            
            int next;
            
            next = cur - 1;
            if(0 <= next) {
                if(count[next] == -1) {
                    count[next] = count[cur] + 1;
                    q.add(next);
                }
                
                if(count[next] > count[cur] + 1) {
                    count[next] = count[cur] + 1;
                    q.add(next);
                }
            }
            
            next = cur + 1;
            if(next <= 100000) {
                
                if(count[next] == -1) {
                    count[next] = count[cur] + 1;
                    q.add(next);
                }
                    
                if(count[next] > count[cur] + 1) {
                    count[next] = count[cur] + 1;
                    q.add(next);
                }
            }
            
        }
    }
}