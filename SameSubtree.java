class SameSubtree {
	/*
	 * Same subtree
	 */
	private boolean contains = false;
	TreeNode node1 = null, node2 = null;
	public boolean sameSubTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		HashMap<List<Integer>, TreeNode> record = new HashMap<>();
		search(root, record);
		return contains;
	}
	
	private List<List<Integer>> search(TreeNode root, HashMap<List<Integer>, TreeNode> record) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			res.add(new ArrayList<>());
			res.add(new ArrayList<>());
			return res;
		}
		List<List<Integer>> left = search(root.left, record), right = search(root.right, record);
		List<Integer> preorder = new ArrayList<>(), inorder = new ArrayList<>(), key = new ArrayList<>();;
		preorder.add(root.val);
		preorder.addAll(left.get(0));
		preorder.addAll(right.get(0));
		inorder.addAll(left.get(1));
		inorder.add(root.val);
		inorder.addAll(right.get(1));
		res.add(preorder);
		res.add(inorder);
		key.addAll(preorder);
		key.addAll(inorder);
		if (record.containsKey(key)) {
			contains = true;
			node1 = record.get(key);
			node2 = root;
		}
		record.put(key, root);
		return res;
	}
}
