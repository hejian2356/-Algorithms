class NonNeighborMaxPathSumInBT {
	/*
	 * max path sum, but node can not be neighbor
	 *         1
	 *        / \
	 *       2   3
  	 *      / \
	 *     1   8	 
	 * the max path sum is {8, 2, 1, 3}, but the non-neighbor sum is 8+3 = 11;
	 * Max path sum+dp;
	 */  
	class Result {
		int single;
		int maxPath;
		ArrayList<Integer> sin, max;
		public Result(int single, int maxPath, ArrayList<Integer> sin, ArrayList<Integer> max) {
			this.single = single;
			this.maxPath = maxPath;
			this.sin = sin;
			this.max = max;
		}
	}
	
	private Result helper(TreeNode root) {
		if (root == null) {
			return new Result(0, Integer.MIN_VALUE, new ArrayList<>(), new ArrayList<>());
		}
		Result l = helper(root.left), r = helper(root.right);
		ArrayList<Integer> sin = new ArrayList<>(), max = new ArrayList<>();
		int single = Math.max(0, Math.max(l.single, r.single)+root.val);
		if (0 >= l.single+root.val && 0 >= r.single+root.val) {
		}
		else if (l.single+root.val >= 0 && l.single+root.val >= r.single+root.val) {
			sin.addAll(l.sin);
			sin.add(root.val);
		}
		else {
			sin.addAll(r.sin);
			sin.add(root.val);
		}
		int tmp = l.single+r.single+root.val;
		int maxPath = Math.max(tmp, Math.max(l.maxPath, r.maxPath));
		if (tmp >= l.maxPath && tmp >= r.maxPath) {
			max.addAll(l.sin);
			max.add(root.val);
			Collections.reverse(r.sin);
			max.addAll(r.sin);
		}
		else if (l.maxPath >= tmp && l.maxPath >= r.maxPath) {
			max.addAll(l.max);
		}
		else {
			max.addAll(r.max);
		}
		return new Result(single, maxPath, sin, max);
	}
	
	public int getMaxPathSum(TreeNode root) {
		ArrayList<Integer> res = helper(root).max;
		int[] dp = new int[res.size()];
		if (res.size() == 0) {
			return 0;
		}
		if (res.size() == 1) {
			return res.get(0);
		}
		dp[0] = res.get(0);
		dp[1] = Math.max(res.get(0), res.get(1));
		for (int i = 2; i < res.size(); i++) {
			dp[i] = Math.max(dp[i-1], dp[i-2]+res.get(i));
		}
		return dp[res.size()-1];
	}
}
