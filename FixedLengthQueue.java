class FixedLengthQueue<T> {
	T[] array;
	int sta = 0, end = 0;
	public FixedLengthQueue(Class<T> c, int size) {
		array = (T[])Array.newInstance(c, size+1);
	}
	
	public int size() {
		return (end+array.length-sta) % array.length;
	}
	
	public boolean isEmpty() {
		return sta == end;
	}
	
	public boolean add(T element) {
		if ((end+1) % array.length == sta) {
			return false;
		}
		array[end] = element;
		end = (end+1) % array.length;
		return true;
	}
	
	public T remove() {
		if (sta == end) {
			return null;
		}
		T res = array[sta];
		sta = (sta+1) % array.length;
		return res;
	}
}

