import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] domino = new int[n][m];
        boolean[][] isDown = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                domino[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;
        for (int i = 0; i < r; i++) {
            // attack
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                char d = st.nextToken().charAt(0);
                score += attack(domino, isDown, x, y, d);
            }

            // defense
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                defense(isDown, x, y);
            }
        }

        System.out.println(score);
        print(isDown);
    }

    private static void print(boolean[][] isDown) {
        for (int i = 0; i < isDown.length; i++) {
            for (int j = 0; j < isDown[0].length; j++) {
                System.out.print(isDown[i][j] ? "F " : "S ");
            }
            System.out.println();
        }
    }

    private static int attack(int[][] domino, boolean[][] isDown, int x, int y, char d) {
        if (isDown[x][y]) return 0;

        int[] dir = getDir(d);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y, domino[x][y]});
        isDown[x][y] = true;

        int score = 1;

        while (!q.isEmpty()) {
            int[] poll = q.poll();

            for (int i = 1; i < poll[2]; i++) {
                int nx = poll[0] + dir[0] * i;
                int ny = poll[1] + dir[1] * i;

                if (!checkRange(domino, nx, ny) || isDown[nx][ny]) continue;

                q.add(new int[]{nx, ny, domino[nx][ny]});
                isDown[nx][ny] = true;
                ++score;
            }
        }
        return score;
    }

    private static int[] getDir(char d) {
        if (d == 'E') return new int[]{0, 1};
        if (d == 'W') return new int[]{0, -1};
        if (d == 'S') return new int[]{1, 0};
        if (d == 'N') return new int[]{-1, 0};
        return new int[]{0, 0};
    }

    private static boolean checkRange(int[][] domino, int nx, int ny) {
        return nx >= 0 && nx < domino.length && ny >= 0 && ny < domino[0].length;
    }

    private static void defense(boolean[][] isDown, int x, int y) {
        isDown[x][y] = false;
    }
}
