
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] nodes;
    static int N;
    static int[] time;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N + 1];
        time = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
            time[i] = -1;
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) break;
                nodes[i].add(num);
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < M; i++) {
            int node = Integer.parseInt(st.nextToken());
            q.add(node);
            time[node] = 0;
        }

        bfs(q);

        for(int i = 1; i < time.length; i++) {
            System.out.print(time[i] + " ");
        }

    }

    static void bfs(Queue<Integer> q) {
        int[] cnt = new int[N + 1];

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : nodes[cur]) {
                cnt[next]++;

                if(time[next] == -1 && (nodes[next].size() + 1) / 2 <= cnt[next]) {
                    q.add(next);
                    time[next] = time[cur] + 1;
                }
            }
        }
    }

}
