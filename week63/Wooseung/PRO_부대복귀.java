import java.util.*;

class Solution {
    static int[] cost;
    static List<Integer>[] graph;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        cost = new int[n + 1];
        graph = new List[n + 1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for(int[] arr : roads) {
            graph[arr[0]].add(arr[1]);
            graph[arr[1]].add(arr[0]);
        }
        Arrays.fill(cost, -1);
        bfs(destination);
        
        for(int i = 0; i < answer.length; i++) answer[i] = cost[sources[i]];
        
        return answer;
    }
    
    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        cost[start] = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            int len = graph[cur].size();
            for(int i = 0; i < len; i++) {
                int next = graph[cur].get(i);
                if(cost[next] == -1) {
                    cost[next] = cost[cur] + 1;
                    q.add(next);
                }
            }
        }
    }
}
