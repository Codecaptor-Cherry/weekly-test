import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_01477_휴게소세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        List<Integer> stations = new ArrayList<>();
        stations.add(l);

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreElements()) {
            stations.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(stations);

        List<Section> sections = new ArrayList<>();
        int from = 0;
        for (int station : stations) {
            sections.add(new Section(from, station));
            from = station;
        }
        
        // to - mid * x >= from + 1
        // to - from - 1 >= mid * x
        // mid <= (to - from - 1) / x
        int left = 1;
        int right = l;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;

            for (Section section : sections) {
                count += ((section.to - section.from - 1) / mid);
            }

            if (m < count) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(left);
    }

    static class Section {
        int from;
        int to;

        public Section(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
