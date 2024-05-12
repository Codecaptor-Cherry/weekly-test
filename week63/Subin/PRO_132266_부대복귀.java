import java.util.*;

class Node implements Comparable<Node> {
    int to;
    int cost;
    
    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}

class Solution {
    private final int INF = (int) 1e9;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Node>[] adj = getAdjList(n, roads);
        int[] dist = dijkstra(n, destination - 1, adj);
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i] - 1];
            if (answer[i] == INF) answer[i] = -1;
        }
        return answer;
    }
    
    private List<Node>[] getAdjList(int n, int[][] roads) {
        List<Node>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int r = road[0] - 1;
            int c = road[1] - 1;
    
            adj[r].add(new Node(c, 1));
            adj[c].add(new Node(r, 1));
        }
        return adj;
    }
    
    private int[] dijkstra(int n, int start, List<Node>[] adj) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        
        pq.add(new Node(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (Node next : adj[cur.to]) {
                if (dist[next.to] > dist[cur.to] + next.cost) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
        
        return dist;
    }
}
