import java.util.*;

class Solution {
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        Date todayDate = new Date(today);
        int idx = 1;
        for(String p : privacies) {
            Date date = new Date(p);
            char term = p.charAt(11);
            
            if(isExpiration(date, todayDate, convertToIntTerm(term, terms))) {
                answer.add(idx);
            }
            
            idx++;
        }
        
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private boolean isExpiration(Date target, Date today, int term) {
        target.month += term;
        while(target.month > 12) {
            target.month -= 12;
            target.year += 1;
        }
        
        System.out.println("[Expiration Date] => " + target.year + "." + target.month + "." + target.day);
        
        if(target.year < today.year)
            return true;
        
        if(target.year == today.year)
            if(target.month < today.month)
                return true;
        
        if(target.year == today.year)
            if(target.month == today.month)
                if(target.day <= today.day)
                    return true;
        
        return false;
    }
    
    private int convertToIntTerm(char t, String[] terms) {
        for(String term : terms) {
            char c = term.charAt(0);
            int i = Integer.parseInt(term.substring(2));
            
            if(c == t) return i;
        }
        return -1;
    }
}

class Date {
    int year;
    int month;
    int day;
    
    Date(String date) {
        this.year = Integer.parseInt(date.substring(0,4));
        this.month = Integer.parseInt(date.substring(5,7));
        this.day = Integer.parseInt(date.substring(8,10));
    }
}