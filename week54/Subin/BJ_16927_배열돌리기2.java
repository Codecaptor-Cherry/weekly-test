import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16927_배열돌리기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            int edgeLen = 2 * (n + m) - 4 - 8 * i;
            int rot = r % edgeLen; // 회전 횟수
            for (int j = 0; j < rot; j++) {
                rotate(n, m, i, a);
            }
        }

        print(n, m, a);
    }

    private static void rotate(int n, int m, int start, int[][] a) {
        int tmp = a[start][start];

        for (int i = start; i < m - start - 1; i++) {
            a[start][i] = a[start][i + 1];
        }

        for (int i = start; i < n - start - 1; i++) {
            a[i][m - start - 1] = a[i + 1][m - start - 1];
        }

        for (int i = m - start - 1; i > start; i--) {
            a[n - start - 1][i] = a[n - start - 1][i - 1];
        }

        for (int i = n - start - 1; i > start; i--) {
            a[i][start] = a[i - 1][start];
        }

        a[start + 1][start] = tmp;
    }

    private static void print(int n, int m, int[][] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(a[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
