package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat192;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192R1Field */
public class SecP192R1Field {

    /* renamed from: M */
    private static final long f7008M = 4294967295L;

    /* renamed from: P */
    static final int[] f7009P = {-1, -1, -2, -1, -1, -1};

    /* renamed from: P5 */
    private static final int f7010P5 = -1;
    static final int[] PExt = {1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};
    private static final int PExt11 = -1;
    private static final int[] PExtInv = {-1, -1, -3, -1, -2, -1, 1, 0, 2};

    public static void add(int[] x, int[] y, int[] z) {
        if (Nat192.add(x, y, z) != 0 || (z[5] == -1 && Nat192.gte(z, f7009P))) {
            addPInvTo(z);
        }
    }

    public static void addExt(int[] xx, int[] yy, int[] zz) {
        if ((Nat.add(12, xx, yy, zz) != 0 || (zz[11] == -1 && Nat.gte(12, zz, PExt))) && Nat.addTo(PExtInv.length, PExtInv, zz) != 0) {
            Nat.incAt(12, zz, PExtInv.length);
        }
    }

    public static void addOne(int[] x, int[] z) {
        if (Nat.inc(6, x, z) != 0 || (z[5] == -1 && Nat192.gte(z, f7009P))) {
            addPInvTo(z);
        }
    }

    public static int[] fromBigInteger(BigInteger x) {
        int[] z = Nat192.fromBigInteger(x);
        if (z[5] == -1 && Nat192.gte(z, f7009P)) {
            Nat192.subFrom(f7009P, z);
        }
        return z;
    }

    public static void half(int[] x, int[] z) {
        if ((x[0] & 1) == 0) {
            Nat.shiftDownBit(6, x, 0, z);
        } else {
            Nat.shiftDownBit(6, z, Nat192.add(x, f7009P, z));
        }
    }

    public static void multiply(int[] x, int[] y, int[] z) {
        int[] tt = Nat192.createExt();
        Nat192.mul(x, y, tt);
        reduce(tt, z);
    }

    public static void multiplyAddToExt(int[] x, int[] y, int[] zz) {
        if ((Nat192.mulAddTo(x, y, zz) != 0 || (zz[11] == -1 && Nat.gte(12, zz, PExt))) && Nat.addTo(PExtInv.length, PExtInv, zz) != 0) {
            Nat.incAt(12, zz, PExtInv.length);
        }
    }

    public static void negate(int[] x, int[] z) {
        if (Nat192.isZero(x)) {
            Nat192.zero(z);
        } else {
            Nat192.sub(f7009P, x, z);
        }
    }

    public static void reduce(int[] xx, int[] z) {
        long xx06 = ((long) xx[6]) & f7008M;
        long xx07 = ((long) xx[7]) & f7008M;
        long xx08 = ((long) xx[8]) & f7008M;
        long xx09 = ((long) xx[9]) & f7008M;
        long t0 = xx06 + (((long) xx[10]) & f7008M);
        long t1 = xx07 + (((long) xx[11]) & f7008M);
        long cc = 0 + (((long) xx[0]) & f7008M) + t0;
        int z0 = (int) cc;
        long cc2 = (cc >> 32) + (((long) xx[1]) & f7008M) + t1;
        z[1] = (int) cc2;
        long t02 = t0 + xx08;
        long t12 = t1 + xx09;
        long cc3 = (cc2 >> 32) + (((long) xx[2]) & f7008M) + t02;
        long z2 = cc3 & f7008M;
        long cc4 = (cc3 >> 32) + (((long) xx[3]) & f7008M) + t12;
        z[3] = (int) cc4;
        long t13 = t12 - xx07;
        long cc5 = (cc4 >> 32) + (((long) xx[4]) & f7008M) + (t02 - xx06);
        z[4] = (int) cc5;
        long cc6 = (cc5 >> 32) + (((long) xx[5]) & f7008M) + t13;
        z[5] = (int) cc6;
        long cc7 = cc6 >> 32;
        long z22 = z2 + cc7;
        long cc8 = cc7 + (((long) z0) & f7008M);
        z[0] = (int) cc8;
        long cc9 = cc8 >> 32;
        if (cc9 != 0) {
            long cc10 = cc9 + (((long) z[1]) & f7008M);
            z[1] = (int) cc10;
            z22 += cc10 >> 32;
        }
        z[2] = (int) z22;
        if (((z22 >> 32) != 0 && Nat.incAt(6, z, 3) != 0) || (z[5] == -1 && Nat192.gte(z, f7009P))) {
            addPInvTo(z);
        }
    }

    public static void reduce32(int x, int[] z) {
        long cc = 0;
        if (x != 0) {
            long xx06 = ((long) x) & f7008M;
            long cc2 = 0 + (((long) z[0]) & f7008M) + xx06;
            z[0] = (int) cc2;
            long cc3 = cc2 >> 32;
            if (cc3 != 0) {
                long cc4 = cc3 + (((long) z[1]) & f7008M);
                z[1] = (int) cc4;
                cc3 = cc4 >> 32;
            }
            long cc5 = cc3 + (((long) z[2]) & f7008M) + xx06;
            z[2] = (int) cc5;
            cc = cc5 >> 32;
        }
        if ((cc != 0 && Nat.incAt(6, z, 3) != 0) || (z[5] == -1 && Nat192.gte(z, f7009P))) {
            addPInvTo(z);
        }
    }

    public static void square(int[] x, int[] z) {
        int[] tt = Nat192.createExt();
        Nat192.square(x, tt);
        reduce(tt, z);
    }

    public static void squareN(int[] x, int n, int[] z) {
        int[] tt = Nat192.createExt();
        Nat192.square(x, tt);
        reduce(tt, z);
        while (true) {
            n--;
            if (n > 0) {
                Nat192.square(z, tt);
                reduce(tt, z);
            } else {
                return;
            }
        }
    }

    public static void subtract(int[] x, int[] y, int[] z) {
        if (Nat192.sub(x, y, z) != 0) {
            subPInvFrom(z);
        }
    }

    public static void subtractExt(int[] xx, int[] yy, int[] zz) {
        if (Nat.sub(12, xx, yy, zz) != 0 && Nat.subFrom(PExtInv.length, PExtInv, zz) != 0) {
            Nat.decAt(12, zz, PExtInv.length);
        }
    }

    public static void twice(int[] x, int[] z) {
        if (Nat.shiftUpBit(6, x, 0, z) != 0 || (z[5] == -1 && Nat192.gte(z, f7009P))) {
            addPInvTo(z);
        }
    }

    private static void addPInvTo(int[] z) {
        long c = (((long) z[0]) & f7008M) + 1;
        z[0] = (int) c;
        long c2 = c >> 32;
        if (c2 != 0) {
            long c3 = c2 + (((long) z[1]) & f7008M);
            z[1] = (int) c3;
            c2 = c3 >> 32;
        }
        long c4 = c2 + (((long) z[2]) & f7008M) + 1;
        z[2] = (int) c4;
        if ((c4 >> 32) != 0) {
            Nat.incAt(6, z, 3);
        }
    }

    private static void subPInvFrom(int[] z) {
        long c = (((long) z[0]) & f7008M) - 1;
        z[0] = (int) c;
        long c2 = c >> 32;
        if (c2 != 0) {
            long c3 = c2 + (((long) z[1]) & f7008M);
            z[1] = (int) c3;
            c2 = c3 >> 32;
        }
        long c4 = c2 + ((((long) z[2]) & f7008M) - 1);
        z[2] = (int) c4;
        if ((c4 >> 32) != 0) {
            Nat.decAt(6, z, 3);
        }
    }
}
