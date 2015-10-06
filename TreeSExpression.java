class TreeSExpression {
	/*
	 * Given edge pair :(A,B) (A,C) (B,G) (C,H) (E,F) (B,D) (C,E)
	 * return one possible s-expression: (A(B(G)(D))(C(H)(E(F))))
	 */
	class Pair {
		char fir;
		char sec;
		public Pair(char fir, char sec) {
			this.fir = fir;
			this.sec = sec;
		}
	}
	
	public void test() {
		Pair[] pa = new Pair[7];
		pa[0] = new Pair('A', 'B');
		pa[1] = new Pair('A', 'C');
		pa[2] = new Pair('B', 'G');
		pa[3] = new Pair('C', 'H');
		pa[4] = new Pair('E', 'F');
		pa[5] = new Pair('B', 'D');
		pa[6] = new Pair('C', 'E');
		try {
			System.out.println(getSExpression(pa));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getSExpression(Pair[] edge) throws Exception {
		char[][] node = new char[26][2];
		HashMap<Character, Integer> inDegree = new HashMap<>();
		for (Pair pair: edge) {
			inDegree.put(pair.fir, 0);
			inDegree.put(pair.sec, 0);
		}
		for (Pair pair: edge) {
			inDegree.put(pair.sec, inDegree.get(pair.sec)+1);
			int pos = (int)(pair.fir-'A');
			if (node[pos][0] == pair.sec || node[pos][1] == pair.sec) {
				throw new Exception("Duplicate edge");
			}
			if (node[pos][0] == 0) {
				node[pos][0] = pair.sec;
			}
			else if (node[pos][1] == 0) {
				node[pos][1] = pair.sec;
			}
			else {
				throw new Exception("more than 2 children");
			}
		}
		if (edge.length != inDegree.size()-1) {
			throw new Exception("cycle present");
		}
		char root = 0;
		for (Map.Entry<Character, Integer> entry: inDegree.entrySet()) {
			if (entry.getValue() == 0) {
				if (root == 0) {
					root = entry.getKey();
				}
				else {
					throw new Exception("Multiple root");
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		dfs(root, node, sb);
		return sb.toString();
	}
	
	private void dfs(char root, char[][] node, StringBuilder cur) {
		cur.append(root);
		int pos = (int)(root-'A');
		if (node[pos][0] != 0) {
			cur.append('(');
			dfs(node[pos][0], node, cur);
		}
		if (node[pos][1] != 0) {
			cur.append('(');
			dfs(node[pos][1], node, cur);
		}
		cur.append(')');
	}
}

