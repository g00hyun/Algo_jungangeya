import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[][] map;
	static Pos red, blue;
	static boolean[][][][] visited;

	public static void main(String[] args) throws IOException {
		// 입력 로직
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = line[0]; m = line[1];
		map = new int[n][m];
		visited = new boolean[n][m][n][m];

		for (int i = 0; i < n; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if(chars[j] == 'R') red = new Pos(i, j);
				if(chars[j] == 'B') blue = new Pos(i, j);

				if(chars[j] == 'O') map[i][j] = 2;
				if(chars[j] == '#') map[i][j] = 1;
			}
		}

		// 메인 로직 - BFS
		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<State> queue = new LinkedList<>();
		queue.offer(new State(red.x, red.y, blue.x, blue.y, 0));
		visited[red.x][red.y][blue.x][blue.y] = true;

		int[] dx = {0, 0, -1, 1};  // 좌, 우, 상, 하
		int[] dy = {-1, 1, 0, 0};

		while(!queue.isEmpty()) {
			State current = queue.poll();

			// 10번 초과하면 실패
			if(current.count >= 10) {
				continue;
			}

			// 4방향 탐색
			for(int dir = 0; dir < 4; dir++) {
				// 새로운 위치 계산
				int nrx = current.rx;
				int nry = current.ry;
				int nbx = current.bx;
				int nby = current.by;

				// 빨간 구슬 이동
				int rcnt = 0;
				while(map[nrx + dx[dir]][nry + dy[dir]] != 1) {
					nrx += dx[dir];
					nry += dy[dir];
					rcnt++;
					if(map[nrx][nry] == 2) break;  // 구멍에 도달
				}

				// 파란 구슬 이동
				int bcnt = 0;
				while(map[nbx + dx[dir]][nby + dy[dir]] != 1) {
					nbx += dx[dir];
					nby += dy[dir];
					bcnt++;
					if(map[nbx][nby] == 2) break;  // 구멍에 도달
				}

				// 파란 구슬이 구멍에 빠지면 실패
				if(map[nbx][nby] == 2) continue;

				// 빨간 구슬만 구멍에 빠지면 성공
				if(map[nrx][nry] == 2) {
					return current.count + 1;
				}

				// 두 구슬이 겹치면 더 많이 이동한 구슬을 한 칸 뒤로
				if(nrx == nbx && nry == nby) {
					if(rcnt > bcnt) {
						nrx -= dx[dir];
						nry -= dy[dir];
					} else {
						nbx -= dx[dir];
						nby -= dy[dir];
					}
				}

				// 방문하지 않은 상태면 큐에 추가
				if(!visited[nrx][nry][nbx][nby]) {
					visited[nrx][nry][nbx][nby] = true;
					queue.offer(new State(nrx, nry, nbx, nby, current.count + 1));
				}
			}
		}

		return -1;  // 10번 이내에 성공하지 못함
	}
}

class Pos {
	int x, y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class State {
	int rx, ry, bx, by, count;
	public State(int rx, int ry, int bx, int by, int count) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.count = count;
	}
}
