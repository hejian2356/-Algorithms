class Solution {
	/*
	 * directed graph detect cycle
	 */
	public boolean hasCycle(List<List<Integer>> neighbor, int n) {
		int[] visited = new int[n];
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				if (!dfs(i, neighbor, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(int cur, List<List<Integer>> neighbor, int[] visited) {
		visited[cur] = -1;
		for (int next: neighbor.get(cur)) {
			if (visited[next] == -1) {
				return false;
			}
			else if (visited[next] == 0) {
				if (!dfs(next, neighbor, visited)) {
					return false;
				}
			}
		}
		visited[cur] = 1;
		return true;
	}
	
	/*
	 * undirected graph detect cycle
	 */
    public boolean hasCycle(List<List<Integer>> neighbor, int n) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(-1, i, neighbor, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(int pre, int cur, List<List<Integer>> neighbor, boolean[] visited) {
        visited[cur] = true;
        for (int next: neighbor.get(cur)) {
            if (visited[next] && pre != next) {
                return true;
            }
            else if (!visited[next]) {
                if (dfs(cur, next, neighbor, visited)) {
                    return true;
                }
            }
        }
        return false;
    }}
