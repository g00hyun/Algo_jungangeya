import java.util.*;

class Main {
    static TreeMap<String, Integer> map = new TreeMap<>();
    static int n;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        while(n-- > 0) {
            String[] line = sc.next().split("\\.");
            String key = line[line.length - 1];
            
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        map.forEach((k,v) -> {
            System.out.println(k + " " + v);
        });
    }
}