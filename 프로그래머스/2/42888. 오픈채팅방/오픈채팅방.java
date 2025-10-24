import java.util.*;

class Solution {
    Map<String, String> nmap = new HashMap<>();
    
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        
        for(String r : record) {
            String[] line = r.split(" ");
            
            String type = line[0];
            String uid = line[1];
            
            if(type.equals("Enter")) {
                // if(nmap.containsKey(uid))
                //     nmap.set(uid, line[2]);
                nmap.put(uid, line[2]);
            }
            else if(type.equals("Change"))
                nmap.put(uid, line[2]);
        }
        
        for(String r : record) {
            String[] line = r.split(" ");
            
            String type = line[0];
            String uid = line[1];
            
            if(type.equals("Enter")) {
                answer.add(nmap.get(uid) + "님이 들어왔습니다.");
            }
            else if(type.equals("Leave")) {
                answer.add(nmap.get(uid) + "님이 나갔습니다.");
            }
        }
        
        
        return answer.stream().toArray(String[]::new);
    }
}