import java.util.*;

class Main {
    static int n, target;
    static Queue<Pair> q = new LinkedList<>();
    static Integer[] order;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        
        while(testcase-- > 0) {
            n = sc.nextInt(); target = sc.nextInt();
            order = new Integer[n];
            
            for(int i = 0; i<n; i++) {
                int value = sc.nextInt();
                order[i] = value;
                q.add(new Pair(i, value));
            }
            
            Arrays.sort(order, (a,b) -> b-a);
            
            for(int level = 1; level<=n; level++) {
                int oIdx = level - 1;
                
                while(q.peek().value != order[oIdx]) {
                    Pair p = q.poll();
                    q.add(p);
                }
                
                Pair p = q.poll();
                if(p.num == target) {
                    System.out.println(level);
                    continue;
                }
            }
        }
    }
}

class Pair {
    int num;
    int value;
    
    Pair(int num, int value) {
        this.num = num;
        this.value = value;
    }
}