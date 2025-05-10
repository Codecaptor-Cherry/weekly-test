package saturday.year25.sat250510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 초기 위치 (0, 0)
 * 출구 (N, 0)
 * 현재 위치 (x, y)
 * 각 이동마다 -1 이상의 정수 a를 선택하여 (x + 1, y + a)까지 직선 경로를 따라 이동
 * 올라갈 때는 자유, 내려갈 땐 한 칸씩만 가능
 * 위아래에서 솟은 끈끈이주걱들을 피하며 N번의 이동 후 출구에 도달하는 것이 목표
 * 아래에서 솟아 올라온 주걱 끝의 y좌표가 b라면 파리의 y좌표는 b 초과여야 함
 * 위에서 솟아 내려온 주걱 끝의 y좌표가 b라면 파리의 y좌표는 b 미만이어야 함
 * 파리는 정확히 출구에 도달해야 함 ... 즉, x좌표가 N일 때, y좌표는 0이어야 함
 * 각 끈끈이주걱의 x좌표는 0 초과 N 미만이며, 같은 x좌표에서는 2개 이상의 끈끈이주걱이 자라지 않음
 * 탈출 가능할까 ?
 */

class Sundew {
    int type, x, y;

    Sundew(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}

public class BJ_32247_이상한나라의끈끈이주걱 {
    static String[] results = {"stay", "adios"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken()); // 출구의 위치
        int M = stoi(st.nextToken()); // 끈끈이주걱의 개수

        Sundew[] sundews = new Sundew[M + 1];
        sundews[0] = new Sundew(0, 0, 0);
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = stoi(st.nextToken()); // 아래(0), 위(1)
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            sundews[i] = new Sundew(type, x, y);
        }

        if(M == 0) {
            System.out.println(results[0]);
            return;
        }

        Arrays.sort(sundews, (o1, o2) -> o1.x - o2.x); // x좌표 오름차순 정렬

        int now = 0; // 현재 파리의 높이
        int interval = 0; // 다음 주걱까지의 거리

        // ! 올라가는 건 쉽지만, 내려올 때는 한 칸씩 내려올 수 있으니 내려갈 수 있을 때 최대한 내려가기~
        for (int i = 1; i <= M; i++) {
            interval = sundews[i].x - sundews[i - 1].x; // 이동 가능 거리

            if(sundews[i].type == 0) { // 아래에서 솟음 ... b 초과
                if(now - interval > sundews[i].y) { // 여유분 내려가기 가능
                    now -= interval;
                } else {
                    now = sundews[i].y + 1; // 지나갈수 있도록 최소 높이로 조정
                }
            } else if(sundews[i].type == 1) { // 위에서 내려옴 ... b 미만
                if(now - interval >= sundews[i].y) { // 불가능
                    System.out.println(results[1]);
                    return;
                } else {
                    now -= interval; // 이동할 수 있는만큼 최대한 내려가기 .. 올라오는건 문제없음 !
                }

            }
        }

        // 마지막 출구 탈출 가능? (N, 0)
        interval = N - sundews[M].x;
        if(now <= interval) {
            System.out.println(results[0]);
        } else {
            System.out.println(results[1]);
        }
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
