
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();

        int Q = Integer.parseInt(br.readLine());
        long ans = 0;

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            if(qType == 1) {
                for(int j = 0; j < cnt; j++) {
                    if(!map.containsKey(name)) {
                        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                        pq.add(Integer.parseInt(st.nextToken()));
                        map.put(name, pq);
                    }else map.get(name).add(Integer.parseInt(st.nextToken()));
                }
            }else {
                if(!map.containsKey(name)) continue;
                while(!map.get(name).isEmpty() && cnt > 0) {
                    ans += map.get(name).poll();
                    cnt--;
                }
            }
        }
        System.out.println(ans);
    }

}
