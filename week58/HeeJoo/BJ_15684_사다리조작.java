package saturday.sat240406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개 세로선, M개 가로선
 * 세로선마다 가로선을 놓을 수 있는 위치의 개수 H
 * 두 가로선이 연속하거나 서로 접하면 안됨
 * 사다리에 가로선을 추가해서 결과 조작하기
 * i번 세로선의 결과가 i번이 나오도록 조작할 때, 추가해야 하는 가로선 개수의 최솟값 구하기
 * 만약 정답이 3보다 큰 값이면 -1 / 불가능한 경우에도 -1

 * 개선 전 1364ms
 * 개선 후 96ms
 * start를 이용해서 경우의 수가 겹치지 않도록 한 것과 limit를 0부터 시작해서 만족하면 종료한 게 영향이 큼
 */
public class BJ_15684_사다리조작 {
    static int N, M, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로선 개수
        M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 가로선 개수
        H = Integer.parseInt(st.nextToken()); // 가로선 개수

        // 사다리 정보 입력
//        int[][] map = new int[H + 1][N + 1]; // 개선 전 map 크기
        int[][] map = new int[H][N];
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

//            map[a][b] = -i;
//            map[a][b + 1] = -i;

            // 개선 1. 1, -1로 가는 방향만 저장
            map[a][b] = 1;
            map[a][b + 1] = -1;
        }

        // 개선 2. 사다리가 자기의 위치로 돌아가기 위해서는 반드시 연결 다리의 수가 짝수여야 함
        // 최대 3개까지 다리를 추가할 수 있으니 연결 다리가 홀수인 곳이 3군데 이상이면 실패
        if(betweenOdd(map) > 3){
            System.out.println(-1);
            return;
        }

//        dfs(map, 0);

        // 개선 3. 최소 개수만 구하면 되니 조합으로 다리연결 경우의 수 구하기
        for(int i = 0; i <= 3; i++) {
            if(comb(map, 0, 0,  i)) { // i개만 추가해서 연결이 가능한 경우 종료
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);

    }

    public static boolean comb(int[][] map, int start, int cnt, int limit) {
        if(cnt == limit) { // 조합 완성
            if(check(map)) { // 회귀 사다리인지 확인
                return true;
            }

            return false;
        }

        // 개선 4. start로 가로선 추가 시작 지점을 지정하여 반복된 경우가 없게 하기
        // 이전 방법은 이중 for문을 이용해서 이미 지나친 곳을 다시 연결함;;
        for(int idx = start; idx < N * H; idx++) {
            int i = idx / N; // 가로
            int j = idx % N; // 세로

            if(j == N - 1) { // 맨 오른쪽은 가로선 연결 불가능
                continue;
            }

            if(map[i][j] != 0 || map[i][j + 1] != 0) { // 현재 위치에 연결된 가로선이 있거나, 오른쪽으로 연속된 가로선인 경우 불가능
                continue;
            }

            map[i][j] = 1;
            map[i][j + 1] = -1;

            if(comb(map, start + 1, cnt + 1, limit)) { // 만족한 경우 종료
                return true;
            }

            map[i][j] = map[i][j + 1] = 0;

        }

        return false;
    }

    public static int betweenOdd(int[][] map) {
        int result = 0;

        for(int i = 0; i < N - 1; i++) { // 사다리 사이는 N - 1개
            int cnt = 0;
            for(int j = 0; j < H; j++) {
                if(map[j][i] == 1) { // 연결다리 존재
                    cnt++;
                }
            }

            if(cnt % 2 == 1) { // 사다리 사이의 연결다리 개수가 홀수이면 +1
                result++;
            }
        }

        return result;
    }

//    public static void dfs(int[][] map, int depth) {
//        if(check(map)) { // i -> i인지 확인
//            ans = Math.min(ans, depth);
//            return;
//        }
//
//        if(depth == 3 || depth > M) { // 3보다 큰 경우는 고려x
//            return;
//        }
//
//        for(int i = 1; i <= H; i++) {
//            for(int j = 1; j < N; j++) {
//                if(map[i][j] == 0 && map[i][j+1] == 0) { // 현재 위치에 가로선이 없고, 연속한 가로선이 아닐 때만 가로선 추가
////                    map[i][j] = depth + 1;
////                    map[i][j+1] = depth + 1;
////
////                    dfs(map, depth + 1);
////
////                    map[i][j] = 0;
////                    map[i][j+1] = 0;
//
//                    map[i][j] = 1;
//                    map[i][j+1] = -1;
//
//                    dfs(map, depth + 1);
//
//                    map[i][j] = 0;
//                    map[i][j+1] = 0;
//                }
//            }
//        }
//
//    }

    public static boolean check(int[][] map) {
//        for(int i = 1; i <= N; i++) {
        for(int i = 0; i < N; i++) {
            if(ladder(map, i) != i) {
                return false;
            }
        }

        return true;
    }

    public static int ladder(int[][] map, int idx) {
        for(int row = 0; row < H; row++) {
            // 연결 가로선 존재할 때만 이동
            if(map[row][idx] == 1) {
                idx++; // 오른쪽으로 이동
            } else if(map[row][idx] == -1) {
                idx--; // 왼쪽으로 이동
            }
        }

        return idx;
    }

    public static void print(int[][] map) {
        for(int[] arr : map) {
            for(int k : arr) {
                System.out.printf("%d ", k);
            }
            System.out.println();
        }
        System.out.println();
    }
}
