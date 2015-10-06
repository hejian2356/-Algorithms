class BT2DoubleLinkedList {
	/*
	 * Given     3
	 *          / \
	 *         1   4
	 *          \   \
	 *           2   5
	 *output double linked list: 1 2 3 4 5
	 */
	 class Node {
		 int val;
		 Node left, right, pre, next;
		 public Node(int val) {
			 this.val = val;
			 left = right = pre = next = null;
		 }
	 }
	 
	 public Node[] bt2DoubleLinkedList(Node root) {
		 if (root == null) {
			 return new Node[]{null, null};
		 }
		 if (root.left == null && root.right == null) {
			 return new Node[]{root, root};
		 }
		 Node head = null, tail = null;
		 if (root.right != null) {
			 Node res[] = bt2DoubleLinkedList(root.right);
			 res[0].pre = root;
			 root.next = res[0];
			 tail = res[1];
			 head = root;
		 }
		 if (root.left != null) {
			 Node res[] = bt2DoubleLinkedList(root.left);
			 res[1].next = root;
			 root.pre = res[1];
			 if (tail == null) {
				 tail = root;
			 }
			 head = res[0];
		 }
		 return new Node[]{head, tail};
	 }
	 
	 public void test() {
		 Node l1 = new Node(3), l2 = new Node(1), l3 = new Node(4), l4 = new Node(2), l5 = new Node(5);
		 l1.left = l2;
		 l1.right = l3;
		 l2.right = l4;
		 l3.right = l5;
		 Node[] res = bt2DoubleLinkedList(l1);
		 Node head = res[0], tail = res[1];
		 while (head != null) {
			 System.out.print(head.val+" ");
			 head = head.next;
		 }
		 System.out.println();
		 while (tail != null) {
			 System.out.print(tail.val+" ");
			 tail = tail.pre;
		 }
	 }
}
