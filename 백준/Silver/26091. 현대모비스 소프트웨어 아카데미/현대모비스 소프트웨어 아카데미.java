import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] members;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = line[0];
		m = line[1];
		members = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(members);

		int result = 0;
		int s = 0; int e = n-1;
		while (s < e) {
			if(members[s] + members[e] < m) {
				s++;
				continue;
			}

			s++; e--;
			result++;
		}


		System.out.println(result);
	}
}
