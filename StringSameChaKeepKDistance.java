class Solution {
	/*
	 * reshape a string, so same character must keep at least k distance
	 * ex: given "AAAABBBCDE" and k = 3, return "ABCABDABEA"
	 */
	class Pair implements Comparable<Pair> {
		char c;
		int freq;
		public Pair(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}
		@Override
		public int compareTo(Pair o) {
			return ((Pair)o).freq - this.freq;
		}
	}
	
	public String reshape(String s, int k) {
		if (k <= 0) {
			return s;
		}
		PriorityQueue<Pair> heap = new PriorityQueue<>();
		HashMap<Character, Integer> hash = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (hash.containsKey(c)) {
				hash.put(c, hash.get(c)+1);
			}
			else {
				hash.put(c, 1);
			}
		}
		for (Map.Entry<Character, Integer> entry: hash.entrySet()) {
			heap.add(new Pair(entry.getKey(), entry.getValue()));
		}
		Pair[] pairs = new Pair[k];
		StringBuilder res = new StringBuilder();
		while (!heap.isEmpty()) {
			int idx = 0;
			while (!heap.isEmpty() && idx < k) {
				pairs[idx++] = heap.poll();
			}
			if (heap.isEmpty() && idx < k && idx+res.length() != s.length()) {
				return null;
			}
			for (int i = 0; i < k; i++) {
				if (i == idx) {
					break;
				}
				res.append(pairs[i].c);
				pairs[i].freq--;
				if (pairs[i].freq > 0) {
					heap.add(pairs[i]);
				}
			}
		}
		return res.toString();
	}
}
