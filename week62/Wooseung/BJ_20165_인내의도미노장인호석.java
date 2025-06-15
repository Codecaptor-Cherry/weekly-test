import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] arr;
    static char[][] check;
    static Map<String, int[]> map;
    static int N, M, ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new HashMap<>();
        map.put("E", new int[]{0, 1});
        map.put("W", new int[]{0, -1});
        map.put("S", new int[]{1, 0});
        map.put("N", new int[]{-1, 0});

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        check = new char[N + 1][M + 1];


        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(check[i], 'S');
            for(int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            atk(r, c, dir);

            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            def(r, c);
        }
        
        sb.append(ans + "\n");
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                sb.append(check[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void atk(int r, int c, String dir) {
        if(check[r][c] == 'F') return;
        else {
            int[] dirArr = map.get(dir);
            int cnt = arr[r][c] - 1;
            check[r][c] = 'F';
            ans++;

            while(cnt > 0) {
                int nr = r + dirArr[0];
                int nc = c + dirArr[1];

                if(nr < 1 || nc < 1 || nr > N || nc > M) return;
                cnt--;
                r = nr;
                c = nc;
                if(check[nr][nc] == 'F') continue;
                check[nr][nc] = 'F';
                cnt = Math.max(cnt, arr[nr][nc] - 1);
                ans++;
            }
        }
    }

    static void def(int r, int c) {
        if(check[r][c] == 'F') check[r][c] = 'S';
    }
}
