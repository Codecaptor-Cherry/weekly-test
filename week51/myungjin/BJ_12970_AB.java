
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		List<char[]>  res = new ArrayList<>();
		char [] stringSet = new char[N];
		for(int n = 0; n < N; n++){
			stringSet[n] = 'A';
		}
		// 뒤에서부터 하나씩 B 를 넣어본다
		for(int idx = N -1; idx >=0; idx--){
			stringSet[idx] ='B';
			if(checkSet(stringSet) == K){
				res.add(stringSet.clone()); // 이거 확인 필요함..
				break;
			}else if(checkSet(stringSet) > K){
				stringSet[idx] = 'A'; // 갯수가 넘어가면 줄여야하니 다시 원복하고 한칸뛰고 에이교체 시도
			}
		}
		if(res.size() == 0){
			System.out.println(-1);
		}else {
			for(char val :res.get(0)){
				System.out.print(val);
			}
		}
	}
	private static int checkSet(char [] testTarget){
		int count = 0;
		int aCount = 0;
		for(int idx = 0; idx < N; idx++){
			if(testTarget[idx] == 'A'){
				aCount++;
			}else {
				count+=aCount;
			}
		}
		return count;
	}

}
