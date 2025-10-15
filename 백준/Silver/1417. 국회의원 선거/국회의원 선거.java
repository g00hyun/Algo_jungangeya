import java.util.*;

class Main {
    static int n;
    static int dasom;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        dasom = sc.nextInt();
        for(int i = 0; i<n-1; i++)
            pq.add(sc.nextInt());
            
        int answer = 0;
        while(!Objects.isNull(pq.peek()) && dasom <= pq.peek()) {
            int popularity = pq.poll();
            pq.add(popularity-1);
            dasom++;
            answer++;
        }
        
        System.out.println(answer);
    }
}