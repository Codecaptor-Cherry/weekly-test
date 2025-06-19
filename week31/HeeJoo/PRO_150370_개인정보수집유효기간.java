/*
 * 1 ~ n번으로 분류되는 개인정보 n개
 * 각 약관마다 유효기간 존재
 * 유효기간이 지났다면 반드시 파기... 당일 이전까지만 가능
 * 모든 달은 28일까지 존재
 * 파기해야 할 개인정보의 번호를 오름차순으로 return
 * 다른 풀이는 year, month, day의 합산을 구해서 비교
 */

import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> ans = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(today, ".");
        int ty = Integer.parseInt(st.nextToken());
        int tm = Integer.parseInt(st.nextToken());
        int td = Integer.parseInt(st.nextToken());
        
        int[] periods = new int[26]; // 약관의 유효기간. 알파벳 오름차순
        for(String term : terms) {
            st = new StringTokenizer(term);
            int idx = atoi(st.nextToken().charAt(0));
            periods[idx] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String date = st.nextToken();
            char type = st.nextToken().charAt(0);
            
            st = new StringTokenizer(date, ".");
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            
            int period = periods[atoi(type)]; // 유효기간
            
            // System.out.printf("%d.%d.%d : %d개월 유효\n", year,month,day,period);
            // today와 날짜 비교 : 계약 날짜 + 유효기간 < today면 파기
            // 계약 날짜 + 유효기간
            year += period / 12;
            period %= 12;
            
            if(month + period > 12) { // 12월을 넘어가면 연도++
                month += period - 12;
                year++;
            } else {
                month += period;
            }
            
            // 날짜 비교
            // 1. 연 비교
            if(year < ty) { // 파기
                ans.add(i + 1);
                continue;
            } else if(year == ty) { // 2. 월 비교
                if(month < tm) { // 파기
                    ans.add(i + 1);
                    continue;
                } else if(month == tm) { // 3. 일 비교
                    if(day <= td) { // 파기
                    ans.add(i + 1);
                    continue;
                    } else { // 유효
                        continue;
                    }
                } else { // 유효
                    continue;
                }
            } else { // 유효
                continue;
            }
            
        }
        
        return ans.stream()
            .mapToInt(i -> i)
            .toArray();
    }
    
    public int atoi(char ch) {
        return (int)(ch - 'A');
    }
}
