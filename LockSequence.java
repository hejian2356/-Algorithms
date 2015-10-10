class Solution2 {
	/*
	 * 3*3 Lock, give all the possible sequence
	 */
	class Pair {
		char cur;
		char next;
		public Pair(char cur, char next) {
			this.cur = cur;
			this.next = next;
		}
	}
	private void dfs(List<String> res, StringBuilder sb, HashMap<Character, List<Character>> hash, boolean[] visited, HashMap<Character, List<Pair>> next) {
		if (sb.length() > 3) {
			res.add(sb.toString());
		}
		if (sb.length() == 0) {
			for (char c = '1'; c <= '9'; c++) {
				sb.append(c);
				visited[c-'0'] = true;
				dfs(res, sb, hash, visited, next);
				visited[c-'0'] = false;
				sb.deleteCharAt(0);
			}
		}
		else {
			for (Pair pair: next.get(sb.charAt(sb.length()-1))) {
				if (visited[pair.cur-'0'] && !visited[pair.next-'0']) {
					sb.append(pair.next);
					visited[pair.next-'0'] = true;
					dfs(res, sb, hash, visited, next);
					visited[pair.next-'0'] = false;
					sb.deleteCharAt(sb.length()-1);
				}
			}
			for (char c: hash.get(sb.charAt(sb.length()-1))) {
				if (visited[c-'0']) {
					continue;
				}
				sb.append(c);
				visited[c-'0'] = true;
				dfs(res, sb, hash, visited, next);
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
		
		HashMap<Character, List<Pair>> next = new HashMap<>();
		next.put('1', Arrays.asList(new Pair('2', '3'), new Pair('4', '7'), new Pair('5', '9')));
		next.put('2', Arrays.asList(new Pair('5', '8')));
		next.put('3', Arrays.asList(new Pair('2', '1'), new Pair('5', '7'), new Pair('6', '9')));
		next.put('4', Arrays.asList(new Pair('5', '6')));
		next.put('5', Arrays.asList());
		next.put('6', Arrays.asList(new Pair('5', '4')));
		next.put('7', Arrays.asList(new Pair('4', '1'), new Pair('5', '3'), new Pair('8', '9')));
		next.put('8', Arrays.asList(new Pair('5', '2')));
		next.put('9', Arrays.asList(new Pair('5', '1'), new Pair('6', '3'), new Pair('8', '7')));
		
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		boolean[] visited = new boolean[10];
		dfs(res, sb, hash, visited, next);
		return res;
	}
}
