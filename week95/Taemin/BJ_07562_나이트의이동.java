import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_07562_나이트의이동 {

    static int size;
    static int INF = (int)1e9;

    static int[][] directions = {
            { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 },
            { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }
    };

    static class Horse {
        int c;
        int r;
        int step;

        public Horse(int c, int r, int step) {
            this.c = c;
            this.r = r;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int numOfTest = Integer.parseInt(br.readLine());

        for (int t = 0; t < numOfTest; t++) {
            size = Integer.parseInt(br.readLine());
            int[] from = Arrays.stream(br.readLine().split(" "))
                               .mapToInt(Integer::parseInt).toArray();
            int[] to = Arrays.stream(br.readLine().split(" "))
                             .mapToInt(Integer::parseInt).toArray();

            int[][] map = new int[size][size];
            for (int r = 0; r < size; r++) {
                Arrays.fill(map[r], INF);
            }

            Queue<Horse> queue = new LinkedList<Horse>();
            queue.add(new Horse(from[0], from[1], 0));
            map[from[0]][from[1]] = 0;

            while (!queue.isEmpty()) {
                Horse cur = queue.poll();
                int c = cur.c;
                int r = cur.r;
                int step = cur.step;

                for (int d = 0; d < 8; d++) {
                    int nextC = c + directions[d][0];
                    int nextR = r + directions[d][1];

                    if (!validateRange(nextC, nextR)) continue;

                    if (map[nextC][nextR] > step + 1) {
                        map[nextC][nextR] = step + 1;
                        queue.add(new Horse(nextC, nextR, step + 1));
                    }
                }
            }

            sb.append(map[to[0]][to[1]]).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static boolean validateRange(int c, int r) {
        return (c >= 0 && c < size && r >= 0 && r < size);
    }
}
