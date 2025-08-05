import java.util.*;

public class Main {
	static int n;
	static Integer[] products = new Integer[100_000];
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for(int i = 0; i<n; i++)
			products[i] = sc.nextInt();

		Arrays.sort(products, 0, n, (a,b) -> b - a);

		for(int i = 0; i<n; i++)
			if(i % 3 != 2)
				result += products[i];

		System.out.println(result);
	}
}
