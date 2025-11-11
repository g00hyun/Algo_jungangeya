import java.util.*;

class Main {
    static int k;
    static int[] comp;
    static int[] seq;
    static boolean[] visited = new boolean[10];
    static List<String> answers = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        comp = new int[k];
        seq = new int[k+1];
        
        Arrays.fill(seq, -1);
        
        for(int i = 0; i<k; i++) {
            char c = sc.next().charAt(0);
            if(c == '<') comp[i] = -1;
            else comp[i] = 1;
        }
        
        Backtracking(0);
        
        System.out.println(answers.get(answers.size() - 1));
        System.out.println(answers.get(0));
    }
    
    private static void Backtracking(int cnt) {
        if(cnt == k+1) {
            if(!isValid()) return;
            
            String s = "";
            for(int i = 0; i<=k; i++)
                s += seq[i];
            
            answers.add(s);
            
            return;
        }
        
        for(int i = 0; i<=9; i++) {
            if(visited[i]) continue;
            
            seq[cnt] = i;
            visited[i] = true;
            Backtracking(cnt + 1);
            visited[i] = false;
            seq[cnt] = -1;
        }
    }
    
    private static boolean isValid() {
        for(int i = 0; i<k; i++) {
            int diff = seq[i] - seq[i+1];
            if(diff * comp[i] < 0) return false;
        }
        return true;
    }
}