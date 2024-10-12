package saturday.sat241012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * M x N개의 정사각형으로 이루어진 지도
 * 정사각형은 벽 또는 빈 공간으로 구성
 * 그림의 길이는 정사각형의 변의 길이 2배
 * 빈 공간과 인접해 있는 벽에만 그림을 걸 수 있고, 그림은 서로 겹칠 수 없음
 * 최대로 걸 수 있는 그림의 개수 구하기
 *
 * 1. 어떤 빈 공간 A와 인접한 빈 공간 B
 * 2. A와 B가 연결된 방향을 제외한 방향에 A와 B는 인접한 빈 공간이 없어야 됨
 *
 */
public class BJ_2115_갤러리 {
    static int M, N, ans = 0;
    static int[][] map;
    static int[] dirX = new int[]{-1, 1, 0, 0}; // 상하우좌
    static int[] dirY = new int[]{0, 0, 1, -1}; // 상하우좌
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        M = stoi(inputs[0]); // 세로 행
        N = stoi(inputs[1]); // 가로 열

        map = new int[M][N];
        for(int i = 0; i < M; i++) {
            char[] ca = br.readLine().toCharArray();
            for(int j = 0; j < N; j++) {
                if(ca[j] == 'X') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        // 첫 줄, 마지막 줄, 첫 열, 마지막 열은 모두 벽
        for(int i = 1; i < M - 1; i++) {
            for(int j = 1; j < N - 1; j++) {
                if(map[i][j] != -1) { // 빈 공간인 경우에만 체크
                    func(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    public static void func(int x, int y) {
        // 1. 인접한 벽이 있는지 : 반복문 방향에 따라 우, 하 방향만 체크
        for(int i = 1; i <= 2; i++) {
            int dx = x + dirX[i];
            int dy = y + dirY[i];

            if(map[dx][dy] != -1) { // 인접한 벽이 빈 공간인 경우
                // 2. 자신과 인접한 벽에 대해 checkAvail
                ans += checkAvail(x, y, dx, dy, i);
            }
        }
    }
    public static int checkAvail(int x, int y, int dx, int dy, int d) { // 상하우좌
        int sum = 0;

        if(d == 1) { // 하
            if(check(x, y, dx, dy, 2)) { // 우
//                System.out.printf("%d, %d | %d, %d ~ 우\n", x, y, dx, dy);
                sum++;
            }

            if(check(x, y, dx, dy, 3)) { // 좌
//                System.out.printf("%d, %d | %d, %d ~ 좌\n", x, y, dx, dy);
                sum++;
            }

        } else { // 우
            if(check(x, y, dx, dy, 0)) { // 상
//                System.out.printf("%d, %d | %d, %d ~ 상\n", x, y, dx, dy);
                sum++;
            }

            if(check(x, y, dx, dy, 1)) { // 하
//                System.out.printf("%d, %d | %d, %d ~ 하\n", x, y, dx, dy);
                sum++;
            }

        }

        return sum;
    }

    public static boolean check(int x, int y, int dx, int dy, int d) {
        if(map[x + dirX[d]][y + dirY[d]] == -1 && map[dx + dirX[d]][dy + dirY[d]] == -1) { // 좌
            if((map[x][y] & (1 << d)) == 0 && (map[dx][dy] & (1 << d)) == 0) {
                map[x][y] |= 1 << d;
                map[dx][dy] |= 1 << d;
                return true;
            }
        }

        return false;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
