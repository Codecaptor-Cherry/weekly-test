
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int ans, N;
    static boolean[] visited;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        ans = -1;

        graph = new List[N + 1];
        for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        dfs(start, end, 0);

        System.out.println(ans);

    }

    static void dfs(int cur, int end, int cnt) {
        if(cur == end) {
            ans = cnt;
            return;
        }

        for(int i = 0; i < graph[cur].size(); i++) {
            if(!visited[graph[cur].get(i)]) {
                visited[graph[cur].get(i)] = true;
                dfs(graph[cur].get(i), end, cnt + 1);
                visited[graph[cur].get(i)] = false;
            }
        }

    }

}
