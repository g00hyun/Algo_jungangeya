import java.util.*;

public class Main {
	static int n;
	static Dice[] dices = new Dice[10000];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for(int i = 0; i<n; i++)
			dices[i] = new Dice(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());

		int result = 0;
		// bottom => A ~ F
		for(int bottomD1 = 1; bottomD1<=6; bottomD1++) {
			int sum = 0;
			int curBottom = bottomD1;
			for(int d = 0; d<n; d++) {
				sum += dices[d].maxSideByBottom(curBottom);
				curBottom = dices[d].convert(curBottom);
			}
			result = Math.max(result, sum);
		}

		System.out.println(result);
	}
}

class Dice {
	int A,B,C,D,E,F;

	Dice(int a, int b, int c, int d, int e, int f) {
		this.A = a;
		this.B = b;
		this.C = c;
		this.D = d;
		this.E = e;
		this.F = f;
	}

	int maxSideByBottom(int bottom) {
		if (this.A == bottom || this.F == bottom)
			return Math.max(Math.max(Math.max(this.B, this.C), this.E), this.D);
		else if (this.B == bottom || this.D == bottom)
			return Math.max(Math.max(Math.max(this.A, this.C), this.E), this.F);
		else
			return Math.max(Math.max(Math.max(this.A, this.B), this.D), this.F);
	}

	// bottomToTop
	int convert(int val) {
		if (this.A == val) return this.F;
		else if (this.B == val) return this.D;
		else if (this.C == val) return this.E;
		else if (this.D == val) return this.B;
		else if (this.E == val) return this.C;
		else return this.A;
	}
}
