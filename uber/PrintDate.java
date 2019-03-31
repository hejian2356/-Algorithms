class Solution {
    public static void run() {
        System.out.println(printDate(2099, 8, 5, 2100, 3, 3));
    }

    public static List<String> printDate(int y1, int m1, int d1, int y2, int m2, int d2) {
        int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        List<String> res = new ArrayList<>();
        for (int j = y1; j <= y2; j++) {
            if (j < y2) {
                for (int i = m1; i <= 12; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(j + "-" + i + "-" + d1 + " to " + i + "-");
                    d1 = 1;
                    if (i == 2) {
                        if (isLeapYear(j)) {
                            sb.append(29);
                        } else {
                            sb.append(28);
                        }
                    } else {
                        sb.append(monthDays[i - 1]);
                    }
                    res.add(sb.toString());
                }
                m1 = 1;
            } else {
                for (int i = m1; i <= m2; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(j + "-" + i + "-" + d1 + " to " + i + "-");
                    if (i < m2) {
                        if (i == 2) {
                            if (isLeapYear(j)) {
                                sb.append(29);
                            } else {
                                sb.append(28);
                            }
                        } else {
                            sb.append(monthDays[i - 1]);
                        }
                    } else {
                        sb.append(d2);
                    }
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }

    private static boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            }
            return false;
        }
        return year % 4 == 0;
    }
}
