class FindShortestSquareSumLength {
	/**
	 * find shortest square sum length example: 
	 * Given 50, return [1, 49] or [25, 25]
	 */
	List<Integer> findShortestSquareSum(int num) {
		List<List<Integer>> res = new LinkedList<>();
		int[] dp = new int[num + 1];
		for (int i = 0; i <= num; i++) {
			List<Integer> tmpp = new LinkedList<>();
			int min1 = 0, minLen = Integer.MAX_VALUE;
			int tmp = (int) Math.sqrt(i);
			if (tmp * tmp == i) {
				dp[i] = 1;
				tmpp.add(i);
			} else {
				for (int j = 1; j <= i / 2; j++) {
					if (dp[j] + dp[i - j] < minLen) {
						minLen = dp[j] + dp[i - j];
						min1 = j;
					}
				}
				dp[i] = minLen;
				tmpp.addAll(res.get(min1));
				tmpp.addAll(res.get(i - min1));
			}
			res.add(tmpp);
		}
		return res.get(num);
	}
}
