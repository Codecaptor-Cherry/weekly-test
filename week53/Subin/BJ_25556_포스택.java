import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_25556_포스택 {

    private static class FourStack {
        private Stack<Integer>[] stacks;

        public FourStack() {
            stacks = new Stack[4];
            for (int i = 0; i < 4; i++) {
                stacks[i] = new Stack();
            }
        }

        /**
         * 자기보다 작은 것 중 가장 큰 값이 들어있는 스택의 인덱스 반환
         */
        private int findStackIndexToPush(int num) {
            int idx = -1;
            int max = 0;
            for (int i = 0; i < 4; i++) {
                if (stacks[i].isEmpty() || stacks[i].peek() > num) continue;

                if (stacks[i].peek() > max) {
                    max = stacks[i].peek();
                    idx = i;
                }
            }
            return idx;
        }

        private int getEmptyStackIndex() {
            for (int i = 0; i < 4; i++) {
                if (stacks[i].isEmpty()) return i;
            }
            return -1;
        }

        public boolean pushStack(int num) {
            int idx = findStackIndexToPush(num);
            if (idx == -1) {
                idx = getEmptyStackIndex();
                if (idx == -1) return false;
            }

            stacks[idx].push(num);
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        FourStack fourStack = new FourStack();
        for (int i = 0; i < n; i++) {
            if (!fourStack.pushStack(a[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
