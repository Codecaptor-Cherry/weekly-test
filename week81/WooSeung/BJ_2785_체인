import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        int cnt = 0;
        while (true) {
            if(list.size() <= 1) break;
            list.set(0, list.get(0) - 1);
            list.remove(list.size() - 1);
            if(list.get(0) == 0) list.remove(0);
            cnt++;
        }

        System.out.println(cnt);

    }

}
