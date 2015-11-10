class Solution {
	/*
	 *  stack1: 1, 4, 700, 3
        stack2: 5, 10, 6
        if n = 2, then the max sum should be 5+10.
        if n = 3, then the max sum should be 1+4+700
	 */
	
        //a better solution written by @lianlu, make some modification
	public int getmaxsum(int[][] nums, int n) {
		int[][] dp = new int[nums.length+1][n+1];
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= nums.length; i++) {
				int len = nums[i-1].length, sum = 0;
				for (int j = 0; j < len && j < k; j++) {
					sum += nums[i-1][j];
					dp[i][k] = Math.max(dp[i][k], sum+dp[i-1][k-j-1]);
				}
			}
		}
		return dp[nums.length][n];
	}
       
	
	//dp
	public int getMax(int n) {
		int[] nums1 = {1, 4, 700, 3}, nums2 = {5, 10, 6};
		int len1 = nums1.length, len2 = nums2.length;
		int[][][] dp = new int[len1+1][len2+1][n];
		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < len2; i++) {
			int sum = 0;
			for (int k = 0; k < n; k++) {
				if (i+k >= len2) {
					break;
				}
				sum += nums2[i+k];
				dp[len1][i][k] = sum;
			}
		}
		for (int j = 0; j < len1; j++) {
			int sum = 0;
			for (int k = 0; k < n; k++) {
				if (j+k >= len1) {
					break;
				}
				sum += nums1[j+k];
				dp[j][len2][k] = sum;
			}
		}
		
		for (int k = 0; k < n; k++) {
			for (int j = 0; j < len1; j++) {
				for (int i = 0; i < len2; i++) {
					if (k == 0) {
						dp[j][i][k] = Math.max(nums1[j], nums2[i]);
					}
					else {
						dp[j][i][k] = Math.max(nums1[j]+dp[j+1][i][k-1], nums2[i]+dp[j][i+1][k-1]);
					}
				}
			}
		}
		return dp[0][0][n-1];
	}
	
	
	//recursion
	private int helper(int[] nums1, int[] nums2, int sta1, int sta2, int n) {
		int sum = 0;
		if (sta1 == nums1.length) {
			for (int i = sta2; i < sta2+n; i++) {
				if (i == nums2.length) {
					return -1;
				}
				sum += nums2[i];
			}
			return sum;
		}
		if (sta2 == nums2.length) {
			for (int i = sta1; i < sta1+n; i++) {
				if (i == nums1.length) {
					return -1;
				}
				sum += nums1[i];
			}
			return sum;
		}
		if (n == 1) {
			return Math.max(nums1[sta1], nums2[sta2]);
		}
		return Math.max(nums1[sta1]+getmax(nums1, nums2, sta1+1, sta2, n-1), nums2[sta2]+getmax(nums1, nums2, sta1, sta2+1, n-1));
	}
	
	public int getMaxRecursion(int n) {
		int[] nums1 = {1, 4, 700, 3}, nums2 = {5, 10, 6};
		return getmax(nums1, nums2, 0, 0, n);
	}
	
}
