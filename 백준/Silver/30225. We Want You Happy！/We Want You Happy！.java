import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] list = new int[n][4];
		for(int i = 0; i<n; i++) {
			list[i][0] = sc.nextInt();
			list[i][1] = sc.nextInt();
			list[i][2] = sc.nextInt();
			list[i][3] = sc.nextInt();
		}
		Simulation(n, list);
	}
	static void Simulation(int n, int[][] list) {
		int nt = 0;
		for(int[] person : list) {
			if(nt > person[1] + person[3]) continue;
			nt = Math.max(nt, person[1]) + person[2];

			System.out.println(person[0]);
		}
	}

}
