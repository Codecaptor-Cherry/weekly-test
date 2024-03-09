import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BJ_11559_PuyoPuyo {
    private static final int ROW = 12, COL = 6;
    private final static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[ROW][];
        for (int i = 0; i < ROW; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        while (bomb(map)) {
            drop(map);
            ++answer;
        }

        System.out.println(answer);
    }

    // 1. 현재 필드에서 터트릴 모든 뿌요 찾기 -> .으로 바꾸기
    private static boolean bomb(char[][] map) {
        int cnt = 0;
        boolean[][] visited = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (map[i][j] != '.' && !visited[i][j]) {
                    if (bfs(map, i, j, visited) >= 4) {
                        fill(map, i, j, '.');
                        ++cnt;
                    }
                }
            }
        }
        return cnt > 0;
    }

    private static int bfs(char[][] map, int i, int j, boolean[][] visited) {
        Queue<Point> q = new ArrayDeque<>();

        q.add(new Point(i, j));
        visited[i][j] = true;

        char flag = map[i][j];
        int cnt = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();
            ++cnt;

            for (int[] d : dir) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || nx >= ROW || ny < 0 || ny >= COL ||
                        visited[nx][ny] || map[nx][ny] != flag) continue;

                q.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        return cnt;
    }

    private static void fill(char[][] map, int i, int j, char c) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(i, j));

        char flag = map[i][j];

        while (!q.isEmpty()) {
            Point p = q.poll();
            map[p.x][p.y] = c;

            for (int[] d : dir) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || nx >= ROW || ny < 0 || ny >= COL ||
                        map[nx][ny] != flag) continue;

                q.add(new Point(nx, ny));
            }
        }
    }

    // 2. 아래로 이동
    private static void drop(char[][] map) {
        for (int c = 0; c < COL; c++) {
            Stack<Character> stack = new Stack();
            for (int r = 0; r < ROW; r++) {
                if (map[r][c] != '.') {
                    stack.push(map[r][c]);
                    map[r][c] = '.';
                }
            }

            int r = ROW - 1;
            while (!stack.isEmpty()) {
                map[r--][c] = stack.pop();
            }
        }
    }
}
