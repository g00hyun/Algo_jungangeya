import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제 링크: https://www.acmicpc.net/problem/12887
 */
public class Main {
	static int col;
	static char[][] grid = new char[2][50];
	static boolean[][] visited = new boolean[2][50];
	static Queue<Pair> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		col = sc.nextInt();
		String s1 = sc.next();
		String s2 = sc.next();

		for(int i = 0; i<col; i++)
			grid[0][i] = s1.toCharArray()[i];

		for(int i = 0; i<col; i++)
			grid[1][i] = s2.toCharArray()[i];

		int min = Integer.MAX_VALUE;
		// 0,0 출발 최단거리
		if(grid[0][0] == '.') {
			q.add(new Pair(0,0, 1));
			visited[0][0] = true;
			min = Math.min(min, BFS());
		}
		// System.out.println("result1) => " + min);

		// 1,0 출발 최단거리
		Arrays.fill(visited[0], false);
		Arrays.fill(visited[1], false);
		q.clear();

		if(grid[1][0] == '.') {
			q.add(new Pair(1,0, 1));
			visited[1][0] = true;
			min = Math.min(min, BFS());
		}
		// System.out.println("result2) => " + min);

		int totalWhite = 0;
		for(int i = 0; i<col; i++)
			if(grid[0][i] == '.') totalWhite++;

		for(int i = 0; i<col; i++)
			if(grid[1][i] == '.') totalWhite++;

		// white 갯수 - 최단거리 == 정답
		System.out.println(totalWhite - min);
	}
	static int BFS() {
		int[] dx = {0,1,-1};
		int[] dy = {1,0,0};

		while(!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.x, y = p.y, cnt = p.cnt;
			// System.out.println("[Point] => (" + x + "," + y + ") => " + cnt);

			if(p.y == col-1) return cnt;

			for(int i = 0; i<3; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if(CanGo(nx,ny)) {
					visited[nx][ny] = true;
					q.add(new Pair(nx,ny,cnt+1));
				}
			}
		}

		return -1;
	}

	private static boolean InRange(int x, int y) {
		return 0<=x && x<2 && 0<=y && y<col;
	}

	private static boolean CanGo(int x, int y) {
		if(!InRange(x,y)) return false;
		if(visited[x][y]) return false;
		if(grid[x][y] == '#') return false;
		return true;
 	}

}

class Pair {
	int x,y,cnt;
	Pair(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}