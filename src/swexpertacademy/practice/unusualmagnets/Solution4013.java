package swexpertacademy.practice.unusualmagnets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution4013 {
	public static int T, K;
	public static int[] m1, m2, m3, m4, rotateNumber, rotateDirection;
	public static boolean[] check;

	public static void main(String[] args) throws IOException {
		Solution4013 solution = new Solution4013();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		m1 = new int[8];
		m2 = new int[8];
		m3 = new int[8];
		m4 = new int[8];
		check = new boolean[3];

		for (int tc = 0; tc < T; tc++) {
			int point = 0;
			K = Integer.parseInt(br.readLine());
			rotateNumber = new int[K];
			rotateDirection = new int[K];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				m1[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				m2[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				m3[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				m4[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				rotateNumber[i] = Integer.parseInt(st.nextToken());
				rotateDirection[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < K; i++) {
				solution.doCheck();
				solution.doChain(rotateNumber[i], rotateDirection[i], new boolean[4]);
			}
			
			point = m1[0] * 1 + m2[0] * 2 + m3[0] * 4 + m4[0] * 8;
			
			bw.flush();
			bw.write("#" + (tc + 1) + " " + point + "\n");
		}

		br.close();
		bw.close();
	}

	public void doChain(int n, int d, boolean[] visit) {
		visit[n - 1] = true;
		if (n == 1) {
			doRotate(m1, d);
			if (!visit[1] && check[0]) {
				doChain(2, d * (-1), visit);
			}
		} else if (n == 2) {
			doRotate(m2, d);
			if (!visit[0] && check[0]) {
				doChain(1, d * (-1), visit);
			}
			if (!visit[2] && check[1]) {
				doChain(3, d * (-1), visit);
			}
		} else if (n == 3) {
			doRotate(m3, d);
			if (!visit[1] && check[1]) {
				doChain(2, d * (-1), visit);
			}
			if (!visit[3] && check[2]) {
				doChain(4, d * (-1), visit);
			}
		} else if (n == 4) {
			doRotate(m4, d);
			if (!visit[2] && check[2]) {
				doChain(3, d * (-1), visit);
			}
		}
	}

	public void doRotate(int[] m, int d) {
		if (d == 1) {
			int last = m[7];
			for (int i = 7; i > 0; i--) {
				m[i] = m[i - 1];
			}
			m[0] = last;
		} else if (d == -1) {
			int first = m[0];
			for (int i = 0; i < 7; i++) {
				m[i] = m[i + 1];
			}
			m[7] = first;
		}
	}
	
	public void doCheck() {
		if (m1[2] != m2[6]) {
			check[0] = true;
		} else {
			check[0] = false;
		}
		if (m2[2] != m3[6]) {
			check[1] = true;
		} else {
			check[1] = false;
		}
		if (m3[2] != m4[6]) {
			check[2] = true;
		} else {
			check[2] = false;
		}
	}
}
