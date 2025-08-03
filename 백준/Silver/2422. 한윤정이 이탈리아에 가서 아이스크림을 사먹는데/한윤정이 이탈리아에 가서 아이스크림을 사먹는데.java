import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n,m;
	static boolean[][] worst = new boolean[201][201];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp;
		tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		for(int i = 0; i<m; i++) {
			tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			worst[a][b] = true;
			worst[b][a] = true;
		}

		int result = 0;
		for(int i = 1; i<=n; i++)
			for(int j = i+1; j<=n; j++)
				for(int k = j+1; k<=n; k++)
					if(isGood(i,j,k)) result++;

		System.out.println(result);
	}

	static boolean isGood(int a, int b, int c) {
		for(int i = 1; i<=n; i++) {
			if(a == i && (worst[a][b] || worst[a][c])) return false;
			if(b == i && (worst[b][a] || worst[b][c])) return false;
		}
		return true;
	}
}
