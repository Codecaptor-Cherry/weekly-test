package saturday.sat241221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 팬그램 : 알파벳의 모든 문자가 최소 한 번씩 등장하는 문자열
 * 그램팬 : 모든 알파벳이 순서대로 나열된 팬그램
 * 문자열 S의 부분 문자열 중 그램팬인 것의 개수 구하기
 *
 * 부분 문자열 ~ A로 시작, 최소 26 길이, A와 Z로 개수 체크
 */

class Alpha {
    char alpha;
    long cnt;

    Alpha(char alpha, long cnt) {
        this.alpha = alpha;
        this.cnt = cnt;
    }
}
public class BJ_26650_그램팬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int length = S.length();
        long ans = 0; // int 범위를 넘어가는 경우 존재 ex) 49988(A) * 49988(Z) = 약 25억

        List<Alpha> list = new ArrayList<>();
        if(length < 26) {
            System.out.println(ans);
        } else {
            // 1. 문자열 압축(문자, 개수)
            int idx = 0;
            int cnt = 0;
            while(idx < length) {
                char now = S.charAt(idx);
                while(idx + cnt < length && S.charAt(idx + cnt) == now) {
                    cnt++;
                }

                idx += cnt;
                list.add(new Alpha(now, cnt));
                cnt = 0;
            }

            // 2. 그램팬
            idx = 0;
            int size = list.size();
            if(size >= 26) {
                while (idx < size) {
                    // 시작은 A
                    if (list.get(idx).alpha == 'A') {
                        long a = list.get(idx).cnt;

                        int end = 0;

                        // B ~ Y
                        for(end = 1; end < 25; end++) {
                            if(idx + end >= size - 1) { // Z 공간을 남겨야 함
                                break;
                            }

                            if(list.get(idx + end).alpha != list.get(idx).alpha + end){
                                break;
                            }
                        }

                        // 끝은 Z
                        if(end == 25 && list.get(idx + end).alpha == 'Z') {
                            ans += a * list.get(idx + end).cnt;

                            System.out.println(a + " * " + list.get(idx + end).cnt);
                        }

                        idx += end;

                    } else {
                        idx++;
                    }
                }
            }

            System.out.println(ans);
        }

    }
}
