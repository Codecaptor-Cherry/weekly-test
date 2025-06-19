/*
 * 음악 제목, 재생 시작-끝 시각, 악보 제공
 * 사용된 음(12) : C, C#, D, D#, E, F, F#, G, G#, A, A#, B
 * 각 음은 1분에 1개씩 재생
 * 반드시 처음부터 재생됨
 * 음악 길이 < 재생 시간인 경우, 처음부터 반복해서 재생
 * 음악 길이 > 재생 시간인 경우, 재생 시간만큼만 재생
 * 00:00를 넘겨서 재생되지 않음
 * 조건에 일치하는 음악이 여러 개인 경우 재생된 시간이 제일 긴 음악 제목 return
 * 재생된 시간도 같을 경우 먼저 입력된 음악 제목 return
 * 조건에 일치하는 음악이 없을 때에는 "(None)" return
 
 * 주의 ! 재생 기간과 악보의 길이 ~ 실제 재생된 악보가 다름
 * 반음을 다른 문자로 대체해서 처리하기
 */

import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String ans = "(None)";
        
        m = changeSemitone(m);
        
        int length = 0;
        for(int i = 0; i < musicinfos.length; i++) {
            String[] infos = musicinfos[i].split(",");
            int start = getTime(infos[0]);
            int end = getTime(infos[1]);
            String name = infos[2];
            String music = changeSemitone(infos[3]);
            
            int time = end - start; // 실제 재생 시간
            if(time < music.length()) { // 기존 악보보다 짧은 경우
                music = music.substring(0, time);
            } else if(time > music.length()) { // 기존 악보보다 긴 경우
                String tmp = music;
                for(int j = 0; j < time / music.length(); j++) {
                    tmp += music;
                }
                
                for(int j = 0; j < time % music.length(); j++) {
                    tmp += music.charAt(j);
                }
                
                music = tmp;                
            } 
            
            // System.out.printf("%s | %s\n", m, music);
            if(music.contains(m)) {
                if(length < time) {
                    ans = name;
                    length = time;
                }
            }
            
        }
        
        return ans;
    }
    
    public int getTime(String str) {
        String[] time = str.split(":");
        
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
    public String changeSemitone(String str) {
        str = str.replaceAll("C#", "1");
        str = str.replaceAll("D#", "2");
        str = str.replaceAll("F#", "3");
        str = str.replaceAll("G#", "4");
        str = str.replaceAll("A#", "5");

        return str;
    }
}
