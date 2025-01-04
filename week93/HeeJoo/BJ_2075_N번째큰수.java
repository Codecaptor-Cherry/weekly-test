package saturday.sat250104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N x N표에 수 N^2개 ... 모두 서로 다른 수
 * 모든 수는 자신의 한 칸 위에 있는 수보다 크다
 * 표가 주어졌을 때, N번째 큰 수를 찾는 프로그램
 */
public class BJ_2075_N번째큰수 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());

        long[][] map = new long[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        // ------------- 입력 종료 -------------

        int[] selected = new int[N];
        Arrays.fill(selected, N - 1);

        long ans = 0;
        for(int i = 0; i < N; i++) {
            ans = getMax(selected, map);
        }

        System.out.println(ans);
    }

    public static long getMax(int[] selected, long[][] map) {
        long max = -(long)1e10;
        int idx = 0;

        for(int i = 0; i < N; i++) {
            if(selected[i] < 0) {
                continue;
            }

            if(max < map[selected[i]][i]) {
                idx = i;
                max = map[selected[i]][i];
            }
        }

        selected[idx]--;

        return max;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
