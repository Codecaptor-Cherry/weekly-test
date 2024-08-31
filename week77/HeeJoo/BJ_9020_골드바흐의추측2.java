package saturday.sat240824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 소수 : 1보다 큰 자연수 중에서 1과 자기 자신을 제외한 약수가 없는 자연수 ex) 5
 * 골드바흐 수 : 두 소수의 합으로 나타낼 수 있는 2보다 큰 짝수
 * 골드바흐 파티션 : 짝수를 두 소수의 합으로 나타내는 표현
 * 2보다 큰 짝수 n이 주어졌을 때, n의 골드바흐 파티션을 출력하는 프로그램
 * 만약, 가능한 n의 골드바흐 파티션이 여러 가지인 경우, 두 소수의 차이가 가장 작은 것 출력
 *
 * 1. 소수 구하기 - 에라토스테네스
 * 2. 골드바흐 파티션
 * 
 * 실행 시간 감소 2. 에라토스테네스를 전체 범위에 먼저 적용하고 테스트 케이스에 대한 정답 출력
 */
public class BJ_9020_골드바흐의추측2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = stoi(br.readLine()); // 테스트 케이스 개수 T

        // 에라토스테네스의 체
        boolean[] prime = new boolean[10000 + 1];
        Arrays.fill(prime, true); // 1. 모두 소수라고 초기화
        // 0과 1 제외
        prime[0] = false;
        prime[1] = false;
        eratos(prime);

        // 정답 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            int n = stoi(br.readLine());
            for(int j = n / 2; j > 1; j--) { // 두 소수의 차이가 가장 작은 것을 출력하니 중간부터 거꾸로 확인
                if(prime[j] && prime[n - j]) {
                    sb.append(j + " " + (n - j) + "\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }

    public static void eratos( boolean[] prime) {
        int length = prime.length;
        for(int i = 2; i <= Math.sqrt(length); i++) { // 3. 입력값 k의 제곱근까지 확인
            if(prime[i]) { // 4. i가 소수라면 i의 배수는 모두 소수가 아님
                for(int j = i + i ; j < length; j += i) {
                    prime[j] = false; // 배수는 소수가 아님을 기록
                }
            }
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
