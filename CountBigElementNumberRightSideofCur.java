class BSTNode{
	int val;
	int rightCnt;
	BSTNode left, right;
	public BSTNode(int val, int cnt) {
		this.val = val;
		this.rightCnt = cnt;
		left = right = null;
	}
}

class Solution2 {
	public int[] countTheNumberOfElementRightSideMoreThanCurrent(int[] nums) {
		int[] res = new int[nums.length];
		int len = nums.length;
		BSTNode root = new BSTNode(nums[len-1], 0);
		res[res.length-1] = 0;
		for (int i = len-2; i >= 0; i--) {
			BSTNode cur = root;
			int rightCnt = 0;
			while (true) {
				if (nums[i] <= cur.val) {
					if (nums[i] == cur.val) {
						rightCnt += cur.rightCnt;
					}
					else {
						rightCnt += cur.rightCnt+1;
					}
					if (cur.left == null) {
						cur.left = new BSTNode(nums[i], 0);
						break;
					}
					else {	
						cur = cur.left;
					}
				}
				else {
					cur.rightCnt++;
					if (cur.right == null) {
						cur.right = new BSTNode(nums[i], 0);
						break;
					}
					else {
						cur = cur.right;
					}
				}
			}
			res[i] = rightCnt;
		}
		return res;
	}
}
