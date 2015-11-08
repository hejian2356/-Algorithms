class Solution {
	//(5 − 6) * 7 return * - 5 6 7
	public ArrayList<String> convertToPN(String[] exp) {
		ArrayList<String> res = new ArrayList<>();
		Stack<String> stk = new Stack<>();
		for (int i = exp.length-1; i >= 0; i--) {
			String s = exp[i];
			if (isOp(s)) {
				if (s == ")") {
					stk.push(s);
				}
				else if (s == "(") {
					while (!stk.isEmpty()) {
						String tmp = stk.pop();
						if (tmp == ")") {
							break;
						}
						res.add(tmp);
					}
				}
				else {
					while (!stk.isEmpty() && order(s) < order(stk.peek())) {
						res.add(stk.pop());
					}
					stk.push(s);
				}
			}
			else {
				res.add(s);
			}
		}
		while (!stk.isEmpty()) {
			res.add(stk.pop());
		}
		Collections.reverse(res);
		return res;
	}
	
	private boolean isOp(String s) {
		if (s == "+" || s == "-" || s == "*" || s == "/" || s == "(" || s == ")") {
			return true;
		}
		return false;
	}
	
	private int order(String s) {
		if (s == "*" || s == "/") {
			return 2;
		}
		if (s == "+" || s == "-") {
			return 1;
		}
		return 0;
	}
}
