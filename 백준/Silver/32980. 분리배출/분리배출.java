import java.util.HashMap;
import java.util.Scanner;

/**
 * 문제 링크 : https://www.acmicpc.net/problem/32980
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		String[] list = new String[n];

		for(int i = 0; i<n; i++)
			list[i] = sc.next();

		HashMap<Character, Integer> unitSize = new HashMap<>();
		unitSize.put('P', sc.nextInt());
		unitSize.put('C', sc.nextInt());
		unitSize.put('V', sc.nextInt());
		unitSize.put('S', sc.nextInt());
		unitSize.put('G', sc.nextInt());
		unitSize.put('F', sc.nextInt());
		unitSize.put('O', sc.nextInt());

		long result = Simulation(n, list, unitSize);
		System.out.println(result);
	}
	static long Simulation(int n, String[] list, HashMap<Character,Integer> unitSize) {
		long result = 0;
		for (String s : list) {
			char c = compression(s);

			c = unitSize.get(c) > unitSize.get('O') ? 'O' : c;

			result += unitSize.get(c) * s.length();
		}
		return result;
	}

	static private char compression(String s) {
		char[] clist = s.toCharArray();
		for(int i = 1; i<s.length(); i++) {
			if(clist[i-1] != clist[i])
				return 'O';
		}
		return clist[0];
	}

}
