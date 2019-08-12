package org.spongycastle.math.raw;

public abstract class Mont256 {

    /* renamed from: M */
    private static final long f7057M = 4294967295L;

    public static int inverse32(int x) {
        int z = x;
        int z2 = z * (2 - (x * z));
        int z3 = z2 * (2 - (x * z2));
        int z4 = z3 * (2 - (x * z3));
        return z4 * (2 - (x * z4));
    }

    public static void multAdd(int[] x, int[] y, int[] z, int[] m, int mInv32) {
        int z_8 = 0;
        long y_0 = ((long) y[0]) & f7057M;
        for (int i = 0; i < 8; i++) {
            long x_i = ((long) x[i]) & f7057M;
            long prod1 = x_i * y_0;
            long carry = (f7057M & prod1) + (((long) z[0]) & f7057M);
            long t = ((long) (((int) carry) * mInv32)) & f7057M;
            long prod2 = t * (((long) m[0]) & f7057M);
            long carry2 = ((carry + (f7057M & prod2)) >>> 32) + (prod1 >>> 32) + (prod2 >>> 32);
            for (int j = 1; j < 8; j++) {
                long prod12 = x_i * (((long) y[j]) & f7057M);
                long prod22 = t * (((long) m[j]) & f7057M);
                long carry3 = carry2 + (f7057M & prod12) + (f7057M & prod22) + (((long) z[j]) & f7057M);
                z[j - 1] = (int) carry3;
                carry2 = (carry3 >>> 32) + (prod12 >>> 32) + (prod22 >>> 32);
            }
            long carry4 = carry2 + (((long) z_8) & f7057M);
            z[7] = (int) carry4;
            z_8 = (int) (carry4 >>> 32);
        }
        if (z_8 != 0 || Nat256.gte(z, m)) {
            Nat256.sub(z, m, z);
        }
    }

    public static void multAddXF(int[] x, int[] y, int[] z, int[] m) {
        int z_8 = 0;
        long y_0 = ((long) y[0]) & f7057M;
        for (int i = 0; i < 8; i++) {
            long x_i = ((long) x[i]) & f7057M;
            long carry = (x_i * y_0) + (((long) z[0]) & f7057M);
            long t = carry & f7057M;
            long carry2 = (carry >>> 32) + t;
            for (int j = 1; j < 8; j++) {
                long prod1 = x_i * (((long) y[j]) & f7057M);
                long prod2 = t * (((long) m[j]) & f7057M);
                long carry3 = carry2 + (f7057M & prod1) + (f7057M & prod2) + (((long) z[j]) & f7057M);
                z[j - 1] = (int) carry3;
                carry2 = (carry3 >>> 32) + (prod1 >>> 32) + (prod2 >>> 32);
            }
            long carry4 = carry2 + (((long) z_8) & f7057M);
            z[7] = (int) carry4;
            z_8 = (int) (carry4 >>> 32);
        }
        if (z_8 != 0 || Nat256.gte(z, m)) {
            Nat256.sub(z, m, z);
        }
    }

    public static void reduce(int[] z, int[] m, int mInv32) {
        for (int i = 0; i < 8; i++) {
            int z_0 = z[0];
            long t = ((long) (z_0 * mInv32)) & f7057M;
            long carry = (((((long) m[0]) & f7057M) * t) + (((long) z_0) & f7057M)) >>> 32;
            for (int j = 1; j < 8; j++) {
                long carry2 = carry + ((((long) m[j]) & f7057M) * t) + (((long) z[j]) & f7057M);
                z[j - 1] = (int) carry2;
                carry = carry2 >>> 32;
            }
            z[7] = (int) carry;
        }
        if (Nat256.gte(z, m)) {
            Nat256.sub(z, m, z);
        }
    }

    public static void reduceXF(int[] z, int[] m) {
        for (int i = 0; i < 8; i++) {
            long t = ((long) z[0]) & f7057M;
            long carry = t;
            for (int j = 1; j < 8; j++) {
                long carry2 = carry + ((((long) m[j]) & f7057M) * t) + (((long) z[j]) & f7057M);
                z[j - 1] = (int) carry2;
                carry = carry2 >>> 32;
            }
            z[7] = (int) carry;
        }
        if (Nat256.gte(z, m)) {
            Nat256.sub(z, m, z);
        }
    }
}
