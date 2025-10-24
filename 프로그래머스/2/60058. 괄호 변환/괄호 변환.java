class Solution {
    public String solution(String p) {
        return recursive(p);
    }
    
    private String recursive(String w) {
        if(w.length() == 0) return w;
        if(isCorrect(w)) return w;
        
        String u;
        String v;
        for(int i = 2; i<=w.length(); i=i+2) {
            u = w.substring(0, i);
            v = w.substring(i);
            
            if(!isBalance(u)) continue;
            
            if(isCorrect(u)) {
                return u + recursive(v);
            }
            else {
                String s = u.substring(1,u.length()-1);
                String ss = "";
                for(char c : s.toCharArray()) {
                    if(c == '(') ss += ')';
                    else ss += '(';
                }
                
                return "(" + recursive(v) + ")" + ss;
            }
        }
        
        return w;
    }
    
    private boolean isBalance(String s) {
        int cnt = 0;
        
        for(char i : s.toCharArray()) {
            if(i == '(') cnt++;
            else cnt--;
        }
        
        return cnt == 0 ? true : false;
    }
    
    private boolean isCorrect(String s) {
        int cnt = 0;
        
        for(char i : s.toCharArray()) {
            if(i == '(') cnt++;
            else cnt--;
            
            if(cnt < 0) return false;
        }
        
        return true;
    }
}