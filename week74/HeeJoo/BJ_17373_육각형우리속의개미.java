package saturday.sat240720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 무한히 많은 정육각형이 서로 맞닿아 놓인 형태의 개미 우리
 * 개미는 변을 통해 이동할 수 있음
 * 세 정육각형이 서로 맞닿아 있는 어떤 점에 개미 하나 놓기
 * 개미는 점과 연결된 세 변 중 하나를 향해 이동
 * 변이 세 갈래로 갈라지는 점에 도착하면, 자신이 이동해온 변을 제외한 나머지 두 변 중 하나를 골라 이동
 * 이전에 방문했던 지점에 도착하면 탐색 멈춤
 * 탐색을 멈췄을 때, 방향을 회전한 횟수가 정확히 N번이 되는 경우의 수 구하기
 * 궤적은 동일해도 개미의 이동 방향에 따라 경로 구별
 *
 * !!! 궤적은 동일해도 개미의 이동 방향에 따라 경로 구별 -> 회전해서 동일한 궤적을 만들 수 있으면 동일한 경우의 수
 * ex) N = 5일 때, 반시계, 시계 방향 육각형 궤적 ~ 답은 2
 * 처음에는 12시, 5시, 7시 출발로 총 6가지 경우의 수라고 생각했으나 모두 동일한 궤적(육각형)이므로 이동 방향으로 경로 구별
 * 해당 궤적을 만드는 이동 방향이 중요
 */
public class BJ_17373_육각형우리속의개미 {
    static int N, ans = 0;

    // 12시, 1시, 5시, 6시, 7시, 10시
    static int[] dirX = new int[]{-1, -1, 1, 1, 1, -1};
    static int[] dirY = new int[]{0, 1, 1, 0, -1, -1};
    static int[][] availableMove = new int[][]{{1, 5}, {0, 2}, {1, 3}, {2, 4}, {3, 5}, {0, 4}}; // 직전 방향에 따라 가능한 다음 이동방향 고정
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        if(N < 5) {
            System.out.println(0);
            System.exit(0);
        }

        boolean[][] map = new boolean[50][50];
        map[25][25] = true; // 시작지점
        dfs(0, 0, 24, 25, map); // 다음 점부터 시작. 첫 이동은 회전 수 포함x

        System.out.println(ans);
    }

    public static void dfs(int n, int dir, int x, int y, boolean[][] map) {
        if(map[x][y]) { // 탐색 종료 조건
            if(n == N) { // 정답 포함 조건
                ans++;
            }

            return;
        }

        if(n == N) { // 가지치기
            return;
        }

        map[x][y] = true; // 이번 좌표 방문 처리
        for(int i = 0; i < 2; i++) {
            int d = availableMove[dir][i]; // 다음 이동 방향
            int dx = x + dirX[d];
            int dy = y + dirY[d];

            dfs(n + 1, d, dx, dy, map);
        }
        map[x][y] = false; // 방문 처리 해제
      
    }
}
