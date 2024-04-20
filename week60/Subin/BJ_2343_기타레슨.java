import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] lec = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lec[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(lec, m));
    }

    private static int binarySearch(int[] lec, int cnt) {
        int s = 0;
        int e = sum(lec);

        while (s < e) {
            int m = (s + e) / 2;
            if (check(lec, m, cnt)) {
                e = m;
            } else {
                s = m + 1;
            }
        }

        return e;
    }

    private static boolean check(int[] lec, int size, int cnt) {
        int s = 0;
        for (int l : lec) {
            s += l;
            if (s > size) {
                --cnt;
                s = l;
                if (s > size) return false; // 이거 추가해야 함 !! 아니면 이분탐색 start를 블루레이 최댓값으로 해도 ㄱㅊ
            }
        }
        return cnt > 0;
    }

    private static int max(int[] lec) {
        int max = 0;
        for (int l : lec) {
            max = Math.max(max, l);
        }
        return max;
    }

    private static int sum(int[] lec) {
        int s = 0;
        for (int l : lec) {
            s += l;
        }
        return s;
    }

}
