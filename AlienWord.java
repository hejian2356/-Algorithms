class AlienWord {
    private void helper(HashMap<Character, HashSet<Character>> hash, String[] words, int pos, int sta1, int end1) {
        List<List<Integer>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char pre = 'a';
        int sta = -1, end = -1;
        for (int i = sta1; i <= end1; i++) {
            if (pos >= words[i].length()) {
                continue;
            }
            if (sb.length() == 0) {
                pre = words[i].charAt(pos);
                sb.append(pre);
                sta = i;
            }
            else {
                char cur = words[i].charAt(pos);
                if (cur == pre) {
                    continue;
                }
                end = i;
                sb.append(cur);
                pre = cur;
                if (sta == end-1) {
                    sta++;
                    continue;
                }
                List<Integer> tmp = new ArrayList<>();
                tmp.add(sta);
                tmp.add(end-1);
                list.add(tmp);
                sta = end;
            }
        }
        end = end1;
        if (sta != end) {
        	List<Integer> tmp = new ArrayList<>();
            tmp.add(sta);
            tmp.add(end);
            list.add(tmp);
        }
        if (sb.length() == 0) {
            return;
        }
        if (sb.length() == 1) {
        	helper(hash, words, pos+1, sta1, end1);
        	return;
        }
        char[] array = sb.toString().toCharArray();
        for (int j = 0; j < array.length-1; j++) {
            char c = array[j];
            if (!hash.containsKey(c)) {
                HashSet<Character> tmp = new HashSet<>();
                hash.put(c, tmp);
            }
            HashSet<Character> tmp = hash.get(c);
            for (int k = j+1; k < array.length; k++) {
                tmp.add(array[k]);
            }
        }
        for (List<Integer> tmp: list) {
            helper(hash, words, pos+1, tmp.get(0), tmp.get(1));
        }
    }
    
    public String alienOrder(String[] words) {
        HashMap<Character, HashSet<Character>> hash = new HashMap<>();
        int[] cha = new int[26];
        HashSet<Character> num = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
        	for (int j = 0; j < words[i].length(); j++) {
        		num.add(words[i].charAt(j));
        	}
        }
        helper(hash, words, 0, 0, words.length-1);
        
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char c: hash.keySet()) {
            for (char c0: hash.get(c)) {
                cha[c0-'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (cha[i] == 0 && num.contains((char)(i+'a'))) {
                queue.offer((char)(i+'a'));
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            if (!hash.containsKey(c)) {
                continue;
            }
            for (char c0: hash.get(c)) {
                cha[c0-'a']--;
                if (cha[c0-'a'] == 0) {
                    queue.offer(c0);
                }
            }
        }
        if (sb.length() < num.size()) {
            return "";
        }
        return sb.toString();
    }
}
