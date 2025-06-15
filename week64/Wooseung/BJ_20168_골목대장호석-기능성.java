import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int cur, m;

        public Point(int cur, int m) {
            this.cur = cur;
            this.m = m;
        }
    }

    static int N, M, A, B, C, ans;
    static List<int[]>[] listArr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        listArr = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++) listArr[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            listArr[start].add(new int[]{end, cost});
            listArr[end].add(new int[]{start, cost});
        }
        dfs(A, B, C, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void dfs(int cur, int dst, int money, int max) {

        if(cur == dst) {
            ans = Math.min(ans, max);
            return;
        }

        for(int[] arr : listArr[cur]) {
            int next = arr[0];
            int cost = arr[1];
            if(visited[next]) continue;
            if(money < cost) continue;
            visited[next] = true;
            int temp = Math.max(max, cost);
            dfs(next, dst, money - cost, temp);
            visited[next] = false;
        }

    }

}
