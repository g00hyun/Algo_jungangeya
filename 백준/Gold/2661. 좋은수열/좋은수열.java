import java.util.*;

class Main {
    static int n;
    static List<Integer> answer = new ArrayList<>();
    static boolean flag = false;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        
        Backtracking(0);
        
        for(int i : answer)
            System.out.print(i);
    }
    
    private static void Backtracking(int cur) {
        if(cur == n) {
            flag = true;
            return;
        }
        
        for(int i = 1; i<=3; i++) {
            answer.add(i);
            if(isGoodSeq()) {
                Backtracking(cur+1);
            }
            if(flag) return;
            answer.remove(answer.size() - 1);
        }
    }
    
    private static boolean isGoodSeq() {
        for(int i = 1; i<=answer.size() / 2; i++) {
            String s1 = "";
            // side 1
            for(int j = answer.size()-1; j > answer.size() - i - 1; j--) {
                s1 += answer.get(j);
            }
            
            String s2 = "";
            // side 2
            for(int j = answer.size() - i - 1; j > answer.size() - i*2 - 1; j--) {
                s2 += answer.get(j);
            }
            
            if(s1.equals(s2))
                return false;
        }
        return true;
    }
}