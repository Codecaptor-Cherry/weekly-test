package saturday.year25.sat250222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 10개의 버섯을 순서대로 먹을 수 있음
 * 중간에 중단 가능
 * 점수 합을 최대한 100에 가깝게 만들기
 * 100에 가까운 수가 2개 라면 큰 값을 선택
 */
public class BJ_2851_슈퍼마리오 {
    static final int N = 10;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int ans = 0;
        for(int i = 0; i < N; i++) {
            int score = stoi(br.readLine());
            int eatGap = Math.abs(ans + score - 100);
            int noEatGap = Math.abs(ans - 100);

            if(eatGap <= noEatGap) {
                ans += score;
            } else {
                break;
            }
        }

        System.out.println(ans);

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
