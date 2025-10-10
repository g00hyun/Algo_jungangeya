import java.util.*;

class Solution {
    int n;
    int[] nodeNum; // depth의 총 node 갯수
    // (child의 한 side의 갯수) == ((nodeNum[i] - 1) / 2)
    // long[] childNum; // depth의 한 side의 자식의 갯수
    Queue<Node> q = new LinkedList<>();
    boolean[] visited = new boolean[50 + 1];
    int[] answer;
    
    public int[] solution(long[] numbers) {
        n = numbers.length;
        nodeNum = new int[10 + 1];
        answer = new int[n];
        
        for(int i = 1; i<=10; i++)
            nodeNum[i] = (int)Math.pow(2, i) - 1;
        
        for(int i = 0; i<n; i++) {
            q.clear();
            Arrays.fill(visited, false);
            
            long num = numbers[i];
            String numB = toBinary(num);

            int depth = getDepth(numB.length());
            String fullTree = fillZeroBinary(depth, numB);
            // System.out.println(fullTree);

            int root = fullTree.length() / 2;

            q.add(new Node(root, depth));
            visited[root] = true;
            
            answer[i] = BFS(fullTree) ? 1 : 0; 
        }
        
        return answer;
    }
    
    private boolean BFS(String fullTree) {
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if(cur.depth < 2) continue;
            
            int sideNumByChild = getSideNumOfChild(cur.depth);
            int leftIdx = cur.idx - (sideNumByChild + 1);
            int rightIdx = cur.idx + (sideNumByChild + 1);
            
            char nodeVal = fullTree.charAt(cur.idx);
            char leftVal = fullTree.charAt(leftIdx);
            char rightVal = fullTree.charAt(rightIdx);
            // System.out.println(nodeVal + " " + leftVal + " " + rightVal);
            
            if((leftVal == '1' || rightVal == '1') && nodeVal == '0')
                return false;
            
            q.add(new Node(leftIdx, cur.depth-1));
            q.add(new Node(rightIdx, cur.depth-1));
            
        }
        
        return true;
    }
    
    private int getSideNumOfChild(int depth) {
        return nodeNum[depth-1] / 2;
    }
    
    private String fillZeroBinary(int depth, String s) {
        int restSize = nodeNum[depth] - s.length();
        String prefix = "";
        
        while(restSize-- > 0) prefix += "0";
        
        return prefix + s;
    }
    
    private int getDepth(int length) {
        for(int i = 1; i<=10; i++)
            if(length <= nodeNum[i])
                return i;
        return -1;
    }
    
    private String toBinary(long num) {
        String binary = "";
        while(num > 0) {
            long mod = num % 2;
            binary += Long.toString(mod);
            num /= 2;
        }
        String result = "";
        for(int i = binary.length() - 1; i >= 0; i--)
            result += binary.charAt(i);
        return result;
    }
}

class Node {
    int idx;
    int depth;
    
    Node(int idx, int depth) {
        this.idx = idx;
        this.depth = depth;
    }
}