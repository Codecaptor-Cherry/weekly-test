package saturday.sat240309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 아직 보여주지 않은 문자 중 추가했을 때의 문자열이 사전 순으로 가장 앞에 오도록 하는 문자 만들기
 * 즉, 주어진 문자열의 글자가 하나씩 공개될 때, 사전순으로 앞에 오는 것을 보여주기
 * 한 글자씩 추가할 때 사전 순으로 가장 앞에 오는 문자를 출력하기 ...
 */

public class BJ_16719_ZOAC {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        boolean[] visited = new boolean[input.length()];
        zoac(input, 0, input.length(), visited);

        System.out.println(sb);
    }

    public static void zoac(String str, int left, int right, boolean[] visited) {
        if(left >= right) {
            return;
        }

        // 주어진 범위 내 가장 앞에 오는 문자 찾기
        int idx = left;
        for(int i = left; i < right; i++) {
            if(str.charAt(idx) > str.charAt(i)) {
                idx = i;
            }
        }

        visited[idx] = true; // 해당 문자 선택

        // 현재 선택된 문자 + 이전에 선택된 문자 출력
        for(int i = 0; i < str.length(); i++) {
            if(visited[i]) {
                sb.append(str.charAt(i));
            }
        }
        sb.append("\n");

        // 2. 현재 선택된 문자 기준 뒤쪽 먼저 해야 사전 순
        zoac(str, idx + 1, right, visited);
        zoac(str, left, idx, visited); // 뒷 부분을 다하고 앞 부분 수행
    }
}
