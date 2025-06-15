
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String first = br.readLine();

        int[] check = new int[26];
        int ans = 0;

        for(int i = 0; i < first.length(); i++) {
            check[first.charAt(i) - 'A']++;
        }

        for(int i = 1; i < N; i++) {

            String str = br.readLine();
            int[] curCheck = check.clone();
            int cnt = 0;

            if(Math.abs(first.length() - str.length()) > 1) continue;


            for(int j = 0; j < str.length(); j++) {
                int idx = str.charAt(j) - 'A';
                if(curCheck[idx] > 0) {
                    curCheck[idx]--;
                    cnt++;
                }
            }

            if(first.length() == str.length()
                    && (first.length() == cnt || first.length() - 1 == cnt)) ans++;
            else if(first.length() == str.length() - 1 && str.length() - 1 == cnt) ans++;
            else if(first.length() == str.length() + 1 && str.length() == cnt) ans++;
        }

        System.out.println(ans);

    }

}
