class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // O(50억)
        long answer = 0;
        
        int dIdx = nextPoint(deliveries, deliveries.length - 1);
        int pIdx = nextPoint(pickups, pickups.length - 1);
        
        while(true) {
            dIdx = nextPoint(deliveries, dIdx);
            pIdx = nextPoint(pickups, pIdx);
            
            if(dIdx == -1 && pIdx == -1) break;
            
            int distance = Math.max(dIdx, pIdx) + 1;
            answer += distance * 2;
            
            int dqu = 0;
            for(int i = dIdx; i >= 0; i--) {
                dqu += deliveries[i];
                deliveries[i] = 0;
                
                int diff = 0;
                if(dqu > cap)
                    diff = dqu - cap;
                dqu -= diff;
                deliveries[i] += diff;
                
                if(dqu == cap) break;
            }
            
            int pqu = 0;
            for(int i = pIdx; i >= 0; i--) {
                pqu += pickups[i];
                pickups[i] = 0;
                
                int diff = 0;
                if(pqu > cap)
                    diff = pqu - cap;
                pqu -= diff;
                pickups[i] += diff;
                
                if(pqu == cap) break;
            }
        }
        
        return answer;
    }
    
    private int nextPoint(int[] list, int idx) {
        for(int i = idx; i>=0; i--)
            if(list[i] > 0) return i;
        return -1;
    }
}