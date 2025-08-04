
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge {
    int start;
    int end;
    int weight;

    public Edge (int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class Main {

    static int[] parent;
    static ArrayList<Edge> edgeList;

    public static void main(String[] args) throws IOException {



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[] gender = new char[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) gender[i] = st.nextToken().charAt(0);

        edgeList = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(start, end, weight));
        }

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        Collections.sort(edgeList, (o1, o2) -> o1.weight - o2.weight);

        int cnt = 0;
        int ans = 0;

        for(int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);

            if(find(edge.start) != find(edge.end)) {
                if(gender[edge.start] != gender[edge.end]) {
                    cnt++;
                    ans += edge.weight;

                    union(edge.start, edge.end);
                }
            }
        }

        System.out.println(cnt != N - 1 ? -1 : ans);
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) parent[y] = x;
    }

}
