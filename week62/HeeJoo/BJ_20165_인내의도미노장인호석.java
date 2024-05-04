package saturday.sat240504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 공격 : 도미노 넘어뜨리기
 * 수비 : 도미노 세우기
 * N x M 격자 모양 게임판의 각 격자에 도미노 세우기. 각 도미노는 1 이상 5 이하의 높이
 * 매 라운드는 선공, 후수
 * 공격수는 특정 격자에 놓인 도미노를 동서남북 중 원하는 방향으로 넘어뜨리기
 * 길이가 K인 도미노가 특정 방향으로 넘어지면, 그 방향으로 K-1개의 도미노 연쇄 넘김 발생
 * 수비수는 넘어져 있는 도미노들 중 원하는 하나를 다시 세울 수 있음. 넘어지지 않은 도미노를 선택할 경우 아무 일 xxx
 * 총 R번의 라운드동안 위 과정 반복
 * 매 라운드마다 해당 라운드에 공격수가 넘어뜨린 도미노의 개수 총합이 공격수의 점수
 *
 * input :  초기 도미노 판, 각 라운드 행동
 * output : 공격수의 점수, 게임판 최종 상태
 */

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class BJ_20165_인내의도미노장인호석 {
    static String[] dirArr = new String[]{"E", "W", "S", "N"}; // 동서남북
    static int[] dirX = new int[]{0, 0, 1, -1};
    static int[] dirY = new int[]{1, -1, 0, 0};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        int R = Integer.parseInt(st.nextToken()); // 라운드

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int t = 0; t < R * 2; t++) { // 행동 입력. 선공후수
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            if (t % 2 == 0) { // 선공
                String dir = st.nextToken();

                ans += atk(map, x, y, cmdToDir(dir));
            } else if(t % 2 != 0) { // 후수
                def(map, x, y);
            }
        }

        System.out.println(ans);
        print(map);
    }

    public static int atk(int[][] map, int x, int y, int dir) {
        int result = 0;

        if(map[x][y] <= 0) { // 이미 넘어진 도미노
            return result;
        }

        Queue<Point> queue = new ArrayDeque();
        queue.offer(new Point(x, y));

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            if(map[now.x][now.y] <= 0) {
                continue;
            }

            // 1. 이번 순서 꺼내고 false
            result++;
            int length = map[now.x][now.y] - 1; // 자기자신부터 1
            map[now.x][now.y] = -map[now.x][now.y];

            int dx = now.x;
            int dy = now.y;
            for(int i = 0; i < length; i++) {
                dx += dirX[dir];
                dy += dirY[dir];

                // 2. 쓰러질 수 있는 도미노 큐에 넣기
                if (!checkRange(dx, dy)) { // 일방향이라 벗어나는 순간 stop
                    break;
                }

                if(map[dx][dy] > 0) {
                    queue.offer(new Point(dx, dy));
                }
            }
        }

        return result;
    }

    public static void def(int[][] map, int x, int y) {
        if(map[x][y] <= 0) { // 이미 넘어진 도미노
            map[x][y] = -map[x][y];
        }
    }

    public static int cmdToDir(String cmd) {
        for(int idx = 0; idx < 4; idx++) {
            if(dirArr[idx].equals(cmd)) {
                return idx;
            }
        }

        return -1;
    }

    public static boolean checkRange(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }

        return true;
    }

    public static void print(int[][] map) {
        for(int[] arr : map) {
            for(int k : arr) {
                System.out.print(k <= 0 ? "F " : "S ");
            }
            System.out.println();
        }
    }
}
