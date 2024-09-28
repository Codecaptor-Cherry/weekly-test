package saturday.sat240928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * N개의 체인
 * 각각의 체인은 몇 개의 고리로 연결된 상태
 * 각각의 고리는 최대 두 개의 인접한 고리를 가질 수 있음
 * 각각의 고리는 열고 닫을 수 있음 -> 체인을 분리하거나 두 체인을 연결하여 하나의 긴 체인으로 만들 수 있음
 * 가능한 한 적은 고리를 열고 닫아서, 모든 체인을 하나의 긴 체인으로 연결하기
 *
 * 1개인 경우, 자신 포함 3개를 엮을 수 있음
 * 가장 짧은 체인을 고리화해서 긴 체인들을 연결
 */
public class BJ_2785_체인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        String[] inputs = br.readLine().split(" ");
        int[] chains = new int[N];
        Deque<Integer> dq = new ArrayDeque<>(); // 고리 크기 오름차순
        for(int i = 0; i < N; i++) {
            chains[i] = stoi(inputs[i]);
        }

        Arrays.sort(chains);
        for(int k : chains) {
            dq.offer(k);
        }

        int ans = 0;

        while(dq.size() > 1) {
            ans++;

            dq.offerLast(dq.pollLast() + dq.pollLast()); // 가장 긴 체인 2개 연결

            if(dq.size() >= 2) { // 아직 하나의 체인이 되지 못한 경우
                int first = dq.pollFirst(); // 고리화 대상 : 가장 짧은 체인
                if(first > 1) { // 2이상인 경우 잔여. 1인 경우 연결하면서 합쳐짐
                    dq.offerFirst(first - 1); // 1개는 긴 체인 연결하는데 사용됨
                }
            }
        }

        System.out.println(ans);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
