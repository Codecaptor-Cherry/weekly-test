package saturday.sat240713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 정수 A를 B로 바꾸기
 * 연산 1. 곱하기 2
 * 연산 2. 1을 수의 가장 오른쪽에 추가
 * A를 B로 바꾸는데 필요한 연산의 최솟값 구하기
 * 만들 수 없는 경우 -1 출력
 */
public class BJ_16953_AtoB {
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        long A = Long.parseLong(inputs[0]);
        long B = Long.parseLong(inputs[1]);

        change(A, B, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans + 1);
    }

    public static void change(long a, long b, int cnt) {
        if(a > b || cnt >= ans) {
            return;
        }

        if(a == b) {
            ans = Math.min(ans, cnt);
        }

        change(func1(a), b, cnt + 1);
        change(func2(a), b, cnt + 1);
    }

    // 연산 1. 곱하기 2
    public static long func1(long n) {
        return 2 * n;
    }

    // 연산 2. 1을 수의 가장 오른쪽에 추가
    public static long func2(long n) {
        return n * 10 + 1;
    }
}
