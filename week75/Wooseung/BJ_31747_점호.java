
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }
        Deque<Integer> dq = new ArrayDeque<>();

        while(true) {
            ans++;

            while(dq.size() < K) {
                if(q.size() == 0) break;

                int num = q.poll();

                if(num == 1) dq.addFirst(num);
                else dq.addLast(num);
            }
            if(dq.peekFirst() == dq.peekLast()) {
                dq.poll();
            }else {
                dq.pollFirst();
                dq.pollLast();
            }

            if(q.size() == 0 && dq.size() == 0) break;
        }

        System.out.println(ans);
    }

}
