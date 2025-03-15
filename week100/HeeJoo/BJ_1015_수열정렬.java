package saturday.year25.sat250315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *P[0] ~ P[N-1] : 0부터 N-1까지의 수를 한 번씩 포함하고 있는 수열
 * 수열 P를 길이가 N인 배열 A에 적용하면 길이가 N인 배열 B가 됨 ... B[P[i]] = A[i]
 * 배열 A가 주어졌을 때, 수열 P를 적용한 결과가 비내림차순이 되는 수열 찾기
 * 비내림차순 : 각각의 원소가 바로 앞에 있는 원소보다 크거나 같은 경우
 * 그러한 수열이 여러개라면 사전순으로 앞서는 것을 출력
 * 2 3 1
 * a[0] = 2 = b[p[0]]
 * a[1] = 3 = b[p[1]]
 * a[2] = 1 = b[p[2]]
 * 0 1 2

 * 1 2 3
 * [2] [0] [1]
 *
 * 0 1 2
 * 1 2 0
 */

class Element implements Comparable<Element>{
    int idx, val;

    Element(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Element o) {
        return this.val - o.val;
    }
}
public class BJ_1015_수열정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = stoi(br.readLine());

        Element[] arr = new Element[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = new Element(i, stoi(st.nextToken()));
        }

        Arrays.sort(arr);
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[arr[i].idx] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i] + " ");
        }

        System.out.println(sb);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
