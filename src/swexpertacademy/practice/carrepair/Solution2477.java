package swexpertacademy.practice.carrepair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2477 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N, M, K, A, B;
			int[] a, b, tk, reception, repair;
			int sum = 0;
			ArrayList<Customer> customerList = new ArrayList<Customer>();
			ArrayList<Customer> finishList = new ArrayList<Customer>();
			Queue<Integer> receptionList = new LinkedList<Integer>();
			PriorityQueue<Customer> repairList = new PriorityQueue<Customer>();

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			a = new int[N + 1];
			reception = new int[N + 1];
			b = new int[M + 1];
			repair = new int[M + 1];
			tk = new int[K + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < K + 1; i++) {
				tk[i] = Integer.parseInt(st.nextToken());
				Customer c = new Customer(i, tk[i]);
				customerList.add(c);
				receptionList.add(i);
			}

			int min = 0;
			while (finishList.size() != K) {
				// reception ¿Ï·áµÈ °í°´ »«´Ù.
				for (int i = 1; i < N + 1; i++) {
					if (reception[i] == 0) {
						continue;
					}
					Customer c = customerList.get(reception[i] - 1);
					if (c.no == min) {
						reception[i] = 0;
						c.ri = min;
						repairList.add(c);
					}
				}

				// reception ´ë±â Áß °í°´ ³Ö´Â´Ù.
				for (int i = 1; i < N + 1; i++) {
					if (reception[i] != 0) {
						continue;
					}
					if (!receptionList.isEmpty()) {
						int number = receptionList.peek();
						Customer c = customerList.get(number - 1);
						if (c.t > min) {
							break;
						}

						reception[i] = number;
						c.n = i;
						c.no = min + a[i];
						receptionList.poll();
					}
				}

				// repair ¿Ï·áµÈ °í°´ »«´Ù.
				for (int i = 1; i < M + 1; i++) {
					if (repair[i] == 0) {
						continue;
					}
					Customer c = customerList.get(repair[i] - 1);
					if (c.mo == min) {
						repair[i] = 0;
						finishList.add(c);
					}
				}

				// repair ´ë±â Áß °í°´ ³Ö´Â´Ù.
				for (int i = 1; i < M + 1; i++) {
					if (repair[i] != 0) {
						continue;
					}
					if (!repairList.isEmpty()) {
						Customer c = repairList.poll();
						int number = c.number;
						c = customerList.get(number - 1);
						c.m = i;
						c.mo = min + b[i];
						repair[i] = number;
					}
				}
				min++;
			}
			
			for (int i = 0; i < finishList.size(); i++) {
				Customer c = finishList.get(i);
				if (c.n == A && c.m == B) {
					sum += c.number;
				}
			}
			
			if (sum == 0) {
				sum = -1;
			}

			bw.flush();
			bw.write("#" + (tc + 1) + " " + sum + "\n");
		}

		br.close();
		bw.close();
	}
}

class Customer implements Comparable<Customer> {
	int number, t, n, m, no, ri, mo;

	Customer(int number, int t) {
		this.number = number;
		this.t = t;
	}

	@Override
	public int compareTo(Customer c) {
		if (this.ri < c.ri) {
			return -1;
		} else if (this.ri == c.ri) {
			if (this.n < c.n) {
				return -1;
			}
		}
		return 1;
	}
}
