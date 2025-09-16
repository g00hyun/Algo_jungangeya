import java.util.*;

public class Main {
	static String s;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();

		String result = "";
		String temp = "";
		boolean reverse = true;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '<') {
				for(int j = temp.length() - 1; j >= 0; j--)
					result += temp.charAt(j);
				temp = "";
				reverse = false;
			}

			if(s.charAt(i) == ' ') {
				for(int j = temp.length() - 1; j >= 0; j--)
					result += temp.charAt(j);
				result += " ";
				temp = "";
			}
			else {
				if(reverse)
					temp += s.charAt(i);
				else
					result += s.charAt(i);
			}

			if(s.charAt(i) == '>') {
				reverse = true;
			}
		}

		for(int j = temp.length() - 1; j >= 0; j--)
			result += temp.charAt(j);

		System.out.println(result);
	}
}
