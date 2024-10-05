import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_20159_동작그만밑장빼기냐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] cards = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt).toArray();

        int[] player = new int[n/2 + 1];
        int[] opponent = new int[n/2 + 1];
        int index = 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                player[index] = cards[i];
            } else {
                opponent[index++] = cards[i];
            }
        }

        for (int i = 1; i <= n/2; i++) {
            player[i] += player[i-1];
            opponent[i] += opponent[i-1];
        }

        int max = player[n/2];

        // 밑장을 내가 받는 경우
        for (int i = 0; i < n/2; i++) {
            max = Math.max(max,
                           player[i] + opponent[n/2] - opponent[i]);
        }

        // 밑장을 상대에게 주는 경우
        for (int i = 1; i < n/2; i++) {
            max = Math.max(max,
                           player[i] + opponent[n/2 - 1] - opponent[i - 1]);
        }

        System.out.print(max);


        // 밑장을 빼서 상대에게 줄 수도 있다
        //


        // 2명의 플레이어
        // 분배를 한 사람부터 카드 - 나 카드 번갈아가면서
        // 정훈이 부터 카드 받는다.
        // 밑장뺴기를 했을 때 얻을 수 있는 최대 카드의 합
        // 카드의 수가 n이라서.. 너무 많아 100,000
        // 밑장을 빼지 않을 수도 있다.

        // 밑장을 빼면 순서가 바뀐다
        // 정훈이가 최대 한번 밑장 빼기를 했을 때 얻을 수 있는 최대 합
        // 밑장을 n/2 번 중에서 몇 번째에 뺄 것인가?

        // x  0 (0,2) (0,2,4)
        // x  1 (1,3) (1,3,5)

        // 0번
        // a[3]

        // 1번
        // a[0] + b[3] - b[0]

        // 2번
        // a[1] + b[3] - b[1]

        // 3번
        // a[2] + b[3] - b[2]


        // [내가 받는 경우]
        // 밑장을 안뺀 경우 (index)
        // 0 2 4 <> 1 3 5

        // 1번째 밑장을 뺀 경우 (첫 번째 이하 교환)
        // 5 1 3 <> 0 2 4

        // 2번째 밑장을 뺀 경우 (두 번째 이하 교환)
        // 0 5 3 <> 1 2 4

        // 3번째 밑장을 뺀 경우 (세 번째 이하 교환)
        // 0 2 5 <> 1 3 4

        // [상대가 받는 경우]
        // 밑장을 안뺀 경우
        // 0 2 4 <> 1 3 5

        // 1번째 밑장을 뺀 경우
        // 0 1 3 <> 5 2 4
        // a[1] + b[2] - b[0]

        // 2번째 밑장을 뺀 경우
        // 0 2 3 <> 1 5 4
        // a[2] + b[2] - b[1]

        // 3번째 밑장을 뺀 경우 (x, 뒷 순서이기 때문에 의미가 없다)
        // 0 2 4 <> 1 3 5

    }
}
