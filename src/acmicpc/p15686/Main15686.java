package acmicpc.p15686;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main15686 {
	public static int N, M, totalMinDistance = Integer.MAX_VALUE;
	public static Stack<Point> pick = new Stack<Point>();
	public static LinkedList<Point> homeList = new LinkedList<Point>();
	public static LinkedList<Point> shopList = new LinkedList<Point>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {
					homeList.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					shopList.add(new Point(i, j));
				}
			}
		}
		
		Main15686 main = new Main15686();
		main.dfs(0);
		
		bw.write(totalMinDistance + "\n");
		br.close();
		bw.close();
	}
	
	public void dfs(int index) {
		if (pick.size() == M) {
			int totalDistance = 0;
			
			for (int i = 0; i < homeList.size(); i++) {
				Point home = homeList.get(i);
				int minDistance = 101;
				
				for (int j = 0; j < pick.size(); j++) {
					Point shop = pick.get(j);
					int distance = Math.abs(home.r - shop.r) + Math.abs(home.c - shop.c);
					minDistance = Math.min(minDistance, distance);
				}
				
				totalDistance += minDistance;
			}
			
			totalMinDistance = Math.min(totalMinDistance, totalDistance);
			return;
		}
		
		for (int i = index; i < shopList.size(); i++) {
			pick.push(shopList.get(i));
			dfs(i + 1);
			pick.pop();
		}
	}
}

class Point {
	int r, c;
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
