import java.util.*;

class Main {
    static int n;
    static int[] list;
    
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        list = new int[n];
        for(int i = 0; i<n; i++)
            list[i] = sc.nextInt();
            
        for(int i : list)
            pq.add(i);
        
        int answer = 0;
        while(pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            
            answer += a + b;
            
            pq.add(a+b);
        }
        
        System.out.println(answer);
        
    }
}