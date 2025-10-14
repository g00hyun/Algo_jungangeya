// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*;

class Main {
    static int n;
    static int[][] arr;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        arr = new int[n*2][2];
        for(int i = 0; i<n*2; i = i+2) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = 1;
            
            arr[i+1][0] = sc.nextInt();
            arr[i+1][1] = -1;
        }
        
        Arrays.sort(arr, (a,b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        
        int cnt = 0;
        int answer = -1;
        for(int[] i : arr) {
            cnt += i[1];
            
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
}