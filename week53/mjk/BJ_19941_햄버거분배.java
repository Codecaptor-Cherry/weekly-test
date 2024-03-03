

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static String[] input;
	static int ans;

	// boolean 같은 자료를 쓰는것보다 input 의 데이터를 바꾸는것도 방법이다
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = br.readLine().split("");
		for (int n = 0; n < N; n++) {
			if (input[n].equals("P")) { // 사람 발견
				// 가능한 왼쪽 햄버거 인덱스 시작지점
				int hStart = Math.max(n - K, 0);
				boolean check = false;
				for (int h = hStart; h < n; h++) {
					if (checkAble(h)) {
						check = true;
						break;
					}
				}
				if (!check) {// 없다면 오른쪽 탐방
					hStart = n + K >= N ? N - 1 : n + K;
					for (int h = n + 1; h <= hStart; h++) {
						if (checkAble(h)) {
							break;
						}
					}

				}
			}

		}
		System.out.println(ans);

	}

	public static boolean checkAble(int index) {
		if (input[index].equals("H")) {
			input[index] = "X"; // X 로 변경
			ans++;
			return true;
		}
		return false;
	}
}
