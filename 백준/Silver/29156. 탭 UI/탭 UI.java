import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n;
	static int[] taps = new int[100_000];
	static double[] tapsCenter = new double[100_000];
	static int screenLength;
	static int touchCount;
	static double min, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		taps[0] = Integer.parseInt(br.readLine());
		for(int i = 1; i<n; i++)
			taps[i] = Integer.parseInt(br.readLine()) + taps[i-1];

		// tap UI 별 중앙값 구하기
		tapsCenter[0] = (double)taps[0]/2;
		for(int i = 1; i<n; i++)
			tapsCenter[i] = taps[i] - (double)(taps[i] - taps[i-1]) / 2;

		screenLength = Integer.parseInt(br.readLine());

		// tap UI 중앙값 최대 최소
		min = (double)screenLength/2;
		max = taps[n-1] > screenLength ? taps[n-1] - (double)screenLength/2 : (double)screenLength/2;
		double mid = min;

		touchCount = Integer.parseInt(br.readLine());
		for(int i = 0; i< touchCount; i++) {
			int index = Integer.parseInt(br.readLine()) - 1;

			double nextMid;
			if(tapsCenter[index] < min)
				nextMid = min;
			else if (tapsCenter[index] > max)
				nextMid = max;
			else
				nextMid = tapsCenter[index];

			mid = nextMid;
			// System.out.printf("%.2f\n", mid - min);
			sb.append(String.format("%.2f\n", mid - min));
		}
		System.out.print(sb.toString());
	}
}
