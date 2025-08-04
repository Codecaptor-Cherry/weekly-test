
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int N = Integer.parseInt(br.readLine());
        Set<Character> check = new HashSet<>();

        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            String[] arr = str.split(" ");
            sb = new StringBuilder();
            boolean flag = false;

            for(int i = 0; i < arr.length; i++) {

                char ch = arr[i].toUpperCase().charAt(0);

                if(!check.contains(ch)) {
                    check.add(arr[i].toUpperCase().charAt(0));
                    flag = true;

                    sb = new StringBuilder(arr[i]);
                    sb.insert(0, "[");
                    sb.insert(2, "]");
                    arr[i] = sb.toString();
                    break;
                }
            }
            if(flag) {
                print(arr);
                continue;
            }

            for(int i = 0; i < arr.length; i++) {

                for(int j = 1; j < arr[i].length(); j++) {
                    char ch = arr[i].toUpperCase().charAt(j);

                    if(!check.contains(ch)) {
                        check.add(ch);
                        flag = true;
                        sb = new StringBuilder(arr[i]);
                        sb.insert(j, "[");
                        sb.insert(j + 2, "]");
                        arr[i] = sb.toString();
                        break;
                    }
                }
                if(flag) break;
            }
            print(arr);
        }
    }

    public static void print(String[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < arr.length ; i++) sb.append(arr[i]).append(" ");
        System.out.println(sb.toString().trim());
    }
}
