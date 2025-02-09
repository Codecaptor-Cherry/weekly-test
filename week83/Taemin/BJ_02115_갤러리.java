import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02115_갤러리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int column = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        char[][] map = new char[column][row];
        for (int i = 0; i < column; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        boolean[][] displayed = new boolean[column][row];
        for (int r = 0; r < row - 1; r++) {
            for (int c = 0; c < column - 1; c++) {
                if ((!displayed[c][r] && map[c][r] == 'X' && map[c + 1][r] == '.') &&
                    (!displayed[c][r + 1] && map[c][r + 1] == 'X' && map[c + 1][r + 1] == '.')) {
                    displayed[c][r] = displayed[c][r + 1] = true;
                    count++;
                }

                if ((!displayed[c][r] && map[c][r] == '.' && map[c + 1][r] == 'X') &&
                    (!displayed[c][r + 1] && map[c][r + 1] == '.' && map[c + 1][r + 1] == 'X')) {
                    displayed[c][r] = displayed[c][r + 1] = true;
                    count++;
                }
            }
        }

        displayed = new boolean[column][row];
        for (int c = 0; c < column - 1; c++) {
            for (int r = 0; r < row - 1; r++) {
                if ((!displayed[c][r] && map[c][r] == 'X' && map[c][r + 1] == '.') &&
                    (!displayed[c + 1][r] && map[c + 1][r] == 'X' && map[c + 1][r + 1] == '.')) {
                    displayed[c][r] = displayed[c + 1][r] = true;
                    count++;
                }

                if ((!displayed[c][r] && map[c][r] == '.' && map[c][r + 1] == 'X') &&
                    (!displayed[c + 1][r] && map[c + 1][r] == '.' && map[c + 1][r + 1] == 'X')) {
                    displayed[c][r] = displayed[c + 1][r] = true;
                    count++;
                }
            }
        }

        System.out.print(count);
    }
}
