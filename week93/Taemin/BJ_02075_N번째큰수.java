import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] array = new int[n][n];
        for (int r = 0; r < n; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                array[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        for (int c = 0; c < n; c++) {
            pq.add(new int[]{0, c, array[0][c]});
        }

        int answer = 0;
        int left = n * n;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            left--;

            if (cur[0] + 1 < n) {
                pq.add(new int[]{cur[0]+1, cur[1], array[cur[0]+1][cur[1]]});
            }
            
            if (left == n - 1) {
                answer = cur[2];
                break;
            }
        }

        System.out.println(answer);
    }
}
