package acmicpc.population;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int N, L, R;
	public static int[][] map = new int[50][50];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int updateCount = 0;
		boolean isUpdate = true;
		while(isUpdate) {
			isUpdate = false;
			
			int[][] union = new int[50][50];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (union[i][j] == 0) {
						
					}
				}
			}
			
			
			
			
			updateCount++;
			isUpdate = true;
		}
		
		bw.flush();
		bw.write(updateCount + "\n");
		br.close();
		bw.close();
	}
}
