import java.util.*;
import java.io.*;

class Main {
    static int n;
    static Point[] points = new Point[100_000];
    static int[] distance = new int[100_000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i<n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            points[i] = new Point(line[0], line[1]);
        }
        
        for(int i = 0; i<n-1; i++)
            distance[i] = points[i].calcDist(points[i+1]);
            
        int sum = 0;
        for(int i = 0; i<n-1; i++)
            sum += distance[i];
            
        int result = Integer.MAX_VALUE;
        for(int i = 0; i<n-2; i++) {
            int dist2 = points[i].calcDist(points[i+2]);
            
            sum += dist2;
            sum -= distance[i] + distance[i+1];
            
            result = Math.min(result, sum);
            
            sum -= dist2;
            sum += distance[i] + distance[i+1];
        }
        
        System.out.println(result);
    }
}

class Point {
    int x;
    int y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    int calcDist(Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }
}