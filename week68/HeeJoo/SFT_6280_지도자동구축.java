/*
 * 1. 정사각형의 각 변에 중점 추가
 * 2. 정사각형의 중심에 점 추가

 * 4 -> 9 -> 25 -> 81
 * 2x2 -> 3x3 -> 5x5 -> 9x9
 * 2+0 -> 2+1 -> 3+2 -> 5+4
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int ans = getDotCount(N);

        System.out.println(ans * ans);
    }

    public static int getDotCount(int n) {
        if(n == 0) {
            return 2;
        }

        return getDotCount(n - 1) + getPow(n); 
    }

    public static int getPow(int n) {
        return 1 << (n - 1);
    }
}
