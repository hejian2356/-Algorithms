class reverseNumber180Degree {
	/*
	 * Reverse a number by 180 degree, 0->0, 1->1, 2->5, 5->2, 6->9, 8->8, 9->9.
	 * Given n, the digits of number, return all numbers that when reversing this number we still
	 * get the same number. example: n=2, return 11, 25, 52, 69, 96, 88
	 */
	private void dfs(List<String> res, StringBuilder sb, int pos, int n, char[] nums, HashMap<Character, Character> hash) {
		if (pos == n) {
			res.add(sb.toString());
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (pos == 0 && nums[i] == '0') {
				continue;
			}
			if (pos >= n/2) {
				if (pos == n-1-pos) {
					while (i < nums.length && nums[i] != '0' && nums[i] != '1' && nums[i] != '8') {
						i++;
					}
				}
				else {
					while (i < nums.length && hash.get(nums[i]) != sb.charAt(n-1-pos)) {
						i++;
					}
				}
				if (i == nums.length) {
					return;
				}
			}
			sb.append(nums[i]);
			dfs(res, sb, pos+1, n, nums, hash);
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	public List<String> give(int n) {
		HashMap<Character, Character> hash = new HashMap<>();
		hash.put('5', '2');
		hash.put('2', '5');
		hash.put('6', '9');
		hash.put('9', '6');
		hash.put('0', '0');
		hash.put('1', '1');
		hash.put('8', '8');
		char[] nums = {'0', '1', '2', '5', '6', '8', '9'};
		List<String> res = new LinkedList<>();
		if (n == 1) {
			res.add("0");
			res.add("1");
			res.add("8");
			return res;
		}
		dfs(res, new StringBuilder(), 0, n, nums, hash);
		return res;
	}
}
