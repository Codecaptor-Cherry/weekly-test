package saturday.sat241102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * (, ), [, ] 를 이용해서 만들어지는 괄호열 중에서 올바른 괄호열은 다음과 같이 정의된다.
 * 1. 한 쌍의 괄호로만 이루어진 ()와 []는 올바른 괄호열이다.
 * 2. 만일 x가 올바른 괄호열이면 (x)나 [x]도 모두 올바른 괄호열이 된다.
 * 3. x와 y 모두 올바른 괄호열이라면 이들을 결합한 xy도 올바른 괄호열이 된다.
 *
 * 괄호값 정의는 다음과 같다.
 * 1. () : 2
 * 2. [] : 3
 * 3. (x) : 2 * (x)값
 * 4. [x] : 3 * (x)값
 * 5. xy : (x)값 + (y)값
 *
 * 주어진 괄호열을 읽고 그 괄호값을 앞에서 정의한대로 계산하여 출력하기
 * 만약 입력이 올바르지 못한 괄호열이면 반드시 0을 출력해야 한다.
 *
 * 1. 올바른 괄호열인지 ~ stack을 이용하여 직전의 열린 괄호와 짝이 맞는지 체크
 * 2. 괄호값 계산 ~ 분배법칙 이용
 * ex) (()(())) = 2 x (2 + (2 x 2)) = 12 = (2 x 2) + (2 x 4)
 */
public class BJ_2504_괄호의값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        Stack<Character> stack = new Stack<>();

        int idx = 0;
        int len = input.length();
        int ans = 0;
        int tmp = 1; // 현재 계산된 값

        while(idx < len) { // 문자열 끝까지 확인
            char now = input.charAt(idx); // 현재 선택된 문자

            // 열린 괄호인 경우 스택에 넣기
            // 분배법칙을 위해 해당 괄호에 맞게 x2 또는 x3
            if(now == '(') {
                stack.push(now);
                tmp *= 2;
            } else if(now == '[') {
                stack.push(now);
                tmp *= 3;
            }
            // 닫힌 괄호인 경우, 닫힐 때마가 해당 괄호에 맞게 /2 or /3 ~ why ? 분배법칙 끝 !
            else {
                if (now == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') { // 짝이 안맞는 경우
                        ans = 0;
                        break;
                    }
                    if (input.charAt(idx - 1) == '(') { // 완전한 괄호 x가 되는 경우 지금까지 계산된 값 저장
                        ans += tmp;
                    }
                    tmp /= 2;
                } else if (now == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') { // 짝이 안맞는 경우
                        ans = 0;
                        break;
                    }
                    if (input.charAt(idx - 1) == '[') { // 완전한 괄호 x가 되는 경우 지금까지 계산된 값 저장
                        ans += tmp;
                    }
                    tmp /= 3;
                }
            }

            idx++;
        }

        if(!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }
}
