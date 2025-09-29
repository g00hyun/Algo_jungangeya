import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Egg[] eggs;
	static int[] attackedIndexes;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n =  Integer.parseInt(br.readLine());
		eggs = new Egg[n];
		attackedIndexes = new int[n];

		for (int i = 0; i < n; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			eggs[i] = new Egg(line[0], line[1]);
		}

		Arrays.fill(attackedIndexes, -1);
		answer = 0;
		Backtracking(0);
		System.out.println(answer);
	}

	private static void eggsBreak(Egg[] eggs) {
		for(int i = 0; i < n; i++) {
			if(eggs[i].defense <= 0) continue;

			int attackedIdx = attackedIndexes[i];

			if(eggs[attackedIdx].defense <= 0) continue;

			eggs[i].defense -= eggs[attackedIdx].offense;
			eggs[attackedIdx].defense -= eggs[i].offense;
		}

	}

	private static void Backtracking(int idx) {
		if(idx == n) {
			Egg[] cpyEggs = new Egg[n];
			for(int i = 0; i < n; i++) {
				cpyEggs[i] = new Egg(eggs[i].defense, eggs[i].offense);
			}
			eggsBreak(cpyEggs);
			// System.out.println("[START EGG BREAK]");
			// for(Egg e : cpyEggs) {
			// 	System.out.println(e.defense + " " + e.offense);
			// }

			int tmp = Arrays.stream(cpyEggs).map(v -> v.defense).filter(v -> v <= 0).toArray().length;
			answer = Math.max(answer, tmp);

			return;
		}

		for(int i = 0; i<n; i++) {
			if(i == idx) continue;

			attackedIndexes[idx] = i;
			Backtracking(idx+1);
			attackedIndexes[idx] = -1;
		}
	}
}

class Egg {
	int defense;
	int offense;

	Egg(int defense, int offense) {
		this.defense = defense;
		this.offense = offense;
	}
}