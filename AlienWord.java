class AlienWord {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }
        HashSet<Character> allCha = new HashSet<>();
        for (int j = 0; j < words.length; j++) {
            for (int i = 0; i < words[j].length(); i++) {
                allCha.add(words[j].charAt(i));
            }
        }
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        makeGraph(graph, words);
        return toposort(allCha, graph);
    }
    
    private String toposort(HashSet<Character> allCha, HashMap<Character, HashSet<Character>> graph) {
        int[] visited = new int[26];
        StringBuilder res = new StringBuilder();
        for (char c: allCha) {
            if (visited[c-'a'] == 0) {
                if (!dfs(res, graph, visited, c)) {
                    return "";
                }
            }
        }
        res.reverse();
        return res.toString();
    }
    
    private boolean dfs(StringBuilder res, HashMap<Character, HashSet<Character>> graph, int[] visited, char cur) {
        visited[cur-'a'] = -1;
        if (graph.containsKey(cur)) {
            for (char next: graph.get(cur)) {
                if (visited[next-'a'] == -1) {
                    return false;
                }
                if (visited[next-'a'] == 0) {
                    if (!dfs(res, graph, visited, next)) {
                        return false;
                    }
                }
            }
        }
        visited[cur-'a'] = 1;
        res.append(cur);
        return true;
    }
    
    private void makeGraph(HashMap<Character, HashSet<Character>> graph, String[] words) {
        for (int i = 1; i < words.length; i++) {
            String s1 = words[i-1], s2 = words[i];
            int len1 = s1.length(), len2 = s2.length(), l = len1 > len2 ? len1 : len2;
            for (int j = 0; j < l; j++) {
                if (j < len1 && !graph.containsKey(s1.charAt(j))) {
                    graph.put(s1.charAt(j), new HashSet<>());
                }
                if (j < len2 && !graph.containsKey(s2.charAt(j))) {
                    graph.put(s2.charAt(j), new HashSet<>());
                }
                if (j < len1 && j < len2 && s1.charAt(j) != s2.charAt(j)) {
                    graph.get(s1.charAt(j)).add(s2.charAt(j));
                    break;
                }
            }
        }
    }
}
