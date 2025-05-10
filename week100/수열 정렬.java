import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        
        int min = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            min = 0;
            for (int j = 0; j < n; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            ans[min] = i;
            a[min] = 1001;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }
}
