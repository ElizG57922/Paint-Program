package serializeAndSwingGUI;

import java.util.Iterator;

public class MyList<TYPE> {
	private TYPE data[];
	private int capacity;
	private int growthFactor;
	private int size;

	public MyList() {
		capacity = 10;
		growthFactor = 2;
		Object o = new Object[capacity];
		data = (TYPE[])o;
		for(int i = 0; i < capacity; i++) {
			data[i] = null;
		}
	}
	
	public MyList(int c, int g) {
		this.capacity = c;
		this.growthFactor = g;
		Object o = new Object[capacity];
		data = (TYPE[])o;
		for(int i = 0; i < capacity; i++) {
			data[i] = null;
		}
	}

	public int capacity() {
		return capacity;
	}

    boolean isEmpty() {
    	return (size == 0) ? true: false;
	}
	
	int size() {
	    return size;
	}
	
	boolean contains(TYPE e) {
		for(TYPE i : data) {
			if(i.equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	TYPE[] toArray(TYPE[] array) {
	    for(int i = 0; i < size; i++) {
	    	array[i] = data[i];
	    }
	    return array;
	}
	
	void add(TYPE e) {
	    size ++;
	    if(size > capacity) {
	    	/////////////////////////////grow//////////////////////////////////////
	    	int newCapacity = 0;
	    	if(growthFactor == 2) {
	    		newCapacity = capacity * 2;
	    	}
	    	else {
    	    	newCapacity = capacity + growthFactor;
	    	}
	    	Object newO = new Object[newCapacity];
			TYPE newData[] = (TYPE[])newO;
			for(int i = 0; i < capacity; i++) {
				newData[i] = data[i];
			}
			for(int i = capacity; i < newCapacity; i++) {
				newData[i] = null;
			}
			capacity = newCapacity;
			data = newData;
	    	
	    }
	    data[size - 1] = e;
	}
	
	void add(int index, TYPE e) {
		if(index >= 0 && index <= size) {
    	    size ++;
	        if(size > capacity) {
	        	/////////////////////////////grow//////////////////////////////////////
	        	int newCapacity = 0;
		    	if(growthFactor == 2) {
		    		newCapacity = capacity * 2;
		    	}
		    	else {
	    	    	newCapacity = capacity + growthFactor;
		    	}
    	    	Object newO = new Object[newCapacity];
	    		TYPE newData[] = (TYPE[])newO;
		    	for(int i = 0; i < capacity; i++) {
			    	newData[i] = data[i];
    			}
	    		for(int i = capacity; i < newCapacity; i++) {
		    		newData[i] = null;
			    }
	    		capacity = newCapacity;
		    	data = newData;
	        }
	        for(int i = size - 2; i >= index; i--) {
	        	data[i + 1] = data[i];
	        }
	        data[index] = e;
		}
	}
	
	boolean remove(TYPE e) {
	    for(int i = 0; i < size; i++) {
	    	if(data[i].equals(e)) {
	    		for(int j = i; j < size; j++) {
	    			data[j] = data[j + 1];
	    		}
	    	    size --;
	    		return true;
	    	}
	    }
	    return false;
	}
	
	boolean remove(int index) {
	    if(index >= 0 && index < size) {
	    	for(int i = index; i < size; i++) {
	    		data[i] = data[i + 1];
	    	}
	    	size --;
    		return true;
	    }
	    else {
	    	return false;
	    }
	}
			
	void clear() {
	    size = 0;
	}

	public TYPE get(int index) {
		if(index >= 0 && index < size) {
			return data[index];
		}
		else {
			return null;
		}
	}


	public TYPE set(int index, TYPE e) {
		if(index >= 0 && index < size) {
			data[index] = e;
			return e;
		}
		else {
			return null;
		}
	}
	
	int indexOf(TYPE e) {
		for(int i = 0; i < size; i ++) {
			if(data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
	
	int lastIndexOf(TYPE e) {
		for(int i = size - 1; i >= 0; i--) {
			if(data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	public Iterator<TYPE> iterator() {
		return new MyIterator();
	}
    
	//inner class
	public class MyIterator implements Iterator<TYPE> {
		private int current;

		public MyIterator() {
			current = 0;
		}

		public boolean hasNext() {
			return (current < size);
		}

		public TYPE next() {
			if(current < size) {
				return data[current++];
			}
			else {
				return null;
			}
		}
	}
}