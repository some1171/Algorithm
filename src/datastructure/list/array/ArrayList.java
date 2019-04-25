package datastructure.list.array;

/*
 * ArrayList
 * 읽기, 쓰기: O(1)
 * 읽기 worst: O(n)
 * 
 */

public class ArrayList {
	private Object[] data;
	private int size;
	private int index;
	
	public ArrayList() {
		this.size = 1;
		this.data = new Object[this.size];
		this.index = 0;
	}
	
	public void add(Object obj) {
		if (this.index == this.size - 1) {
			doubling();
		}
		data[this.index] = obj;
		this.index++;
		System.out.println("indx: " + this.index + ", size: " + this.size + ", data size: " + this.data.length);
	}
	
	private void doubling() {
		this.size =this.size * 2;
		Object[] newData = new Object[this.size];
		
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		this.data = newData;
		System.out.println("*** indx: " + this.index + ", size: " + this.size + ", data size: " + this.data.length);
	}
	
	public Object get(int i) throws Exception {
		if (i > this.index - 1) {
			throw new Exception("ArrayIndexOutOfBound");
		} else if (i < 0) {
			throw new Exception("Negative Value");
		}
		
		return this.data[i];
	}
	
	public void remove(int i) throws Exception {
		if (i > this.index - 1) {
			throw new Exception("ArrayIndexOutOfBound");
		} else if (i < 0) {
			throw new Exception("Negative Value");
		}
		
		for (int j = i; j < this.data.length - 1; j++) {
			data[j] = data[j + 1];
		}
		this.index--;
	}

}
