

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 2H24M 걸림
//14408kb	136ms

class Cordi {
	int row;
	int col;
	String color;

	Cordi(int row, int col, String color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.row);
		sb.append(" ");
		sb.append(this.col);
		sb.append(" ");
		sb.append(this.color);
		sb.append("]");
		return sb.toString();
	}
}

public class Main {
	final static int ROW = 12;
	final static int COL = 6;
	static String[][] map;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new String[ROW][COL];
		for (int row = 0; row < ROW; row++) {
			String rowLine = br.readLine();
			for (int col = 0; col < COL; col++) {
				map[row][col] = String.valueOf(rowLine.charAt(col));
			}
		}
		// 1. 부슨다 - 전체 맵을 돌면서
		// 2. 내린다
		int count = 0;
		while (destroy()){
			count++;
			gravity();
		}
		//print(map);
		System.out.println(count);

	}

	public static void gravity() {
		// column 단위로 보면서  . 을 제거 한다.
		for (int col = 0; col < COL; col++) {
			String nextColLine = ""; // 재배치된 뿌요 column 라인
			for (int row = ROW - 1; row >= 0; row--) {
				if (!map[row][col].equals(".")) {
					nextColLine += map[row][col]; //뿌요들만 보관
				}
			}
			int len = nextColLine.length();
			for (int count = 1; count <= ROW - len; count++) {
				nextColLine += "."; // 뒤를 다 . 으로 채운다
			}
			int idx = 0;
			for (int row = ROW - 1; row >= 0; row--) {
				map[row][col] = String.valueOf(nextColLine.charAt(idx++));// map 에 재배치
			}
		}

	}

	public static boolean destroy() { // 연쇄 만큼 돈다.
		boolean[][] check = new boolean[ROW][COL];
		int destroyCount = 0;
		Cordi curr = null;
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++) {
				if (!check[row][col] && !map[row][col].equals(".")) {
					curr = new Cordi(row, col, map[row][col]);
					if (bfs(curr, check)) {
						destroyCount++;
					}
				}

			}
		}
		return destroyCount > 0; // 한번이라도 부셨다면 true 반환
	}

	private static boolean bfs(Cordi input, boolean[][] check) {  // 바꾼게 있다면 true 없다면 false
		// 현재 들어온것과 같은것만 제거하는함수
		Queue<Cordi> q = new ArrayDeque<>();
		int destroyCount = 0; // 제거갯수
		List<Cordi> searchList = new ArrayList<>(); // 같은걸 찾은 리스트
		q.add(input);
		while (!q.isEmpty()) {
			Cordi curr = q.poll();
			for (int[] d : dir) {
				int nextRow = curr.row + d[0];
				int nextCol = curr.col + d[1];
				if (isIn(nextRow, nextCol) && !map[nextRow][nextCol].equals(".") && map[nextRow][nextCol].equals(
					curr.color)
					&& !check[nextRow][nextCol]) {
					check[nextRow][nextCol] = true;
					Cordi next = new Cordi(nextRow, nextCol, map[nextRow][nextCol]);
					q.add(next);
					searchList.add(next);
					destroyCount++;

				}
			}
		}
		if (destroyCount >= 4) { // 4개 이상이면 바꿔라
			for (Cordi cordi : searchList) {
				map[cordi.row][cordi.col] = ".";
			}

			return true;
		}
		return false;
	}

	private static boolean isIn(int row, int col) {
		return row < ROW && row >= 0 && col < COL && col >= 0;
	}

	private static void print(String[][] map) {
		System.out.println();
		for (int row = 0; row < ROW; row++) {
			System.out.println();
			for (int col = 0; col < COL; col++) {
				System.out.print(map[row][col] + " ");
			}
		}
	}

}
