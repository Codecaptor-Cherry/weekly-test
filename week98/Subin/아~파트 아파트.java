import java.io.*;
import java.util.*;

public class Main {
	
	static public class Member {
		int number, floor;
		
		public Member(int n, int f) {
			number = n;
			floor = f;
		}
	} 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 아파트 층 수.
		int m = Integer.parseInt(st.nextToken()); // 참가자의 수.
		int max = m * 2; // 최대 층 수.
		int result = 0;
		
		List<Member> list = new LinkedList<>();
		list.add(new Member(0, 0));
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			list.add(new Member(i, r));
			list.add(new Member(i, l));
		}
		
		// 층 수에 따라 정렬.
		Collections.sort(list, (o1, o2) -> o1.floor - o2.floor);
		
		// 나머지가 0이면 max값을 아니면 나머지 값을 입력.
		int floor = n % max == 0 ? max : n % max; 
		
		// 해당 층 수의 number 입력.
		result = list.get(floor).number;
		
		bw.write(Integer.toString(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
