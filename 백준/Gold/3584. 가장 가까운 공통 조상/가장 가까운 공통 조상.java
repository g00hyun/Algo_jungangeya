import java.util.*;

class Main {
    static int t;
    static int n;
    static Node[] nodes;
    static int n1,n2;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        
        while(t-- > 0) {
            n = sc.nextInt();
            nodes = new Node[n+1];
            for(int i = 1; i<=n; i++)
                nodes[i] = new Node(i, null);
            
            for(int i = 0; i<n-1; i++) {
                int p = sc.nextInt();
                int c = sc.nextInt();
                
                nodes[c].parent = nodes[p];
            }
            
            n1 = sc.nextInt();
            n2 = sc.nextInt();
            
            Node cur = nodes[n1];
            List<Integer> parents = new ArrayList<>();
            while(!Objects.isNull(cur)) {
                parents.add(cur.num);
                cur = cur.parent;
            }
            
            cur = nodes[n2];
            int sameParent = -1;
            // List<Integer> parents2 = new ArrayList<>();
            while(!Objects.isNull(cur) && sameParent == -1) {
                // parents2.add(cur.parent.num);
                for(int i : parents)
                    if(cur.num == i)
                        sameParent = i;
                cur = cur.parent;
            }
            
            System.out.println(sameParent);
        }
        
    }
}

class Node {
    int num;
    Node parent;
    
    Node(int num, Node p) {
        this.num = num;
        this.parent = p;
    }
}