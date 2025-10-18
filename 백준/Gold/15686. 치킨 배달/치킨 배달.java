import java.util.*;

class Main {
    static int n, m;
    
    static List<Pos> house = new ArrayList<>();
    static List<Pos> chicken = new ArrayList<>();
    
    static boolean[] isUsed;
    static int maxChickenDist = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++) {
                int num = sc.nextInt();
                if(num == 1) house.add(new Pos(i,j));
                if(num == 2) chicken.add(new Pos(i,j));
            }
            
        isUsed = new boolean[chicken.size()];
        
        Backtracking(0, 0);
        
        System.out.println(maxChickenDist);
    }
    
    private static void Backtracking(int cur, int start) {
        if(cur == m) {
            int chickenDist = house.stream().map(v -> {
                int minVal = Integer.MAX_VALUE;
                
                for(int i = 0; i<chicken.size(); i++)
                    if(isUsed[i])
                        minVal = Math.min(minVal, chicken.get(i).getDistance(v));
                
                return minVal;
            }).reduce(0, (a,b) -> a+b);
            
            maxChickenDist = Math.min(maxChickenDist, chickenDist);
            
            return;
        }
        
        for(int i = start; i<chicken.size(); i++) {
            isUsed[i] = true;
            Backtracking(cur + 1, i + 1);
            isUsed[i] = false;
        }
    }
}

class Pos {
    int x;
    int y;
    
    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    int getDistance(Pos p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }
}