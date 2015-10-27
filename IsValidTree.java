public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n != edges.length+1) {
            return false;
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
            list.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] visited = new boolean[n];
        if (!dfs(list, visited, -1, 0)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean dfs(List<List<Integer>> list, boolean[] visited, int pre, int cur) {
        if (visited[cur]) {
            return true;
        }
        visited[cur] = true;
        for (int next: list.get(cur)) {
            if (!visited[next]) {
                if (!dfs(list, visited, cur, next)) {
                    return false;
                }
            }
            else if (next != pre) {
                return false;
            }
        }
        return true;
    }
}


//////////////////
//////////////////below is a tedious way
class IsValidTree {
	/**
	 * @param num: edge with two nodes' value          
	 * @return: valid tree or not.
	 * example: Given [[0, 1], [1, 2], [3, 4]], return false
	 * example: Given [[0, 1], [1, 2], [2, 0]], return false
	 * example: Given [[0, 1], [1, 2], [2, 3]], return true
	 */
public boolean validTree(int n, int[][] num) {
        HashSet<Integer> hash0 = new HashSet<>();
        if (num.length == 0) {
            if (n == 1) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < num.length; i++) {
            hash0.add(num[i][0]);
            hash0.add(num[i][1]);
        }
        for (int i = 0; i < n; i++) {
            if (!hash0.contains(i)) {
                return false;
            }
        }
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
