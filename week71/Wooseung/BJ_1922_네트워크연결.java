
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int a, b, cost;

        public Point(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        int ans = 0;
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;
        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new Point(a, b, cost));
        }

        Collections.sort(list, (o1, o2) -> {
            return o1.cost - o2.cost;
        });

        for (int i = 0; i < list.size(); i++) {
            Point p = list.get(i);
            if (find(p.a) != find(p.b)) {
                ans += p.cost;
                union(p.a, p.b);
            }
        }

        System.out.println(ans);
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[y] = x;
    }
}
