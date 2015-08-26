class TopK {
	/**
	 * return top K numbers of an array
	 * example: Given [0, 6, 7, 3, 8, 10], k = 2, return [10, 6]
	 */
	void swap(int[] num, int i, int j) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
	
	void partition(int[] num, int sta, int end, int k) {
		if (sta == end) {
			return;
		}
		int pivot = num[end];
		int l = sta, r = end-1;
		while (l < r) {
			if (num[l] <= pivot) {
				l++;
			}
			else {
				swap(num, l, r);
				r--;
			}
		}
		int pos = 0;
		if (num[l] <= pivot) {
			swap(num, l+1, end);
			pos = l+1;
		}
		else {
			swap(num, l, end);
			pos = l;
		}
		if (pos > k) {
			partition(num, sta, pos-1, k);
		}
		else if (pos < k-1) {
			partition(num, pos+1, end, k);
		}
	}
	
	int[] topK(int[] num, int k) {
		int[] res = new int[k];
		partition(num, 0, num.length-1, num.length-k);
		for (int i = num.length-k; i < num.length; i++) {
			res[i-num.length+k] = num[i];
		}
		return res;
	}
}
