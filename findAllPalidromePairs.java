class Solution2 {
	/*
	 * Given a String list, find all pairs that can be concatenated as a palidrome;
	 * ex: Given {"abc", "cba", "cb", "c"}, it should return [[cba, abc], [cb, abc], [abc, cba], [cb, c]].
	 */
	private boolean isPalidrome(String s) {
		if (s.length() == 0) {
			return true;
		}
		int l = 0, r = s.length()-1;
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
	
	private void swap(char[] a, int i, int j) {
		char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	private void reverse(char[] a) {
		for (int i = 0; i < a.length/2; i++) {
			swap(a, i, a.length-i-1);
		}
	}
	
	private String reverse(String s) {
		char[] a = s.toCharArray();
		reverse(a);
		return new String(a);
	}
	
	public List<List<String>> getPalidrome(List<String> s) {
		HashSet<String> hash = new HashSet<>();
		List<List<String>> res = new LinkedList<>();
		for (String x: s) {
			hash.add(x);
		}
		for (String x: s) {
			String rever = reverse(x);
			if (rever.contains(x)) {
				continue;
			}
			if (hash.contains(rever)) {
				List<String> tmp = new LinkedList<>();				
				tmp.add(rever);
				tmp.add(x);
				res.add(tmp);
			}
			for (int i = 1;i < x.length(); i++) {
				String s1 = x.substring(0, i);
				if (!isPalidrome(s1)) {
					continue;
				}
				String s2 = reverse(x.substring(i));
				if (hash.contains(s2)) {
					List<String> tmp = new LinkedList<>();				
					tmp.add(s2);
					tmp.add(x);
					res.add(tmp);
				}
			}
			for (int i = x.length()-1;i > 0; i--) {
				String s1 = x.substring(i);
				if (!isPalidrome(s1)) {
					continue;
				}
				String s2 = reverse(x.substring(0, i));
				if (hash.contains(s2)) {
					List<String> tmp = new LinkedList<>();				
					tmp.add(x);
					tmp.add(s2);
					res.add(tmp);
				}
			}
		}
		return res;
	}
}
