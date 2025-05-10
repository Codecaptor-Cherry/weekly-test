import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> chain = new ArrayList<>();
        int ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            chain.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(chain);

        while (chain.size() > 1) {
            chain.set(0, chain.get(0) - 1);
            chain.remove(chain.size() - 1);
            if (chain.get(0) == 0) chain.remove(0);

            ++ans;
        }

        System.out.println(ans);

    }
}
