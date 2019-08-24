package swexpertacademy.practice.treasurebox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution5658 {
	public static int T, N, K, L, answer;
	public static int[] numbers;
	public static char[] chars;

	public static void main(String[] args) throws IOException {
		Solution5658 solution = new Solution5658();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			L = N / 4;
			String s = br.readLine();
			chars = new char[N];
			numbers = new int[N];
			answer = 0;
			for (int i = 0; i < s.length(); i++) {
				chars[i] = s.charAt(i);
			}

			answer = solution.makeNumberList(numbers);
			
			bw.flush();
			bw.write("#" + (tc + 1) + " " + answer + "\n");
		}

		br.close();
		bw.close();
	}

	public int makeNumberList(int[] numbers) {
		int idx = 0;
		
		for (int i = 0; i < L; i++) {
			if (i != 0) {
				rotate(chars);
			}
			for (int j = 0; j < N; j = j + L) {
				int num = getNumber(j, chars);
				boolean isAlready = false;
				for (int k = 0; k < idx; k++) {
					if (numbers[k] == num) {
						isAlready = true;
					}
				}
				if (!isAlready) {
					numbers[idx] = num;
					idx++;
				}
			}
		}
		Arrays.sort(numbers);
		K = N - K;
		return numbers[K];
	}
	
	public int getNumber(int idx, char[] arr) {
		int result = 0;
		for (int i = 0; i < L; i++) {
			int num;
			switch(arr[idx + i]) {
				case 'A': num = 10;
				break;
				case 'B': num = 11;
				break;
				case 'C': num = 12;
				break;
				case 'D': num = 13;
				break;
				case 'E': num = 14;
				break;
				case 'F': num = 15;
				break;
				default: num = arr[idx + i] - '0';
				break;
			}
			result += num * Math.pow(16, L - 1 - i);
		}
		return result;
	}

	public void rotate(char[] arr) {
		char head = arr[0];
		for (int i = 0; i < N - 1; i++) {
			arr[i] = arr[i + 1];
		}
		arr[N - 1] = head;
	}
}
