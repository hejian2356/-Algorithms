class IsValidTree {
	/**
	 * @param num: edge with two nodes' value          
	 * @return: valid tree or not.
	 * example: Given [[0, 1], [1, 2], [3, 4]], return false
	 * example: Given [[0, 1], [1, 2], [2, 0]], return false
	 * example: Given [[0, 1], [1, 2], [2, 3]], return true
	 */
	boolean isValidTree(int[][] num) {
		List<HashSet<Integer>> hash = new LinkedList<>();
		for (int i = 0; i < num.length; i++) {
			boolean flag = false;
			for (int j = 0; j < hash.size(); j++) {
				if (hash.get(j).contains(num[i][0])
						&& hash.get(j).contains(num[i][1])) {
					return false;
				}
				if (hash.get(j).contains(num[i][0])
						|| hash.get(j).contains(num[i][1])) {
					HashSet<Integer> tmpp = hash.get(j);
					int target = num[i][1];
					if (hash.get(j).contains(num[i][1])) {
						target = num[i][0];
					}
					flag = true;
					int l = 0;
					for (l = 0; l < hash.size(); l++) {
						if (l == j) {
							continue;
						}
						if (hash.get(l).contains(target)) {
							for (int x : hash.get(l)) {
								if (hash.get(j).contains(x)) {
									return false;
								}
								hash.get(j).add(x);
							}
							hash.remove(l);
							break;
						}
					}
					tmpp.add(target);
					break;
				}
			}
			if (!flag) {
				HashSet<Integer> tmp = new HashSet<>();
				tmp.add(num[i][0]);
				tmp.add(num[i][1]);
				hash.add(tmp);
			}
		}
		return hash.size() == 1;
	}
}
