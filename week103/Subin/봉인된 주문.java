import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        // 삭제된 주문 리스트
        // 사전순으로 앞에서부터 삭제해야 제대로 반영됨
        // 근데 문자열을 정렬할 순 없으니 숫자로..
        List<Long> removes = new ArrayList<>();
        for (String ban : bans) {
            removes.add(stringToIndex(ban));
        }
        Collections.sort(removes);
        
        // 주문 삭제 후 인덱스 반영
        for (long remove : removes) {
            if (remove <= n) ++n;
            else break;
        }
        
        // 주문 출력
        return indexToString(n);
    }
    
    private long stringToIndex(String s) {
        long base = 0;
        for (int len = 1; len < s.length(); len++) {
            base += (long) Math.pow(26, len);
        }
        
        long index = 0;
        for (char c : s.toCharArray()) {
            index = index * 26 + (c - 'a');
        }
        
        return base + index + 1;
    }

    private String indexToString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append((char) (--n % 26 + 'a'));
            n /= 26;
        }
        return sb.reverse().toString();
    }
}
