class BinaryTreeAllPath1 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        if (root.left == null && root.right == null) {
            res.add(""+root.val);
            return res;
        }
        List<String> left = null, right = null;
        if (root.left != null) {
            left = binaryTreePaths(root.left);
            for (String s: left) {
                s = ""+root.val+"->"+s;
                res.add(s);
            }
        }
        if (root.right != null) {
            right = binaryTreePaths(root.right);
            for (String s: right) {
                s = ""+root.val+"->"+s;
                res.add(s);
            }
        }
        return res;
    }
}



class BinaryTreeAllPath2 {
    private void dfs(List<String> res, StringBuilder cur, TreeNode root) {
        if (root == null) {
            return;
        }
        cur.append("->"+root.val);
        int len = cur.length();
        if (root.left == null && root.right == null) {
            res.add(cur.toString().substring(2));
            return;
        }
        if (root.left != null) {
            dfs(res, cur, root.left);
            cur.delete(len, cur.length());
        }
        if (root.right != null) {
            dfs(res, cur, root.right);
            cur.delete(len, cur.length());
        }
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        dfs(res, new StringBuilder(), root);
        return res;
    }
}
