package saturday.year25.sat250503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 예제를 보고 규칙을 유추한 뒤에 별 찍기
 */
public class BJ_2448_별찍기_11 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // N은 항상 3 * 2^k

        char[][] map = new char[N][N * 2 - 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], ' ');
        }

        fill(0, N - 1, N, map); // 맨 위 꼭지점 위치(0, N - 1)
        draw(N, map);
    }

    public static void fill(int x, int y, int N, char[][] map) {
        if(N == 3) { // k = 0이면 삼각형을 더 나눌 수 없음
            map[x][y] = '*'; // *
            map[x + 1][y - 1] = map[x + 1][y + 1] = '*'; // * *
            map[x + 2][y - 2] = map[x + 2][y - 1] = map[x + 2][y] = map[x + 2][y + 1] = map[x + 2][y + 2] = '*'; // *****

            return;
        } else { // 삼각형을 3개로 나누기
            int cut = N / 2;
            fill(x, y, cut, map); // 상단
            fill(x + cut, y - cut, cut, map); // 하단 좌측
            fill(x + cut, y + cut, cut, map); // 하단 우측
        }
    }

    public static void draw(int N, char[][] map) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N * 2 - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
