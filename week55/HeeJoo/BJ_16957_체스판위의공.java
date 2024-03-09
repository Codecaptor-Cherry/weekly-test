package saturday.sat240309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * R x C 체스판의 각 칸에 모두 서로 다른 정수 하나씩
 * 각 칸 위에 공을 하나씩 놓고 다음과 같은 규칙에 의해 자동으로 움직인다.
 * 규칙 1. 인접한 8방향에 적힌 모든 정수가 현재 칸에 적힌 수보다 크면 이동을 멈춤
 * 규칙 2. 그 외의 경우에는 가장 작은 정수가 있는 칸으로 이동
 * 한 칸에 여러 개의 공이 있을 수 있다.
 * 공이 더 이상 움직이지 않을 때, 각 칸에 있는 공의 개수 구하기
 *
 * 각 칸에서 이동하는 칸 구하기
 * 루트 칸으로 변경
 */
class Point {
    int val, nextIdx, nextVal;

    Point(int val, int nextIdx, int nextVal) {
        this.val = val;
        this.nextIdx = nextIdx;
        this.nextVal = nextVal;
    }
}

public class BJ_16957_체스판위의공 {
    static int R, C;
    static int[] cnt;
    static Point[][] map;
    static int[][] dir = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}}; // 인접 8방향
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new Point[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = new Point(val, -1, val);
            }
        }

        cnt = new int[300000 + 1];

        chess();
    }

    public static void chess() {
        // 1. 각 칸의 다음 칸 구하기
        getNext();

        // 2. 각 칸의 루트 칸 구하기
        getRoot();

        // 3. 공의 개수 구하기
        getBallCount();
    }

    public static void getBallCount() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(cnt[map[i][j].val] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void getRoot() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j].val == map[i][j].nextVal) { // 현재 칸이 루트
                    cnt[map[i][j].val]++;
                    continue;
                }

                // 다음 칸 좌표 정보
                int dx = i + dir[map[i][j].nextIdx][0];
                int dy = j + dir[map[i][j].nextIdx][1];
                int next = map[dx][dy].val;


                while(next != map[dx][dy].nextVal) { // 다음 칸이 루트가 아닐 때까지
                    next = map[dx][dy].nextVal;

                    // 이렇게 해줘야 오류 안남 ㅠㅠ
                    // 초반에 map[dx][dy].nextIdx 썼다가 갱신된 dx로 dy 갱신해서 계속 invalid array index 오류 발생
                    int nextIdx = map[dx][dy].nextIdx;
                    dx += dir[nextIdx][0];
                    dy += dir[nextIdx][1];
                }

                map[i][j].nextVal = next; // 최종 부모로 변경
                cnt[next]++;
            }
        }
    }

    public static void getNext() {
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for(int k = 0; k < 8; k++) {
                    int dx = i + dir[k][0];
                    int dy = j + dir[k][1];

                    if(!checkRange(dx, dy)) {
                        continue;
                    }

                    if(map[i][j].nextVal > map[dx][dy].val) { // 가장 작은 정수가 다음 칸
                        map[i][j].nextIdx = k;
                        map[i][j].nextVal = map[dx][dy].val;
                    }
                }
            }
        }
    }

    public static boolean checkRange(int x, int y) {
        if(x < 0 || x >= R) return false;
        if(y < 0 || y >= C) return  false;

        return true;
    }
}
