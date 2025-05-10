import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static String recycle = "PCVSGFO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] len = new int[n];
        int[] type = new int[n];
        Arrays.fill(type, 6);

        for (int i = 0; i < n; i++) {
            String trash = br.readLine();
            len[i] = trash.length();
            for (int j = 0; j < 7; j++) {
                if (trash.matches("^" + recycle.charAt(j) + "+$")) {
                    type[i] = j;
                    break;
                }
            }
        }

        int[] cost = new int[7];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        cost[6] = Integer.parseInt(br.readLine());

        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += Math.min(cost[type[i]], cost[6]) * len[i];
        }
        System.out.println(answer);
    }
}
