import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, answer;
    private static int[][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(new int[3], 0, 0);
        System.out.println(answer);
    }

    public static void comb(int[] num, int idx, int s) {
        if (idx == 3) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.max(A[i][num[0]], Math.max(A[i][num[1]], A[i][num[2]]));
            }
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = s; i < M; i++) {
            num[idx] = i;
            comb(num, idx + 1, i);
        }
    }
}
