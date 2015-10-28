class Abbreviation {
	/**
	 * Given a string, return its all abbreviation. 
	 * Test: given "sat", return [1a1, 3, 1at, 2t, sa1, s2, sat, s1t].       
	 * 
	 */
    public List<String> abbre(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        if (s.length() == 1) {
            res.add(s);
            res.add("1");
            return res;
        }
        List<String> left = abbre(s.substring(0, s.length()/2)), right = abbre(s.substring(s.length()/2));
        for (String a: left) {
            for (String b: right) {
                int pos1 = -1, num1 = -1, pos2 = -1, num2 = -1;
                if (a.length() != 0 && Character.isDigit(a.charAt(a.length()-1))) {
                    pos1 = a.length()-1;
                    while (pos1 >= 0 && Character.isDigit(a.charAt(pos1))) {
                        pos1--;
                    }
                    num1 = Integer.parseInt(a.substring(pos1+1));
                }
                if (b.length() != 0 && Character.isDigit(b.charAt(0))) {
                    pos2 = 0;
                    while (pos2 < b.length() && Character.isDigit(b.charAt(pos2))) {
                        pos2++;
                    }
                    num2 = Integer.parseInt(b.substring(0, pos2));
                }
                if (num1 == -1 || num2 == -1) {
                    res.add(a+b);
                }
                else {
                    num1 += num2;
                    res.add(a.substring(0, pos1+1)+num1+b.substring(pos2));
                }
            }
        }
        return res;
    }
}