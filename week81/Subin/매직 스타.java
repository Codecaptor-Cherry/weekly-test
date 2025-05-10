import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static char[][] input;
    private static boolean[] selected;
    private static List<Point> xList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = new char[5][9];
        selected = new boolean[13];
        xList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            input[i] = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                if (input[i][j] == 'x') {
                    xList.add(new Point(i, j));
                } else if (input[i][j] != '.') {
                    selected[input[i][j] - 'A'] = true;
                }
            }
        }
        dfs(0);
    }

    private static void dfs(int idx) {
        if (idx == xList.size()) {
            if (check()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    sb.append(String.valueOf(input[i])).append("\n");
                }
                System.out.print(sb);
                System.exit(0);
            } else {
                return;
            }
        }

        for (int i = 0; i < 12; i++) {
            if (!selected[i]) {
                Point p = xList.get(idx);
                input[p.x][p.y] = (char) ('A' + i);
                selected[i] = true;

                dfs(idx + 1);

                input[p.x][p.y] = '.';
                selected[i] = false;
            }
        }
    }

    public static boolean check() {
        if ((input[0][4] - 'A' + 1) + (input[1][3] - 'A' + 1) + (input[2][2] - 'A' + 1) + (input[3][1] - 'A' + 1) != 26) return false;
        if ((input[0][4] - 'A' + 1) + (input[1][5] - 'A' + 1) + (input[2][6] - 'A' + 1) + (input[3][7] - 'A' + 1) != 26) return false;
        if ((input[1][1] - 'A' + 1) + (input[1][3] - 'A' + 1) + (input[1][5] - 'A' + 1) + (input[1][7] - 'A' + 1) != 26) return false;
        if ((input[3][1] - 'A' + 1) + (input[3][3] - 'A' + 1) + (input[3][5] - 'A' + 1) + (input[3][7] - 'A' + 1) != 26) return false;
        if ((input[4][4] - 'A' + 1) + (input[3][3] - 'A' + 1) + (input[2][2] - 'A' + 1) + (input[1][1] - 'A' + 1) != 26) return false;
        if ((input[4][4] - 'A' + 1) + (input[3][5] - 'A' + 1) + (input[2][6] - 'A' + 1) + (input[1][7] - 'A' + 1) != 26) return false;
        return true;
    }
    
}
