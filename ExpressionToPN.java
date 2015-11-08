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
///////////construct an expression tree
//expression -> reverse polish notation -> expresstion tree
class ExpressionTreeNode {
	      public String symbol;
	      public ExpressionTreeNode left, right;
	      public ExpressionTreeNode(String symbol) {
	          this.symbol = symbol;
	          this.left = this.right = null;
	      }
	  }

class Solution {	
	public ExpressionTreeNode build(String[] exp) {
        // write your code here
			ArrayList<String> res = new ArrayList<>();
			Stack<String> stk = new Stack<>();
			for (int i = 0; i < exp.length; i++) {
				String s = exp[i];
				if (isOp(s)) {
					if (s == "(") {
						stk.push(s);
					}
					else if (s == ")") {
						while (!stk.isEmpty()) {
							String tmp = stk.pop();
							if (tmp == "(") {
								break;
							}
							res.add(tmp);
						}
					}
					else {
						while (!stk.isEmpty() && order(s) <= order(stk.peek())) {
							if (stk.peek() == "(") {
								break;
							}
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
			System.out.println(res);
			Stack<ExpressionTreeNode> stk1 = new Stack<>();
			for (String s: res) {
			    if (!isOp(s)) {
			        stk1.push(new ExpressionTreeNode(s));
			    }
			    else {
			        ExpressionTreeNode tmp = new ExpressionTreeNode(s);
			        tmp.right = stk1.pop();
			        tmp.left = stk1.pop();
			        stk1.push(tmp);
			    }
			}
			return stk1.peek();
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
			return 3;
		}
}
