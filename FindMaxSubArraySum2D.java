class Solution2 {
	public static List<Integer> continuousSubarraySumII(int[] A) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0) {
            return res;
        }
        int sta = 0, maxsta = 0, maxend = 0, gmax = A[0], lmax = A[0], i, total = A[0];
        for (i = 1; i < A.length; i++) {
            total += A[i];
            if (lmax+A[i] < A[i]) {
                sta = i;
                lmax = A[i];
            }
            else {
                lmax += A[i];
            }
            if (gmax < lmax) {
                maxsta = sta;
                maxend = i;
                gmax = lmax;
            }
        }
        res.add(maxsta);
        res.add(maxend);
        res.add(gmax);
        int lmin = A[0], gmin = A[0], minsta = 0, minend = 0;
        sta = 0;
        for (i = 1; i < A.length; i++) {
            if (lmin > 0) {
                sta = i;
                lmin = A[i];
            }
            else {
                lmin += A[i];
            }
            if (gmin > lmin) {
                minsta = sta;
                minend = i;
                gmin = lmin;
            }
        }
        if (total - gmin > gmax && (minsta != 0 || minend != A.length-1)) {
            res.clear();
            res.add(minend+1);
            res.add((minsta-1+A.length)%A.length);
            res.add(total-gmin);
        }
        return res;
    }
	
	
	
	public void findMaxSum(int[][] matrix) {
		int row = matrix.length, col = matrix[0].length;
		int[][] expandMatrix = new int[row*2][col];
		for (int j = 0; j < row; j++) {
			for (int i = 0; i < col; i++) {
				expandMatrix[j][i] = matrix[j][i];
				expandMatrix[j+row][i] = matrix[j][i];
			}
		}
		long maxSum = Long.MIN_VALUE;
		int rowSta = 0, colSta = 0, rowEnd = 0, colEnd = 0;
		for (int j = 0; j < row; j++) {
			int[] tmp = new int[col];
			for (int i = j; i < row+j; i++) {
				for (int pos = 0; pos < col; pos++) {
					tmp[pos] += expandMatrix[i][pos];
				}
				List<Integer> res = continuousSubarraySumII(tmp);
				if (maxSum < res.get(2)) {
					maxSum = res.get(2);
					rowSta = j;
					rowEnd = i;
					colSta = res.get(0);
					colEnd = res.get(1);
				}
			}
		}
		System.out.println("start row: " + rowSta);
		System.out.println("end row: " + rowEnd % row);
		System.out.println("start column: " + colSta);
		System.out.println("end column: " + colEnd);
		System.out.println("maximum sum is: " + maxSum);
	}
}
