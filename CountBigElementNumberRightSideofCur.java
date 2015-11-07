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


///////////count smaller numbers' number
//surpass
	//given {4, 3, 2, 1}, return {3, 2, 1, 0}
	private void merge(int[] nums, int[] pos, int sta, int mid, int end, int[] res) {
		int[] tmp = new int[nums.length];
		for (int i = sta; i <= end; i++) {
			tmp[i] = pos[i];
		}
		int l = sta, r = mid+1, idx = sta;
		while (l <= mid && r <= end) {
			if (nums[tmp[l]] <= nums[tmp[r]]) {
				pos[idx] = tmp[l];
				res[pos[idx]] += r-mid-1;
				l++;
				idx++;
			}
			else {
				pos[idx++] = tmp[r++];
			}
		}
		while (l <= mid) {
			pos[idx] = tmp[l];
			res[pos[idx]] += end-mid;
			idx++;
			l++;
		}
		while (r <= end) {
			pos[idx++] = tmp[r++];
		}
	}
	
	private void mergeSort(int[] nums, int[] pos, int sta, int end, int[] res) {
		if (sta >= end) {
			return;
		}
		int mid = (sta + end) / 2;
		mergeSort(nums, pos, sta, mid, res);
		mergeSort(nums, pos, mid+1, end, res);
		merge(nums, pos, sta, mid, end, res);
	}
	
	public List<Integer> surpass(int[] nums) {
		int[] res = new int[nums.length], pos = new int[nums.length];
		for (int i = 0; i < pos.length; i++) {
			pos[i] = i;
		}
		mergeSort(nums, pos, 0, nums.length-1, res);
		List<Integer> result = new ArrayList<>();
		for (int x: res) {
			result.add(x);
		}
		return result;
	}
