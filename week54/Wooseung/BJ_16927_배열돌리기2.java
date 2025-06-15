import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int start_r = 0;
        int end_r = N - 1;
        int start_c = 0;
        int end_c = M - 1;

        while(true) {
            int size = (start_r - end_r + 1) * 2 + (start_c - end_c + 1) * 2 - 4;
            rotate(start_r, start_c, end_r, end_c, R % size);
            start_r+=1;
            end_r-=1;
            start_c+=1;
            end_c-=1;
            if(start_r > end_r || start_c > end_c) break;
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void rotate(int start_r, int start_c, int end_r, int end_c, int cnt) {
        for(int c = 0; c < cnt; c++) {
            int temp = arr[start_r][start_c];

            for(int j = start_c; j < end_c; j++) {
                arr[start_r][j] = arr[start_r][j + 1];
            }

            for(int i = start_r; i < end_r; i++) {
                arr[i][end_c] = arr[i + 1][end_c];
            }

            for(int j = end_c; j > start_c; j--) {
                arr[end_r][j] = arr[end_r][j - 1];
            }

            for(int i = end_r; i > start_r; i--) {
                arr[i][start_c] = arr[i - 1][start_c];
            }
            arr[start_r + 1][start_c] = temp;
        }
    }
}
