import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String log = st.nextToken();

            if (log.equals("enter")) {
                set.add(name);
            } else if (log.equals("leave")) {
                set.remove(name);
            }
        }

        List<String> remainNames = new ArrayList<>(set);
        Collections.sort(remainNames, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (String name : remainNames) {
            sb.append(name).append("\n");
        }
        System.out.print(sb);
    }

}
