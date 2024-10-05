import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_14621_나만안되는연애 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfSchool = Integer.parseInt(st.nextToken());
        int numOfRoad = Integer.parseInt(st.nextToken());

        char[] schools = new char[numOfSchool + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= numOfSchool; i++) {
            schools[i] = st.nextToken().charAt(0);
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();
        for (int i = 0; i < numOfRoad; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (schools[from] == schools[to]) continue;
            pq.add(new Road(from, to, distance));
        }

        int[] parents = new int[numOfSchool + 1];
        for (int i = 0; i <= numOfSchool; i++) {
            parents[i] = i;
        }

        int count = 0;
        int total = 0;
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            int curFrom = road.from;
            int curTo = road.to;
            int curDistance = road.distance;

            if (findParent(parents, curFrom) != findParent(parents, curTo)) {
                union(parents, curFrom, curTo);
                count++;
                total += curDistance;
            }

            if (count == numOfSchool - 1) break;
        }

        if (count == numOfSchool -1) System.out.print(total);
        else System.out.print(-1);
    }

    private static int findParent(int[] parents, int x) {
        if (parents[x] == x) return x;
        return parents[x] = findParent(parents, parents[x]);
    }

    private static void union(int[] parents, int a, int b) {
        a = findParent(parents, a);
        b = findParent(parents, b);

        if (a < b) parents[b] = a;
        else parents[a] = b;
    }


    private static class Road implements Comparable<Road> {
        int from;
        int to;
        int distance;

        public Road(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
