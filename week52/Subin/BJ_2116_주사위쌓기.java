import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2116_주사위쌓기 {

    public static class Dice {
        private static final int CNT = 6;
        private static final int[] opposites = {5, 3, 4, 1, 2, 0};

        private int[] pip;

        public Dice(String numString) {
            String[] nums = numString.split(" ");
            this.pip = new int[CNT];
            for (int i = 0; i < CNT; i++) {
                pip[i] = Integer.parseInt(nums[i]);
            }
        }

        public int getOppositeValue(int num) {
            int idx = getIndex(num);
            return pip[opposites[idx]];
        }

        public int getMaxAdjacentValue(int num) {
            int idx = getIndex(num);
            int opIdx = opposites[idx];

            int max = 0;
            for (int i = 0; i < CNT; i++) {
                if (i != idx && i != opIdx) {
                    max = Math.max(max, pip[i]);
                }
            }
            return max;
        }

        private int getIndex(int num) {
            for (int i = 0; i < CNT; i++) {
                if (pip[i] == num) {
                    return i;
                }
            }
            throw new RuntimeException("주사위의 눈이 아닙니다. [" + num + "]");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Dice[] dices = new Dice[n];

        for (int i = 0; i < n; i++) {
            dices[i] = new Dice(br.readLine());
        }

        int answer = 0;

        // 가장 아래의 주사위는 마음대로 놓을 수 있다.
        // 바닥에 위치할 숫자를 1부터 6까지 brute-force
        for (int i = 1; i <= 6; i++) {
            int sum = dices[0].getMaxAdjacentValue(i);
            int bottom = dices[0].getOppositeValue(i);

            // 두 번째 ~ n 번째 주사위 돌면서 구하기
            for (int j = 1; j < n; j++) {
                sum += dices[j].getMaxAdjacentValue(bottom);
                bottom = dices[j].getOppositeValue(bottom);
            }

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

}
