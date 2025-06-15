
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        Map<String, String> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String record = st.nextToken();
            if(record.equals("enter")) map.put(name, record);
            else map.remove(name);
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        Collections.reverse(keySet);

        for(int i = 0; i < keySet.size(); i++) {
            System.out.println(keySet.get(i));
        }
    }

}
