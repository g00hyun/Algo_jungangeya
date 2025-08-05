import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int r,c;
	static int totalR = 1, totalC = 1;
	static int[][] totalMatrix = new int[1024][1024];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		totalMatrix[0][0] = 1;

		while(n-- > 0) {
			int[] line = readLinetoIntArr(br);

			r = line[0];
			c = line[1];
			int[][] inputMatrix = new int[r][c];

			for(int i = 0; i<r; i++) {
				line = readLinetoIntArr(br);
				for(int j = 0; j<c; j++)
					inputMatrix[i][j] = line[j];
			}

			int[][] calcMatrix = deepCopy(totalMatrix);
			tensorProduct(calcMatrix, inputMatrix);
			totalR *= r; totalC *= c;
		}

		printAnswer();
	}

	private static void printAnswer() {
		/**
		 * the maximum element in the tensor product
		 * the minimum element in the tensor product
		 * the maximum row sum (i.e., sum of entries in each row)
		 * the minimum row sum
		 * the maximum column sum
		 * the minimum column sum
		 */
		int maxElem = Integer.MIN_VALUE;
		for(int i = 0; i<totalR; i++)
			for(int j = 0; j<totalC; j++)
				maxElem = Math.max(maxElem, totalMatrix[i][j]);

		int minElem = Integer.MAX_VALUE;
		for(int i = 0; i<totalR; i++)
			for(int j = 0; j<totalC; j++)
				minElem = Math.min(minElem, totalMatrix[i][j]);

		int maxInRowSum = Integer.MIN_VALUE;
		for(int i = 0; i<totalR; i++)
			maxInRowSum = Math.max(maxInRowSum, Arrays.stream(totalMatrix[i]).reduce(0, (a,b) -> a+b));

		int minInRowSum = Integer.MAX_VALUE;
		for(int i = 0; i<totalR; i++)
			minInRowSum = Math.min(minInRowSum, Arrays.stream(totalMatrix[i]).reduce(0, (a,b) -> a+b));

		int maxInColSum = Integer.MIN_VALUE;
		for(int j = 0; j<totalC; j++) {
			int colSum = 0;
			for(int i = 0; i<totalR; i++) {
				colSum += totalMatrix[i][j];
			}
			maxInColSum = Math.max(maxInColSum, colSum);
		}

		int minInColSum = Integer.MAX_VALUE;
		for(int j = 0; j<totalC; j++) {
			int colSum = 0;
			for(int i = 0; i<totalR; i++) {
				colSum += totalMatrix[i][j];
			}
			minInColSum = Math.min(minInColSum, colSum);
		}

		System.out.println(maxElem + "\n" + minElem + "\n" + maxInRowSum + "\n" + minInRowSum + "\n" + maxInColSum + "\n" + minInColSum);
	}

	private static void tensorProduct(int[][] arr, int[][] addArr) {
		for(int i = 0; i<totalR * r; i++)
			for(int j = 0; j<totalC * c; j++)
				totalMatrix[i][j] = arr[i / r][j / c] * addArr[i % r][j % c];
	}

	private static int[] readLinetoIntArr(BufferedReader br) throws IOException {
		return Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
	}

	private static int[][] deepCopy(int[][] arr){
		int[][] result = new int[arr.length][arr[0].length];
		for (int i=0; i<arr.length; i++) {
			result[i] = arr[i].clone();
		}
		return result;
	}
}
