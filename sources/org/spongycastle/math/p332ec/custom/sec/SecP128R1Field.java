package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat128;
import org.spongycastle.math.raw.Nat256;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP128R1Field */
public class SecP128R1Field {

    /* renamed from: M */
    private static final long f6985M = 4294967295L;

    /* renamed from: P */
    static final int[] f6986P = {-1, -1, -1, -3};

    /* renamed from: P3 */
    private static final int f6987P3 = -3;
    static final int[] PExt = {1, 0, 0, 4, -2, -1, 3, -4};
    private static final int PExt7 = -4;
    private static final int[] PExtInv = {-1, -1, -1, -5, 1, 0, -4, 3};

    public static void add(int[] x, int[] y, int[] z) {
        if (Nat128.add(x, y, z) != 0 || (z[3] == -3 && Nat128.gte(z, f6986P))) {
            addPInvTo(z);
        }
    }

    public static void addExt(int[] xx, int[] yy, int[] zz) {
        if (Nat256.add(xx, yy, zz) != 0 || (zz[7] == -4 && Nat256.gte(zz, PExt))) {
            Nat.addTo(PExtInv.length, PExtInv, zz);
        }
    }

    public static void addOne(int[] x, int[] z) {
        if (Nat.inc(4, x, z) != 0 || (z[3] == -3 && Nat128.gte(z, f6986P))) {
            addPInvTo(z);
        }
    }

    public static int[] fromBigInteger(BigInteger x) {
        int[] z = Nat128.fromBigInteger(x);
        if (z[3] == -3 && Nat128.gte(z, f6986P)) {
            Nat128.subFrom(f6986P, z);
        }
        return z;
    }

    public static void half(int[] x, int[] z) {
        if ((x[0] & 1) == 0) {
            Nat.shiftDownBit(4, x, 0, z);
        } else {
            Nat.shiftDownBit(4, z, Nat128.add(x, f6986P, z));
        }
    }

    public static void multiply(int[] x, int[] y, int[] z) {
        int[] tt = Nat128.createExt();
        Nat128.mul(x, y, tt);
        reduce(tt, z);
    }

    public static void multiplyAddToExt(int[] x, int[] y, int[] zz) {
        if (Nat128.mulAddTo(x, y, zz) != 0 || (zz[7] == -4 && Nat256.gte(zz, PExt))) {
            Nat.addTo(PExtInv.length, PExtInv, zz);
        }
    }

    public static void negate(int[] x, int[] z) {
        if (Nat128.isZero(x)) {
            Nat128.zero(z);
        } else {
            Nat128.sub(f6986P, x, z);
        }
    }

    public static void reduce(int[] xx, int[] z) {
        long x7 = ((long) xx[7]) & f6985M;
        long x6 = (((long) xx[6]) & f6985M) + (x7 << 1);
        long x2 = (((long) xx[2]) & f6985M) + x6;
        long x5 = (((long) xx[5]) & f6985M) + (x6 << 1);
        long x1 = (((long) xx[1]) & f6985M) + x5;
        long x4 = (((long) xx[4]) & f6985M) + (x5 << 1);
        long x0 = (((long) xx[0]) & f6985M) + x4;
        long x3 = (((long) xx[3]) & f6985M) + x7 + (x4 << 1);
        z[0] = (int) x0;
        long x12 = x1 + (x0 >>> 32);
        z[1] = (int) x12;
        long x22 = x2 + (x12 >>> 32);
        z[2] = (int) x22;
        long x32 = x3 + (x22 >>> 32);
        z[3] = (int) x32;
        reduce32((int) (x32 >>> 32), z);
    }

    public static void reduce32(int x, int[] z) {
        while (x != 0) {
            long x4 = ((long) x) & f6985M;
            long c = (((long) z[0]) & f6985M) + x4;
            z[0] = (int) c;
            long c2 = c >> 32;
            if (c2 != 0) {
                long c3 = c2 + (((long) z[1]) & f6985M);
                z[1] = (int) c3;
                long c4 = (c3 >> 32) + (((long) z[2]) & f6985M);
                z[2] = (int) c4;
                c2 = c4 >> 32;
            }
            long c5 = c2 + (((long) z[3]) & f6985M) + (x4 << 1);
            z[3] = (int) c5;
            x = (int) (c5 >> 32);
        }
    }

    public static void square(int[] x, int[] z) {
        int[] tt = Nat128.createExt();
        Nat128.square(x, tt);
        reduce(tt, z);
    }

    public static void squareN(int[] x, int n, int[] z) {
        int[] tt = Nat128.createExt();
        Nat128.square(x, tt);
        reduce(tt, z);
        while (true) {
            n--;
            if (n > 0) {
                Nat128.square(z, tt);
                reduce(tt, z);
            } else {
                return;
            }
        }
    }

    public static void subtract(int[] x, int[] y, int[] z) {
        if (Nat128.sub(x, y, z) != 0) {
            subPInvFrom(z);
        }
    }

    public static void subtractExt(int[] xx, int[] yy, int[] zz) {
        if (Nat.sub(10, xx, yy, zz) != 0) {
            Nat.subFrom(PExtInv.length, PExtInv, zz);
        }
    }

    public static void twice(int[] x, int[] z) {
        if (Nat.shiftUpBit(4, x, 0, z) != 0 || (z[3] == -3 && Nat128.gte(z, f6986P))) {
            addPInvTo(z);
        }
    }

    private static void addPInvTo(int[] z) {
        long c = (((long) z[0]) & f6985M) + 1;
        z[0] = (int) c;
        long c2 = c >> 32;
        if (c2 != 0) {
            long c3 = c2 + (((long) z[1]) & f6985M);
            z[1] = (int) c3;
            long c4 = (c3 >> 32) + (((long) z[2]) & f6985M);
            z[2] = (int) c4;
            c2 = c4 >> 32;
        }
        z[3] = (int) (c2 + (((long) z[3]) & f6985M) + 2);
    }

    private static void subPInvFrom(int[] z) {
        long c = (((long) z[0]) & f6985M) - 1;
        z[0] = (int) c;
        long c2 = c >> 32;
        if (c2 != 0) {
            long c3 = c2 + (((long) z[1]) & f6985M);
            z[1] = (int) c3;
            long c4 = (c3 >> 32) + (((long) z[2]) & f6985M);
            z[2] = (int) c4;
            c2 = c4 >> 32;
        }
        z[3] = (int) (c2 + ((((long) z[3]) & f6985M) - 2));
    }
}
