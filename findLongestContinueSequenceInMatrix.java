class FindLongestPath {
        /*
	 * Given 7, 8, 6
                 9, 4, 5
                 2, 3, 1
           return path length 5 (2, 3, 4, 5, 6).
	 */
	int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	private int dfs(int[][] matrix, int posj, int posi, int[][] dp) {
		if (dp[posj][posi] != 0) {
			return dp[posj][posi];
		}
		for (int k = 0; k < 4; k++) {
			int j = posj+dir[k][0], i = posi+dir[k][1];
			if (j < 0 || j >= matrix.length || i < 0 || i >= matrix[0].length) {
				continue;
			}
			if (matrix[j][i] == matrix[posj][posi]+1) {
				dp[posj][posi] = Math.max(dp[posj][posi], 1+dfs(matrix, j, i, dp));
			}
		}
		if (dp[posj][posi] == 0) {
			dp[posj][posi] = 1;
		}
		return dp[posj][posi];
	}
	
	public int getMaxLen(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int wid = matrix.length, len = matrix[0].length;
		int[][] dp = new int[wid][len];
		int max = 0;
		for (int j = 0; j < wid; j++) {
			for (int i = 0; i < len; i++) {
				if (dp[j][i] == 0) {
					dfs(matrix, j, i, dp);
				}
				if (dp[j][i] > max) {
					max = dp[j][i];
				}
			}
		}
		return max;
	}
}
