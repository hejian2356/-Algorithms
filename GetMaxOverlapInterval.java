class Solution {
	public void getMaxOverlap(List<Interval> input) {
		List<Integer> list = new ArrayList<>();
		for (Interval x: input) {
			list.add(x.start);
			list.add(-x.end);
		}
		Collections.sort(list, new Comparator<Integer>(){
			public int compare(Integer a, Integer b) {
				int x = Math.abs(a), y = Math.abs(b);
				if (x != y) {
					return x-y;
				}
				return b-a;
			}
		});
		int cnt = 1, pre = list.get(0), maxOverlap = 0;
		HashMap<Integer, List<Interval>> hash = new HashMap<>();
		for (int i = 1; i < list.size(); i++) {
			int cur = list.get(i);
			if (!hash.containsKey(cnt)) {
				hash.put(cnt, new ArrayList<>());
			}
			if (Math.abs(pre) != Math.abs(cur)) {
				maxOverlap = Math.max(maxOverlap, cnt);
				hash.get(cnt).add(new Interval(pre, cur));
			}
			if (cur > 0) {
				cnt++;
			}
			else {
				cnt--;
			}
			pre = cur;
		}
		System.out.println("Max overlap times: "+maxOverlap);
		List<Interval> res = hash.get(maxOverlap);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(Math.abs(res.get(i).start)+" , "+Math.abs(res.get(i).end));
		}
	}
}
