package saturday.sat241116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 직사각형 격자판 내에서 이동하는 "넴모"~
 * 1. 비어 있는 칸을 임의로 골라 넴모 올려놓기
 * 2. 넴모가 올라칸 칸 네 개가 2 x 2 사각형을 이루면 넴모 지우기
 * 격자판 위에 없앨 수 있는 넴모가 없으면 게임 stop
 * 넴모가 게임을 그만두었을 때 나올 수 있는 넴모의 배치 가짓수 구하기
 *
 * 1. (x, y)에 넴모 올리기 or 안올리기
 * 2. 해당 위치를 기준으로 2 x 2 사각형을 이루면 return
 * 3. 마지막 칸까지 완성되면 ans++
 */
public class BJ_14712_넴모넴모Easy {
    static int N, M, ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());

        int[][] map = new int[N][M];

        func(0, 0, map);

        System.out.println(ans);
    }

    public static void print(int[][] map) {
        for(int[] arr : map) {
            for(int k : arr) {
                System.out.print(k + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
    public static void func(int x, int y, int[][] map) {
        if(x >= N) {
            ans++;
            return;
        }

        int nx, ny;

        if((y + 1) % M == 0) {
            nx = x + 1;
            ny = 0;
        } else {
            nx = x;
            ny = y + 1;
        }

        map[x][y] = 1;

        // 2 x 2 체크
        if(checkReg(x, y, map)) {
            func(nx, ny, map);
        }

        map[x][y] = 0;
        func(nx, ny, map);
    }

    public static boolean checkReg(int x, int y, int[][] map) {
        if(x - 1 < 0 || y - 1 < 0) {
            return true;
        }

        if(map[x - 1][y - 1] == 1 && map[x - 1][y] == 1 && map[x][y - 1] == 1) {
            return false;
        }

        return true;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
