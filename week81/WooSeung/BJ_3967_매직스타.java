
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static char[][] arr = new char[5][9];
    static boolean[] visited = new boolean[13];
    static List<Point> list = new ArrayList<>();
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] >= 'A' && arr[i][j] <= 'L') {
                    int num = arr[i][j] - 'A' + 1;
                    visited[num] = true;
                } else if (arr[i][j] == 'x') {
                    list.add(new Point(i, j));
                }
            }
        }

        dfs(0);
    }

    static void dfs(int idx) {
        if (flag) return;

        if (list.size() == idx) {
            if (check()) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print(arr[i][j]);
                    }
                    System.out.println();
                }
                flag = true;
            }
            return;
        }

        for (int i = 1; i <= 12; i++) {
            if (!visited[i]) {
                Point p = list.get(idx);
                char ch = (char) ('A' - 1 + i);
                arr[p.r][p.c] = ch;
                visited[i] = true;
                dfs(idx + 1);
                visited[i] = false;
                arr[p.r][p.c] = 'x';
            }
        }
    }

    static boolean check() {
        
        if (arr[1][1] + arr[1][3] + arr[1][5] + arr[1][7] - 4 * 'A' + 4 != 26) return false;
        if (arr[0][4] + arr[1][3] + arr[2][2] + arr[3][1] - 4 * 'A' + 4 != 26) return false;
        if (arr[0][4] + arr[1][5] + arr[2][6] + arr[3][7] - 4 * 'A' + 4 != 26) return false;
        if (arr[3][1] + arr[3][3] + arr[3][5] + arr[3][7] - 4 * 'A' + 4 != 26) return false;
        if (arr[1][1] + arr[2][2] + arr[3][3] + arr[4][4] - 4 * 'A' + 4 != 26) return false;
        if (arr[3][1] + arr[2][2] + arr[1][3] + arr[0][4] - 4 * 'A' + 4 != 26) return false;
        return true;
    }
}
