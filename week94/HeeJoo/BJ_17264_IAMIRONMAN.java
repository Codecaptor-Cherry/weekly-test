package saturday.sat250111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * W(+20) / L(-15) ... 0점 밑으로 떨어지지않음
 * 아이언 티어를 탈출하기 위해서 G점 이상이 되어야 함
 * N번의 게임을 통해서 아이언 티어 탈출 여부 확인
 */
public class BJ_17264_IAMIRONMAN {
    static final String success = "I AM NOT IRONMAN!!";
    static final String fail = "I AM IRONMAN!!";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken()); // 게임 횟수 N
        int P = stoi(st.nextToken()); // 플레이어 정보 수 P

        st = new StringTokenizer(br.readLine());
        int W = stoi(st.nextToken()); // 승리 시 획득 점수 W
        int L = stoi(st.nextToken()); // 패배 시 손실 점수 L
        int G = stoi(st.nextToken()); // 아이언 티어 탈출에 필요한 점수 G

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }

        int score = 0;
        for (int i = 0; i < N; i++) {
            String player = br.readLine();

            // 정보 있음
            if(map.containsKey(player)) {
                if(map.get(player).equals("W")) { // 승리
                    score += W;
                } else { // 패배
                    score = score - L < 0 ? 0 : score - L;
                }
            } else { // 정보 없음 ~ 패배
                score = score - L < 0 ? 0 : score - L;
            }

            if(score >= G) {
                System.out.println(success);
                System.exit(0);
            }
        }

        System.out.println(fail);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
