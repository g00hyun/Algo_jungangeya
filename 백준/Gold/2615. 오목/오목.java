import java.util.*;

class Main {
    static int[][] map = new int[19][19];
    static int ax,ay;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i<19; i++)
            for(int j = 0; j<19; j++)
                map[i][j] = sc.nextInt();
                
        // 검은돌 확인
        if(isWinner(1)) {
            System.out.println(1);
            System.out.println(ax + " " + ay);
        }
        // 흰돌 확인
        else if(isWinner(2)) {
            System.out.println(2);
            System.out.println(ax + " " + ay);
        }
        // 아직 결정 안남
        else {
            System.out.println(0);
        }
    }
    
    private static boolean isWinner(int num) {
        for(int i = 0; i<19; i++)
            for(int j = 0; j<19; j++)
                if(checkRow(i,j,num))
                    return true;
        
        for(int i = 0; i<19; i++)
            for(int j = 0; j<19; j++)
                if(checkCol(i,j,num))
                    return true;
                    
        for(int i = 0; i<19; i++)
            for(int j = 0; j<19; j++)
                if(checkDiagonal(i,j,num))
                    return true;
                    
        for(int i = 0; i<19; i++)
            for(int j = 0; j<19; j++)
                if(checkReverseDiagonal(i,j,num))
                    return true;
                    
        return false;
    }
    
    private static boolean checkRow(int x, int y, int num) {
        for(int j = y; j<y+5; j++) {
            if(!inRange(x, j) || map[x][j] != num)
                return false;
        }
        int fy = y-1;
        int ly = y+5;
        if(inRange(x, fy) && map[x][fy] == num) return false;
        if(inRange(x, ly) && map[x][ly] == num) return false;
        
        ax = x+1;
        ay = y+1;
        return true;
    }
    
    private static boolean checkCol(int x, int y, int num) {
        for(int i = x; i<x+5; i++) {
            if(!inRange(i, y) || map[i][y] != num)
                return false;
        }
        int fx = x-1;
        int lx = x+5;
        if(inRange(fx, y) && map[fx][y] == num) return false;
        if(inRange(lx, y) && map[lx][y] == num) return false;
        
        ax = x+1;
        ay = y+1;
        return true;
    }
    
    private static boolean checkDiagonal(int x, int y, int num) {
        for(int a = 0; a<5; a++) {
            int nx = x + a;
            int ny = y + a;
            
            if(!inRange(nx, ny) || map[nx][ny] != num)
                return false;
        }
        
        int fx = x - 1;
        int fy = y - 1;
        int lx = x + 5;
        int ly = y + 5;
        if(inRange(fx, fy) && map[fx][fy] == num) return false;
        if(inRange(lx, ly) && map[lx][ly] == num) return false;
        
        ax = x+1;
        ay = y+1;
        return true;
    }
    
    private static boolean checkReverseDiagonal(int x, int y, int num) {
        for(int a = 0; a<5; a++) {
            int nx = x - a;
            int ny = y + a;
            
            if(!inRange(nx, ny) || map[nx][ny] != num)
                return false;
        }
        
        int fx = x + 1;
        int fy = y - 1;
        int lx = x - 5;
        int ly = y + 5;
        if(inRange(fx, fy) && map[fx][fy] == num) return false;
        if(inRange(lx, ly) && map[lx][ly] == num) return false;
        
        ax = x+1;
        ay = y+1;
        return true;
    }
    
    private static boolean inRange(int x, int y) {
        return 0 <= x && x < 19 && 0 <= y && y < 19;
    }
}