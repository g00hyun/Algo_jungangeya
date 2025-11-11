import java.util.*;

class Main {
    static int n;
    static Potion[] potions;
    static int[] order;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        potions = new Potion[n+1];
        order = new int[n];
        visited = new boolean[n];
        
        for(int i = 1; i<=n; i++)
            potions[i] = new Potion(i, sc.nextInt());
        
        for(int i = 1; i<=n; i++) {
            int k = sc.nextInt();
            while(k-- > 0) {
                int dnum = sc.nextInt();
                int value = sc.nextInt();
                
                potions[i].discounts.add(new int[]{dnum, value});
            }
        }
        
        Backtracking(0);
        
        System.out.print(answer);
    }
    
    private static int calcTotalPrice() {
        int result = 0;
        
        int[] prices = new int[n+1];
        boolean[] purchase = new boolean[n+1];
        
        for(int i = 1; i<=n; i++) {
            prices[i] = potions[i].price;
        }
        
        for(int o : order) {
            purchase[o] = true;
            
            for(int[] a : potions[o].discounts) {
                if(purchase[a[0]]) continue;
                
                if(prices[a[0]] - a[1] > 0)
                    prices[a[0]] -= a[1];
                else
                    prices[a[0]] = 1;
            }
        }
        
        for(int i = 1; i<=n; i++)
            result += prices[i];
        
        return result;
    }
    
    private static void Backtracking(int cnt) {
        if(cnt == n) {
            int price = calcTotalPrice();
            answer = Math.min(answer, price);
            
            return;
        }
        
        for(int i = 1; i<=n; i++) {
            if(visited[i-1]) continue;
            
            visited[i-1] = true;
            order[cnt] = i;
            Backtracking(cnt+1);
            order[cnt] = 0;
            visited[i-1] = false;
        }
    }
}

class Potion {
    int num;
    int price;
    List<int[]> discounts;
    
    Potion(int num, int price) {
        this.num = num;
        this.price = price;
        discounts = new ArrayList<>();
    }
}