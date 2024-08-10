package saturday.sat240810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1부터 31까지 순차적으로 1개 이상, 3개 이하 연달아 부르기 게임
 * 마지막 31을 부른 사람이 패배
 * 1개 이상 N개 이하의 수를 부를 수 있는 규칙으로 변형
 * 후공일 때, 범위 A 내의 게임을 이길 수 있는 모든 N 구하기
 *
 * 총 31개의 숫자
 * A = 3일때(기존) 필승 조건 : 30, 26, 22, 19, ... 를 말하는 사람 ~ 일종의 규칙 찾기
 * 30 29 28 27
 * 26 25 24 23
 * 26 = 30 - (3 + 1) : 내가 26을 말하면 상대방은 29까지밖에 안됨 ~ 30 불가능
 * 22 = 30 - (3 + 1) * 2 : 내가 22를 말하면 상대방은 25까지밖에 안됨 ~ 26 불가능
 * 즉 상대방이 30을 부르지 못하도록 하기
 * 30 % (n + 1)의 나머지에 따라 선공, 후공 승리가 바뀜
 * 0이면 후공 승리, 1이면 선공 승리
 * 나머지가 1인 경우, 선공이 남은 수 만큼 말해서 마지막으로 필승 숫자를 말함
 * 나머지가 0인 경우, 후공이 필승 숫자를 말함
 */
public class BJ_20004_베스킨라빈스31 {
    static boolean check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());

        for(int i = 1; i <= A; i++) {
            if(30 % (i + 1) == 0) {
                System.out.println(i);
            }
        }
    }
}
