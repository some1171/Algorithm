package swexpertacademy.d3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1206 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 0, N = 0, A = 0;
		int leftView, rightView;
		int[] buildings = null;
		String[] temp = null;
		
		while(N < 10) {
			A = 0;
			T = Integer.parseInt(in.readLine());
			temp = in.readLine().split(" ");
			buildings = new int[T];
			
			for(int i = 0; i < temp.length; i++) {
				buildings[i] = Integer.parseInt(temp[i]);
			}
			
			for(int i = 2; i < buildings.length - 2; i++) {
				leftView = buildings[i] - Math.max(buildings[i-1], buildings[i-2]);
				rightView = buildings[i] - Math.max(buildings[i+1], buildings[i+2]);

				if (leftView > 0 && rightView > 0) {
					A += Math.min(leftView, rightView);
				}
			}
			
			System.out.println("#" + (N+1) + " " + A);
			N++;
		}
	}
}

