import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_17264_IRONMAN {

    static int N, P, W, L, G;
    static Map<String, String> PlayerMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        PlayerMap = new HashMap<String, String>();
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            String player = st.nextToken();
            String result = st.nextToken();

            PlayerMap.put(player, result);
        }

        int score = 0;
        for (int i = 0; i < N; i++) {
            String player = br.readLine();
            String result = PlayerMap.get(player);
            score = (result != null && result.equals("W")) ?
                    score + W :
                    (score - L < 0) ? 0 : score - L;

            if (score >= G) break;
        }

        StringBuilder builder = new StringBuilder();
        if (score >= G) {
            builder.append("I AM NOT IRONMAN!!");
        } else {
            builder.append("I AM IRONMAN!!");
        }

        System.out.print(builder.toString());
    }
}
