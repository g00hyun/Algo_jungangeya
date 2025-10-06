import java.util.*;

class Solution {
    // Set<Integer> s = new HashSet<>();
    List<Long> l = new ArrayList<>();
    
    public int solution(int n, int k) {
        String reverseNum = "";
        while(n > 0) {
            int tmp = n % k;
            reverseNum += tmp;
            n /= k;
        }
        
        String knum = "";
        char[] carr = reverseNum.toCharArray();
        for(int i = reverseNum.length() - 1; i >= 0; i--)
            knum += carr[i];
        
        long[] arr = Arrays.stream(knum.split("0+")).mapToLong(Long::parseLong).toArray();
        
        for(long i : arr)
            if(isPrime(i))
                l.add(i);
        
//         int[] arr2 = Arrays.stream(knum.split("")).mapToInt(Integer::parseInt).toArray();
        
//         for(int i : arr)
//             if(isPrime(i))
//                 l.add(i);
        
        // for(int i : l)
        //     System.out.print(i + " ");
        
        return l.size();
    }
    
    private boolean isPrime(long val) {
        if(val == 1 || val == 0) return false;
        for(long i = 2; i*i <= val; i++)
            if(val % i == 0)
                return false;
        return true;
    }
}