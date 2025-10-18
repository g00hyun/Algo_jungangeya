import java.util.*;

class Main {
    static long MOD = 1000000007;
    static Map<Long, Long> memo = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        
        memo.put(0l, 0l);
        memo.put(1l, 1l);
        memo.put(2l, 1l);
        
        System.out.println(fibo(n));
    }
    
    private static long fibo(long n) {
        if(memo.containsKey(n)) return memo.get(n);
        
        long k = n / 2;
        
        if (n % 2 == 0) {
            // F(2k) = F(k) * [2*F(k+1) - F(k)]
            long fk = fibo(k);
            long fk1 = fibo(k + 1);
            
            long result = (fk * ((2 * fk1 - fk + MOD) % MOD)) % MOD;
            memo.put(n, result);
            return result;
        } else {
            // F(2k+1) = F(k+1)^2 + F(k)^2
            long fk = fibo(k);
            long fk1 = fibo(k + 1);
            
            long result = ((fk1 * fk1) % MOD + (fk * fk) % MOD) % MOD;
            memo.put(n, result);
            return result;
        }
    }
}