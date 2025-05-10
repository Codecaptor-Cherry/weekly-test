import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int truth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int liarCnt = Integer.parseInt(st.nextToken());
        truth = -1;  // 진실을 아는 사람을 나타내는 값 (대표 한명만)
        for (int i = 0; i < liarCnt; i++) {
            int person = Integer.parseInt(st.nextToken());
            if (i == 0) {
                truth = person;
            } else {
                union(truth, person);
            }
        }

        // 파티를 돌면서 만나는 모든 사람을 같은 집합으로 묶는다.
        int[][] parties = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int candidateCnt = Integer.parseInt(st.nextToken());

            parties[i] = new int[candidateCnt];
            for (int j = 0; j < candidateCnt; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
                // 파티에서 만난 사람들 같은 그룹으로 묶기
                if (j != 0) union(parties[i][j - 1], parties[i][j]);
            }

        }

        int ans = M;
        // 파티를 돌면서 파티에 진실을 아는 사람이 존재하는지 확인
        for (int i = 0; i < M; i++) {
            for (int person : parties[i]) {
                // 진실을 아는 사람이 파티에 속해있다면 거짓말을 할 수 있는 파티 수 줄이기
                if (truth != -1 && find(person) == find(truth)) {
                    --ans;
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }
}
