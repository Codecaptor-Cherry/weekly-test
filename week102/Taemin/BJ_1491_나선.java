import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1491_나선 {

    static int[][] movements = {
        { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int numOfRow = Integer.parseInt(st.nextToken());
        int numOfCol = Integer.parseInt(st.nextToken());

        char[][] map = new char[numOfRow][numOfCol];

        // (0, numOfCol - 1)                   (numOfRow - 1, numOfCol -1)
        // (0, 0)                              (numOfRow - 1, 0)
        int[] result = play(map, numOfRow, numOfCol);
        System.out.print(result[0] + " " + result[1]);
    }

    public static int[] play(char[][] map, int numOfRow, int numOfCol) {
        boolean[][] visited = new boolean[numOfRow][numOfCol];
        int[] result = new int[2];
        Visitor visitor = new Visitor(0, 0, 0);
        visited[0][0] = true;

        // 0(East), 1(North), 2(West), 3(South)
        while (true) {
            int nextRow = visitor.row + movements[visitor.direction][0];
            int nextCol = visitor.col + movements[visitor.direction][1];

            if ((0 > nextRow || nextRow >= numOfRow || 0 > nextCol || nextCol >= numOfCol) || visited[nextRow][nextCol]) {
                int changedDirection = (visitor.direction + 1) % 4;
                visitor.direction = changedDirection;

                nextRow = visitor.row + movements[visitor.direction][0];
                nextCol = visitor.col + movements[visitor.direction][1];
            }

            if ((0 > nextRow || nextRow >= numOfRow || 0 > nextCol || nextCol >= numOfCol) || visited[nextRow][nextCol]) {
                result[0] = visitor.row;
                result[1] = visitor.col;
                break;
            }
            else {
                visitor.row = nextRow;
                visitor.col = nextCol;
                visited[nextRow][nextCol] = true;
            }
        }

        return result;
    }

    public static class Visitor {
        int row;
        int col;
        int direction;

        public Visitor(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }
    }

}
