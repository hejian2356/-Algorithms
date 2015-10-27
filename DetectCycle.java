class Solution {
	/*
	 * undirected graph detect cycle
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
	 * directed graph detect cycle
	 */
	public boolean hasCycleDirected(List<List<Integer>> neighbor, int n) {
		int[] visited = new int[n];
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				if (!dfs(-1, i, neighbor, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(int pre, int cur, List<List<Integer>> neighbor, int[] visited) {
		visited[cur] = -1;
		for (int next: neighbor.get(cur)) {
			if (visited[next] == -1 && pre != next) {
				return false;
			}
			else if (visited[next] == 0) {
				if (!dfs(cur, next, neighbor, visited)) {
					return false;
				}
			}
		}
		visited[cur] = 1;
		return true;
	}
}
