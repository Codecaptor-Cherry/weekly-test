package saturday.sat241026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * N개의 옵션 : 한 개 또는 여러 개의 단어로 기능 설명
 * 각 옵션에 단축키를 의미하는 대표 알파벳 지정
 * 1. 먼저 하나의 옵션에 대해 왼쪽부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정됐는지 확인
 * 1-1. 만약 단축키 지정이 안 된 경우 해당 알파벳을 단축키로 지정
 * 2. 만약 모든 안어의 첫 글자가 이미 지정된 경우, 왼쪽부터 차례대로 알파벳을 보며 단축키로 지정 안 된 것을 지정
 * 3. 어떠한 단축키도 지정할 수 없으면 그냥 두기
 *
 * 1. 문자열이 입력될 때 마다 확인
 * 2. 단어의 첫 글자 확인
 * 3. 오른쪽부터 확인
 * 4. 출력
 */
public class BJ_1283_단축키지정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = stoi(br.readLine());

        int[] alpha = new int[26];
        for(int i = 0; i < N; i++) {
            String[] arr = br.readLine().split(" "); // 입력된 옵션

            int[] ans = first(arr, alpha);

            if(ans[0] != -1) {
                print(arr, ans, sb);
                continue;
            }

            ans = second(arr, alpha);

            if(ans[0] != -1) {
                print(arr, ans, sb);
                continue;
            }

            for(String str : arr) {
                sb.append(str + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int[] first(String[] arr, int[] alpha) {
        int idx = -1;

        for(int i = 0; i < arr.length; i++) {
            int aIdx = getIdx(arr[i].charAt(0));

            if(alpha[aIdx] == 0) {
                idx = i;
                alpha[aIdx] = 1;
                break;
            }
        }

        return new int[]{idx, 0};
    }

    public static int[] second(String[] arr, int[] alpha) {
        int idx = -1, at = -1;

        for(int i = 0; i < arr.length; i++) {
            for(int j = 1; j < arr[i].length(); j++) {
                int aIdx = getIdx(arr[i].charAt(j));

                if(alpha[aIdx] == 0) {
                    idx = i;
                    at = j;
                    alpha[aIdx] = 1;
                    break;
                }
            }

            if(idx != -1) {
                break;
            }
        }

        return new int[]{idx, at};
    }

    public static int getIdx(char ch) {
        if(Character.isUpperCase(ch)) {
            return (int)(ch - 'A');
        } else {
            return (int)(ch - 'a');
        }
    }

    public static void print(String[] arr, int[] ans, StringBuilder sb) {
        for(int i = 0; i < arr.length; i++) {
            if(i == ans[0]) {
                for(int j = 0; j < arr[i].length(); j++) {
                    if(j == ans[1]) {
                        sb.append("[" + arr[i].charAt(j) + "]");
                    } else {
                        sb.append(arr[i].charAt(j));
                    }
                }

                sb.append(" ");
            } else {
                sb.append(arr[i] + " ");
            }
        }

        sb.append("\n");
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
