import java.util.*;
import java.io.*;

class Main {
    static int page;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            page = Integer.parseInt(br.readLine());
            if(page == 0) return;
            
            String[] line = br.readLine().split(",");
            int[] count = new int[page+1];
            
            Arrays.stream(line)
                .filter(v -> !v.contains("-"))
                .mapToInt(Integer::parseInt)
                .forEach(v -> {
                    if(v <= page)
                        count[v]++;
                });
                
            Arrays.stream(line)
                .filter(v -> v.contains("-"))
                .map(v -> {
                    int[] arr = new int[2];
                    String[] s = v.split("-");
                    
                    arr[0] = Integer.parseInt(s[0]);
                    arr[1] = Integer.parseInt(s[1]);
                    
                    return arr;
                })
                .filter(v -> v[0] <= v[1])
                .forEach(v -> {
                    for(int i = v[0]; i<=v[1]; i++)
                        if(i <= page)
                            count[i]++;
                });
            
            
            int answer = 0;
            for(int i : count)
                if(i > 0) answer ++;
            System.out.println(answer);
        }
        
        
    }
}