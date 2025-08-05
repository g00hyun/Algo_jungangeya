import java.io.*;

public class Main {
	static long n;
	static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		long len = Long.toString(n).length();

		result = recursiveSum(len-1);

		long diff = n - (long)Math.pow(10, len-1) + 1;

		result += diff * len;
		System.out.println(result % 1234567);
	}

	private static long recursiveSum(long expo) {
		int sum = 9;
		if(expo == 0) return 0;

		for(int i = 1; i<expo; i++)
			sum *= 10;

		return ((sum * expo) + recursiveSum(expo - 1)) % 1234567;
	}
}
