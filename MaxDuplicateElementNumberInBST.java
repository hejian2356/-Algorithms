class MaxDuplicateElementNumberInBST {
	/*
	 * Special BST, same elements will be put into left subtree.
	 * like:
	 *     2
	 *  1
	 *     2
	 * output: 2.
	 */
	int max = 0;
	int cur = 0;
	TreeNode pre = null;
	void maxDuplicateElementNumberInBST(TreeNode root) {
		if (root == null) {
			max = Math.max(cur, max);
			return;
		}
		maxDuplicateElementNumberInBST(root.left);
		if (pre == null) {
			cur = 1;
		}
		else if (pre.val == root.val) {
			cur++;
		}
		else {
			max = Math.max(cur, max);
			cur = 1;
		}
		pre = root;
		maxDuplicateElementNumberInBST(root.right);
	}
}
