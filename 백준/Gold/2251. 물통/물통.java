import java.util.*;

class Main {
    static int[] size = new int[3];
    static Set<Snapshot> set = new HashSet<>();
    // static boolean[][][] visited;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i<3; i++)
            size[i] = sc.nextInt();
            
        // visited = new boolean[size[0] + 1][size[1] + 1][size[2] + 1];
            
        set.add(new Snapshot(0,0,size[2]));
        Backtracking(0,0,size[2]);
        
        List<Integer> answer = new ArrayList<>();
        for(Snapshot s : set)
            if(s.ss[0] == 0)
                answer.add(s.ss[2]);
        
        answer.sort((a,b) -> a-b);
        for(int i : answer)
            System.out.print(i + " ");
    }
    
    private static void Backtracking(int a, int b, int c) {
        int give;
        if(a > 0) {
            // a -> b
            give = size[1]-b >= a ? a : size[1] - b;
            if(!set.contains(new Snapshot(a-give, b+give, c))) { 
                set.add(new Snapshot(a-give, b+give, c));
                Backtracking(a-give, b+give, c);
            }
            
            
            // a -> c
            give = size[2]-c >= a ? a : size[2] - c;
            if(!set.contains(new Snapshot(a-give, b, c+give))) {
                set.add(new Snapshot(a-give, b, c+give));
                Backtracking(a-give, b, c+give);
            }
            
        }
        
        if(b > 0) {
            // b -> a
            give = size[0]-a >= b ? b : size[0] - a;
            if(!set.contains(new Snapshot(a+give, b-give, c))) {
                set.add(new Snapshot(a+give, b-give, c));
                Backtracking(a+give, b-give, c);
            }
            
            // b -> c
            give = size[2]-c >= b ? b : size[2] - c;
            if(!set.contains(new Snapshot(a, b-give, c+give))) {
                set.add(new Snapshot(a, b-give, c+give));
                Backtracking(a, b-give, c+give);
            }
        }
        
        if(c > 0) {
            // c -> a
            give = size[0]-a >= c ? c : size[0] - a;
            if(!set.contains(new Snapshot(a+give, b, c-give))) {
                set.add(new Snapshot(a+give, b, c-give));
                Backtracking(a+give, b, c-give);
            }
            
            
            // c -> b
            give = size[1]-b >= c ? c : size[1] - b;
            if(!set.contains(new Snapshot(a, b+give, c-give))) {
                set.add(new Snapshot(a, b+give, c-give));
                Backtracking(a, b+give, c-give);
            }
            
            
        }
        
    }
}

class Snapshot {
    int[] ss;
    
    Snapshot(int a, int b, int c) {
        this.ss = new int[3];
        this.ss[0] = a;
        this.ss[1] = b;
        this.ss[2] = c;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.ss[0] + " " + this.ss[1] + " " + this.ss[2]); 
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Snapshot)) {
            return false;
        }

        Snapshot s = (Snapshot) obj;
        
        String s1 = this.ss[0] + " " + this.ss[1] + " " + this.ss[2];
        String s2 = s.ss[0] + " " + s.ss[1] + " " + s.ss[2];
        return s1.equals(s2);
    }
}