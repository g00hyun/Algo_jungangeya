import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        
        Deque<Character> stack = new ArrayDeque<>();
        String answer = "";
        
        for(char c : input.toCharArray()) {
            // 1. 피연산자(알파벳)는 바로 출력
            if(Character.isLetter(c)) {
                answer += c;
            }
            // 2. 여는 괄호는 스택에 push
            else if(c == '(') {
                stack.push(c);
            }
            // 3. 닫는 괄호는 여는 괄호 나올 때까지 pop
            else if(c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(')
                    answer += stack.pop();
                stack.pop(); // '(' 제거
            }
            // 4. 연산자 처리
            else {
                // 스택 top이 현재 연산자보다 우선순위가 높거나 같으면 pop
                while(!stack.isEmpty() && priority(stack.peek()) >= priority(c))
                    answer += stack.pop();
                stack.push(c);
            }
        }
        
        // 5. 남은 연산자 모두 출력
        while(!stack.isEmpty()) {
            answer += stack.pop();
        }
        
        System.out.println(answer);
    }
    
    // 연산자 우선순위 반환
    private static int priority(char op) {
        if(op == '(' || op == ')') return 0;
        if(op == '+' || op == '-') return 1;
        if(op == '*' || op == '/') return 2;
        return -1;
    }
}
