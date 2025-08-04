
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

        int ans = 0;

        Set<String> set = new HashSet<>();

        String str;
        while((str = br.readLine()) != null) {
            String[] arr = str.split(" ");

            if (arr[0].compareTo(S) <= 0)
                set.add(arr[1]);
            else if (arr[0].compareTo(E) >= 0 && arr[0].compareTo(Q) <= 0) {
                if (set.contains(arr[1])) {
                    ans++;
                    set.remove(arr[1]);
                }
            }
        }
        System.out.println(ans);
    }

}
