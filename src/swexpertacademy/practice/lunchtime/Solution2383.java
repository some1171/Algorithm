package swexpertacademy.practice.lunchtime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2383 {
	public static int T, N, M, minTime;
	public static int s0R, s0C, s0L, s1R, s1C, s1L;
	public static ArrayList<Point> peopleList = new ArrayList<Point>();
	public static ArrayList<Point> stairList = new ArrayList<Point>();
	
	public static void main(String[] args) throws IOException {
		Solution2383 solution = new Solution2383();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			M = 0;
			minTime = Integer.MAX_VALUE;
			peopleList.clear();
			stairList.clear();
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						// 사람일 경우
						peopleList.add(new Point(i, j));
						M++;
					} else if (map[i][j] > 1) {
						// 계단일 경우, 값은 계단의 길이
						stairList.add(new Point(i, j));
					}
				}
			}
			Point s0 = stairList.get(0);
			Point s1 = stairList.get(1);
			s0R = s0.r;
			s0C = s0.c;
			s0L = map[s0R][s0C];
			s1R = s1.r;
			s1C = s1.c;
			s1L = map[s1R][s1C];
			solution.peek(0, new int[M], 0, 0);
			
			bw.flush();
			bw.write("#" + tc + " " + minTime + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public void peek(int pos, int[] peek, int s0count, int s1count) {
		if (pos == M) {
			// 사람 수 만큼 다 뽑은 경우, 시간 계산하여 걸린 시간 업데이트
			update(peek, s0count, s1count);
			return;
		}
		peek[pos] = 0;
		peek(pos + 1, peek, s0count + 1, s1count);
		peek[pos] = 1;
		peek(pos + 1, peek, s0count, s1count + 1);
	}
	
	public void update(int[] peek, int s0count, int s1count) {
		int[] stair0List = new int[s0count];
		int[] stair1List = new int[s1count];
		int s0ListIndex = 0;
		int s1ListIndex = 0;
		for (int i = 0; i < M; i++) {
			Point p = peopleList.get(i);
			if (peek[i] == 0) {
				int arriveTime = Math.abs(p.r - s0R) + Math.abs(p.c - s0C) + 1;
				stair0List[s0ListIndex] = arriveTime;
				s0ListIndex++;
			} else {
				int arriveTime = Math.abs(p.r - s1R) + Math.abs(p.c - s1C) + 1;
				stair1List[s1ListIndex] = arriveTime;
				s1ListIndex++;
			}
		}
		Arrays.sort(stair0List);
		Arrays.sort(stair1List);
		// 계단 0 시간 계산
		boolean isExist = true;
		if (s0count == 0) {
			isExist = false;
		}
		int[] stair = new int[3];
		int time0 = 0, idx = 0;
		while (isExist) {
			for (int i = 0; i < 3; i++) {
				if (stair[i] == 0) {
					if (stair0List[idx] <= time0) {
						stair[i] = s0L;
						idx++;
						if (idx == s0count) {
							break;
						}
					}
				}
			}
			
			for (int i = 0; i < 3; i++) {
				if (stair[i] != 0) {
					stair[i]--;
				}
			}
			
			if (idx == s0count) {
				isExist = false;
				int restTime = 0;
				restTime = Math.max(stair[0], stair[1]);
				restTime = Math.max(restTime, stair[2]);
				time0 += restTime;
			}
			time0++;
		}
		// 계단 1 시간 계산
		isExist = true;
		if (s1count == 0) {
			isExist = false;
		}
		stair = new int[3];
		int time1 = 0;
		idx = 0;
		while (isExist) {
			for (int i = 0; i < 3; i++) {
				if (stair[i] == 0) {
					if (stair1List[idx] <= time1) {
						stair[i] = s1L;
						idx++;
						if (idx == s1count) {
							break;
						}
					}
				}
			}
			
			for (int i = 0; i < 3; i++) {
				if (stair[i] != 0) {
					stair[i]--;
				}
			}
			
			if (idx == s1count) {
				isExist = false;
				int restTime = 0;
				restTime = Math.max(stair[0], stair[1]);
				restTime = Math.max(restTime, stair[2]);
				time1 += restTime;
			}
			time1++;
		}
		minTime = Math.min(minTime, Math.max(time0, time1));
	}
}

class Point {
	int r, c;
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
