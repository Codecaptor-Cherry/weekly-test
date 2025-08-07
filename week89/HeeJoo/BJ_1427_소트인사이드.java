package saturday.year24.sat241130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ_1427_소트인사이드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Character> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            pq.offer(input.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }

        System.out.println(sb);
    }
}
