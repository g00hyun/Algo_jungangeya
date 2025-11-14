import java.util.*;

class Main {
    static int n;
    static int[] arr;
    static int[] tmp;
    static HashMap<Integer, Integer> count = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        tmp = new int[n];
        
        for(int i = 0; i<n; i++)
            arr[i] = sc.nextInt();
            
        for(int i = 0; i<n; i++)
            tmp[i] = arr[i];
            
        Arrays.sort(tmp);
            
        int num = 0;
        int prev = Integer.MIN_VALUE;
        for(int i : tmp) {
            if(prev != i)
                count.put(i, num++);
            prev = i;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i : arr)
            sb.append(count.get(i)).append(" ");
            
        System.out.println(sb);
    }
}