class PostOrderTraversalBT1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        TreeNode pre = null, cur = null;
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            cur = stk.peek();
            if (pre == null || pre.left == cur || pre.right == cur) {
                if (cur.left != null) {
                    stk.push(cur.left);
                }
                else if (cur.right != null) {
                    stk.push(cur.right);
                }
            }
            else if (cur.left == pre) {
                if (cur.right != null) {
                    stk.push(cur.right);
                }
            }
            else {
                res.add(stk.pop().val);
            }
            pre = cur;
        }
        return res;
    }
}


class PostOrderTraversalBT2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stk = new Stack<>();
        HashSet<TreeNode> hash = new HashSet<>();
        TreeNode l1 = root;
        while (l1 != null) {
            stk.push(l1);
            l1 = l1.left;
        }
        while (!stk.isEmpty()) {
            if (!hash.contains(stk.peek())) {
                while (stk.peek().right != null) {
                    hash.add(stk.peek());
                    TreeNode l2 = stk.peek().right;
                    while (l2 != null) {
                        stk.push(l2);
                        l2 = l2.left;
                    }
                }
            }
            res.add(stk.pop().val);
        }
        return res;
    }
}
