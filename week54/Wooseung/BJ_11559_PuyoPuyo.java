
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static class Puyo {
        int r, c;
        public Puyo(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static char[][] arr;
    static boolean flag;

    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new char[12][6];
        int ans = 0;

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        while (true) {
            flag = false;
            bfs();
            down();
            if (!flag) break;
            ans++;
        }

        System.out.println(ans);
    }

    static void bfs() {

        Queue<Puyo> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[12][6];

        for (int r = 0; r < 12; r++) {
            for (int c = 0; c < 6; c++) {
                if (arr[r][c] != '.' && !visited[r][c]) {
                    List<Puyo> list = new ArrayList<>();
                    char curColor = arr[r][c];
                    q.add(new Puyo(r, c));
                    list.add(new Puyo(r, c));
                    visited[r][c] = true;

                    while (!q.isEmpty()) {
                        Puyo cur = q.poll();

                        for (int d = 0; d < 4; d++) {
                            int nr = cur.r + dr[d];
                            int nc = cur.c + dc[d];

                            if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6) continue;
                            if (!visited[nr][nc] && arr[nr][nc] == curColor) {
                                visited[nr][nc] = true;
                                q.add(new Puyo(nr, nc));
                                list.add(new Puyo(nr, nc));
                            }
                        }
                    }

                    if (list.size() >= 4) {
                        for (Puyo puyo : list) {
                            arr[puyo.r][puyo.c] = '.';
                            flag = true;
                        }
                    }
                }
            }
        }
    }

    static Queue<Character> check(int c) {

        Queue<Character> q = new ArrayDeque<>();
        for (int r = 11; r >= 0; r--) {
            if (arr[r][c] != '.') q.add(arr[r][c]);
        }

        return q;
    }

    static void down() {
        for (int c = 0; c < 6; c++) {
            Queue<Character> q = check(c);
            for (int r = 11; r >= 0; r--) {
                if (!q.isEmpty()) arr[r][c] = q.poll();
                else arr[r][c] = '.';
            }
        }
    }
}
