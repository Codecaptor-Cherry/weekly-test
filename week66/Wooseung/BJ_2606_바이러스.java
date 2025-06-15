
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visit;
    static int N;
    static int[][] map;
    static int ans;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());
        visit = new boolean[N + 1];
        int INF = (int) 1e9;
        ans = 0;

        map = new int[N + 1][N + 1];

        for(int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = map[y][x] = 1;
        }

        dfs(1);
        System.out.println(ans);
    }

    static void dfs(int start) {
        visit[start] = true;

        for(int i = 1; i <= N; i++) {
            if(map[start][i] == 1 && !visit[i]) {
                ans++;
                dfs(i);
            }
        }
    }

}
