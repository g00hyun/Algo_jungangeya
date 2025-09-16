import java.util.*;

public class Main {
	static int n;
	static int[][] sticks = new int[1000][2];
	static int maxH = 0;
	static int maxIdx = 0;
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			sticks[i][0] = sc.nextInt();
			sticks[i][1] = sc.nextInt();
		}

		Arrays.sort(sticks, 0, n, (a,b) -> a[0] - b[0]);

		for(int i = 0; i < n; i++) {
			if(maxH < sticks[i][1]) {
				maxH = sticks[i][1];
				maxIdx = i;
			}
		}

		int oldX = sticks[0][0];
		int oldH = sticks[0][1];
		// 오름차순
		for(int i = 1; i <= maxIdx; i++) {
			int curX = sticks[i][0];
			int curH = sticks[i][1];

			if(curH > oldH) {
				result += (curX - oldX) * oldH;
				oldH = curH;
				oldX = curX;
			}
		}

		// 중간
		result += maxH;

		int oldX2 = sticks[n-1][0];
		int oldH2 = sticks[n-1][1];
		// 내림차순
		for(int i = n-2; i >= maxIdx; i--) {
			int curX = sticks[i][0];
			int curH = sticks[i][1];

			if(curH >= oldH2) {
				result += (oldX2 - curX) * oldH2;
				oldH2 = curH;
				oldX2 = curX;
			}
		}

		System.out.println(result);
	}
}
