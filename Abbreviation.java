class Abbreviation {
	/**
	 * Given a string, return its all abbreviation. 
	 * Test: given "sat", return [1a1, 3, 1at, 2t, sa1, s2, sat, s1t].       
	 * 
	 */
	List<String> abbre(String s) {
		List<String> res = new LinkedList<>();
		if (s.length() == 0) {
			res.add("");
			return res;
		}
		if (s.length() == 1) {
			res.add("1");
			res.add(s);
			return res;
		}
		int i = s.length() / 2;
		List<String> l = null, r = null;
		if (i == 0) {
			l = abbre("");
		} else {
			l = abbre(s.substring(0, i));
		}
		if (i == s.length() - 1) {
			r = abbre("");
		} else {
			r = abbre(s.substring(i + 1, s.length()));
		}
		for (int j = 0; j < l.size(); j++) {
			for (int k = 0; k < r.size(); k++) {
				String tmp = l.get(j) + s.charAt(i) + r.get(k);
				res.add(tmp);
				String left = l.get(j), right = r.get(k);
				int i1;
				for (i1 = left.length() - 1; i1 >= 0; i1--) {
					char c = left.charAt(i1);
					if (!(c >= '0' && c <= '9')) {
						break;
					}
				}
				int endLeft = i1;

				for (i1 = 0; i1 < right.length(); i1++) {
					char c = right.charAt(i1);
					if (!(c >= '0' && c <= '9')) {
						break;
					}
				}
				int staRight = i1;
				if (endLeft == left.length() - 1 && staRight == 0) {
					res.add(left + "1" + right);
				} else if (endLeft == left.length() - 1) {
					int rightNum = Integer.parseInt(right
							.substring(0, staRight));
					res.add(left + ("" + (1 + rightNum))
							+ right.substring(staRight));
				} else if (staRight == 0) {
					int leftNum = Integer.parseInt(left.substring(endLeft + 1));
					res.add(left.substring(0, endLeft + 1)
							+ ("" + (leftNum + 1)) + right);
				} else {
					int leftNum = Integer.parseInt(left.substring(endLeft + 1));
					int rightNum = Integer.parseInt(right
							.substring(0, staRight));
					res.add(left.substring(0, endLeft + 1)
							+ ("" + (leftNum + 1 + rightNum))
							+ right.substring(staRight));
				}
			}
		}
		return res;
	}
}

