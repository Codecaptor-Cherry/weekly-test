
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static String str;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        str = br.readLine();
        visited = new boolean[str.length()];

        func(0, str.length() - 1);

        System.out.println(sb.toString());
    }

    static void func(int left, int right) {
        if(left > right) return;

        int idx = left;
        for(int i = left; i <= right; i++) {
            if(str.charAt(idx) > str.charAt(i)) idx = i;
        }
        visited[idx] = true;
        for(int i = 0; i < str.length(); i++) {
            if(visited[i]) sb.append(str.charAt(i));
        }
        sb.append("\n");
        func(idx + 1, right);
        func(left, idx - 1);
    }

}
