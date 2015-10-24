class EvenPos<Integer> implements Iterator<Integer> {
	
	Iterator<Integer> it;
	Integer next = null;
	boolean first = true;
	
	public EvenPos(Iterator<Integer> it) throws NoSuchElementException{
		this.it = it;
		findNextEven();
		if (next == null) {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return next != null;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		Integer res = next;
		findNextEven();
		return res;
	}
	
	private void findNextEven() {
		next = null;
		if (first) {
			first = false;
			if (it.hasNext()) {
				next = it.next();
			}
			return;
		}
		if (it.hasNext()) {
			it.next();
			if (it.hasNext()) {
				next = it.next();
			}
		}
	}
}

class EvenIterator<Integer> implements Iterator<Integer> {
	
	Iterator<Integer> it;
	Integer next = null;
	
	public EvenIterator(Iterator<Integer> it) throws NoSuchElementException{
		this.it = it;
		findNextEven();
		if (next == null) {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return next != null;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		Integer res = next;
		findNextEven();
		return res;
	}
	
	private void findNextEven() {
		next = null;
		while (it.hasNext()) {
			Integer tmp = it.next();
			if ((int)tmp % 2 == 0) {
				next = tmp;
				break;
			}
		}
	}
}
