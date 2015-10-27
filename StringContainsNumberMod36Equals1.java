class Solution {
	/*
	 * "dsfj12435245367768542lskdfjdffd 5e445456", 685 % 36 == 1, return true
	 */
	public boolean ifStringContainsOneNumberMod36Equals1(String s) {
		int sta = 0;
		while (sta < s.length()) {
			char curCha = s.charAt(sta);
			if (!Character.isDigit(curCha)) {
				sta++;
				continue;
			}
			StringBuilder cur = new StringBuilder();
			while (Character.isDigit(curCha)) {
				cur.append(curCha);
				sta++;
				if (sta >= s.length()) {
					break;
				}
				curCha = s.charAt(sta);
			}
			String tmpString = cur.toString();
			int[] array = new int[tmpString.length() - 1];
			int len = 3;
			for (int i = 1; i < tmpString.length(); i++) {
				array[i - 1] = Integer.parseInt(tmpString.substring(i - 1, i + 1)) % 36;
				if (array[i - 1] == 1)
					return true;
			}
			while (len <= tmpString.length()) {
				for (int i = 0; i <= tmpString.length() - len; i++) {
					array[i] = array[i] * 10 + tmpString.charAt(i + len - 1) - '0';
					array[i] %= 36;
					if (array[i] == 1) {
						System.out.println(tmpString.substring(i, i+len));
						return true;
					}
				}
				len++;
			}
		}
		return false;
	}
}
