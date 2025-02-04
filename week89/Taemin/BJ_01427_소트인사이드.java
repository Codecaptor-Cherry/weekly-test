import java.util.*;

class BJ_01427_소트인사이드 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] digits = sc.next().toCharArray();
		
		int size = digits.length;
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int)digits[i] - 48;
		}
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		for (int elem : arr)
			System.out.print(elem);
	}
}
