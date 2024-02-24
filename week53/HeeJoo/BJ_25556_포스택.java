package saturday.sat240224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 길이가 N인 순열 A와 빈 스택 4개
 * 순열 A는 1 이상 N 이하의 서로 다른 정수 N개가 임의로 나열된 수열
 * 순열을 다음 과정에 따라 오름차순으로 정렬
 * 규칙 1. 순열 A의 원소들을 앞 원소부터 순서대로 네 개의 스택 중 하나에 삽입
 * 규칙 2. 순열 A의 모든 원소를 스택에 삽입했다면, 네 개 중 원하는 스택에서 수를 꺼내는 것을 반복하여 네 개의 스택에서 모든 수 꺼내기
 * 규칙 3. 꺼낸 수들을 꺼낸 순서대로 오른쪽에서 왼쪽으로 나열 ... 즉, 가장 처음에 꺼낸 수가 맨 뒤, 가장 나중에 꺼낸 수가 맨 앞에 위치
 * 주어진 순열을 오름차순으로 정렬할 수 있는지 판별하기
 */
public class BJ_25556_포스택 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        String[] input = br.readLine().split(" ");

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        Stack<Integer> st3 = new Stack<>();
        Stack<Integer> st4 = new Stack<>();

        // top보다 큰 경우만 stack에 넣기
        boolean flag = true;
        for(int i = 0; i < N; i++) {
            if(st1.isEmpty() || st1.peek() < arr[i]) {
                st1.push(arr[i]);
                continue;
            }

            if(st2.isEmpty() || st2.peek() < arr[i]) {
                st2.push(arr[i]);
                continue;
            }

            if(st3.isEmpty() || st3.peek() < arr[i]) {
                st3.push(arr[i]);
                continue;
            }

            if(st4.isEmpty() || st4.peek() < arr[i]) {
                st4.push(arr[i]);
                continue;
            }

            flag = false;
            break;
        }

        if(flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}
