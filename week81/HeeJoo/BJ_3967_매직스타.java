package saturday.sat240928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1부터 12까지 숫자가 헥사그램에 채워져 있음
 * 숫자 4개로 이루어진 줄의 숫자를 모두 합하면 26
 * 일부만 채워진 매직 스타가 주어졌을 때, 수를 전부 다 채워서 매직 스타 만들기
 *
 * DFS
 * 빈 칸에 사전 순으로 사용되지 않은 알파벳 넣기
 */

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class BJ_3967_매직스타 {
    static Point[] points; // 헥사그램 좌표
    static boolean ans = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[5][9]; // 합산을 구하기 위해 int타입으로 map 생성
        boolean[] used = new boolean[12 + 1]; // 알파벳 사용 여부
        points = new Point[12 + 1];
        int idx = 1;
        for(int i = 0; i < 5; i++) {
            String input = br.readLine();
            for(int j = 0; j < 9; j++) {
                char ch = input.charAt(j);

                if(ch == 'x') { // x : 0 치환, 헥사그램 좌표 저장
                    map[i][j] = 0;
                    points[idx++] = new Point(i, j);
                } else if (ch == '.') { // . : 0 치환
                    map[i][j] = 0;
                    continue;
                } else { // 알파벳 : 수 대입, 사용 여부 체크, 헥사그램 좌표 저장
                    map[i][j] = ctoi(ch);
                    points[idx++] = new Point(i, j);
                    used[map[i][j]] = true;
                }
            }
        }

        // 시작 좌표
        idx = 1;
        int x = points[idx].x;
        int y = points[idx].y;

        while(map[x][y] != 0) { // x가 나올 때 까지 pass
            idx++;
            x = points[idx].x;
            y = points[idx].y;
        }

        for(int i = 1; i <= 12; i++) { // A ~ L 순서대로 대입
            if(!used[i]) {
                map[x][y] = i;
                used[i] = true;
                dfs(map, used,idx + 1);
                used[i] = false;
            }

            if(ans) { // 정답이 나온 경우 stop
                break;
            }
        }

        printItoC(map); // int타입을 char타입으로 치환해서 출력
    }

    public static void dfs(int[][] map, boolean[] used, int idx) {
        if(idx == 13) { // 모든 알파벳을 대입 완료한 경우
            ans = checkHex(map); // 매직 스타 여부 확인
            return;
        }

        // 이번에 값을 변경할 좌표
        int x = points[idx].x;
        int y = points[idx].y;

        if(map[x][y] == 0) { // x인 경우
            for(int t = 1; t <= 12; t++) { // A ~ L 대입
                if(used[t]) { // 이미 사용된 알파벳인 경우 pass
                    continue;
                }

                map[x][y] = t; // 숫자로 A ~ L 대입
                used[t] = true; // 사용 여부 갱신
                dfs(map, used,idx + 1);

                if(ans) { // 정답이 나온 경우 stop
                    return;
                }

                // 다음 DFS를 위해 복구 작업
                map[x][y] = 0;
                used[t] = false;
            }
        } else { // 알파벳인 경우 다음 좌표로
            dfs(map, used, idx + 1);
        }
    }

    public static boolean checkHex(int[][] map) {
        // 1
        if(map[0][4] + map[1][3] + map[2][2] + map[3][1] != 26) {
            return false;
        }

        // 2
        if(map[0][4] + map[1][5] + map[2][6] + map[3][7] != 26) {
            return false;
        }

        // 3
        if(map[3][1] + map[3][3] + map[3][5] + map[3][7] != 26) {
            return false;
        }

        // 4
        if(map[1][1] + map[1][3] + map[1][5] + map[1][7] != 26) {
            return false;
        }

        // 5
        if(map[1][1] + map[2][2] + map[3][3] + map[4][4] != 26) {
            return false;
        }

        // 6
        if(map[1][7] + map[2][6] + map[3][5] + map[4][4] != 26) {
            return false;
        }

        return true;
    }

    public static char itoc(int i) {
        return (char)(i + 'A');
    }
    public static int ctoi(char ch) {
        return ch - 'A' + 1;
    }

    public static void printItoC(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 0) {
                    sb.append('.');
                } else {
                    sb.append(itoc(map[i][j] - 1));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
     public static void print(int[][] map) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
     }
}
