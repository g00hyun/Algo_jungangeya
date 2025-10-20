import java.util.*;

class Main {
    static int n;
    static int[] val;
    static int prove = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        val = new int[n];
        
        for(int i = 0; i<n; i++) {
            val[i] = sc.nextInt();
        }
        
        Arrays.sort(val);
        
        for(int i = 0; i<n; i++) {
            if(prove + 1 < val[i]) break;
            prove += val[i];
        }
        
        System.out.println(prove + 1);
    }
}