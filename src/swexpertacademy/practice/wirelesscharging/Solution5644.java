package swexpertacademy.practice.wirelesscharging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5644 {
	public static int T, M, A, total;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[] directionA;
	public static int[] directionB;
	public static ArrayList<Charger> chargers = new ArrayList<Charger>();
	public static ArrayList<User> users = new ArrayList<User>();

	public static void main(String[] args) throws IOException {
		Solution5644 solution = new Solution5644();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			directionA = new int[M];
			directionB = new int[M];
			total = 0;
			chargers.clear();
			users.clear();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				directionA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				directionB[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int len = Integer.parseInt(st.nextToken());
				int pf = Integer.parseInt(st.nextToken());
				chargers.add(new Charger(r - 1, c - 1, len, pf));
			}
			users.add(new User(0, 0));
			users.add(new User(9, 9));
			
			User userA = users.get(0);
			User userB = users.get(1);
			for (int t = 0; t < M + 1; t++) {
				int countA = 0;
				int countB = 0;
				int maxPA = 0, maxCA = 0;
				int maxPB = 0, maxCB = 0;
				
				// 범위 안에 있는 충전기 중 최대 값
				for (int i = 0; i < A; i++) {
					Charger c = chargers.get(i);
					if(solution.isInRange(c, userA)) {
						countA++;
						if (maxPA < c.pf) {
							maxCA = i;
							maxPA = c.pf;
						}
					}
					if(solution.isInRange(c, userB)) {
						countB++;
						if (maxPB < c.pf) {
							maxCB = i;
							maxPB = c.pf;
						}
					}
				}
				// 둘이 최대가 같은거 일때
				if (countA != 0 && countB != 0 && maxCA == maxCB) {
					if (countA == 1 && countB == 1) {
						userA.sum += maxPA / 2;
						userB.sum += maxPB / 2;
					} else if (countA == 1) {
						userA.sum += maxPA;
						int secondPB = 0;
						for (int i = 0; i < A; i++) {
							if (i == maxCB) {
								continue;
							}
							Charger c = chargers.get(i);
							if(solution.isInRange(c, userB)) {
								if (secondPB < c.pf) {
									secondPB = c.pf;
								}
							}
						}
						userB.sum += secondPB;
					} else if (countB == 1) {
						userB.sum += maxPB;
						int secondPA = 0;
						for (int i = 0; i < A; i++) {
							if (i == maxCA) {
								continue;
							}
							Charger c = chargers.get(i);
							if(solution.isInRange(c, userA)) {
								if (secondPA < c.pf) {
									secondPA = c.pf;
								}
							}
						}
						userA.sum += secondPA;
					} else {
						int secondPA = 0;
						int secondPB = 0;
						for (int i = 0; i < A; i++) {
							Charger c = chargers.get(i);
							if (i == maxCA) {
								continue;
							}
							if(solution.isInRange(c, userA)) {
								if (secondPA < c.pf) {
									secondPA = c.pf;
								}
							}
							if(solution.isInRange(c, userB)) {
								if (secondPB < c.pf) {
									secondPB = c.pf;
								}
							}
						}
						if (secondPA >= secondPB) {
							userA.sum += secondPA;
							userB.sum += maxPB;
						} else {
							userA.sum += maxPA;
							userB.sum += secondPB;
						}
					}
				} else {
					if (countA != 0) {
						userA.sum += maxPA;
					}
					if (countB != 0) {
						userB.sum += maxPB;
					}
				}
				
				if (t == M) {
					break;
				}
				
				solution.move(userA, directionA[t]);
				solution.move(userB, directionB[t]);
			}
			total = users.get(0).sum + users.get(1).sum;
			bw.flush();
			bw.write("#" + (tc + 1) + " " + total + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public void move(User u, int d) {
		if (d == 0) {
			return;
		}
		u.r = u.r + dr[d - 1];
		u.c = u.c + dc[d - 1];
	}
	
	public boolean isInRange(Charger c, User u) {
		int distance = Math.abs(c.r - u.r) + Math.abs(c.c - u.c);
		if (distance <= c.len) {
			return true;
		}
		return false;
	}
}
class Charger {
	int r, c, len, pf;
	
	public Charger (int r, int c, int l, int p) {
		this.r = r;
		this.c = c;
		this.len = l;
		this.pf = p;
	}
}

class User {
	int r, c, sum;
	
	public User (int r, int c) {
		this.r = r;
		this.c = c;
		this.sum = 0;
	}
}
