import java.util.*;
import java.io.*;

public class Main {
	static int n,m,k;
	static int[][] map;
	static int[][] calcList;
	static List<int[]> calcSeq;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		n = line[0];
		m = line[1];
		k = line[2];
		map = new int[n][m];
		calcList = new int[k][3];
		calcSeq = new ArrayList<>();
		visited = new boolean[k];

		for(int i=0; i<n; i++)
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for(int i=0; i<k; i++)
			calcList[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		answer = Integer.MAX_VALUE;
		Backtracking(0);
		System.out.println(answer);
	}

	private static void Backtracking(int cnt) {
		if(cnt == k) {
			int[][] cpyMap = new int[n][m];
			for(int i=0; i<n; i++)
				for(int j=0; j<m; j++)
					cpyMap[i][j] = map[i][j];

			for(int[] seq : calcSeq) {
				spinMap(seq, cpyMap);
			}


			int val = getMapVal(cpyMap);
			answer = Math.min(answer, val);
			// System.out.println(val);

			return;
		}

		for(int i=0; i<k; i++) {
			if(visited[i]) continue;

			visited[i] = true;
			calcSeq.add(calcList[i]);
			Backtracking(cnt+1);
			calcSeq.remove(calcSeq.size()-1);
			visited[i] = false;
		}
	}

	private static void spinMap(int[] seq, int[][] map) {
		for(int diff = 1; diff<=seq[2]; diff++) {
			int sx = seq[0]-diff-1;
			int ex = seq[0]+diff-1;
			int sy = seq[1]-diff-1;
			int ey = seq[1]+diff-1;

			// 오른쪽 방향
			int tmp1 = map[sx][ey];
			for(int j = ey; j>sy; j--)
				map[sx][j] = map[sx][j-1];

			// 아래쪽 방향
			int tmp2 = map[ex][ey];
			for(int i = ex; i>sx; i--)
				map[i][ey] = map[i-1][ey];
			map[sx+1][ey] = tmp1;

			// 왼쪽 방향
			int tmp3 = map[ex][sy];
			for(int j = sy; j<ey; j++)
				map[ex][j] = map[ex][j+1];
			map[ex][ey-1] = tmp2;

			// 위쪽 방향
			// int tmp4 = map[sx][sy];
			for(int i = sx; i<ex; i++)
				map[i][sy] = map[i+1][sy];
			map[ex-1][sy] = tmp3;

		}


	}

	private static int getMapVal(int[][] map) {
		return Arrays.stream(map).map(v -> Arrays.stream(v).reduce(0, (a, b) -> a+b)).min(Integer::compareTo).get();
	}
}
