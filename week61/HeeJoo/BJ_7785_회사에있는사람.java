package saturday.sat240427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 로그가 주어졌을 때, 현재 회사에 있는 모든 사람 구하기
 */
public class BJ_7785_회사에있는사람 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> hm = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            if("enter".equals(input[1])) {
                hm.put(input[0], 1);
            } else if("leave".equals(input[1])) {
                hm.remove(input[0]);
            }
        }

        List<String> keySet = new ArrayList<>(hm.keySet());

        Collections.sort(keySet); // Set은 정렬된다는 보장이 없으니 꼭! 먼저 정렬해줘야함 ㅡㅅㅡ!!
        Collections.reverse(keySet);

//        HashMap<String, Boolean> hm = new HashMap<>();
//
//        for(int i = 0; i < n; i++) {
//            String[] input = br.readLine().split(" ");
//
//            if("enter".equals(input[1])) {
//                hm.put(input[0], true);
//            } else if("leave".equals(input[1])) {
//                hm.put(input[0], false);
//            }
//        }
//
//        List<String> keySet = new ArrayList<>();
//
//        for(Map.Entry<String, Boolean> entry : hm.entrySet()) {
//            if(entry.getValue()) {
//                keySet.add(entry.getKey());
//            }
//        }

        for(String name : keySet) {
            System.out.println(name);
        }
    }
}
