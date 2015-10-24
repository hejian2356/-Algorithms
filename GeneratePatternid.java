class Solution {
	public String generate(String input) {
		/*
		 * i means increase, d means decrease
		 * get the smallest pattern
		 */
		char sta = '1';
		int i = 0;
		StringBuilder sb = new StringBuilder();
		if (input.charAt(0) == 'i') {
			sb.append(sta++);
		}
		else {
			int dNum = 0;
			for (int j = 0; j < input.length() && input.charAt(j) == 'd'; j++) {
				dNum++;
			}
			for (char c = (char)(sta+1+dNum); c >= '1'; c--) {
				sb.append(c);
			}
			sta += 2+dNum;
		}
		while (i < input.length()) {
				if (i == input.length()-1 || input.charAt(i+1) == 'i') {
					sb.append(sta++);
					i++;
					continue;
				}
				int dNum = 0;
				for (i++; i < input.length() && input.charAt(i) == 'd'; i++) {
					dNum++;
				}
				for (char c = (char)(sta+dNum); c >= sta; c--) {
					sb.append(c);
				}
				sta += dNum+1;
		}
		return sb.toString();
	}
}
