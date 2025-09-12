import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static List<int[]> seq = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			seq.add(line);
		}

		int idx = 0;
		while(!isUnique(idx++)) set.clear();
		System.out.println(idx);
	}

	private static boolean isUnique(int idx) {
		for(int i = 0; i<n; i++) {
			if(idx < seq.get(i).length-1) {
				if(set.contains(seq.get(i)[idx])) return false;
				set.add(seq.get(i)[idx]);
			}
		}
		return true;
	}
}
