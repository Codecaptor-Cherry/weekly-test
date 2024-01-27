

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int N;
	private static int K;
	private static boolean[] check;
	private static Map<Character, Integer> charToIntMap;
	private static Map<Integer, Character> intToCharMap;
	private static List<Set<Character>> inputSet;
	private static Set<Integer> banList;
	private static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		res = 0;
		inputSet = new ArrayList<>();
		check = new boolean[26];
		charToIntMap = new HashMap<>();
		intToCharMap = new HashMap<>();
		banList = new HashSet<>();
		// {a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7,
		// i=8, j=9, k=10, l=11, m=12, n=13, o=14, p=15,
		// q=16, r=17, s=18, t=19, u=20, v=21, w=22, x=23, y=24, z=25}

		check[0] = true;//a
		check[2] = true; //c
		check[8] = true; //i
		check[13] = true;// n
		check[19] = true; //t // 다음 글자들은 포함되어 있어야한다.
		banList.add(0); // acint 는 포함 안시키기 위한 것
		banList.add(2);
		banList.add(8);
		banList.add(13);
		banList.add(19);



		for (int n = 0; n < N; n++) {
			String val = br.readLine();
			Set<Character> temp = new HashSet<>();
			for(int col = 0; col < val.length(); col++){
				char ch = val.charAt(col);
				if(!(ch == 'a' || ch == 'c' || ch == 'i' || ch == 'n' || ch == 't')){
					temp.add(ch);
				}
			}
			inputSet.add(temp);
		}
		if (K < 5) {
			System.out.println(0);
			return;
		}else if (K == 26){
			System.out.println(N);
			return;
		}

		K -= 5; //antic 가 이미 포함되어야한다.

		char start = 'a';
		for (int i = 0; i < 26; i++) {
			charToIntMap.put(start, i); // 변환
			intToCharMap.put(i, start);
			start++;
		}

		dfs(0, 0);
		System.out.println(res);

	}

	private static void dfs(int depth, int start) { //50 * 10 ^ 7
		if (depth == K) {
			res = Math.max(res,howManyCanRead());  //
			return;
		}
		for (int i = start; i < 26; i++) {
			char ch = intToCharMap.get(i);
			int idx = charToIntMap.get(ch);
			if (!check[idx] ) {
				check[idx] = true;
				dfs(depth + 1, i);
				check[idx] = false;
			}
		}
	}

	private static int howManyCanRead() { // n 개중에 얼마나 포함되어 있는지 확인
		int count = 0;
		for (Set<Character> input : inputSet) {
			boolean isContain = true;
			for(char targetChar : input){
				if(!check[charToIntMap.get(targetChar)]){
					isContain = false;
					break;
				}
			}
			if(isContain){
				count++;
			}
		}
		return count;
	}

}
