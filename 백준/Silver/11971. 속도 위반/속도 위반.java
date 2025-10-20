import java.util.*;

class Main {
    static int n,m;
    static int[] road = new int[100];
    static int[] driving = new int[100];
    static int maxDiff = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        
        int idx = 0;
        for(int i = 0; i<n; i++) {
            int km = sc.nextInt();
            int speed = sc.nextInt();
            
            while(km-- > 0)
                road[idx++] = speed;
        }
        
        idx = 0;
        for(int i = 0; i<m; i++) {
            int km = sc.nextInt();
            int speed = sc.nextInt();
            
            while(km-- > 0)
                driving[idx++] = speed;
        }
        
        for(int i = 0; i<100; i++)
            if(road[i] < driving[i])
                maxDiff = Math.max(maxDiff, driving[i] - road[i]);
                
        System.out.println(maxDiff);
    }
}