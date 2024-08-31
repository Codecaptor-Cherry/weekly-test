package saturday.sat240831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 2 이상의 자연수로 표현되는 슬라임 에너지
 * 슬라임 합성 : A슬라임과 B슬라임을 합성하려면 A x B만큼의 전기 에너지 필요 ... 결과는 A x B슬라임 탄생
 * N마리의 슬라임을 모두 합성해서 1마리의 슬라임을 만들 때, 각각의 합성 비용 곱셈값이 최소가 되도록 하는 프로그램
 *
 * 1마리가 될 때까지 소모된 각각의 합성 비용을 곱한 값의 최솟값 구하기...
 * 합성할 때 합성비용이 작게 나오도록 하기 -> 가장 작은 값 x 두번째로 작은 값 = 현재 단계에서 만들 수 있는 최소 합성 비용
 * 우선순위 큐 이용
 * 이 문제에서 주의할 것은 Long과 나머지 연산
 */
public class BJ_14698_전생했더니슬라임연구자였던건에대하여Hard {
    static final Long MOD = 1000000007L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());

        for(int t = 1; t <= T; t++) {
            int N = stoi(br.readLine()); // 슬라임의 수

            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());

            // 여기를 st보다 먼저하면 입력형식 어긋나서 NoSuchElementException 발생
            if(N == 1) { // 전기 에너지가 전혀 필요하지 않은 경우
                sb.append(1 + "\n");
                continue;
            }

            while(N-- > 0) {
                pq.offer(stol(st.nextToken())); // 슬라임 에너지는 최대 2 x 10^18
            }

            long ans = 1;

            while(pq.size() > 1) {
                Long cost = pq.poll() * pq.poll(); // 조건 : 합성 비용이 2 x 10^8 이하임이 보장됨
                ans = ans * cost % MOD; // 오버플로우 방지를 위해 나머지연산

                pq.offer(cost);
            }

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static long stol(String str) {
        return Long.parseLong(str);
    }
}