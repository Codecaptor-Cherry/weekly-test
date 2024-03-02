package saturday.sat240302;

/*
 * 반시계 방향으로 배열 돌리기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16927_배열돌리기2 {
    static int N, M, R;
    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우하좌상
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
           for(int j = 0; j < M; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
           }
        }


        for(int i = 0; i < Math.min(N, M) / 2; i++) {
            // 2 * (N - i * 2) + 2 * (M - i * 2) - 4 : i번째 테두리 배열 길이
            rotateArray(map, i, 2 * (N - i * 2) + 2 * (M - i * 2) - 4);
        }

        print(map);
    }

    public static void rotateArray(int[][] map, int start, int len) {
        int rotate = R % len; // 회전 수 최소화

        for(int t = 0; t < rotate; t++) {
            int tmp = map[start][start]; // 마지막에 넣을 값

            int idx = 0; // 회전 방향
            int x = start, y = start; // 시작 좌표

            while(idx < 4) { // 4를 넘어서면 회전 완료
                int dx = x + dir[idx][0];
                int dy = y + dir[idx][1];

                if(checkRange(dx, dy, start)) { // 해당 좌표가 테두리인지 확인
                    map[x][y] = map[dx][dy];
                    x = dx;
                    y = dy;
                } else {
                    idx++;
                }
            }

            map[start + 1][start] = tmp; // 마지막 값 넣어주기 ~ 시작 좌표의 아래 자리
        }

    }

    public static boolean checkRange(int x, int y, int gap) {
        // 좌상단 (0, 0) -> (1, 1) -> (2, 2) -> ...
        // 우하단 (N - 1, M - 1) -> (N - 2, M - 2) -> (N - 3, M - 3) -> ...
        if(x < gap || x >= N - gap) return false;
        if(y < gap || y >= M - gap) return false;

        return true;
    }

    public static void print(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int[] arr: map) {
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
        }
        sb.append("\n");

        System.out.println(sb);
    }
}
