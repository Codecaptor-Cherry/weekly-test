import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HashMap<String, PriorityQueue<Integer>> map = new HashMap<>();

        int q = Integer.parseInt(br.readLine());

        long ans = 0;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());
            String gorilla = st.nextToken();
            if (!map.containsKey(gorilla)) {
                map.put(gorilla, new PriorityQueue<>(Comparator.reverseOrder()));
            }
            PriorityQueue<Integer> info = map.get(gorilla);

            if (query == 1) {
                int k = Integer.parseInt(st.nextToken());
                for (int j = 0; j < k; j++) {
                    info.add(Integer.parseInt(st.nextToken()));
                }
            } else if (query == 2) {
                int b = Integer.parseInt(st.nextToken());
                for (int j = 0; j < b && !info.isEmpty(); j++) {
                    ans += info.poll();
                }
            }
        }

        System.out.println(ans);
    }

}
