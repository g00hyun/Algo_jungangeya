import java.util.*;

class Main {
    static int total;
    static int s, e;
    static int u, d;
    static int[] cnt;
    static Queue<Integer> q = new LinkedList<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        total = sc.nextInt();
        s = sc.nextInt();
        e = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();
        
        cnt = new int[total+1];
        Arrays.fill(cnt, -1);
        
        cnt[s] = 0;
        q.add(s);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            int next1 = cur + u;
            int next2 = cur - d;
            
            if(1 <= next1 && next1 <= total && cnt[next1] == -1) {
                cnt[next1] = cnt[cur] + 1;
                q.add(next1);
            }
            
            if(1 <= next2 && next2 <= total && cnt[next2] == -1) {
                cnt[next2] = cnt[cur] + 1;
                q.add(next2);
            }
        }
        
        if(cnt[e] == -1) {
            System.out.println("use the stairs");
        }
        else
            System.out.println(cnt[e]);
    }
}