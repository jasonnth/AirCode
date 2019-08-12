package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.NonMonotonicSequenceException;

public class MathArrays {

    public enum OrderDirection {
        INCREASING,
        DECREASING
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        r8 = r10[r3];
        r3 = r3 + 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkOrder(double[] r10, org.apache.commons.math3.util.MathArrays.OrderDirection r11, boolean r12, boolean r13) throws org.apache.commons.math3.exception.NonMonotonicSequenceException {
        /*
            r0 = 0
            r8 = r10[r0]
            int r6 = r10.length
            r3 = 1
        L_0x0005:
            if (r3 >= r6) goto L_0x0020
            int[] r1 = org.apache.commons.math3.util.MathArrays.C52132.f6325x3cbf57fc
            int r2 = r11.ordinal()
            r1 = r1[r2]
            switch(r1) {
                case 1: goto L_0x0018;
                case 2: goto L_0x002f;
                default: goto L_0x0012;
            }
        L_0x0012:
            org.apache.commons.math3.exception.MathInternalError r0 = new org.apache.commons.math3.exception.MathInternalError
            r0.<init>()
            throw r0
        L_0x0018:
            if (r12 == 0) goto L_0x0024
            r4 = r10[r3]
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 > 0) goto L_0x002a
        L_0x0020:
            if (r3 != r6) goto L_0x003f
            r0 = 1
        L_0x0023:
            return r0
        L_0x0024:
            r4 = r10[r3]
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 < 0) goto L_0x0020
        L_0x002a:
            r8 = r10[r3]
            int r3 = r3 + 1
            goto L_0x0005
        L_0x002f:
            if (r12 == 0) goto L_0x0038
            r4 = r10[r3]
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 < 0) goto L_0x002a
            goto L_0x0020
        L_0x0038:
            r4 = r10[r3]
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x002a
            goto L_0x0020
        L_0x003f:
            if (r13 == 0) goto L_0x0023
            org.apache.commons.math3.exception.NonMonotonicSequenceException r0 = new org.apache.commons.math3.exception.NonMonotonicSequenceException
            r4 = r10[r3]
            java.lang.Double r1 = java.lang.Double.valueOf(r4)
            java.lang.Double r2 = java.lang.Double.valueOf(r8)
            r4 = r11
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.MathArrays.checkOrder(double[], org.apache.commons.math3.util.MathArrays$OrderDirection, boolean, boolean):boolean");
    }

    public static void checkOrder(double[] val, OrderDirection dir, boolean strict) throws NonMonotonicSequenceException {
        checkOrder(val, dir, strict, true);
    }

    public static void checkOrder(double[] val) throws NonMonotonicSequenceException {
        checkOrder(val, OrderDirection.INCREASING, true);
    }
}
