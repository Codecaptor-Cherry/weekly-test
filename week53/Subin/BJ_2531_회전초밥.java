import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2531_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 초밥 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] countOfSushi = new int[d + 1]; // 초밥 종류별 먹은 횟수를 저장하는 배열
        int maxTypes = 0; // 최대 종류의 초밥 수
        int currentTypes = 0; // 현재 선택된 연속된 초밥의 종류 수
        int left = 0, right = k;

        for (int i = left; i < right; i++) {
            if (countOfSushi[sushi[i]]++ == 0)
                currentTypes++;
        }
        maxTypes = Math.max(maxTypes, countOfSushi[c] == 0 ? currentTypes + 1 : currentTypes);

        // 슬라이딩 윈도우
        while (left < n) {
            if (right == n) right = 0;

            if (--countOfSushi[sushi[left++]] == 0) {
                --currentTypes;
            }

            if (countOfSushi[sushi[right++]]++ == 0) {
                ++currentTypes;
            }

            maxTypes = Math.max(maxTypes, countOfSushi[c] == 0 ? currentTypes + 1 : currentTypes);
        }

        System.out.println(maxTypes);
    }
}
