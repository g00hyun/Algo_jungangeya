import java.sql.Array;
import java.util.*;

public class Main {
	static int n,k,t;
	static Person[] person = new Person[3001];
	static boolean[][] graph = new boolean[3001][3001];
	static boolean[] visited = new boolean[3001];
	static Queue<Person> q = new LinkedList<>();
	static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		t = sc.nextInt();
		person[0] = new Person(sc.nextInt(), sc.nextInt(), sc.nextInt(), -1, 0);
		for(int i = 1; i<=n; i++)
			person[i] = new Person(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),i);

		makeGraph();

		q.add(person[0]);
		visited[0] = true;
		BFS();

		if(result.isEmpty())
			System.out.println(0);
		else
			result.stream().sorted().forEach(v -> System.out.print(v + " "));
	}

	private static void BFS() {
		while(!q.isEmpty()) {
			Person p = q.poll();

			if(p.p == 1)
				result.add(p.num);

			for(int i = 0; i<3001; i++) {
				if(!visited[i] && graph[p.num][i]) {
					Person np = person[i];

					if(Math.abs(p.v - np.v) <= t) {
						q.add(np);
						visited[np.num] = true;
					}
				}
			}
		}
	}

	private static void makeGraph() {
		for(int i = 0; i<=n; i++)
			for(int j = 0; j<=n; j++) {
				if(i == j) continue;

				Person p1 = person[i];
				Person p2 = person[j];

				double uclidDist = Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y)));

				if(uclidDist <= k) {
					graph[i][j] = true;
					graph[j][i] = true;
				}
			}
	}
}

class Person{
	int x,y,v,p,num;
	Person(int x, int y, int v, int p, int num) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.p = p;
		this.num = num;
	}
}