class BalancedTreeNumber {
	//N nodes balanced tree number
		public int balancedTreeNumber(int n) {
			if (n <= 0) {
				return 0;
			}
			int lowHeight = (int)(Math.log10(n)/Math.log10(2))+1, highHeight = (int)(Math.log(n)/Math.log10(2))+2;
			int[][] dp = new int[n+1][highHeight+1];
			dp[0][0] = dp[1][1] = 1;
			for (int i = 2; i <= n; i++) {
				lowHeight = (int)(Math.log10(i)/Math.log10(2))+1;
				highHeight = (int)(Math.log(i)/Math.log10(2))+2;
				for (int j = lowHeight; j <= highHeight; j++) {
					for (int k = 0; k < i; k++) {
						dp[i][j] += dp[k][j-1]*dp[i-1-k][j-1]+dp[k][j-1]*dp[i-1-k][j-2]+dp[k][j-2]*dp[i-1-k][j-1];
					}
				}
			}
			lowHeight = (int)(Math.log10(n)/Math.log10(2))+1;
			highHeight = (int)(Math.log(n)/Math.log10(2))+2;
			return dp[n][lowHeight]+dp[n][highHeight];
		}
}
