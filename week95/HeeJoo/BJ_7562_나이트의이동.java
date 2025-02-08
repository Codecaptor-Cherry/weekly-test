package saturday.year25.sat250118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x, y, cnt;

    Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
public class BJ_7562_나이트의이동 {
    static int[] dirX = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dirY = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = stoi(br.readLine());

        while(t-- > 0) {
            ans = (int)1e9;
            int l = stoi(br.readLine()); // 체스판의 한 변의 길이
            int[][] map = new int[l][l];

            // 나이트의 현재 위치
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());

            // 나이트의 목표 위치
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());

            simul(x, y, a, b, l, map);

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

    public static void simul(int x, int y, int a, int b, int l, int[][] map) {
        Queue<Point> queue = new ArrayDeque<>();

        queue.offer(new Point(x, y, 0));
        map[x][y] = 1;

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            if(now.x == a && now.y == b) {
                ans = now.cnt;
                return;
            }

            for(int i = 0; i < dirX.length; i++) {
                int dx = now.x + dirX[i];
                int dy = now.y + dirY[i];

                if(!checkRange(dx, dy, l) || map[dx][dy] == 1) {
                    continue;
                }

                map[dx][dy] = 1;
                queue.offer(new Point(dx, dy, now.cnt + 1));
            }
        }
    }

    public static boolean checkRange(int x, int y, int l) {
        if(x < 0 || x >= l || y < 0 || y >= l) {
            return false;
        }

        return true;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
