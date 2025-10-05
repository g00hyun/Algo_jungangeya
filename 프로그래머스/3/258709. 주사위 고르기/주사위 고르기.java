import java.util.*;

class Solution {
    Dice[] dices;
    boolean[] aDices;
    int maxWinCount;
    int[] answer;
    List<Integer> aCase = new ArrayList<>();
    List<Integer> bCase = new ArrayList<>();
    int[] nums;
    Dice[] aDice;
    Dice[] bDice;
    
    public int[] solution(int[][] dice) {
        dices = new Dice[dice.length];
        aDices = new boolean[dice.length];
        answer = new int[dice.length/2];
        nums = new int[dice.length/2];
        aDice = new Dice[dices.length/2];
        bDice = new Dice[dices.length/2];
        int idx = 0;
        for(int[] d : dice)
            dices[idx++] = new Dice(d);
        
        maxWinCount = -1;
        Backtracking(0,0);
        
        return answer;
    }
    
    private void bt(int cur) {
        if(cur == dices.length/2) {
            int aSum = 0; int bSum = 0;
            
            for(int i = 0; i<dices.length/2; i++) {
                aSum += aDice[i].num[nums[i]];
                bSum += bDice[i].num[nums[i]];
            }
            aCase.add(aSum);
            bCase.add(bSum);
            return;
        }
        
        for(int i = 0; i<6; i++) {
            nums[cur] = i;
            bt(cur+1);
        }
    }
    
    private void Backtracking(int cur, int start) {
        if(cur == dices.length/2) {
            int aidx = 0; int bidx = 0;
            for(int i = 0; i<dices.length; i++) {
                if(aDices[i]) aDice[aidx++] = dices[i];
                else bDice[bidx++] = dices[i];
            }
            
            aCase.clear(); bCase.clear();
            bt(0);
            
            aCase.sort((a,b) -> a-b);
            bCase.sort((a,b) -> a-b);
            
            aidx = bidx = 0;
            int count = 0;
            while(aidx < aCase.size() && bidx < bCase.size()) {
                int av = aCase.get(aidx);
                int bv = bCase.get(bidx);
                
                if(av > bv) {
                    count++;
                    bidx++;
                }
                else {
                    count += bidx;                   
                    aidx++;
                }
            }
            
            if(bidx == bCase.size())
                count += (aCase.size() - aidx) * bCase.size();
            
            if(count > maxWinCount) {
                maxWinCount = count;
                
                int ii = 0;
                for(int i = 0; i<dices.length; i++)
                    if(aDices[i])
                        answer[ii++] = i + 1;
            }
            
            return;
        }
        
        for(int i = start; i < dices.length; i++) {
            aDices[i] = true;
            Backtracking(cur + 1, i+1);
            aDices[i] = false;
        }
    }
    
//     private int Simulation() {
//         int winCount = 0;
        
//         Dice[] aDice = new Dice[dices.length/2];
//         Dice[] bDice = new Dice[dices.length/2];
        
//         int aidx = 0; int bidx = 0;
//         for(int i = 0; i<dices.length; i++) {
//             if(aDices[i]) aDice[aidx++] = dices[i];
//             else bDice[bidx++] = dices[i];
//         }
        
//         int aval; int bval;
//         for(int i = 0; i<60002; i++) {
//             aval = bval = 0;
            
//             for(Dice d : aDice)
//                 aval += d.roll();
            
//             for(Dice d : bDice)
//                 bval += d.roll();
            
//             if(aval > bval) winCount++;
//         }
        
//         return winCount;
//     }
}

class Dice {
    int[] num;
    
    Dice(int[] arr) {
        num = arr;
    }
    
    int roll() {
        Random r = new Random();
        int idx = r.nextInt(6);
        
        return num[idx];
    }
}