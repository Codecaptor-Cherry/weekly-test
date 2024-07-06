package saturday.sat240706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int a, b, c;

    Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Node o) {
        return this.c - o.c; // cost 오름차순
    }
}

public class BJ_1922_네트워크연결 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine()); // 컴퓨터의 수
        int M = stoi(br.readLine()); // 연결 선 수

        StringTokenizer st;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int c = stoi(st.nextToken());

            pq.offer(new Node(a, b, c));
        }

        int ans = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(findParent(now.a) != findParent(now.b)) {
                ans += now.c;
                unionParent(now.a, now.b);
            }

        }

        System.out.println(ans);
    }

    public static int findParent(int x) {
        if(x == parent[x]) { // 루트가 되면 끝
            return x;
        } else {
            return parent[x] = findParent(parent[x]);
        }
    }

    public static void unionParent(int a, int b) {
        int fa = findParent(a);
        int fb = findParent(b);

        if(fa != fb) parent[fa] = fb;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
