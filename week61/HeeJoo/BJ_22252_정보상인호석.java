package saturday.sat240427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * 어떤 이름을 가진 고릴라가 C1, C2, ..., Ck만큼의 가치가 있는 정보 k개를 얻었다.
 * 임의의 시점에 특정 고릴라에게 정보를 몇 개 살것인지 정하기
 * 가치 순으로 가장 비싼 정보들을 구매한다.
 * 한 번 거래한 정보는 더 이상 가치가 없기 때문에 파기한다.
 * 정보 1. 1 [Name] k, C1, C2, ..., Ck : [Name] 고릴라가 k개의 정보를 얻었으며, 각 가치는 C1부터 Ck이다.
 * 정보 2. 2 [Name] b : [Name] 고릴라에게 b개의 정보를 구매한다. 가장 비싼 b개를 구매하고, b개 이하이면 모든 정보 구매
 * 정보를 구매하는 데에 쓴 돈의 총합 구하기
 */
public class BJ_22252_정보상인호석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine()); // 쿼리 개수

        HashMap<String, PriorityQueue<Integer>> map = new HashMap<>();
        long ans = 0; // 범위 잘 보고 long으로 설정할 것 ~!~! 안그럼 오류나용ㅠㅅㅠ
        for (int i = 0; i < Q; i++) {
            String[] input = br.readLine().split(" ");
            PriorityQueue<Integer> pq;

            if("1".equals(input[0])) { // 고릴라의 정보
                // 1. 첫 등록
                if(!map.containsKey(input[1])) {
                    pq = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
                    for(int idx = 3; idx < input.length; idx++) {
                        pq.offer(Integer.parseInt(input[idx]));
                    }
                }
                // 2. 갱신
                else {
                    pq = map.get(input[1]);
                    for(int idx = 3; idx < input.length; idx++) {
                        pq.offer(Integer.parseInt(input[idx]));
                    }
                }

                map.put(input[1], pq);
            } else if("2".equals(input[0]) && map.containsKey(input[1])) { // 정보 구매
                pq = map.get(input[1]);
                int cnt = Integer.parseInt(input[2]);

                while(cnt-- > 0 && !pq.isEmpty()) {
                    ans += pq.poll();
                }
            }
        }

        System.out.println(ans);
    }
}
