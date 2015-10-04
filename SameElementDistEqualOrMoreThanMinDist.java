class Solution2 {
	/*
	 * Given arr[] = {A, B, B}, minDist = 2, output = {B, A, B};
	 */
	class Pair implements Comparable<Pair> {
		char c;
		int num;
		public Pair(char c, int num) {
			this.c = c;
			this.num = num;
		}
		public int compareTo(Pair b) {
			return b.num - this.num;
		}
	}
	
	public char[] adjust(char[] arr, int minDist) throws Exception {
		if (minDist < 2) {
			return arr;
		}
		HashMap<Character, Integer> hash = new HashMap<>();
		for (char c: arr) {
			if (hash.containsKey(c)) {
				hash.put(c, hash.get(c)+1);
			}
			else {
				hash.put(c, 1);
			}
		}
		PriorityQueue<Pair> heap = new PriorityQueue<>();
		for (Map.Entry<Character, Integer> entry: hash.entrySet()) {
			if (entry.getValue() > (arr.length-1)/minDist+1) {
				throw new Exception("cannot generate this sequence");
			}
			heap.add(new Pair(entry.getKey(), entry.getValue()));
		}
		int curPos = 0;
		while (curPos < arr.length) {
			List<Pair> pa = new ArrayList<>();
			for (int i = 0; i < minDist; i++) {
				if (heap.isEmpty()) {
					break;
				}
				Pair curPair = heap.poll();
				arr[curPos++] = curPair.c;
				curPair.num--;
				if (curPair.num > 0) {
					pa.add(curPair);
				}
			}
			for (Pair curPair: pa) {
				heap.add(curPair);
			}
		}
		return arr;
	}
}
