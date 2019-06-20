package swexpertacademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Solution1234 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<String> pw = new LinkedList<String>();
		int size, problem = 0;
		String[] temp, init;
		
		while (problem++ < 10) {
			temp = br.readLine().split(" ");
			size = Integer.parseInt(temp[0]);
			init = new String[size];
			init = temp[1].split("");
			
			for (int i = 0; i < size; i++) {
				pw.add(init[i]);
			}
			
			boolean isExist = true;
			int status = 0;
			while (isExist) {
				for (int i = 0; i < pw.size() - 1; i++) {
					if (pw.get(i).equals(pw.get(i + 1))) {
						pw.remove(i);
						pw.remove(i);
						status = 1;
						break;
					}
				}
				
				if (status == 0) {
					isExist = false;
				} else if (status == 1) {
					isExist = true;
					status = 0;
				}
			}
			
			System.out.print("#" + problem + " ");
			for (String s : pw) {
				System.out.print(s);
			}
			System.out.println();
			pw.clear();
		}
		
		br.close();
	}
}
