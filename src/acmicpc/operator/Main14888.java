package acmicpc.operator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14888 {
	public static int N, M, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	public static int[] numbers, operators;

	public static void main(String[] args) throws IOException {
		Main14888 main = new Main14888();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		M = N - 1;
		numbers = new int[N];
		operators = new int[4];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		main.pick(0, new int[M], 0, 0, 0, 0);
		
		bw.flush();
		bw.write(max + "\n");
		bw.flush();
		bw.write(min + "\n");
		br.close();
		bw.close();
	}

	public void pick(int pos, int[] pick, int plusCount, int minusCount, int multiplyCount, int divideCount) {
		if (pos == M) {
			int result = calculate(pick);
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		// µ¡¼ÀÀ» »Ì´Â´Ù.
		if (plusCount < operators[0]) {
			pick[pos] = 0;
			pick(pos + 1, pick, plusCount + 1, minusCount, multiplyCount, divideCount);
		}
		// »¬¼ÀÀ» »Ì´Â´Ù.
		if (minusCount < operators[1]) {
			pick[pos] = 1;
			pick(pos + 1, pick, plusCount, minusCount + 1, multiplyCount, divideCount);
		}
		// °ö¼ÀÀ» »Ì´Â´Ù.
		if (multiplyCount < operators[2]) {
			pick[pos] = 2;
			pick(pos + 1, pick, plusCount, minusCount, multiplyCount + 1, divideCount);
		}
		// ³ª´°¼ÀÀ» »Ì´Â´Ù.
		if (divideCount < operators[3]) {
			pick[pos] = 3;
			pick(pos + 1, pick, plusCount, minusCount, multiplyCount, divideCount + 1);
		}
	}

	public int calculate(int[] peek) {
		int result = numbers[0];
		int idx = 1;
		for (int i = 0; i < M; i++) {
			if (peek[i] == 0) {
				result += numbers[idx++];
			} else if (peek[i] == 1) {
				result -= numbers[idx++];
			} else if (peek[i] == 2) {
				result *= numbers[idx++];
			} else {
				result /= numbers[idx++];
			}
		}
		return result;
	}
}
