package saturday.sat241116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * V개의 마을(1 ~ V), E개의 도로
 * 일방통행 도로
 * 출발점 = 도착점 사이클
 * 사이클을 이루는 도로의 길이의 합이 최소가 되도록 찾기
 */
public class BJ_1956_운동 {
    final static int INF = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = stoi(st.nextToken());
        int E = stoi(st.nextToken());

        int[][] map = new int[V][V];
        for(int i = 0; i < V; i++) {
            Arrays.fill(map[i], INF);
        }
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken()) - 1;
            int b= stoi(st.nextToken()) - 1;

            map[a][b] = stoi(st.nextToken());
        }

        for(int k = 0; k < V; k++) {
            for(int i = 0; i < V; i++) {
                for(int j = 0; j < V; j++) {
                    if(map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int ans = INF;

        for(int k = 0; k < V; k++) {
            ans = Math.min(ans, map[k][k]);
        }

        System.out.println(ans == INF ? -1 : ans);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
