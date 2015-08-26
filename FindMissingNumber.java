class FindMissingNumber {
	/**
	 * find missing number range between 0~99 
	 * example: Given [0, 1, 2, 50, 52, 75], return "3-49,51,53-74,76-99".
	 */
	String findMissingNumber(int[] num) {
		StringBuilder sb = new StringBuilder();
		int sta = 0;
		for (int i = 0; i < num.length; i++) {
			if (sta == num[i]) {
				sta++;
				continue;
			}
			int l = sta, r = num[i] - 1;
			sb.append("" + l);
			if (l != r) {
				sb.append('-');
				sb.append("" + r);
			}
			sb.append(',');
			sta = num[i] + 1;
		}
		if (sta <= 99) {
			sb.append(sta + "");
			if (sta < 99) {
				sb.append('-');
				sb.append("99");
			}
		} else {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
}
