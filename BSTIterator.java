class BSTIterator {
	/*
	 * No extra space
	 */
	TreeNode cur = null, next = null;
	public BSTIterator(TreeNode root) {
		cur = root;
		if (cur != null) {
			while (cur.left != null) {
				cur = cur.left;
			}
			next = cur;
		}
	}
	
	
	private TreeNode findNext() {
		if (cur.right != null) {
			cur = cur.right;
			while (cur.left != null) {
				cur = cur.left;
			}
			return cur;
		}
		TreeNode pa = cur.parent;
		while (pa != null && pa.right == cur) {
			cur = pa;
			pa = pa.parent;
		}
		if (pa == null) {
			return null;
		}
		cur = pa;
		return cur;
	}
	
	public boolean hasNext() {
		if (next == null) {
			next = findNext();
			if (next == null) {
				return false;
			}
		}
		return true;
	}
	
	public int next() {
		int res = next.val;
		next = null;
		return res;
	}
}
