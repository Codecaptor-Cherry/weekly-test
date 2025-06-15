import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] eggs;
    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        ans = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int idx, int cnt) {
        if(idx == N) {
            ans = Math.max(ans, cnt);
            return;
        }

        if(eggs[idx][0] <= 0 || cnt == N - 1) {
            dfs(idx + 1, cnt);
            return;
        }

        for(int i = 0; i < N; i++) {
            int nCnt = cnt;
            if(i == idx) continue;
            if(eggs[i][0] <= 0) continue;
            eggs[i][0] -= eggs[idx][1];
            eggs[idx][0] -= eggs[i][1];

            if(eggs[idx][0] <= 0) nCnt++;
            if(eggs[i][0] <= 0) nCnt++;
            dfs(idx + 1, nCnt);

            eggs[i][0] += eggs[idx][1];
            eggs[idx][0] += eggs[i][1];
        }

    }

}
