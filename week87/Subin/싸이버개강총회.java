import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();

        Set<String> checkin = new HashSet<>();
        Set<String> answer = new HashSet<>();
        String input = null;

        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            String time = st.nextToken();
            String nickname = st.nextToken();

            if (time.compareTo(S) <= 0) {
                checkin.add(nickname);
            } else if (time.compareTo(E) >= 0 && time.compareTo(Q) <= 0) {
                if (checkin.contains(nickname)) answer.add(nickname);
            }
        }

        System.out.println(answer.size());
    }
}

