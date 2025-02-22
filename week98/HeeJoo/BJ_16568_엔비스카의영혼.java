package saturday.year25.sat250222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N명의 사람. 1초마다 맨 앞의 사람은 집에 감
 * 맨 뒤 한길쓰는 1초마다 다음과 같은 행동 가능 + 행동에 소모되는 시간은 x
 * 행동 1. 기다리기
 * 행동 2. a명 앞으로 가기(앞에 최소 a명)
 * 행동 3. b명 앞으로 가기(앞에 최소 b명)
 * 줄의 맨 앞에 가기 위해 필요한 최소 시간 구하기
 *
 * 1. 맨 앞 사람 이동
 * 2. 한길쓰 행동
 */
public class BJ_16568_엔비스카의영혼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken());
        int a = stoi(st.nextToken());
        int b = stoi(st.nextToken());

        int[] dp = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            dp[i] = i;
        }

        if(a + 1 <= N) {
            dp[a + 1] = 1;
        }
        if(b + 1 <= N) {
            dp[b + 1] = 1;
        }

        for (int i = 1; i <= N; i++) {
            if(i - 1 - a > 0) {
                dp[i] = Math.min(dp[i], dp[i - 1 - a] + 1);
            }
            if(i - 1 - b > 0) {
                dp[i] = Math.min(dp[i], dp[i - 1 - b] + 1);
            }
        }

        System.out.println(dp[N]);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
