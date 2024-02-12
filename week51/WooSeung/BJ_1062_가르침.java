package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1062_가르침 {

    static int N, K, ans;
    static boolean[] learned;
    static String[] word;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        learned = new boolean[26];
        word = new String[N];
        ans = 0;

        if(K < 5) {
            System.out.println(0);
            return;
        } else if(K == 26) {
            System.out.println(N);
            return;
        }

        learned['a' - 'a'] = true;
        learned['c' - 'a'] = true;
        learned['i' - 'a'] = true;
        learned['n' - 'a'] = true;
        learned['t' - 'a'] = true;

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            str = str.replace("anta", "");
            str = str.replace("tica", "");
            word[i] = str;
        }
        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int cur, int cnt) {
        if(cnt == K - 5) {

            int count = 0;

            for(int i = 0; i < N; i++) {
                String str = word[i];
                boolean flag = true;
                for(int j = 0; j < str.length(); j++) {
                    if(!learned[str.charAt(j) - 'a']) flag = false;
                    if(!flag) break;
                }
                if(flag) count++;
            }
            ans = Math.max(ans, count);
        }

        for(int i = cur; i < 26; i++) {
            if(!learned[i]) {
                learned[i] = true;
                dfs(i, cnt + 1);
                learned[i] = false;
            }
        }
    }

}
