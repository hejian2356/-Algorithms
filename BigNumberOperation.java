class Solution {
	/*
	 * big number s1 s2: * - /
	 */
	private String deleteHeadZero(String s) {
		int i = 0;
		for (; i < s.length(); i++) {
			if (s.charAt(i) != '0') {
				break;
			}
		}
		if (i == s.length()) {
			return "0";
		}
		return s.substring(i);
	}
	
	public String subtract(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return "0";
		}
		s1 = deleteHeadZero(s1);
		s2 = deleteHeadZero(s2);
		if (s1.length() < s2.length() || (s1.length() == s2.length() && s1.compareTo(s2) < 0)) {
			return "-"+subtract(s2, s1);
		}
		StringBuilder res = new StringBuilder();
		s1 = new StringBuilder(s1).reverse().toString();
		s2 = new StringBuilder(s2).reverse().toString();
		int i = 0, carry = 0;
		while (i < s1.length() && i < s2.length()) {
			char c1 = s1.charAt(i), c2 = s2.charAt(i);
			if (c1-carry >= c2) {
				res.append((char)(c1-carry-c2+'0'));
				carry = 0;
			}
			else {
				res.append((char)(c1-carry+10-c2+'0'));
				carry = 1;
			}
			i++;
		}
		while (i < s1.length()) {
			char c1 = s1.charAt(i);
			if (c1-carry >= '0') {
				res.append((char)(c1-carry));
				res.append(s1.substring(i+1));
				break;
			}
			else {
				res.append((char)(c1-carry+10));
				carry = 1;
			}
			i++;
		}
		return deleteHeadZero(res.reverse().toString());
	}
	
	public boolean smallerOrEqual(String s1, String s2) {
		s1 = deleteHeadZero(s1);
		s2 = deleteHeadZero(s2);
		if (s1.equals(s2) || s1.length() < s2.length() || (s1.length() == s2.length() && s1.compareTo(s2) < 0)) {
			return true;
		}
		return false;
	}
	
	public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length(), len = len1+len2;
        int[] prod = new int[len];
        for (int i = 0; i < len2; i++) {
            int carry = 0;
            for (int j = len1-1; j >= 0; j--) {
                int tmp = (num1.charAt(j)-'0')*(num2.charAt(len2-1-i)-'0')+prod[len1-1-j+i]+carry;
                carry = 0;
                if (tmp > 9) {
                    carry = tmp / 10;
                    tmp %= 10;
                }
                prod[len1-1-j+i] = tmp;
            }
            prod[len1+i] += carry;
        }
        StringBuilder sb = new StringBuilder();
        int i = len-1;
        for (; i >= 0; i--) {
            if (prod[i] != 0) {
                break;
            }
        }
        if (i < 0) {
            return "0";
        }
        for (; i >= 0; i--) {
            sb.append(""+prod[i]);
        }
        return sb.toString();
    }
	
	public String divide(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return "0";
		}
		s1 = deleteHeadZero(s1);
		s2 = deleteHeadZero(s2);
		if (s1.equals("0") || s2.equals("0") || s1.length() < s2.length()) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		String denominator = s1.substring(0, s2.length());
		int i = s2.length();
		while (i <= s1.length()) {
			int l = 0, r = 9, mid = 0;
			String tmp = "";
			while (l < r) {
				mid = (l+r+1)/2;
				tmp = multiply(s2, ""+mid);
				if (smallerOrEqual(tmp, denominator)) {
					l = mid;
				}
				else {
					r = mid-1;
				}
			}
			res.append(""+l);
			tmp = multiply(s2, ""+l);
			denominator = subtract(denominator, tmp);
			if (i < s1.length()) {
				denominator += s1.substring(i, i+1);
			}
			i++;
		}
		return deleteHeadZero(res.toString());
	}
}
