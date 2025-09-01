import java.util.*;

public class Main {
	static int n,l;
	static int[] leak = new int[1000];
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		l = sc.nextInt();

		for(int i = 0; i<n; i++)
			leak[i] = sc.nextInt();

		Arrays.sort(leak, 0, n);

		int limit = leak[0] + l;
		result++;
		for(int i = 0; i<n; i++) {
			if(leak[i] < limit) continue;

			limit = leak[i] + l;
			result++;
		}

		System.out.println(result);
	}
}
