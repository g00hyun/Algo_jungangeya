import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[] arr;
	static int correct;
	static int[] nums = new int[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = arr.length;

		correct = 0;
		Backtracking(0, -1, -1);
		System.out.println(correct);
	}

	private static void Backtracking(int cnt, int prev, int prev2) {
		if(cnt == n) {
			int tmp = 0;
			for(int i = 0; i < n; i++) {
				if(arr[i] == nums[i])
					tmp++;
			}

			if(tmp >= 5) correct++;

			return;
		}

		for(int i = 1; i<=5; i++) {
			if(cnt >= 2 && prev == i && prev2 == i) continue;

			nums[cnt] = i;
			if(cnt >= 1) Backtracking(cnt+1, nums[cnt], nums[cnt-1]);
			else if(cnt == 0) Backtracking(cnt+1, nums[cnt], -1);
			else Backtracking(cnt+1, -1, -1);
			nums[cnt] = -1;
		}
	}
}
