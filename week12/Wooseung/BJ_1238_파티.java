package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1238_파티 {

    static class Node implements Comparable<Node>{
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M, X;
    static List<Node>[] graph, r_graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int ans = 0;
        graph = new ArrayList[N + 1];
        r_graph = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            r_graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
            r_graph[end].add(new Node(start, cost));
        }
        int[] dist = dijkstra(graph);
        int[] r_dist = dijkstra(r_graph);

        for(int i = 1; i <= N; i++) ans = Math.max(ans, dist[i] + r_dist[i]);
        System.out.println(ans);
    }

    static int[] dijkstra(List<Node>[] list) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));

        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(!visited[cur.idx]) {
                visited[cur.idx] = true;

                for(Node node : list[cur.idx]) {
                    if(!visited[node.idx] && dist[node.idx] > dist[cur.idx] + node.cost) {
                        dist[node.idx] = dist[cur.idx] + node.cost;
                        pq.add(new Node(node.idx, dist[node.idx]));
                    }
                }
            }
        }
        return dist;
    }
}

