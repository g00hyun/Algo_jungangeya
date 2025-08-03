import java.util.*;

/**
 * 문제 링크 : https://www.acmicpc.net/problem/28075
 */
public class Main {
	static int n,m;
	static int[][] map = new int[2][3];
	static List<Integer> q = new ArrayList<>();
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		for(int i = 0; i<2; i++)
			for(int j = 0; j<3; j++)
				map[i][j] = sc.nextInt();

		backtracking(-1);
		System.out.println(result);
	}

	static void backtracking(int y) {
		if(q.size() == n) {
			if(sum() >= m) result++;
			return;
		}

		for(int i = 0; i<2; i++) {
			for(int j = 0; j<3; j++) {
				q.add(j != y ? map[i][j] : map[i][j] / 2);
				backtracking(j);
				q.remove(q.size() - 1);
			}
		}
	}

	static int sum() {
		return q.stream().reduce(0, (a,b) -> a+b);
	}
}
