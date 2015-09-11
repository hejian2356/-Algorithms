class findMaxNumberPoint {
	/**
	 * 在一些区间中，寻找一个点，使这个点能够落入尽量多的区间
           比如 区间： 2,3 | 3,5 | 4,5 | 1,5 | 1,2, return {2, 3, 4, 5}
	 * @author jianh
	 */
	class Interval {
		int sta;
		int end;
		public Interval(int sta, int end) {
			this.sta = sta;
			this.end = end;
		}
	}
	
	class SegmentTreeNode {
		int sta;
		int end;
		int cnt;
		SegmentTreeNode left, right;
		public SegmentTreeNode(int sta, int end) {
			this.sta = sta;
			this.end = end;
			left = null;
			right = null;
		}
	}
	
	private SegmentTreeNode buildST(int sta, int end) {
		if (sta > end) {
			return null;
		}
		if (sta == end) {
			return new SegmentTreeNode(sta, end);
		}
		int mid = (sta+end)/2;
		SegmentTreeNode midNode = new SegmentTreeNode(sta, end);
		midNode.left = buildST(sta, mid);
		midNode.right = buildST(mid+1, end);
		return midNode;
	}
	
	SegmentTreeNode root = null;
	int maxNum = 0;
	
	private void updateST(SegmentTreeNode root, int sta, int end, HashMap<Integer, Integer> hash) {
		if (root.sta == sta && root.end == end && sta == end) {
			root.cnt++;
			if (root.cnt > maxNum) {
				maxNum = root.cnt;
			}
			hash.put(root.sta, root.cnt);
			return;
		}
		int mid = (root.sta+root.end)/2;
		if (end <= mid) {
			updateST(root.left, sta, end, hash);
		}
		else if (sta > mid) {
			updateST(root.right, sta, end, hash);
		}
		else {
			updateST(root.left, sta, mid, hash);
			updateST(root.right, mid+1, end, hash);
		}
		//root.cnt = root.left.cnt+root.right.cnt;
	}
	
	public List<Integer> getmax(Interval[] num) {
		HashMap<Integer, Integer> hash = new HashMap<>();
		int min = num[0].sta, max = num[0].end;
		for (int i = 1; i < num.length; i++) {
			if (num[i].sta < min) {
				min = num[i].sta;
			}
			if (num[i].end > max) {
				max = num[i].end;
			}
		}
		root = buildST(min, max);
		for (int i = 0; i < num.length; i++) {
			updateST(root, num[i].sta, num[i].end, hash);
		}
		Set<Integer> key = hash.keySet();
		List<Integer> res = new LinkedList<>();
		for (int x: key) {
			if (hash.get(x) == maxNum) {
				res.add(x);
			}
		}
		return res;
	}
}
