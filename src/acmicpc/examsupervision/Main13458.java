package acmicpc.examsupervision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main13458 {
	public static int N, B, C;
	public static int[] a;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		a = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine().trim());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		long count = 0;
		for (int i = 0; i < N; i++) {
			a[i] -= B;
			count++;
			if (a[i] > 0) {
				int quotient = a[i] / C;
				int rest = a[i] % C;
				if (rest == 0) {
					count += quotient;
				} else {
					count += quotient + 1;
				}
			}
		}
		
		bw.flush();
		bw.write(count + "\n");
		br.close();
		bw.close();
	}
}
