import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Student[] students = new Student[400];
	static int[][] seats = new int[20][20];
	static int[][] canSeats = new int[20][20];
	static Student[] sortedStudentsById = new Student[401];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		for(int i = 0; i<n*n; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			students[i] = new Student(line);
			sortedStudentsById[line[0]] = new Student(i, line);
		}

		// Step1 check!
		// System.out.println("==== [0] ====");
		// FindSeat(0);
		// Sitting(0);
		//
		// System.out.println("==== [1] ====");
		// initCanSeats();
		// FindSeat(1);
		// Sitting(1);
		//
		// System.out.println("==== [2] ====");
		// initCanSeats();
		// FindSeat(2);
		// Sitting(2);

		for(int i = 0; i<n*n; i++) {
			initCanSeats();
			FindSeat(i);
			Sitting(i);
		}
		System.out.println(CalculatePreferPoint());
	}

	private static void printSeats() {
		System.out.println("====== [CanSeats] =======");
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				System.out.print(canSeats[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("====== [seats] =======");
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				System.out.print(seats[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void initCanSeats() {
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				canSeats[i][j] = 0;
			}
		}
	}

	private static void FindSeat(int s) {
		Step0();
		Step1(s);
		Step2();
		Step3();
	}

	private static void Sitting(int s) {
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				if(canSeats[i][j] != -1) {
					seats[i][j] = students[s].id;
				}
			}
		}
	}

	private static void Step0() {
		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++)
				if(seats[i][j] != 0) canSeats[i][j] = -1;
	}

	private static void Step1(int s) {
		int maxPreferPoint = 0;
		// 탐색
		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++)
				if(canSeats[i][j] != -1) {
					int adjPreferPoint = calAdjPreferPoint(students[s], i, j);

					canSeats[i][j] = adjPreferPoint;
					maxPreferPoint = Math.max(maxPreferPoint, adjPreferPoint);
				}

		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++)
				if(canSeats[i][j] < maxPreferPoint) canSeats[i][j] = -1;
	}

	private static int calAdjPreferPoint(Student stud, int x, int y) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		int point = 0;

		for(int i = 0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(stud.MyPrefer(seats[nx][ny])) point++;
			}
		}

		return point;
	}

	private static void Step2() {
		int maxAdjSeats = 0;

		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++)
				if(canSeats[i][j] != -1) {
					int adjSeats = countAdjSeats(i, j);

					canSeats[i][j] = adjSeats;
					maxAdjSeats = Math.max(maxAdjSeats, adjSeats);
				}

		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++)
				if(canSeats[i][j] != -1 && canSeats[i][j] < maxAdjSeats) canSeats[i][j] = -1;
	}

	private static int countAdjSeats(int x, int y) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		int point = 0;
		for(int i = 0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(seats[nx][ny] == 0) point++;
			}
		}
		return point;
	}

	private static void Step3() {
		boolean isSelected = false;

		for(int i = 0; i<n; i++)
			for(int j = 0; j<n; j++) {
				if (canSeats[i][j] != -1) {
					if (!isSelected)
						isSelected = true;
					else
						canSeats[i][j] = -1;
				}
			}
	}

	private static int CalculatePreferPoint() {
		int result = 0;

		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				int count = 0;
				int studId = seats[i][j];
				Student student = sortedStudentsById[studId];

				for(int d = 0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if(0 <= nx && nx < n && 0 <= ny && ny < n) {
						if(student.MyPrefer(seats[nx][ny])) count++;
					}
				}

				if(count == 1) result += 1;
				else if(count == 2) result += 10;
				else if(count == 3) result += 100;
				else if(count == 4) result += 1000;
			}
		}

		return result;
	}
}

class Student {
	int id;
	int[] preferIds = new int[4];

	Student(int[] data) {
		this.id = data[0];
		for(int i = 1; i<=4; i++) {
			preferIds[i-1] = data[i];
		}
	}

	Student(int index, int[] data) {
		this.id = index;
		for(int i = 0; i<4; i++) {
			preferIds[i] = data[i+1];
		}
	}

	boolean MyPrefer(int other) {
		for(int i = 0; i<4; i++) {
			if(preferIds[i] == other)
				return true;
		}
		return false;
	}
}