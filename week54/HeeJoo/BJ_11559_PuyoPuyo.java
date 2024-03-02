package saturday.sat240302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/*
 * 필드에 여러 가지 색깔의 뿌요 존재
 * 뿌요는 중력의 영향을 받아 아래에 바닥 or 다른 뿌요가 나올 때 까지 아래로 떨어진다.
 * 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. = 1연쇄 시작
 * 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 차례대로 아래로 떨어지게 된다.
 * 알래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때 마다 1연쇄씩 증가
 * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고, 여러 그룹이 터지더라도 한 번의 연쇄가 추가된다.
 * 상대의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하기
 *
 * 1. 현재 상태에서 가능한 연쇄 시작
 * 2. 모든 연쇄를 마친 후 뿌요 내리기
 * 연쇄가 불가능할 때까지 위 과정 반복
 */

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BJ_11559_PuyoPuyo {
    static int height;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        String str = "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".Y....\n" +
                ".YG...\n" +
                "RRYG..\n" +
                "RRYGG."; // 3
        BufferedReader br = new BufferedReader(new StringReader(str));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] field = new char[12][6]; // 필드 크기 고정
        for (int i = 0; i < 12; i++) {
            field[i] = br.readLine().toCharArray();
        }

        height = 11; // 최대 뿌요 높이
        for(int i = 0; i < 6; i++) {
            for(int j = height; j >= 0; j--) {
                if(field[j][i] == '.') {
                    height = Math.min(height, j);
                    break;
                }
            }
        }

        int ans = 0;
        while(puyo(field, height)) {
            ans++;
        }

        System.out.println(ans);

    }

    public static boolean puyo(char[][] field, int height) {
        // 1. 연쇄 찾기
        boolean flag = false;
        for(int i = 11; i >= height; i--) {
            for(int j = 0; j < 6; j++) {
                if(field[i][j] != '.') {
                    if(bfs(field, i, j, field[i][j])) {
                        flag = true;
                    };
                }
            }
        }

        // 2. 뿌요 내리기
        if(flag) {
            downPuyo(field);
            return true;
        }

        return false;
    }

    public static void downPuyo(char[][] field) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 12; j++) {
                if(field[j][i] != '.') {
                    stack.push(field[j][i]);
                    field[j][i] = '.';
                }
            }

            int idx = 12;
            while(!stack.isEmpty()) {
                field[--idx][i] = stack.pop();
            }
        }
    }

    public static boolean bfs(char[][] field, int x, int y, char color) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));
        char lower = Character.toLowerCase(color);

        field[x][y] = lower;
        int cnt = 1;

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for(int i = 0; i < 4; i++) {
                int dx = now.x + dir[i][0];
                int dy = now.y + dir[i][1];

                if(!checkRange(dx, dy) || field[dx][dy] != color) {
                    continue;
                }

                field[dx][dy] = lower;
                queue.offer(new Point(dx, dy));
                cnt++;
            }

            if(cnt >= 4) {
                // 폭발
                boom(field, x, y, color);
                return true;
            }
        }

        if(cnt < 4) { // 연쇄가 불가능한 경우 복구 ...
            field[x][y] = color;
            queue.offer(new Point(x, y));

            while (!queue.isEmpty()) {
                Point now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int dx = now.x + dir[i][0];
                    int dy = now.y + dir[i][1];

                    if (!checkRange(dx, dy) || field[dx][dy] != lower) {
                        continue;
                    }

                    field[dx][dy] = color;
                    queue.offer(new Point(dx, dy));
                }
            }
        }

        return false;
    }

    public static void boom(char[][] field, int x, int y, char color) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));
        char lower = Character.toLowerCase(color);

        field[x][y] = '.';

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for(int i = 0; i < 4; i++) {
                int dx = now.x + dir[i][0];
                int dy = now.y + dir[i][1];

                if(!checkRange(dx, dy)) {
                    continue;
                }

                if(field[dx][dy] == color || field[dx][dy] == lower) {
                    field[dx][dy] = '.';
                    queue.offer(new Point(dx, dy));
                }
            }
        }
    }

    public static boolean checkRange(int x, int y) {
        if(x < 0 || x >= 12) return false;
        if(y < 0 || y >= 6) return false;

        return true;
    }

    public static void print(char[][] map) {
        for(char[] arr : map) {
            for(int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
