class HuluPrintAccordingTitle {
	/*
	 * Ceo: Helen
	 * -Manager: Chris
	 * --Engineer: Laora
	 */
	class Employee implements Comparable<Employee>{
		String title, name, year;
		List<Employee> next;
		public Employee(String name, String title, String year) {
			this.title = title;
			this.name = name;
			this.year = year;
			next = new ArrayList<>();
		}
		@Override
		public int compareTo(Employee o) {
			return name.compareTo(o.name);
		}
	}
	
	public Employee buildTree(String s) {
		HashMap<String, Employee> hash = new HashMap<>();
		int i = 0, sta = 0;
		Employee ceo = null;
		while (i < s.length()) {
			List<String> tmp = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				sta = i;
				while (i < s.length() && s.charAt(i) != ',' && s.charAt(i) != '-') {
					i++;
				}
				tmp.add(s.substring(sta, i));
				i++;
			}
			i ++;
			
			if (!hash.containsKey(tmp.get(0))) {
				Employee employee = new Employee(tmp.get(0), tmp.get(2), tmp.get(3));
				hash.put(tmp.get(0), employee);
			}
			if (!tmp.get(1).equals("NULL") && !hash.containsKey(tmp.get(1))) {
				Employee employee = new Employee(tmp.get(1), null, null);
				hash.put(tmp.get(1), employee);
			}
			Employee employee = hash.get(tmp.get(0));
			employee.title = tmp.get(2);
			employee.year = tmp.get(3);
			if (!tmp.get(1).equals("NULL")) {
				hash.get(tmp.get(1)).next.add(employee);
			}
			else {
				ceo = employee;
			}
		}
		return ceo;
	}
	
	private void dfs(List<String> res, Employee root, int depth) {
		if (root == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		Collections.sort(root.next);
		for (int i = 0; i < depth; i++) {
			sb.append('-');
		}
		sb.append(root.name+" ("+root.title+") "+root.year);
		res.add(sb.toString());
		for (Employee em: root.next) {
			dfs(res, em, depth+1);
		}
	}
	
	public List<String> constructChart(String s) {
		Employee ceo = buildTree(s);
		List<String> res = new ArrayList<>();
		dfs(res, ceo, 0);
		return res;
	}
	
	public void write(String inputFileName, String outputFileName) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(new File(inputFileName)));
			writer = new BufferedWriter(new FileWriter(new File(outputFileName)));
			String tmp = reader.readLine();
			int len = Integer.parseInt(tmp);
			int i = 1;
			tmp = reader.readLine();
			while (tmp != null) {
				if (i > len) {
					break;
				}
				writer.append("Case #"+i);
				writer.newLine();
				for (String cur: constructChart(tmp)) {
					writer.append(cur);
					writer.newLine();
				}
				i++;
				tmp = reader.readLine();
			}
			reader.close();
			writer.close();
		} catch (Exception e) {
			System.out.println("Read or write error!");
		}	
	}
	
	public static void main(String[] args) {
		HuluPrintAccordingTitle so = new HuluPrintAccordingTitle();
		so.write("org_chart.in", "org_chart.out");
	}
}
