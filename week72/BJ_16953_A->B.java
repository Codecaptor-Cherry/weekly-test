import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        long num;
        int cnt;
        public Point(long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in. readLine());

        long num = Long.parseLong(st.nextToken());
        long target = Long.parseLong(st.nextToken());
        int ans = Integer.MAX_VALUE;

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(num, 1));

        while(!q.isEmpty()) {
            Point p = q.poll();
            if(p.num == target) {
                ans = Math.min(ans, p.cnt);
            }

            if(p.num * 2 <= target) {
                q.add(new Point(p.num * 2, p.cnt + 1));
            }
            if(p.num * 10 + 1 <= target) {
                q.add(new Point(p.num * 10 + 1, p.cnt + 1));
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

}
