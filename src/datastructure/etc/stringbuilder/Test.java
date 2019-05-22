package datastructure.etc.stringbuilder;

public class Test {
	public static void main(String[] args) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder("Hello");
		
		sb1.append("Hello");
		sb1.append(" ");
		sb1.append("World");
		sb1.append("!");
		System.out.println(sb1.toString());
		
		sb2.append(" ");
		sb2.append("World");
		sb2.append("!");
		System.out.println(sb2.toString());
	}
}
