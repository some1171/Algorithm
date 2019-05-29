package swexpertacademy.d4;

public class Point {
	int r, c;
	boolean left, right, up, down;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;

		if (r == 0) {
			this.up = true;
		} else if (r == 100) {
			this.down = true;
		}

		if (c == 0) {
			this.left = true;
		} else if (c == 100) {
			this.right = true;
		}
	}
}
