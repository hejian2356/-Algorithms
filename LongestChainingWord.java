class LongestChainingWord {
	/*
	 * Given dict = {"words", "word", "wod", "od", "d", "wore"}, return 5 ("words", "word", "wod", "od", "d").
	 */
	public int longestChaining(Set<String> dict) {
		HashMap<String, Integer> pathLength = new HashMap<>();
		int max = 0;
		for (String s: dict) {
			int len = findLength(s, pathLength, dict);
			if (len > max) {
				max = len;
			}
		}
		return max;
	}
	
	private int findLength(String s, HashMap<String, Integer> pathLength, Set<String> dict) {
		if (pathLength.containsKey(s)) {
			return pathLength.get(s);
		}
		if (s.length() == 1) {
			pathLength.put(s, 1);
			return 1;
		}
		List<String> nextWords = findNextWords(s, dict);
		int max = 0;
		for (String nextWord: nextWords) {
			int len = findLength(nextWord, pathLength, dict)+1;
			if (len > max) {
				max = len;
			}
		}
		pathLength.put(s, max);
		return max;
	}
	
	private List<String> findNextWords(String s, Set<String> dict) {
		List<String> newWords = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			String tmp = s.substring(0, i)+s.substring(i+1);
			if (dict.contains(tmp)) {
				newWords.add(tmp);
			}
		}
		return newWords;
	}
}
