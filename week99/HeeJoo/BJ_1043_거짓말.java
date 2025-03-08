package saturday.year25.sat250308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 사람의 수 N
 * 진실을 아는 사람  존재
 * 각 파티에 오는 사람들의 번호
 * 주인공은 모든 파티참가
 * 거짓말쟁이로 알려지지않으면서 과장된 이야기를 할 수 있는 파티 개수 최댓값 구하기
 * 거짓말쟁이가 되지 않기 위해서는 ...
 * 1. 진실을 아는 사람들이 파티에 왔을 때, 과장 불가
 * 2. 이전에 진실/과장을 들은 사람이 있는 파티에 왔을 때, 과장/진실 불가 ~ 즉 파티마다 엇갈리지 않도록 하기
 *
 * 유니온 파인드
 */
public class BJ_1043_거짓말 {
    static int[] parents;
    static boolean[] tfs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken()); // 사람의 수
        int M = stoi(st.nextToken()); // 파티 수

        st = new StringTokenizer(br.readLine());
        tfs = new boolean[N + 1]; // 해당 인덱스를 번호로 갖는 사람들이 진실을 알고 있는지 체크 (1 ~ N)
        int cnt = stoi(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            tfs[stoi(st.nextToken())] = true;
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        ArrayList<int[]> list = new ArrayList<>(); // 각 파티의 참여자 번호 모음
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()); // 파티 참여자 수

            list.add(new int[stoi(st.nextToken())]);
            for (int j = 0; j < list.get(i).length; j++) { // 참여자 번호 모음
                list.get(i)[j] = stoi(st.nextToken());
            }
        }

        // -----------------------------------------------------

        if(tfs.length == 1) {
            System.out.println(N);
            System.exit(0);
        }

        // -----------------------------------------------------

        for(int[] array : list) {
            for(int i = 0; i < array.length - 1; i++) {
                for(int j = i + 1; j < array.length; j++) {
                    unionParent(array[i], array[j]);
                }
            }
        }

        int ans = 0;
        for(int[] array : list) {
            int idx = findParent(array[0]);

            if(!tfs[idx]) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static int findParent(int x) {
        if(x == parents[x]) {
            return x;
        } else {
            return findParent(parents[x]);
        }
    }

    public static boolean unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if(a == b) {
            return false;
        }

        if(a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }

        // 진실여부 통일
        if(tfs[a]) {
            tfs[b] = true;
        } else if(tfs[b]) {
            tfs[a] = true;
        }

        return true;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
