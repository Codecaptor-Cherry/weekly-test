package saturday.sat240803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_3020_개똥벌레_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = stoi(inputs[0]);
        int H = stoi(inputs[1]);

        int[] down = new int[H + 2]; // 석순
        int[] up = new int[H + 2]; // 종유석
        for(int i = 0; i < N / 2; i++) {
            down[stoi(br.readLine())]++; // 높이가 input인 장애물의 개수
            up[H - stoi(br.readLine()) + 1]++; // 높이가 H - input + 1까지 오는 장애물의 개수
        }

        for(int i = 1; i <= H; i++) {
            down[i] += down[i - 1]; // 구간 i의 석순 장애물 누적합 ~ 오름차순
        }

        for(int i = H; i >= 1; i--) {
            // 종유석의 길이가 x이면 구간 x부터 H - x + 1까지 장애물 존재
            // 구간이 클수록 값이 작음
            up[i] += up[i + 1]; // 구간 i의 종유석 장애물 누적합 ~ 내림차순
        }

        int min = N, ans = 0;
        for(int i = 1; i <= H; i++) {
            // down : down 장애물 최대 개수(down[H]) - 구간 i 이전 장애물 개수(down[i - 1])
            // up : up 장애물 최대 개수(up[1]) - 구간 i 이전 장애물 개수(up[i + 1])
            int conflict  = (down[H] - down[i - 1]) + (up[1] - up[i + 1]);

            if(conflict < min) {
                min = conflict;
                ans = 1;
            } else if(conflict == min) {
                ans++;
            }
        }

        System.out.printf("%d %d", min, ans);

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
