

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[] lamps = new int[M];
		int maxBetween = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			lamps[m] = Integer.parseInt(st.nextToken());
		}
		for(int m = 0; m < M -1; m++){
			int val = lamps[m + 1] - lamps[m];
			maxBetween = Math.max(maxBetween, val);
		}
		int lampLen = maxBetween %2 == 0? (maxBetween/2) : (maxBetween/2)+1;
		//앞부분 뒤부분 이쪽이 맥스면 값을 나누지 않고 그대로 가져가야한다.
		lampLen = Math.max(lamps[0], lampLen);
		lampLen = Math.max(N - lamps[M-1], lampLen);

		System.out.println(lampLen);

	}
}
