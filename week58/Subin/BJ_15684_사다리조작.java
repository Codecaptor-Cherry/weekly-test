import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, h, ans;
    private static boolean finish;
    private static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        for (int i = 0; i <= 3; i++) {
            ans = i;
            solve(1, 1, 0);
            if (finish) break;
        }

        System.out.println(finish ? ans : -1);
    }

    private static void solve(int r, int c, int cnt) {
        if (finish) return;
        if (ans == cnt) {
            if (checkLadder()) finish = true;
            return;
        }

        for (; r <= h; r++) {
            for (; c < n; c++) {
                if (!ladder[r][c] && canAddLine(r, c)) {
                    ladder[r][c] = true;
                    solve(r, c + 2, cnt + 1);
                    ladder[r][c] = false;
                }
            }
            c = 1;
        }
    }

    private static boolean canAddLine(int r, int c) {
        return !(c > 1 && ladder[r][c - 1]) &&
                !(c < n && ladder[r][c + 1]);
    }

    private static boolean checkLadder() {
        for (int i = 1; i <= n; i++) {
            if (!checkLine(i)) return false;
        }
        return true;
    }

    private static boolean checkLine(int start) {
        int r = 1;
        int c = start;

        while (r <= h) {
            if (ladder[r][c]) {
                c += 1;
            } else if (c - 1 >= 1 && ladder[r][c - 1]) {
                c -= 1;
            }
            ++r;
        }

        return start == c;
    }
}
