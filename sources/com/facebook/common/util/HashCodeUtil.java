package com.facebook.common.util;

public class HashCodeUtil {

    /* renamed from: X */
    private static final int f3116X = 31;

    public static int hashCode(Object o1) {
        int hashCode;
        if (o1 == null) {
            hashCode = 0;
        } else {
            hashCode = o1.hashCode();
        }
        return hashCode(hashCode);
    }

    public static int hashCode(Object o1, Object o2) {
        int hashCode;
        int i = 0;
        if (o1 == null) {
            hashCode = 0;
        } else {
            hashCode = o1.hashCode();
        }
        if (o2 != null) {
            i = o2.hashCode();
        }
        return hashCode(hashCode, i);
    }

    public static int hashCode(Object o1, Object o2, Object o3) {
        int hashCode;
        int hashCode2;
        int i = 0;
        if (o1 == null) {
            hashCode = 0;
        } else {
            hashCode = o1.hashCode();
        }
        if (o2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = o2.hashCode();
        }
        if (o3 != null) {
            i = o3.hashCode();
        }
        return hashCode(hashCode, hashCode2, i);
    }

    public static int hashCode(Object o1, Object o2, Object o3, Object o4) {
        int hashCode;
        int hashCode2;
        int hashCode3;
        int i = 0;
        if (o1 == null) {
            hashCode = 0;
        } else {
            hashCode = o1.hashCode();
        }
        if (o2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = o2.hashCode();
        }
        if (o3 == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = o3.hashCode();
        }
        if (o4 != null) {
            i = o4.hashCode();
        }
        return hashCode(hashCode, hashCode2, hashCode3, i);
    }

    public static int hashCode(Object o1, Object o2, Object o3, Object o4, Object o5) {
        int hashCode;
        int hashCode2;
        int hashCode3;
        int hashCode4;
        int i = 0;
        if (o1 == null) {
            hashCode = 0;
        } else {
            hashCode = o1.hashCode();
        }
        if (o2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = o2.hashCode();
        }
        if (o3 == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = o3.hashCode();
        }
        if (o4 == null) {
            hashCode4 = 0;
        } else {
            hashCode4 = o4.hashCode();
        }
        if (o5 != null) {
            i = o5.hashCode();
        }
        return hashCode(hashCode, hashCode2, hashCode3, hashCode4, i);
    }

    public static int hashCode(Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
        int hashCode;
        int hashCode2;
        int hashCode3;
        int hashCode4;
        int hashCode5;
        int i = 0;
        if (o1 == null) {
            hashCode = 0;
        } else {
            hashCode = o1.hashCode();
        }
        if (o2 == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = o2.hashCode();
        }
        if (o3 == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = o3.hashCode();
        }
        if (o4 == null) {
            hashCode4 = 0;
        } else {
            hashCode4 = o4.hashCode();
        }
        if (o5 == null) {
            hashCode5 = 0;
        } else {
            hashCode5 = o5.hashCode();
        }
        if (o6 != null) {
            i = o6.hashCode();
        }
        return hashCode(hashCode, hashCode2, hashCode3, hashCode4, hashCode5, i);
    }

    public static int hashCode(int i1) {
        return i1 + 31;
    }

    public static int hashCode(int i1, int i2) {
        return ((i1 + 31) * 31) + i2;
    }

    public static int hashCode(int i1, int i2, int i3) {
        return ((((i1 + 31) * 31) + i2) * 31) + i3;
    }

    public static int hashCode(int i1, int i2, int i3, int i4) {
        return ((((((i1 + 31) * 31) + i2) * 31) + i3) * 31) + i4;
    }

    public static int hashCode(int i1, int i2, int i3, int i4, int i5) {
        return ((((((((i1 + 31) * 31) + i2) * 31) + i3) * 31) + i4) * 31) + i5;
    }

    public static int hashCode(int i1, int i2, int i3, int i4, int i5, int i6) {
        return ((((((((((i1 + 31) * 31) + i2) * 31) + i3) * 31) + i4) * 31) + i5) * 31) + i6;
    }
}
