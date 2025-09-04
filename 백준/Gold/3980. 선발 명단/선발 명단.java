import java.util.*;
import java.io.*;

public class Main {
	static int c;
	static Person[] person = new Person[11];
	static boolean[] used = new boolean[11];
	static int result;
	static int tempSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		c = Integer.parseInt(br.readLine());

		while(c-- > 0) {
			for(int i = 0; i<11; i++)
				person[i] = new Person(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

			result = 0;
			bt(0, 0);
			System.out.println(result);
		}
	}

	private static void bt(int posIdx, int sum) {
		if(posIdx == 11) {
			result = Math.max(result, sum);
			return;
		}

		for(int perIdx = 0; perIdx < 11; perIdx++) {
			int value = person[perIdx].retrieveValueOfAnyPos(posIdx);

			if(value <= 0 || used[perIdx]) continue;

			used[perIdx] = true;
			bt(posIdx+1, sum + value);
			used[perIdx] = false;
		}

	}
}

class Person {
	int[] values = new int[11];

	Person(int[] v) {
		for(int i = 0; i<11; i++)
			values[i] = v[i];
	}

	int retrieveValueOfAnyPos(int pos) {
		return values[pos];
	}
}
