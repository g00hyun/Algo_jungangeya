import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static long n;
	static long result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextLong();
		long len = Long.toString(n).length();

		result += recursiveCount(len-1);

		if(len <= 2)
			result += n;
		else if(len <= 9) {
			long target = 1L;
			for(long i = 0; i<len-1; i++)
				target *= 10;

			for(long i = target; i<=n; i++)
				if(isHansu(i)) result++;
				
		}
		else {
			long target = 1L;
			for(long i = 0; i<len-1; i++)
				target *= 10;
			long head = n / target;

			long compVal = head;
			for(long i = 0; i<len-1; i++)
				compVal = compVal * 10 + head;

			if(n < compVal) head--;
			result += head;
		}

		System.out.println(result);
	}

	private static boolean isHansu(long num) {
		char[] arr = Long.toString(num).toCharArray();
		long diff = arr[1] - arr[0];
		for(int i = 2; i<arr.length; i++)
			if(arr[i] - arr[i-1] != diff)
				return false;
		return true;
	}

	private static long recursiveCount(long len) {
		if(len == 0) return 0;
		if(len == 1) return 0;
		if(len == 2) return 99;

		long count = 0;
		// 0
		count += 9;

		// 1 ~
		for(long i = 1; i<=4; i++)
			if(9 - i*(len-1) > 0)
				count += 9 - i*(len-1);

		// -1 ~
		for(long i= 1; i<=4; i++)
			if(10 - i*(len-1) > 0)
				count += 10 - i*(len-1);

		return count + recursiveCount(len-1);
	}
}
