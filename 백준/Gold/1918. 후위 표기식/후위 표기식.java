import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        
        Deque<Character> stack = new ArrayDeque<>();
        String answer = "";
        
        for(char c : input.toCharArray()) {
            if(Character.isLetter(c)) {
                answer += c;
            }
            else if(c == '(') {
                stack.push(c);
            }
            else if(c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(')
                    answer += stack.pop();
                stack.pop();
            }
            else {
                while(!stack.isEmpty() && priority(stack.peek()) >= priority(c)) 
                    answer += stack.pop();
                stack.push(c);
            }
        }
        
        while(!stack.isEmpty())
            answer += stack.pop();
        
        System.out.println(answer);
        
    }
    
    private static int priority(char op) {
        if(op == '*' || op == '/') return 2;
        if(op == '+' || op == '-') return 1;
        return 0;
    }
}
