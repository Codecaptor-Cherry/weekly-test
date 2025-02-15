package saturday.year25.sat250215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 회의, 1개의 회의실
 * 각 회의는 시작 시간, 종료 시간, 회의 인원이 주어짐
 * 동시에 두 개 이상의 회의가 진행될 수 없음
 * 한 회의가 끝나는 동시에 다음 회의 시작 가능
 * 회의를 진행할 수 있는 최대 인원 구하기
 * 임의의 회의 K는 회의 K-1, K+1과 회의 시간이 겹치고, 다른 회의들과는 겹치치 않는다. -> 인원 수만 기록
 */

public class BJ_19622_회의실배정3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        int[] nop = new int[N];
        for(int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            nop[i] = stoi(input[2]); // 인원 수만 기록
        }

        // -------------------------------------------------
        int[] dp = new int[N + 1];
        dp[1] = nop[0];
        for(int i = 2; i <= N; i++) {
            dp[i] = Math.max(nop[i - 1] + dp[i - 2], dp[i - 1]);
        }

        System.out.println(dp[N]);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
