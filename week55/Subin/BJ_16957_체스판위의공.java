import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
상하좌우 중 가장 수가 작은 칸으로 이동
이동할 칸이 없다면(전부 자신보다 크다면) 멈춤

union-find + dp
 */
public class BJ_16957_체스판위의공 {
    private static int[] parent;
    private static int[] cnt;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cur = i * m + j;
                int next = i * m + j;
                for (int[] d : dir) {
                    int x = i + d[0];
                    int y = j + d[1];

                    if (x < 0 || x >= n || y < 0 || y >= m) continue;

                    if (map[x][y] < map[next / m][next % m]) {
                        next = x * m + y;
                    }
                }
                union(cur, next);
            }
        }

        print(n, m, cnt);
    }

    private static void init(int n, int m) {
        cnt = new int[n * m];
        Arrays.fill(cnt, 1);

        parent = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;

        parent[x] = y;
        cnt[y] += cnt[x];
        cnt[x] = 0;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void print(int n, int m, int[] cnt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(cnt[i * m + j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
