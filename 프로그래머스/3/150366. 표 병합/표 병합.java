import java.util.*;

class Solution {
    String[][] map = new String[51][51];
    String[][] parent = new String[51][51];
    List<String> answer = new ArrayList<>();
    
    public String[] solution(String[] commands) {
        for(int i = 1; i<=50; i++)
            for(int j = 1; j<=50; j++)
                parent[i][j] = i + " " + j;
        
//         merge(2,1,2,2);
//         merge(3,3,4,4);
//         merge(2,2,4,4);
//         merge(1,1,2,1);
        
        
        
        for(String c : commands) {
            String[] line = c.split(" ");
            
            String command = line[0];
            
            if(command.equals("UPDATE") && line.length == 4) update(Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[3]);
            else if(command.equals("UPDATE") && line.length == 3) update(line[1], line[2]);
            else if(command.equals("MERGE")) merge(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            else if(command.equals("UNMERGE")) unmerge(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            else if(command.equals("PRINT")) print(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
        }
        
        for(int i = 1; i<=5; i++) {
            for(int j = 1; j<=5; j++)
                System.out.print(parent[i][j] + "\t");
            System.out.println();
        }
        
        
        return answer.toArray(new String[answer.size()]);
    }
    
    void update(int r, int c, String value) {
        String p = getParent(r,c);
        
        for(int i = 1; i<=50; i++) 
            for(int j = 1; j<=50; j++) 
                if(parent[i][j].equals(p))
                    map[i][j] = value;
    }
    
    void update(String value1, String value2) {
        for(int i = 1; i<=50; i++) 
            for(int j = 1; j<=50; j++) 
                if(!Objects.isNull(map[i][j]) && map[i][j].equals(value1))
                    map[i][j] = value2;
    }
    
    void merge(int r1, int c1, int r2, int c2) {        
        String a = getParent(r1,c1);
        String b = getParent(r2,c2);
        
        if(a.equals(b)) return;
        
        if(isHeadA(a,b)) {
            for(int i = 1; i<=50; i++)
                for(int j = 1; j<=50; j++)
                    if(parent[i][j].equals(b))
                        parent[i][j] = a;
        }
        else {
            for(int i = 1; i<=50; i++)
                for(int j = 1; j<=50; j++)
                    if(parent[i][j].equals(a))
                        parent[i][j] = b;
        }
        
        String val;
        if(Objects.isNull(map[r1][c1])) val = map[r2][c2];
        else val = map[r1][c1];
        
        for(int i = 1; i<=50; i++)
            for(int j = 1; j<=50; j++)
                if(parent[i][j].equals(parent[r1][c1]))
                    map[i][j] = val;
    }
    
    void unmerge(int r, int c) {
        String p = getParent(r,c);
        
        for(int i = 1; i<=50; i++)
            for(int j = 1; j<=50; j++) {
                if(i == r && j == c) continue;
                 
                if(parent[i][j].equals(p)) {
                    parent[i][j] = i+" "+j;
                    map[i][j] = null;
                }
            }
        
        parent[r][c] = r+" "+c;
    }
    
    boolean isHeadA(String a, String b) {
        return a.compareTo(b) < 0;
    }
    
    String getParent(int r, int c) {
        if(parent[r][c].equals(r+" "+c)) return parent[r][c];
        
        String[] ppos = parent[r][c].split(" ");
        int pr = Integer.parseInt(ppos[0]);
        int pc = Integer.parseInt(ppos[1]);
        return getParent(pr, pc);
        
    }
    
    void print(int r, int c) {
        answer.add(map[r][c] == null ? "EMPTY" : map[r][c]);
    }
}