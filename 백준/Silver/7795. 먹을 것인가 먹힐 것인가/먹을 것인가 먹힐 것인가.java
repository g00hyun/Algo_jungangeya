import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int t;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();

		while(t-- > 0) {
			int lena = sc.nextInt();
			int lenb = sc.nextInt();
			int[] a = new int[lena];
			int[] b = new int[lenb];

			for(int i = 0; i<lena; i++)
				a[i] = sc.nextInt();
			for(int i = 0; i<lenb; i++)
				b[i] = sc.nextInt();

			Arrays.sort(a);
			Arrays.sort(b);

			int idxb = 0;
			int count = 0;
			for(int i = 0; i<lena; i++) {
				while(idxb < lenb && a[i] > b[idxb]) idxb++;
				count += idxb;
			}
			System.out.println(count);
		}
	}
}
