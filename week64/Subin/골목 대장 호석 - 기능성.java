import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://velog.io/@mj3242/%EB%B0%B1%EC%A4%80-20168-%EA%B3%A8%EB%AA%A9-%EB%8C%80%EC%9E%A5-%ED%98%B8%EC%84%9D-%EA%B8%B0%EB%8A%A5%EC%84%B1
public class Main {

    private static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken());

        List<Node>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to, cost));
            adj[to].add(new Node(from, cost));
        }

        System.out.println(binarySearch(adj, n, a, b, c));
    }

    private static final int INF = (int) 1e9;

    private static int binarySearch(List<Node>[] adj, int n, int a, int b, int c) {
        int lo = 1, hi = 1001, mid;
        int answer = -1;

        while (lo <= hi) {
            mid = (lo + hi) / 2;

            int[] dist = getDijkstra(adj, n, a, mid);
            if (dist[b] <= c) {
                answer = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return answer;
    }

    private static int[] getDijkstra(List<Node>[] adj, int n, int a, int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[] cost = new int[n];
        Arrays.fill(cost, INF);

        cost[a] = 0;
        pq.add(new Node(a, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cost[cur.node] < cur.cost) continue;

            for (Node next : adj[cur.node]) {
                if (next.cost > x) continue;

                if (cost[next.node] > cost[cur.node] + next.cost) {
                    cost[next.node] = cost[cur.node] + next.cost;
                    pq.add(new Node(next.node, cost[next.node]));
                }
            }
        }

        return cost;
    }


}
