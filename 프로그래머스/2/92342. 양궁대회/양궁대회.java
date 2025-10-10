// 양궁 n 번 쏘기
// (라이언이 k점에 쏜 화살 갯수) > (어피치가 k점에 쏜 화살 갯수) 이면, 라이언이 k점 획득
// k점에 둘다 화살을 못맞췄으면, 무시

// (라이언 점수 합산) > (어피치 점수 합산) 이면, 라이언 우승

// 점수 합산이 최대가 되도록,
// 동일하다면 가장 낮은 점수를 더 많이 맞힌 경우 == 가장 이득 보며 화살 쏘기

class Solution {
    int n;
    int[] apeach;
    int[] ryan;
    int maxDiff = Integer.MIN_VALUE;
    int[] answer = new int[11];
    
    int[] wrong = {-1};
    
    
    public int[] solution(int n, int[] info) {
        this.n = n;
        apeach = info;
        ryan = new int[11];
        
        Backtracking(0, n);
        
        if(maxDiff == Integer.MIN_VALUE) return wrong;
        return answer;
    }
    
    private void Backtracking(int cnt, int left) {
        if(cnt == 11) {
            ryan[10] = left;
            
            int rpoint = 0;
            int apoint = 0;
            for(int i = 0; i<=10; i++) {
                if(ryan[i] == 0 && apeach[i] == 0) continue;
                
                if(ryan[i] > apeach[i]) rpoint += 10-i;
                else apoint += 10-i;
            }
            
            int diff = rpoint - apoint;
            
            // if(diff > 0) {
            //     for(int i : ryan)
            //         System.out.print(i + " ");
            //     System.out.println(" => " + diff);
            // }
            
            if(diff > 0 && maxDiff <= diff) {
                if(maxDiff == diff && !isLower()) return;
                
                maxDiff = diff;
                int idx = 0;
                for(int i : ryan)
                    answer[idx++] = i;
            }
            
            return;
        }
        
        int minGet = apeach[cnt] + 1;
        if(minGet <= left) {
            ryan[cnt] = minGet;
            Backtracking(cnt+1, left - minGet);
            ryan[cnt] = 0;
        }
        
        Backtracking(cnt+1, left);
    }
    
    private boolean isLower() {
        for(int i = 10; i>=0; i--) {
            if(answer[i] == ryan[i]) continue;
            
            if(answer[i] < ryan[i]) return true;
            else return false;
        }
        return false;
    }
}