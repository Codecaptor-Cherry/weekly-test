package saturday.sat250104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N명의 회원, M가지 치킨
 * 한 사람의 만족도는 주문한 치킨 중에서 선호도가 가장 큰 값으로 결정
 * 최대 세 가지 종류의 치킨만 주문
 * 만족도 합의 최댓값 구하기
 *
 * 1. 주문할 치킨 고르기
 * 2. 만족도 합 구하기
 */
public class BJ_16439_치킨치킨치킨 {
    static int N, M, ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        // ------------ 입력 종료 ------------
        perm(0, 0, new int[3], map);

        System.out.println(ans);
    }

    public static void perm(int cnt, int idx, int[] selected, int[][] map) {
        if(cnt == 3) {
            getSatis(selected, map);
            return;
        }

        if(idx >= M) {
            return;
        }

        // idx 주문x
        perm(cnt, idx + 1, selected, map);

        // idx 주문
        selected[cnt] = idx;
        perm(cnt + 1, idx + 1, selected, map);
        selected[cnt] = -1;
    }

    public static void getSatis(int[] selected, int[][] map) {
        int sum = 0;

        for(int i = 0; i < N; i++) {
            int satis = 0;
            for(int k : selected) {
                satis = Math.max(satis, map[i][k]);
            }

            sum += satis;
        }

        ans = Math.max(ans, sum);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
