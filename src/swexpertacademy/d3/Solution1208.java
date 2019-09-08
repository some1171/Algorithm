package swexpertacademy.d3;

import java.util.Arrays;
import java.util.Scanner;

public class Solution1208 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] boxes = new int[100];
		
		for (int i = 0; i < 10; i++) {
			int dumpLimit = in.nextInt();
			
			for (int j = 0; j < 100; j++) {
				boxes[j] = in.nextInt();
			}
			Arrays.sort(boxes);
			
			for (int j = 0; j < dumpLimit; j++) {
				if (isFlatterned(boxes)) {
					break;
				}
				dump(boxes);
			}
			
			System.out.println("#" + (i + 1) + " " + (boxes[boxes.length - 1] - boxes[0]));
		}
		in.close();
	}

	public static boolean isFlatterned(int[] boxes) {
		int diff = boxes[boxes.length - 1] - boxes[0];
		
		if (diff == 0 || diff == 1) {
			return true;
		}
		
		return false;
	}

	public static void dump(int[] boxes) {
		boxes[boxes.length - 1]--;
		boxes[0]++;
		Arrays.sort(boxes);
	}
}
