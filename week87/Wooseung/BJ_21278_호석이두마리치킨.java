
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = 0;
        int y = 0;
        int ans = Integer.MAX_VALUE;

        int[][] map = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(map[i], 101);
            map[i][i] = 0;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[b][a] = 1;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for(int i = 1; i <= N - 1; i++) {
            for(int j = i + 1; j <= N; j++) {
                int sum = 0;
                for(int k = 1; k <= N; k++) {
                    sum += Math.min(map[i][k], map[j][k]);
                }
                if(sum * 2 < ans) {
                    x = i;
                    y = j;
                    ans = sum * 2;
                }
            }
        }
        System.out.println(x + " " + y + " " + ans);
    }

}
