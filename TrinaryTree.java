class TrinaryTree{
	class TrinaryTreeNode {
		int val;
		TrinaryTreeNode left, mid, right;
		public TrinaryTreeNode(int val) {
			this.val = val;
			left = null;
			mid = null;
			right = null;
		}
	}
	
	TrinaryTreeNode root;
	
	public TrinaryTree() {
		root = null;
	}
	
	public void insert(int val) {
		if (root == null) {
			root = new TrinaryTreeNode(val);
			return;
		}
		TrinaryTreeNode cur = root;
		while (cur.val != val) {
			if (val > cur.val) {
				if (cur.right == null) {
					cur.right = new TrinaryTreeNode(val);
					return;
				}
				cur = cur.right;
			}
			else {
				if (cur.left == null) {
					cur.left = new TrinaryTreeNode(val);
					return;
				}
				cur = cur.left;
			}
		}
		while (cur.mid != null) {
			cur = cur.mid;
		}
		cur.mid = new TrinaryTreeNode(val);
	}
	
	public void delete(int val) {
		if (root == null) {
			return;
		}
		TrinaryTreeNode cur = root, pre = null;
		while (cur != null && cur.val != val) {
			pre = cur;
			if (val > cur.val) {
				cur = cur.right;
			}
			else {
				cur = cur.left;
			}
		}
		if (cur == null) {
			return;
		}
		if (cur.mid != null) {
			while (cur.mid != null) {
				pre = cur;
				cur = cur.mid;
			}
			pre.mid = null;
			return;
		}
		if (cur.left == null && cur.right == null) {
			if (pre.left == cur) {
				pre.left = null;
			}
			else {
				pre.right = null;
			}
			return;
		}
		TrinaryTreeNode needToBeFixed = cur;
		if (cur.left != null) {
			pre = cur;
			cur = cur.left;
			if (cur.right == null) {
				needToBeFixed.val = cur.val;
				pre.left = cur.left;
				needToBeFixed.mid = cur.mid;
				return;
			}
			while (cur.right != null) {
				pre = cur;
				cur = cur.right;
			}
			needToBeFixed.val = cur.val;
			pre.right = cur.left;
			needToBeFixed.mid = cur.mid;
		}
		else {
			pre = cur;
			cur = cur.right;
			if (cur.left == null) {
				needToBeFixed.val = cur.val;
				pre.right = cur.right;
				needToBeFixed.mid = cur.mid;
				return;
			}
			while (cur.left != null) {
				pre = cur;
				cur = cur.left;
			}
			needToBeFixed.val = cur.val;
			pre.left = cur.right;
			needToBeFixed.mid = cur.mid;
		}
	}
}
