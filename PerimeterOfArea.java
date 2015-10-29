class Solution {
	/*
	 * return the perimeter of the area that has same value with given position
	 * ex: 1111
	 *     2121 
	 *     2111
	 *    Given position (0, 0), return 7
	 */
	class Pair {
		int j;
		int i;
		public Pair(int j, int i) {
			this.j = j;
			this.i = i;
		}
	}
	int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	public int perimeter(int[][] arr, int posj, int posi) {
		int wid = arr.length, len = arr[0].length;
		boolean[][] visited = new boolean[wid][len];
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(posj, posi));
		visited[posj][posi] = true;
		int res = 0;
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			for (int k = 0; k < 4; k++) {
				int j = cur.j+dir[k][0], i = cur.i+dir[k][1];
				if (j < 0 || j >= wid || i < 0 || i >= len || visited[j][i]) {
					continue;
				}
				if (arr[j][i] != arr[posj][posi] ) {
					res++;
					continue;
				}
				q.offer(new Pair(j, i));
				visited[j][i] = true;
			}
		}
		return res;
	}
}
