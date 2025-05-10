import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        ans += countSegments(map, m, n, true);  // 가로 확인
        ans += countSegments(map, m, n, false); // 세로 확인

        System.out.println(ans);
    }

    private static int countSegments(char[][] map, int m, int n, boolean isHorizontal) {
        int count = 0;

        for (int i = 0; i < (isHorizontal ? m : n); i++) {
            int uLen = 0;
            int dLen = 0;

            for (int j = 0; j < (isHorizontal ? n : m); j++) {
                char cur = isHorizontal ? map[i][j] : map[j][i];
                char neighbor = isHorizontal ? (i > 0 ? map[i - 1][j] : ' ') : (i > 0 ? map[j][i - 1] : ' ');

                if (cur == '.' && neighbor == 'X') {
                    ++uLen;
                    count += dLen / 2;
                    dLen = 0;
                } else if (cur == 'X' && neighbor == '.') {
                    ++dLen;
                    count += uLen / 2;
                    uLen = 0;
                } else {
                    count += uLen / 2;
                    count += dLen / 2;
                    uLen = dLen = 0;
                }
            }
            count += uLen / 2;
            count += dLen / 2;
        }

        return count;
    }

}
