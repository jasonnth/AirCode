package com.fasterxml.jackson.core.p307io;

import java.math.BigDecimal;

/* renamed from: com.fasterxml.jackson.core.io.NumberInput */
public final class NumberInput {
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);

    public static int parseInt(char[] ch, int off, int len) {
        int num = ch[off] - '0';
        if (len > 4) {
            int off2 = off + 1;
            int off3 = off2 + 1;
            int off4 = off3 + 1;
            off = off4 + 1;
            num = (((((((num * 10) + (ch[off2] - '0')) * 10) + (ch[off3] - '0')) * 10) + (ch[off4] - '0')) * 10) + (ch[off] - '0');
            len -= 4;
            if (len > 4) {
                int off5 = off + 1;
                int off6 = off5 + 1;
                int off7 = off6 + 1;
                return (((((((num * 10) + (ch[off5] - '0')) * 10) + (ch[off6] - '0')) * 10) + (ch[off7] - '0')) * 10) + (ch[off7 + 1] - '0');
            }
        }
        if (len > 1) {
            int off8 = off + 1;
            num = (num * 10) + (ch[off8] - '0');
            if (len > 2) {
                int off9 = off8 + 1;
                num = (num * 10) + (ch[off9] - '0');
                if (len > 3) {
                    num = (num * 10) + (ch[off9 + 1] - '0');
                }
            }
        }
        return num;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int parseInt(java.lang.String r10) {
        /*
            r6 = 1
            r2 = 0
            r9 = 57
            r8 = 48
            char r0 = r10.charAt(r2)
            int r1 = r10.length()
            r7 = 45
            if (r0 != r7) goto L_0x0013
            r2 = r6
        L_0x0013:
            r4 = 1
            if (r2 == 0) goto L_0x0031
            if (r1 == r6) goto L_0x001c
            r6 = 10
            if (r1 <= r6) goto L_0x0021
        L_0x001c:
            int r3 = java.lang.Integer.parseInt(r10)
        L_0x0020:
            return r3
        L_0x0021:
            int r5 = r4 + 1
            char r0 = r10.charAt(r4)
        L_0x0027:
            if (r0 > r9) goto L_0x002b
            if (r0 >= r8) goto L_0x003a
        L_0x002b:
            int r3 = java.lang.Integer.parseInt(r10)
            r4 = r5
            goto L_0x0020
        L_0x0031:
            r6 = 9
            if (r1 <= r6) goto L_0x008b
            int r3 = java.lang.Integer.parseInt(r10)
            goto L_0x0020
        L_0x003a:
            int r3 = r0 + -48
            if (r5 >= r1) goto L_0x0086
            int r4 = r5 + 1
            char r0 = r10.charAt(r5)
            if (r0 > r9) goto L_0x0048
            if (r0 >= r8) goto L_0x004d
        L_0x0048:
            int r3 = java.lang.Integer.parseInt(r10)
            goto L_0x0020
        L_0x004d:
            int r6 = r3 * 10
            int r7 = r0 + -48
            int r3 = r6 + r7
            if (r4 >= r1) goto L_0x0087
            int r5 = r4 + 1
            char r0 = r10.charAt(r4)
            if (r0 > r9) goto L_0x005f
            if (r0 >= r8) goto L_0x0065
        L_0x005f:
            int r3 = java.lang.Integer.parseInt(r10)
            r4 = r5
            goto L_0x0020
        L_0x0065:
            int r6 = r3 * 10
            int r7 = r0 + -48
            int r3 = r6 + r7
            if (r5 >= r1) goto L_0x0086
        L_0x006d:
            r4 = r5
            int r5 = r4 + 1
            char r0 = r10.charAt(r4)
            if (r0 > r9) goto L_0x0078
            if (r0 >= r8) goto L_0x007e
        L_0x0078:
            int r3 = java.lang.Integer.parseInt(r10)
            r4 = r5
            goto L_0x0020
        L_0x007e:
            int r6 = r3 * 10
            int r7 = r0 + -48
            int r3 = r6 + r7
            if (r5 < r1) goto L_0x006d
        L_0x0086:
            r4 = r5
        L_0x0087:
            if (r2 == 0) goto L_0x0020
            int r3 = -r3
            goto L_0x0020
        L_0x008b:
            r5 = r4
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.p307io.NumberInput.parseInt(java.lang.String):int");
    }

    public static long parseLong(char[] ch, int off, int len) {
        int len1 = len - 9;
        return ((long) parseInt(ch, off + len1, 9)) + (((long) parseInt(ch, off, len1)) * 1000000000);
    }

    public static long parseLong(String s) {
        if (s.length() <= 9) {
            return (long) parseInt(s);
        }
        return Long.parseLong(s);
    }

    public static boolean inLongRange(char[] ch, int off, int len, boolean negative) {
        String cmpStr = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int cmpLen = cmpStr.length();
        if (len < cmpLen) {
            return true;
        }
        if (len > cmpLen) {
            return false;
        }
        int i = 0;
        while (i < cmpLen) {
            int diff = ch[off + i] - cmpStr.charAt(i);
            if (diff == 0) {
                i++;
            } else if (diff >= 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public static boolean inLongRange(String s, boolean negative) {
        String cmp = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int cmpLen = cmp.length();
        int alen = s.length();
        if (alen < cmpLen) {
            return true;
        }
        if (alen > cmpLen) {
            return false;
        }
        int i = 0;
        while (i < cmpLen) {
            int diff = s.charAt(i) - cmp.charAt(i);
            if (diff == 0) {
                i++;
            } else if (diff >= 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public static int parseAsInt(String s, int def) {
        if (s == null) {
            return def;
        }
        String s2 = s.trim();
        int len = s2.length();
        if (len == 0) {
            return def;
        }
        int i = 0;
        if (0 < len) {
            char c = s2.charAt(0);
            if (c == '+') {
                s2 = s2.substring(1);
                len = s2.length();
            } else if (c == '-') {
                i = 0 + 1;
            }
        }
        while (i < len) {
            char c2 = s2.charAt(i);
            if (c2 > '9' || c2 < '0') {
                try {
                    return (int) parseDouble(s2);
                } catch (NumberFormatException e) {
                    return def;
                }
            } else {
                i++;
            }
        }
        try {
            return Integer.parseInt(s2);
        } catch (NumberFormatException e2) {
            return def;
        }
    }

    public static long parseAsLong(String s, long def) {
        if (s == null) {
            return def;
        }
        String s2 = s.trim();
        int len = s2.length();
        if (len == 0) {
            return def;
        }
        int i = 0;
        if (0 < len) {
            char c = s2.charAt(0);
            if (c == '+') {
                s2 = s2.substring(1);
                len = s2.length();
            } else if (c == '-') {
                i = 0 + 1;
            }
        }
        while (i < len) {
            char c2 = s2.charAt(i);
            if (c2 > '9' || c2 < '0') {
                try {
                    return (long) parseDouble(s2);
                } catch (NumberFormatException e) {
                    return def;
                }
            } else {
                i++;
            }
        }
        try {
            return Long.parseLong(s2);
        } catch (NumberFormatException e2) {
            return def;
        }
    }

    public static double parseDouble(String s) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(s)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(s);
    }

    public static BigDecimal parseBigDecimal(String s) throws NumberFormatException {
        try {
            return new BigDecimal(s);
        } catch (NumberFormatException e) {
            throw _badBD(s);
        }
    }

    public static BigDecimal parseBigDecimal(char[] b) throws NumberFormatException {
        return parseBigDecimal(b, 0, b.length);
    }

    public static BigDecimal parseBigDecimal(char[] b, int off, int len) throws NumberFormatException {
        try {
            return new BigDecimal(b, off, len);
        } catch (NumberFormatException e) {
            throw _badBD(new String(b, off, len));
        }
    }

    private static NumberFormatException _badBD(String s) {
        return new NumberFormatException("Value \"" + s + "\" can not be represented as BigDecimal");
    }
}
