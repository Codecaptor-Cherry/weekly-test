import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int num = (int) Math.pow(2, h); // 말단 직원 수
        int[][] work = new int[num][k];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                work[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 상사의 업무 순서 구하기
        for (int i = 1; i <= h; i++) {
            num /= 2; k *= 2;
            int[][] newWork = new int[num][k];

            for (int j = 0; j < num; j++) {
                // 왼쪽 먼저? 오른쪽 먼저?
                int first = j * 2;
                int second = j * 2 + 1;                
                if (i % 2 == 1) {
                    first = j * 2 + 1;
                    second = j * 2;
                }

                for (int l = 0; l < k / 2; l++) {
                    newWork[j][l * 2] = work[first][l];
                    newWork[j][l * 2 + 1] = work[second][l];
                }
            }

            work = newWork;
        }

        int ans = 0;
        for (int i = 0; i < r - h && i < work[0].length; i++) {
            ans += work[0][i];
        }
        System.out.println(ans);
    }
}
