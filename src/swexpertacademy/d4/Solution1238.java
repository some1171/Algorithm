package swexpertacademy.d4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1238 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ti = 0;
		
		for (ti = 0; ti < 10; ti++) {
			int size = sc.nextInt();
			Graph graph = new Graph(size);
			int start = sc.nextInt();
			int half = size / 2;
			
			for (int i = 0; i < half; i++) {
				graph.addEdge(sc.nextInt(), sc.nextInt());
			}
			
			System.out.printf("#%d %d\n", ti + 1, graph.bfs(start));
		}
		sc.close();
	}
}

class Graph {
	Person[] people;
	
	public Graph(int size) {
		people = new Person[size];
		
		for (int i = 0; i < size; i++) {
			people[i] = new Person(i);
		}
	}
	
	public void addEdge(int n1, int n2) {
		Person p1 = people[n1];
		Person p2 = people[n2];
		
		if (!p1.getContacts().contains(p2)) {
			p1.addContacts(p2);
		}
	}
	
	public int bfs(int index) {
		int last = 0;
		int level = 0;
		Person start = people[index];
		Queue<Person> queue = new LinkedList<Person>();
		start.setMarked();
		start.setLevel(level++);
		queue.add(start);
		last = start.getNumber();
		
		while (!queue.isEmpty()) {
			Person person = queue.poll();
			
			for (Person p : person.getContacts()) {
				if (!p.isMarked()) {
					p.setMarked();
					p.setLevel(person.getLevel() + 1);
					queue.add(p);
				}
			}
			
			if (person.getLevel() == people[last].getLevel()) {
				last = Math.max(person.getNumber(), people[last].getNumber());
			} else {
				last = person.getNumber();
			}
		}
		
		return last;
	}
}

class Person {
	private int number;
	private int level;
	private LinkedList<Person> contacts;
	private boolean marked;
	
	public Person(int number) {
		this.number = number;
		this.level = 0;
		this.contacts = new LinkedList<Person>();
		this.marked = false;
	}
	
	public LinkedList<Person> getContacts() {
		return contacts;
	}
	
	public void addContacts(Person person) {
		contacts.add(person);
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setMarked() {
		this.marked = true;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getNumber() {
		return number;
	}
}
