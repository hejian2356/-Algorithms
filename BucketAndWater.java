class water {
	/*
	 * Given bucket A and B with max volume maxA and maxB, get the min steps to get target value water.
	 */
	class Node {
		int A;
		int B;
		Node[] next;
		public Node(int A, int B) {
			this.A = A;
			this.B = B;
			next = new Node[6];
		}
	}
	public int get(int maxA, int maxB, int target) {
		Node root = new Node(0, 0);
		int len = 0;
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		if (root.A == target || root.B == target) {
			return 0;
		}
		while (!q.isEmpty()) {
			len++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node cur = q.poll();
				//clear A
				cur.next[0] = new Node(0, cur.B);
				//clear B
				cur.next[1] = new Node(cur.A, 0);
				//full A
				cur.next[2] = new Node(maxA, cur.B);
				//full B
				cur.next[3] = new Node(cur.A, maxB);
				//A into B
				cur.next[4] = new Node(cur.A-(Math.min(maxB, cur.B+cur.A)-cur.B), Math.min(maxB, cur.B+cur.A));
				//B into A
				cur.next[5] = new Node(Math.min(maxA, cur.B+cur.A), cur.B-(Math.min(maxA, cur.B+cur.A)-cur.A));
				for (int j = 0; j < 6; j++) {
					if (cur.next[j].A == target || cur.next[j].B == target) {
						return len;
					}
					q.offer(cur.next[j]);
				}
			}
		}
		return -1;
	}
}
