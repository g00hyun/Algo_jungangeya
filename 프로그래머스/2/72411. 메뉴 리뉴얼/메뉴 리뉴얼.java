import java.util.*;

class Solution {
    Map<String, Integer> comb = new HashMap<>();
    List<Character> q = new ArrayList<>();
    List<String> answer = new ArrayList<>();
    
    public String[] solution(String[] orders, int[] course) {
        for(String o : orders) {
            int len = o.length();
            
            for(int limit = len; limit>=2; limit--)
                Backtracking(0, 0, limit, o);
        }
        
        int[] cmaxval = new int[course.length];
        
        for(int i = 0; i<course.length; i++) {
            int len = course[i];
            int maxval = 0;
            
            Set<String> ks = comb.keySet();
            for(String k : ks) {
                int v = comb.get(k);
                if(k.length() == len && v >= 2)
                    maxval = Math.max(maxval, v);
            }
            
            cmaxval[i] = maxval;
        }
        
        for(int i = 0; i<course.length; i++) {
            int len = course[i];
            int val = cmaxval[i];
            
            Set<String> ks = comb.keySet();
            for(String k : ks) {
                int v = comb.get(k);
                if(k.length() == len && v == val)
                    answer.add(k);
            }
        }
        
        answer.sort((a,b) -> a.compareTo(b));
        
        // comb.forEach((k,v) -> System.out.println(k + " " + v));
        
        return answer.stream().toArray(String[]::new);
    }
    
    private void Backtracking(int cur, int depth, int limit, String target) {
        if(depth == limit) {
            String tmp = "";
            for(char s : q)
                tmp += s;
            
            char[] tmparr = tmp.toCharArray();
            Arrays.sort(tmparr);
            tmp = new String(tmparr);
            
//             Set<String> ks = comb.keySet();
            
//             boolean flag = false;
//             for(String k : ks) {
//                 int v = comb.get(k);
//                 if(isInclude(k,tmp) && v >= 2) flag = true;
//             }
            
//             if(flag) return;
            
            comb.put(tmp, comb.getOrDefault(tmp, 0) + 1);
            
            return;
        }
        
        for(int i = cur; i<target.length(); i++) {
            q.add(target.charAt(i));
            Backtracking(i + 1, depth + 1, limit, target);
            q.remove(q.size() - 1);
        }
    }
    
    // private boolean isInclude(String s, String target) {
    //     for(char c : target.toCharArray()) {
    //         if(s.indexOf(c) == -1) return false;
    //     }
    //     return true;
    // }
}