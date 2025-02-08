package saturday.year25.sat250208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 총 N개의 쓰레기
 * 플라스틱(P), 캔(C), 비닐(V), 종이(S), 유리(G), 스티로폼(F), 일반 쓰레기(O)
 * 재활용은 일반 쓰레기를 제외하고 가능
 * 특정 쓰레기가 하나의 품목 X로만 구성되어 있다면, 해당 품목 분리수거 가능
 * 특정 쓰레기가 2가지 이상의 서로 다른 품목으로 구성되어 있거나 일반 쓰레기라면, 일반 쓰레기로 배출
 * 쓰레기 배출 비용 C = 쓰레기 크기 x X의 단위 크기당 비용
 * 쓰레기의 크기는 문자열의 길이
 * 쓰레기 N개를 모두 버리는 데 필요한 최소 쓰레기 배출 비용 구하기
 */
public class BJ_32980_분리배출 {
    static final String[] TYPE = new String[]{"P", "C", "V", "S", "G", "F", "O"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine()); // 쓰레기의 수

        String[] trashes = new String[N];
        for(int i = 0; i < N; i++) {
            trashes[i] = br.readLine();
        }

        int[] cost = new int[TYPE.length];
        String[] inputs = br.readLine().split(" ");
        for(int i = 0; i < TYPE.length - 1; i++) {
            cost[i] = stoi(inputs[i]);
        }
        cost[TYPE.length - 1] = stoi(br.readLine());
        
        // -----------------------------------------------------

        long ans = 0;

        for(int i = 0; i < N; i++) {
            int length = trashes[i].length();

            // 일반 쓰레기 배출
            if(trashes[i].contains(TYPE[TYPE.length - 1])) {
                ans += length * cost[TYPE.length - 1];
            } else {
                int tc = 0;
                int idx = 0;
                for (int j = 0; j < TYPE.length - 1; j++) {
                    if(trashes[i].contains(TYPE[j])) {
                        tc++;
                        idx = j;
                    }
                }

                if(tc > 1) {
                    ans += length * cost[TYPE.length - 1];
                } else {
                    ans += cost[idx] < cost[TYPE.length - 1] ? length * cost[idx] : length * cost[TYPE.length - 1];
                }
            }

        }

        System.out.println(ans);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
