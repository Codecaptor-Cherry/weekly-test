package saturday.sat241026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 총 N개의 고층 빌딩 중 가장 많은 고층 빌딩이 보이는 고층 빌딩 찾기
 * i번째 빌딩 = (i, 0), (i, 높이)
 * 빌딩 A에서 다른 빌딩 B를 보려면, 두 지붕을 잇는 선분이 A와 B를 제외한 다른 고층 빌딩을 지나거나 접하지 않아야 함
 * 가장 많은 고층 빌딩이 보이는 빌딩을 구하고, 거기서 보이는 빌딩의 수 구하기
 *
 * N <= 50이니까 브루트포스 가능
 * 빌딩 A와 빌딩 B를 잇는 선분이 다른 빌딩에 걸치면 안됨 ... 기울기
 *
 */
public class BJ_1027_고층건물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        double[] heights = new double[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            heights[i] = stod(st.nextToken());
        }

        int[][] map = new int[N][N]; // i번 기둥과 j번 기둥의 연결 여부
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if(func(i, j, heights)) {
                    map[i][j] = 1;
                    map[j][i] = 1;
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1) {
                    sum++;
                }
            }
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }

    // 1. 직선 방정식 구하기 : y = mx + k
    // 2. 중간에 위치하는 빌딩의 높이가 더 크면 불가능
    public static boolean func(int x, int y, double[] heights) {
        double m = (heights[y] - heights[x]) / (double)(y - x);
        double k = heights[x] - m * (double)x;

        for(double i = x + 1; i < y; i++) {
            if(m * i + k <= heights[(int)i]) {
                return false;
            }
        }

        return true;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }

    public static double stod(String str) {
        return Double.parseDouble(str);
    }
}
