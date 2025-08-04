
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new int[N + 1][M + 1];

        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int cnt) {
        if(cnt == N * M) {
            ans++;
            return;
        }

        int r = cnt / M + 1;
        int c = cnt % M + 1;

        if(map[r - 1][c] == 1 && map[r][c - 1] == 1 && map[r - 1][c - 1] == 1) dfs(cnt + 1);
        else {
            dfs(cnt + 1);
            map[r][c] = 1;
            dfs(cnt + 1);
            map[r][c] = 0;
        }
    }

}
