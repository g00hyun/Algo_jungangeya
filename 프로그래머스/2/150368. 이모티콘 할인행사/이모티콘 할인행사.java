import java.util.*;

class Solution {
    int[] discountRate;
    int[] discountPrice;
    int maxEPlusUser = 0;
    int maxEPriceTotal = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        discountRate = new int[emoticons.length];
        discountPrice = new int[emoticons.length];
        initDiscountRate();
        initDiscountPrice();
        
        Backtracking(0, emoticons, users);
        
        
        // Arrays.sort(users, 0, users.length, (a,b) -> b[0] - a[0]);
        
        // System.out.println(maxEPlusUser + " " + maxEPriceTotal);
        
        int[] answer = new int[2];
        answer[0] = maxEPlusUser;
        answer[1] = maxEPriceTotal;
        return answer;
    }
    
    private void Backtracking(int cur, int[] emoticons, int[][] users) {
        if(cur == discountPrice.length) {
            int ePlusUser = 0;
            int ePriceTotal = 0;
            
            initDiscountPrice();
            for(int i = 0; i<emoticons.length; i++)
                discountPrice[i] = emoticons[i] * (100 - discountRate[i]) / 100;
            
            
            for(int[] user : users) {
                int uRate = user[0];
                int uLimit = user[1];
                
                for(int i = 0; i<emoticons.length; i++)
                    if(uRate <= discountRate[i])
                        uLimit -= discountPrice[i];
                
                if(uLimit <= 0) ePlusUser++;
                else ePriceTotal += user[1] - uLimit;
            }
            
            // for(int i : discountPrice)
            //     System.out.print(i + " ");
            // System.out.print(" / ");
            // for(int i : discountRate)
            //     System.out.print(i + " ");
            // System.out.println();
            // System.out.println(ePlusUser + " " + ePriceTotal);
            
            
            if(ePlusUser > maxEPlusUser) {
                maxEPlusUser = ePlusUser;
                maxEPriceTotal = ePriceTotal;
            }
            
            if (ePlusUser == maxEPlusUser && ePriceTotal > maxEPriceTotal) {
                maxEPriceTotal = ePriceTotal;
            }
            
            return;
        }
        
        for(int i = 10; i<=40; i = i+10) {
            discountRate[cur] = i;
            Backtracking(cur+1, emoticons, users);
            discountRate[cur] = -1;
        }
    }
    
    private boolean isAllMatch() {
        for(int i : discountPrice)
            if(i == -1) return false;
        return true;
    }
    
    private void initDiscountRate() {
        for(int i = 0; i<discountRate.length; i++)
            discountRate[i] = -1;
    }
    
    private void initDiscountPrice() {
        for(int i = 0; i<discountPrice.length; i++)
            discountPrice[i] = -1;
    }
}