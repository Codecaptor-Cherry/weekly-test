import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;

        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            adj[x].add(y);
            adj[y].add(x);
        }

        System.out.println(bfs(adj, a, b));
    }

    private static int bfs(List<Integer>[] adj, int a, int b) {
        Queue<int[]> q = new ArrayDeque();
        q.add(new int[]{a, 0});

        boolean[] visited = new boolean[adj.length];
        visited[a] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            visited[poll[0]] = true;

            if (poll[0] == b) return poll[1];

            for (int next : adj[poll[0]]) {
                if (!visited[next]) q.add(new int[]{next, poll[1] + 1});
            }
        }

        return -1;
    }

}
