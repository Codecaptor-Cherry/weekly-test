
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int ans = 0;

        char[][] map = new char[R][C];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int len1 = 0, len2 = 0;

        for(int r = 0; r < R - 1; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] == 'X' && map[r + 1][c] == '.') len1++;
                else {
                    ans += len1 / 2;
                    len1 = 0;
                }
                if(map[r][c] == '.' && map[r + 1][c] == 'X') len2++;
                else {
                    ans += len2 / 2;
                    len2 = 0;
                }
            }
        }

        ans += len1 / 2;
        len1 = 0;
        ans += len2 / 2;
        len2 = 0;

        for(int c = 0; c < C - 1; c++) {
            for(int r = 0; r < R; r++) {
                if(map[r][c] == 'X' && map[r][c + 1] == '.') len1++;
                else {
                    ans += len1 / 2;
                    len1 = 0;
                }
                if(map[r][c] == '.' && map[r][c + 1] == 'X') len2++;
                else {
                    ans += len2 / 2;
                    len2 = 0;
                }
            }
        }
        ans += len1 / 2;
        ans += len2 / 2;

        System.out.println(ans);
    }

}
