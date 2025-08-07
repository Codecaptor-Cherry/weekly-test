package saturday.year24.sat241207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 서로 다른 N개의 자연수의 합이 S라고 한다.
 * S를 알 때, 자연수 N의 최댓값은 얼마일까?
 */
public class BJ_1789_수들의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());

        int N = 1;
        while(S >= N) {
            S -= N++;
        }

        System.out.println(--N);
    }
}
