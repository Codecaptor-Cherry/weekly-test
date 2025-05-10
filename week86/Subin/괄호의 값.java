import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Character> stack = new Stack<>();
        char prev = ' ';
        int answer = 0;
        int mul = 1;

        // (()[[]])([])
        // 2 * (2 + 3 * 3) + 2 * 3
        // -> 2 * 2 + 2 * 3 * 3 + 2 * 3
        for (char c: input.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                mul *= 2;
            } else if (c == '[') {
                stack.push(c);
                mul *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    answer = 0;
                    break;
                } else if (prev == '(') {
                    answer += mul;
                }
                mul /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    answer = 0;
                    break;
                } else if (prev == '[') {
                    answer += mul;
                }
                mul /= 3;
            }
            prev = c;
        }

        if (!stack.isEmpty()) answer = 0;
        System.out.println(answer);
    }
}
