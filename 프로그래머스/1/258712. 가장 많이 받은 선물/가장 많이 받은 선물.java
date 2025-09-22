import java.util.*;

class Solution {
    int[][] logs = new int[50][50];
    int n;
    HashMap<String, Integer> friendsToIdx = new HashMap<>();
    int[] giftPoints = new int[50];
    int[] expectPoints = new int[50];
    
    public int solution(String[] friends, String[] gifts) {
        n = friends.length;
        
        for(int i = 0; i<n; i++)
            friendsToIdx.put(friends[i], i);
        
        // logs에 counting
        for(String gift : gifts) {
            String[] line = gift.split(" ");
            String from = line[0];
            String to = line[1];
            
            logs[friendsToIdx.get(from)][friendsToIdx.get(to)]++;
        }
        
        // 선물 지수 계산
        for(int i = 0; i<n; i++) {
            int give = 0;
            int given = 0;
            
            for(int a = 0; a<n; a++) {
                give += logs[i][a];
                given += logs[a][i];
            }
            
            giftPoints[i] = give - given;
        }
        
        
        // 다음달 예상 결과 계산 (선물 유무 -> 선물 지수 -> 없음)
        for(int i = 0; i<n; i++) {
            // System.out.println("[사람] => " + friends[i] + "의 차례");
            
            for(int j = 0; j<n; j++) {
                if(i == j) continue;
                
                if(logs[i][j] > logs[j][i]) {
                    // System.out.println("[상호 간 선물지수] =>" + friends[i] + "포인트가 올라갑니다");
                    expectPoints[i]++;
                    continue;
                }
                
                if(logs[i][j] < logs[j][i]) continue;
                
                if(giftPoints[i] > giftPoints[j]) {
                    // System.out.println("[선물 포인트] =>" + friends[i] + "포인트가 올라갑니다");
                    expectPoints[i]++;
                }
            }
        }
        
        int answer = 0;
        
        for(int i = 0; i<n; i++)
            answer = Math.max(answer, expectPoints[i]);
        
        return answer;
    }
}