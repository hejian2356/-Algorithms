public static class Token {
		private final int commentId;
		private final int setenceIndex;
		private final String word;
		
		private Token(int commentId, int senId, String word) {
			this.commentId = commentId;
			this.setenceIndex = senId;
			this.word = word;
		}
	}

	public static class CoOccurrence implements Comparable<CoOccurrence>{
		private final String word1;
		private final String word2;
		private final int count;
		
		private CoOccurrence(String word1, String word2, int cnt) {
			this.word1 = word1;
			this.word2 = word2;
			this.count = cnt;
		}
		
		public String toString() {
			return String.format("(%s, %s, %s)", word1, word2, count);
		}
		
		public int compareTo(CoOccurrence o) {
			return Integer.compare(o.count, count);
		}
	}
	
	static class MyCoOccurrence {
		String s1;
		String s2;
		public MyCoOccurrence(String s1, String s2) {
			this.s1 = s1;
			this.s2 = s2;
		}
		public int hashCode() {
			return s1.hashCode()+s2.hashCode();
		}
		public boolean equals(Object c1) {
			return this.s1.equals(((MyCoOccurrence)c1).s1) && this.s2.equals(((MyCoOccurrence)c1).s2);
		}
	}
	
	static class Stas {
		MyCoOccurrence coOccu;
		int freq;
		public Stas(MyCoOccurrence coOccu, int freq) {
			this.coOccu = coOccu;
			this.freq = freq;
		}
	}
	
	static List<CoOccurrence> findFrequenct(int n, Iterable<Token> tokens) {
		Iterator<Token> ite = tokens.iterator();
		List<Token> list = new ArrayList<>();
		while (ite.hasNext()) {
			list.add(ite.next());
		}
		
		Collections.sort(list, new Comparator<Token>(){
			public int compare(Token p1, Token p2) {
				if (p1.commentId != p2.commentId) {
					return p1.commentId-p2.commentId;
				}
				else if (p1.setenceIndex != p2.setenceIndex) {
					return p1.setenceIndex-p2.setenceIndex;
				}
				else {
					return p1.word.compareTo(p2.word);
				}
			}
		});
		HashMap<MyCoOccurrence, Integer> hash = new HashMap<>();
		int sta = 0;
		while (sta < list.size()) {
			List<String> tmp = new ArrayList<>();
			int curj = list.get(sta).commentId, curi = list.get(sta).setenceIndex;
			while (sta < list.size() && list.get(sta).commentId == curj && list.get(sta).setenceIndex == curi) {
				tmp.add(list.get(sta).word);
				sta++;
			}
			for (int j = 0; j < tmp.size()-1; j++) {
				for (int i = j+1; i < tmp.size(); i++) {
					MyCoOccurrence newPair = new MyCoOccurrence(tmp.get(j), tmp.get(i));
					System.out.println(newPair.hashCode());
					if (hash.containsKey(newPair)) {
						hash.put(newPair, hash.get(newPair)+1);
					}
					else {
						hash.put(newPair, 1);
					}
				}
			}
		}
		List<CoOccurrence> allCoOcc = new ArrayList<>();
		for (Map.Entry<MyCoOccurrence, Integer> entry: hash.entrySet()) {
			allCoOcc.add(new CoOccurrence(entry.getKey().s1, entry.getKey().s2, entry.getValue()));
		}
		Collections.sort(allCoOcc);
		List<CoOccurrence> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			res.add(allCoOcc.get(i));
		}
		return res;
	}
	
	/////////////
	interface Node {
		
	}
	
	static class Element implements Node {
		public final String tag;
		public final String id;
		public final List<Node> children;
		
		public Element(String tag, String id, List<Node> children) {
			this.tag = tag;
			this.id = id;
			this.children = children;
		}
	}
	
	static class Content implements Node {
		public final String content;
		public Content(String content) {
			this.content = content;
		}
	}
	
	static class DOM {
		public final Element root;
		public DOM(Element root) {
			this.root = root;
		}
	}
	
	private static boolean isValid(String s, String[] whiteList) {
		if (s == null) {
			return true;
		}
		for (String tmp: whiteList) {
			if (s.indexOf(tmp) != -1) {
				return false;
			}
		}
		return true;
	}
	
	static String format(DOM dom, String[] whiteList) {
		StringBuilder res = new StringBuilder();
		if (dom == null) {
			return res.toString();
		}
		Node root = dom.root;
                if (root == null) {
                        return "";
                }
		if (root instanceof Content) {
			res.append(((Content)root).content);
			return res.toString();
		}
		Queue<Element> q = new LinkedList<>();
		q.offer((Element)root);
		while (!q.isEmpty()) {
			int size = q.size();
			boolean first = true;
			for (int i = 0; i < size; i++) {
				Element cur = q.poll();	
				StringBuilder cont = new StringBuilder();
				for (Node child: cur.children) {
					if (child instanceof Content) {
						if (cont.length() == 0) {
							cont.append(((Content)child).content);
						}
						else {
							cont.append(" "+((Content)child).content);
						}
					}
				}
				if (!isValid(cont.toString(), whiteList)) {
					continue;
				}
				if (!isValid(cur.tag, whiteList) || !isValid(cur.id, whiteList)) {
					continue;
				}
				
					if (cur.tag != null) {
						if (first) {
							res.append(cur.tag);
							first = false;
						}
						else {
							res.append(" "+cur.tag);
						}
					}
					if (cur.id != null) {
						if (first) {
							res.append(cur.id);
							first = false;
						}
						else {
							res.append(" "+cur.id);
						}
					}
					for (Node child: cur.children) {
						if (child instanceof Element) {
							q.offer((Element)child);
						}
						else {
							if (((Content)child).content != null) {
								if (first) {
									res.append(((Content)child).content);
									first = false;
								}
								else {
									res.append(" "+((Content)child).content);
								}
							}
						}
					}
					
			}
			res.append("\n");
		}
		return res.toString();
	}
