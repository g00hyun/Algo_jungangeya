import java.util.*;

public class Main {
	static Node[] nodes = new Node[1000001];
	static List<Integer> input = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int num;
		while(sc.hasNext()) {
			num = sc.nextInt();
			input.add(num);
			nodes[num] = new Node(num);
		}

		Node root = nodes[input.get(0)];
		for(int i : input) {
			if(i == root.num) continue;

			Node n = nodes[i];

			Node parent = root;
			while(true) {
				if(parent.num < n.num) {
					if(parent.right == null) {
						parent.right = n;
						break;
					}
					parent = parent.right;
				}
				else if(parent.num > n.num) {
					if(parent.left == null) {
						parent.left = n;
						break;
					}
					parent = parent.left;
				}
			}
		}

		preOrder(root);

	}

	private static void preOrder(Node node) {
		if(node == null) return;

		preOrder(node.left);
		preOrder(node.right);
		System.out.println(node.num);
	}
}

class Node {
	int num;
	Node left;
	Node right;

	Node(int num) {
		this.num = num;
		this.left = null;
		this.right = null;
	}
}