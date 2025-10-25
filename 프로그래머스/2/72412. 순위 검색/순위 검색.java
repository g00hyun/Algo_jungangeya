// cjp / bf / js / cp
import java.util.*;

class Solution {
    List<Integer>[][][][] cluster = new ArrayList[3][2][2][2];
    Map<String, Integer> convert = new HashMap<>();
    List<Integer> answer = new ArrayList<>();
    
    public int[] solution(String[] info, String[] query) {
        setConvert();
        
        // O(24)
        for(int a = 0; a<3; a++)
            for(int b = 0; b<2; b++)
                for(int c = 0; c<2; c++)
                    for(int d = 0; d<2; d++)
                        cluster[a][b][c][d] = new ArrayList<>();
        
        // O(50_000)
        for(String i : info) {
            String[] line = i.split(" ");
            
            int i1 = convert.get(line[0]);
            int i2 = convert.get(line[1]);
            int i3 = convert.get(line[2]);
            int i4 = convert.get(line[3]);
            
            cluster[i1][i2][i3][i4].add(Integer.parseInt(line[4]));
        }
        
        for(int a = 0; a<3; a++)
            for(int b = 0; b<2; b++)
                for(int c = 0; c<2; c++)
                    for(int d = 0; d<2; d++)
                        cluster[a][b][c][d].sort((i,j) -> i-j);
        
        // O(100_000 * 24)
        for(String q : query) {
            String[] line = q.split(" ");
            
            String v1 = line[0];
            String v2 = line[2];
            String v3 = line[4];
            String v4 = line[6];
            
            int point = Integer.parseInt(line[7]);
            int human = 0;
            
            for(int a = 0; a<3; a++) {
                if(!v1.equals("-") && convert.get(v1) != a) continue;
                
                for(int b = 0; b<2; b++) {
                    if(!v2.equals("-") && convert.get(v2) != b) continue;
                    
                    for(int c = 0; c<2; c++) {
                        if(!v3.equals("-") && convert.get(v3) != c) continue;
                        
                        for(int d = 0; d<2; d++) {
                            if(!v4.equals("-") && convert.get(v4) != d) continue;
                            
                            List<Integer> tmp = cluster[a][b][c][d];
                            // human += tmp.stream().filter(v -> v >= point).count();
                            int idx = binarySearch(tmp, point);
                            human += tmp.size() - idx;
                        }
                    }   
                }       
            }
            
            answer.add(human);
            
            // System.out.println(v1 + " " + v2 + " " + v3 + " " + v4 + " " + point);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size();

        while(left < right) {
            int mid = (left + right) / 2;
            if(list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
    
    private void setConvert() {
        convert.put("cpp", 0);
        convert.put("java", 1);
        convert.put("python", 2);
        
        convert.put("backend", 0);
        convert.put("frontend", 1);
        
        convert.put("junior", 0);
        convert.put("senior", 1);
        
        convert.put("chicken", 0);
        convert.put("pizza", 1);
    }
}