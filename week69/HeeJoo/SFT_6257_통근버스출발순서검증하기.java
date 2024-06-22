import java.io.*;
import java.util.*;

/*
 * 오름차순 스택정렬
 * i < j < k일때, a_i < a_j이고 a_i > a_k인 경우가 하나라도 있으면 정렬 불가능
 * 즉, i < j < k일때, a_k < a_i < a_j인 경우 불가능
 * 불가능한 경우의 수 구하기

 * 구간합
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        String[] inputs = br.readLine().split(" ");
        int[] bus = new int[N + 1]; // 버스 번호 1번부터 시작
        for(int i = 1; i <= N; i++) {
            bus[i] = stoi(inputs[i - 1]);
        }

            int[][] arr = new int[N + 1][N + 1]; // arr[x][j] : j번째 이후에 있는 수 중에 x보다 작은 수의 개수 ... ex) arr[7][3] : 3번째 버스 이후, 즉 4번째 ~ N번째 버스 중 7보다 작은 수의 개수
        for(int i = 1; i <= N; i++) {
            for(int j = N - 1; 0 < j; j--) { // 끝에서부터 시작
                if(bus[j + 1] < i) { // j + 1번째 버스가 i보다 작은 경우
                    arr[i][j] = arr[i][j + 1] + 1; // (j + 1번째 이후 수 중 i보다 작은 수 개수) + 1 ... +1은 j + 1번째 추가
                } else {
                    arr[i][j] = arr[i][j + 1];
                }
            }
        }

        long ans = 0;
        for(int i = 1; i <= N - 2; i++) { // j, k를 생각해서 N-2까지만 가능
            for(int j = i + 1; j <= N - 1; j++) { // a_j
                if(bus[i] < bus[j]) { // a_i < a_j인 경우
                    // a_k < a_i인 경우의 수 ans에 더해주기
                    ans += arr[bus[i]][j]; // j번째 이후 a_i보다 작은 경우의 수
                }
            }
        }
        
        System.out.println(ans);
        
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
