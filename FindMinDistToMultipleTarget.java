class Solution2 {
	/*
	 * find the point to all 'G' that has minimum distance, 'B' is barrier
	 * ex: {{'G', 'B', '0'},
    		{'0', 'B', '0'},
    		{'0', '0', 'G'}}, return 4
	 */
	class Point {
		int j;
		int i;
		public Point(int j, int i) {
			this.j = j;
			this.i = i;
		}
	}
	
	int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	
	private void allPointsToOneG(int[][] res, char[][] matrix, int posj, int posi) {
		int[][] tmp = new int[matrix.length][matrix[0].length];
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[0].length; i++) {
				tmp[j][i] = Integer.MIN_VALUE;
			}
		}
		tmp[posj][posi] = 0;
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(posj, posi));
		int len = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int l = 0; l < size; l++) {
				Point cur = q.poll();
				for (int k = 0; k < 4; k++) {
					int j = cur.j + dir[k][0], i = cur.i + dir[k][1];
					if (j < 0 || j >= matrix.length || i < 0 || i >= matrix[0].length || matrix[j][i] == 'B' || tmp[j][i] != Integer.MIN_VALUE) {
						continue;
					}
					tmp[j][i] = len;
					q.offer(new Point(j, i));
				}
			}
			len++;
		}
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[0].length; i++) {
				res[j][i] += tmp[j][i];
			}
		}
	}
	
	public int getMinDist(char[][] matrix) {
		int wid = matrix.length, len = matrix[0].length;
		int[][] res = new int[wid][len];
		for (int j = 0; j < wid; j++) {
			for (int i = 0; i < len; i++) {
				if (matrix[j][i] == 'G') {
					allPointsToOneG(res, matrix, j, i);
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < wid; j++) {
			for (int i = 0; i < len; i++) {
				if (matrix[j][i] != 'B' && res[j][i] < min) {
					min = res[j][i];
				}
			}
		}
		return min;
	}
}
