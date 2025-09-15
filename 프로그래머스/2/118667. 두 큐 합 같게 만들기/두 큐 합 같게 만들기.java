class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int qLen = queue1.length + queue2.length;
        int[] queue = new int[qLen];
        for(int i = 0; i<queue1.length; i++)
            queue[i] = queue1[i];
        for(int i = queue1.length; i<qLen; i++)
            queue[i] = queue2[i - queue1.length];
        
        Long total = 0L;
        for(int i : queue) total += i;
        
        if(total % 2 == 1) return -1;
        
        int a = 0;
        int b = queue1.length;
        int time = 0;
        Long sumAtoB = 0L;
        for(int i = a; i<b; i++)
            sumAtoB += queue[i];
        while(b < qLen && a < b) {
            // Long sumAtoB = 0L;
            // for(int i = a; i<b; i++)
            //     sumAtoB += queue[i];
            
            
            if(sumAtoB == total/2) return time;
            
            if(sumAtoB < total/2) sumAtoB += queue[b++];
            else sumAtoB -= queue[a++];
            
            time++;
        }
        
        return -1;
    }
}





// import java.util.*;

// class Solution {
//     public int solution(int[] queue1, int[] queue2) {
//         Queue<Integer> q1 = new LinkedList<>();
//         Queue<Integer> q2 = new LinkedList<>();
        
//         Long total = 0L;
//         for(int i = 0; i<queue1.length; i++) {
//             q1.add(queue1[i]);
//             q2.add(queue2[i]);
//             total += queue1[i] + queue2[i];
//         }
        
//         if(total % 2 == 1) return -1;
        
//         boolean flag = false;
//         int time = 0;
//         while(!flag || !isInit(q1, q2, queue1, queue2)) {
//             flag = true;
//             int sum1 = sumQueue(q1);
//             int sum2 = sumQueue(q2);
            
//             if(sum1 == sum2) return time;
            
//             if(q1.size() == 0) return -1;
//             if(q2.size() == 0) return -1;
            
//             int target;
//             if(sum1 > sum2) {
//                 target = q1.poll();
//                 q2.add(target);
//             }
//             else {
//                 target = q2.poll();
//                 q1.add(target);
//             }
//             time++;
                
//         }
        
//         return -1;
//     }
    
//     private int sumQueue(Queue<Integer> q) {
//         int sum = 0;
//         for(Integer i : q)
//             sum += i;
//         return sum;
//     }
    
//     private boolean isInit(Queue<Integer> q1, Queue<Integer> q2, int[] oldQ1, int[] oldQ2) {
//         if(q1.size() != oldQ1.length) return false;
//         if(q2.size() != oldQ2.length) return false;
        
//         int idx = 0;
//         for(Integer i : q1)
//             if(i != oldQ1[idx++]) return false;
        
//         idx = 0;
//         for(Integer i : q2)
//             if(i != oldQ2[idx++]) return false;
        
//         return true;
//     }
// }