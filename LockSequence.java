class Solution2 {
	/*
	 * 3*3 Lock, give all the possible sequence
	 */
	private void dfs(List<String> res, StringBuilder sb, HashMap<Character, List<Character>> hash, boolean[] visited) {
		if (sb.length() > 1) {
			res.add(sb.toString());
		}
		if (sb.length() == 0) {
			for (char c = '1'; c <= '9'; c++) {
				sb.append(c);
				visited[c-'0'] = true;
				dfs(res, sb, hash, visited);
				visited[c-'0'] = false;
				sb.deleteCharAt(0);
			}
		}
		else {
			for (char c: hash.get(sb.charAt(sb.length()-1))) {
				if (visited[c-'0']) {
					continue;
				}
				sb.append(c);
				visited[c-'0'] = true;
				dfs(res, sb, hash, visited);
				visited[c-'0'] = false;
				sb.deleteCharAt(sb.length()-1);
			}
		}
	}
	
	public List<String> getLockSequence() {
		HashMap<Character, List<Character>> hash = new HashMap<>();
		hash.put('1', Arrays.asList('2', '4', '5', '6', '8'));
		hash.put('2', Arrays.asList('1', '3', '4', '5', '6', '7', '9'));
		hash.put('3', Arrays.asList('2', '4', '5', '6', '8'));
		hash.put('4', Arrays.asList('1', '2', '3', '5', '7', '8', '9'));
		hash.put('5', Arrays.asList('1', '2', '3', '4', '6', '7', '8', '9'));
		hash.put('6', Arrays.asList('1', '2', '3', '5', '7', '8', '9'));
		hash.put('7', Arrays.asList('2', '4', '5', '6', '8'));
		hash.put('8', Arrays.asList('1', '3', '4', '5', '6', '7', '9'));
		hash.put('9', Arrays.asList('2', '4', '5', '6', '8'));
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		boolean[] visited = new boolean[10];
		dfs(res, sb, hash, visited);
		return res;
	}
}
