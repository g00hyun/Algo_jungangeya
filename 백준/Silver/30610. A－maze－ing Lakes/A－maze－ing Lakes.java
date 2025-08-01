import java.util.*;

public class Main {
	static int n,m;
	static int[][] grid = new int[1000][1000];
	static boolean[][] visited = new boolean[1000][1000];
	static Queue<Pair> q = new LinkedList<>();
	static List<Integer> answer = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		for(int i = 0; i<n; i++) {
			char[] s = sc.next().toCharArray();
			for(int j = 0; j<m; j++)
				grid[i][j] = s[j] - '0';
		}

		for(int i = 0; i<n; i++)
			for(int j = 0; j<m; j++)
				if(grid[i][j] == 1 && !visited[i][j]) {
					q.add(new Pair(i,j));
					visited[i][j] = true;
					answer.add(BFS());
				}

		System.out.println(answer.size());
		answer.sort((x,y) -> x - y);
		for(int i : answer)
			System.out.print(i + " ");
	}
	static int BFS() {
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		int size = 1;

		while(!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.x, y = p.y;

			for(int i = 0; i<4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if(CanGo(nx,ny)) {
					q.add(new Pair(nx,ny));
					visited[nx][ny] = true;
					size++;
				}
			}
		}

		return size;
	}

	private static boolean CanGo(int x, int y) {
		if((!InRange(x,y))) return false;
		if(grid[x][y] == 0) return false;
		if(visited[x][y]) return false;
		return true;
	}

	private static boolean InRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

}

class Pair {
	int x,y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}