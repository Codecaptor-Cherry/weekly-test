import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static long[] songList;
    private static int maxSongs = -1;
    private static int minGuitars = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        songList = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            String song = st.nextToken();
            for (int j = 0; j < m; j++) {
                if (song.charAt(j) == 'Y') songList[i] |= (1L << j);
            }
        }

        comb(0, 0, 0);

        if (maxSongs < 1) System.out.println(-1);
        else System.out.println(minGuitars);
    }

    private static void comb(int idx, long curSong, int cnt) {
        if (idx == n) {
            int songCount = Long.bitCount(curSong);
            if (songCount > maxSongs) {
                maxSongs = songCount;
                minGuitars = cnt;
            } else if (songCount == maxSongs && minGuitars > cnt) {
                minGuitars = cnt;
            }
            return;
        }

        comb(idx + 1, curSong | songList[idx], cnt + 1);
        comb(idx + 1, curSong, cnt);
    }
}
