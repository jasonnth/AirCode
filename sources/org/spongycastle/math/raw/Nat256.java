package org.spongycastle.math.raw;

import java.math.BigInteger;
import org.spongycastle.util.Pack;

public abstract class Nat256 {

    /* renamed from: M */
    private static final long f7063M = 4294967295L;

    public static int add(int[] x, int[] y, int[] z) {
        long c = 0 + (((long) x[0]) & f7063M) + (((long) y[0]) & f7063M);
        z[0] = (int) c;
        long c2 = (c >>> 32) + (((long) x[1]) & f7063M) + (((long) y[1]) & f7063M);
        z[1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) x[2]) & f7063M) + (((long) y[2]) & f7063M);
        z[2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) x[3]) & f7063M) + (((long) y[3]) & f7063M);
        z[3] = (int) c4;
        long c5 = (c4 >>> 32) + (((long) x[4]) & f7063M) + (((long) y[4]) & f7063M);
        z[4] = (int) c5;
        long c6 = (c5 >>> 32) + (((long) x[5]) & f7063M) + (((long) y[5]) & f7063M);
        z[5] = (int) c6;
        long c7 = (c6 >>> 32) + (((long) x[6]) & f7063M) + (((long) y[6]) & f7063M);
        z[6] = (int) c7;
        long c8 = (c7 >>> 32) + (((long) x[7]) & f7063M) + (((long) y[7]) & f7063M);
        z[7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int add(int[] x, int xOff, int[] y, int yOff, int[] z, int zOff) {
        long c = 0 + (((long) x[xOff + 0]) & f7063M) + (((long) y[yOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long c2 = (c >>> 32) + (((long) x[xOff + 1]) & f7063M) + (((long) y[yOff + 1]) & f7063M);
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) x[xOff + 2]) & f7063M) + (((long) y[yOff + 2]) & f7063M);
        z[zOff + 2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) x[xOff + 3]) & f7063M) + (((long) y[yOff + 3]) & f7063M);
        z[zOff + 3] = (int) c4;
        long c5 = (c4 >>> 32) + (((long) x[xOff + 4]) & f7063M) + (((long) y[yOff + 4]) & f7063M);
        z[zOff + 4] = (int) c5;
        long c6 = (c5 >>> 32) + (((long) x[xOff + 5]) & f7063M) + (((long) y[yOff + 5]) & f7063M);
        z[zOff + 5] = (int) c6;
        long c7 = (c6 >>> 32) + (((long) x[xOff + 6]) & f7063M) + (((long) y[yOff + 6]) & f7063M);
        z[zOff + 6] = (int) c7;
        long c8 = (c7 >>> 32) + (((long) x[xOff + 7]) & f7063M) + (((long) y[yOff + 7]) & f7063M);
        z[zOff + 7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int addBothTo(int[] x, int[] y, int[] z) {
        long c = 0 + (((long) x[0]) & f7063M) + (((long) y[0]) & f7063M) + (((long) z[0]) & f7063M);
        z[0] = (int) c;
        long c2 = (c >>> 32) + (((long) x[1]) & f7063M) + (((long) y[1]) & f7063M) + (((long) z[1]) & f7063M);
        z[1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) x[2]) & f7063M) + (((long) y[2]) & f7063M) + (((long) z[2]) & f7063M);
        z[2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) x[3]) & f7063M) + (((long) y[3]) & f7063M) + (((long) z[3]) & f7063M);
        z[3] = (int) c4;
        long c5 = (c4 >>> 32) + (((long) x[4]) & f7063M) + (((long) y[4]) & f7063M) + (((long) z[4]) & f7063M);
        z[4] = (int) c5;
        long c6 = (c5 >>> 32) + (((long) x[5]) & f7063M) + (((long) y[5]) & f7063M) + (((long) z[5]) & f7063M);
        z[5] = (int) c6;
        long c7 = (c6 >>> 32) + (((long) x[6]) & f7063M) + (((long) y[6]) & f7063M) + (((long) z[6]) & f7063M);
        z[6] = (int) c7;
        long c8 = (c7 >>> 32) + (((long) x[7]) & f7063M) + (((long) y[7]) & f7063M) + (((long) z[7]) & f7063M);
        z[7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int addBothTo(int[] x, int xOff, int[] y, int yOff, int[] z, int zOff) {
        long c = 0 + (((long) x[xOff + 0]) & f7063M) + (((long) y[yOff + 0]) & f7063M) + (((long) z[zOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long c2 = (c >>> 32) + (((long) x[xOff + 1]) & f7063M) + (((long) y[yOff + 1]) & f7063M) + (((long) z[zOff + 1]) & f7063M);
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) x[xOff + 2]) & f7063M) + (((long) y[yOff + 2]) & f7063M) + (((long) z[zOff + 2]) & f7063M);
        z[zOff + 2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) x[xOff + 3]) & f7063M) + (((long) y[yOff + 3]) & f7063M) + (((long) z[zOff + 3]) & f7063M);
        z[zOff + 3] = (int) c4;
        long c5 = (c4 >>> 32) + (((long) x[xOff + 4]) & f7063M) + (((long) y[yOff + 4]) & f7063M) + (((long) z[zOff + 4]) & f7063M);
        z[zOff + 4] = (int) c5;
        long c6 = (c5 >>> 32) + (((long) x[xOff + 5]) & f7063M) + (((long) y[yOff + 5]) & f7063M) + (((long) z[zOff + 5]) & f7063M);
        z[zOff + 5] = (int) c6;
        long c7 = (c6 >>> 32) + (((long) x[xOff + 6]) & f7063M) + (((long) y[yOff + 6]) & f7063M) + (((long) z[zOff + 6]) & f7063M);
        z[zOff + 6] = (int) c7;
        long c8 = (c7 >>> 32) + (((long) x[xOff + 7]) & f7063M) + (((long) y[yOff + 7]) & f7063M) + (((long) z[zOff + 7]) & f7063M);
        z[zOff + 7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int addTo(int[] x, int[] z) {
        long c = 0 + (((long) x[0]) & f7063M) + (((long) z[0]) & f7063M);
        z[0] = (int) c;
        long c2 = (c >>> 32) + (((long) x[1]) & f7063M) + (((long) z[1]) & f7063M);
        z[1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) x[2]) & f7063M) + (((long) z[2]) & f7063M);
        z[2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) x[3]) & f7063M) + (((long) z[3]) & f7063M);
        z[3] = (int) c4;
        long c5 = (c4 >>> 32) + (((long) x[4]) & f7063M) + (((long) z[4]) & f7063M);
        z[4] = (int) c5;
        long c6 = (c5 >>> 32) + (((long) x[5]) & f7063M) + (((long) z[5]) & f7063M);
        z[5] = (int) c6;
        long c7 = (c6 >>> 32) + (((long) x[6]) & f7063M) + (((long) z[6]) & f7063M);
        z[6] = (int) c7;
        long c8 = (c7 >>> 32) + (((long) x[7]) & f7063M) + (((long) z[7]) & f7063M);
        z[7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int addTo(int[] x, int xOff, int[] z, int zOff, int cIn) {
        long c = (((long) cIn) & f7063M) + (((long) x[xOff + 0]) & f7063M) + (((long) z[zOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long c2 = (c >>> 32) + (((long) x[xOff + 1]) & f7063M) + (((long) z[zOff + 1]) & f7063M);
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) x[xOff + 2]) & f7063M) + (((long) z[zOff + 2]) & f7063M);
        z[zOff + 2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) x[xOff + 3]) & f7063M) + (((long) z[zOff + 3]) & f7063M);
        z[zOff + 3] = (int) c4;
        long c5 = (c4 >>> 32) + (((long) x[xOff + 4]) & f7063M) + (((long) z[zOff + 4]) & f7063M);
        z[zOff + 4] = (int) c5;
        long c6 = (c5 >>> 32) + (((long) x[xOff + 5]) & f7063M) + (((long) z[zOff + 5]) & f7063M);
        z[zOff + 5] = (int) c6;
        long c7 = (c6 >>> 32) + (((long) x[xOff + 6]) & f7063M) + (((long) z[zOff + 6]) & f7063M);
        z[zOff + 6] = (int) c7;
        long c8 = (c7 >>> 32) + (((long) x[xOff + 7]) & f7063M) + (((long) z[zOff + 7]) & f7063M);
        z[zOff + 7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int addToEachOther(int[] u, int uOff, int[] v, int vOff) {
        long c = 0 + (((long) u[uOff + 0]) & f7063M) + (((long) v[vOff + 0]) & f7063M);
        u[uOff + 0] = (int) c;
        v[vOff + 0] = (int) c;
        long c2 = (c >>> 32) + (((long) u[uOff + 1]) & f7063M) + (((long) v[vOff + 1]) & f7063M);
        u[uOff + 1] = (int) c2;
        v[vOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) u[uOff + 2]) & f7063M) + (((long) v[vOff + 2]) & f7063M);
        u[uOff + 2] = (int) c3;
        v[vOff + 2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) u[uOff + 3]) & f7063M) + (((long) v[vOff + 3]) & f7063M);
        u[uOff + 3] = (int) c4;
        v[vOff + 3] = (int) c4;
        long c5 = (c4 >>> 32) + (((long) u[uOff + 4]) & f7063M) + (((long) v[vOff + 4]) & f7063M);
        u[uOff + 4] = (int) c5;
        v[vOff + 4] = (int) c5;
        long c6 = (c5 >>> 32) + (((long) u[uOff + 5]) & f7063M) + (((long) v[vOff + 5]) & f7063M);
        u[uOff + 5] = (int) c6;
        v[vOff + 5] = (int) c6;
        long c7 = (c6 >>> 32) + (((long) u[uOff + 6]) & f7063M) + (((long) v[vOff + 6]) & f7063M);
        u[uOff + 6] = (int) c7;
        v[vOff + 6] = (int) c7;
        long c8 = (c7 >>> 32) + (((long) u[uOff + 7]) & f7063M) + (((long) v[vOff + 7]) & f7063M);
        u[uOff + 7] = (int) c8;
        v[vOff + 7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static void copy(int[] x, int[] z) {
        z[0] = x[0];
        z[1] = x[1];
        z[2] = x[2];
        z[3] = x[3];
        z[4] = x[4];
        z[5] = x[5];
        z[6] = x[6];
        z[7] = x[7];
    }

    public static void copy64(long[] x, long[] z) {
        z[0] = x[0];
        z[1] = x[1];
        z[2] = x[2];
        z[3] = x[3];
    }

    public static int[] create() {
        return new int[8];
    }

    public static long[] create64() {
        return new long[4];
    }

    public static int[] createExt() {
        return new int[16];
    }

    public static long[] createExt64() {
        return new long[8];
    }

    public static boolean diff(int[] x, int xOff, int[] y, int yOff, int[] z, int zOff) {
        boolean pos = gte(x, xOff, y, yOff);
        if (pos) {
            sub(x, xOff, y, yOff, z, zOff);
        } else {
            sub(y, yOff, x, xOff, z, zOff);
        }
        return pos;
    }

    /* renamed from: eq */
    public static boolean m4046eq(int[] x, int[] y) {
        for (int i = 7; i >= 0; i--) {
            if (x[i] != y[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] x, long[] y) {
        for (int i = 3; i >= 0; i--) {
            if (x[i] != y[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger x) {
        if (x.signum() < 0 || x.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] z = create();
        int i = 0;
        while (x.signum() != 0) {
            int i2 = i + 1;
            z[i] = x.intValue();
            x = x.shiftRight(32);
            i = i2;
        }
        return z;
    }

    public static long[] fromBigInteger64(BigInteger x) {
        if (x.signum() < 0 || x.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        long[] z = create64();
        int i = 0;
        while (x.signum() != 0) {
            int i2 = i + 1;
            z[i] = x.longValue();
            x = x.shiftRight(64);
            i = i2;
        }
        return z;
    }

    public static int getBit(int[] x, int bit) {
        if (bit == 0) {
            return x[0] & 1;
        }
        if ((bit & 255) != bit) {
            return 0;
        }
        return (x[bit >>> 5] >>> (bit & 31)) & 1;
    }

    public static boolean gte(int[] x, int[] y) {
        for (int i = 7; i >= 0; i--) {
            int x_i = x[i] ^ Integer.MIN_VALUE;
            int y_i = y[i] ^ Integer.MIN_VALUE;
            if (x_i < y_i) {
                return false;
            }
            if (x_i > y_i) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] x, int xOff, int[] y, int yOff) {
        for (int i = 7; i >= 0; i--) {
            int x_i = x[xOff + i] ^ Integer.MIN_VALUE;
            int y_i = y[yOff + i] ^ Integer.MIN_VALUE;
            if (x_i < y_i) {
                return false;
            }
            if (x_i > y_i) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] x) {
        if (x[0] != 1) {
            return false;
        }
        for (int i = 1; i < 8; i++) {
            if (x[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] x) {
        if (x[0] != 1) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (x[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] x) {
        for (int i = 0; i < 8; i++) {
            if (x[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] x) {
        for (int i = 0; i < 4; i++) {
            if (x[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] x, int[] y, int[] zz) {
        long y_0 = ((long) y[0]) & f7063M;
        long y_1 = ((long) y[1]) & f7063M;
        long y_2 = ((long) y[2]) & f7063M;
        long y_3 = ((long) y[3]) & f7063M;
        long y_4 = ((long) y[4]) & f7063M;
        long y_5 = ((long) y[5]) & f7063M;
        long y_6 = ((long) y[6]) & f7063M;
        long y_7 = ((long) y[7]) & f7063M;
        long x_0 = ((long) x[0]) & f7063M;
        long c = 0 + (x_0 * y_0);
        zz[0] = (int) c;
        long c2 = (c >>> 32) + (x_0 * y_1);
        zz[1] = (int) c2;
        long c3 = (c2 >>> 32) + (x_0 * y_2);
        zz[2] = (int) c3;
        long c4 = (c3 >>> 32) + (x_0 * y_3);
        zz[3] = (int) c4;
        long c5 = (c4 >>> 32) + (x_0 * y_4);
        zz[4] = (int) c5;
        long c6 = (c5 >>> 32) + (x_0 * y_5);
        zz[5] = (int) c6;
        long c7 = (c6 >>> 32) + (x_0 * y_6);
        zz[6] = (int) c7;
        long c8 = (c7 >>> 32) + (x_0 * y_7);
        zz[7] = (int) c8;
        zz[8] = (int) (c8 >>> 32);
        for (int i = 1; i < 8; i++) {
            long x_i = ((long) x[i]) & f7063M;
            long c9 = 0 + (x_i * y_0) + (((long) zz[i + 0]) & f7063M);
            zz[i + 0] = (int) c9;
            long c10 = (c9 >>> 32) + (x_i * y_1) + (((long) zz[i + 1]) & f7063M);
            zz[i + 1] = (int) c10;
            long c11 = (c10 >>> 32) + (x_i * y_2) + (((long) zz[i + 2]) & f7063M);
            zz[i + 2] = (int) c11;
            long c12 = (c11 >>> 32) + (x_i * y_3) + (((long) zz[i + 3]) & f7063M);
            zz[i + 3] = (int) c12;
            long c13 = (c12 >>> 32) + (x_i * y_4) + (((long) zz[i + 4]) & f7063M);
            zz[i + 4] = (int) c13;
            long c14 = (c13 >>> 32) + (x_i * y_5) + (((long) zz[i + 5]) & f7063M);
            zz[i + 5] = (int) c14;
            long c15 = (c14 >>> 32) + (x_i * y_6) + (((long) zz[i + 6]) & f7063M);
            zz[i + 6] = (int) c15;
            long c16 = (c15 >>> 32) + (x_i * y_7) + (((long) zz[i + 7]) & f7063M);
            zz[i + 7] = (int) c16;
            zz[i + 8] = (int) (c16 >>> 32);
        }
    }

    public static void mul(int[] x, int xOff, int[] y, int yOff, int[] zz, int zzOff) {
        long y_0 = ((long) y[yOff + 0]) & f7063M;
        long y_1 = ((long) y[yOff + 1]) & f7063M;
        long y_2 = ((long) y[yOff + 2]) & f7063M;
        long y_3 = ((long) y[yOff + 3]) & f7063M;
        long y_4 = ((long) y[yOff + 4]) & f7063M;
        long y_5 = ((long) y[yOff + 5]) & f7063M;
        long y_6 = ((long) y[yOff + 6]) & f7063M;
        long y_7 = ((long) y[yOff + 7]) & f7063M;
        long x_0 = ((long) x[xOff + 0]) & f7063M;
        long c = 0 + (x_0 * y_0);
        zz[zzOff + 0] = (int) c;
        long c2 = (c >>> 32) + (x_0 * y_1);
        zz[zzOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (x_0 * y_2);
        zz[zzOff + 2] = (int) c3;
        long c4 = (c3 >>> 32) + (x_0 * y_3);
        zz[zzOff + 3] = (int) c4;
        long c5 = (c4 >>> 32) + (x_0 * y_4);
        zz[zzOff + 4] = (int) c5;
        long c6 = (c5 >>> 32) + (x_0 * y_5);
        zz[zzOff + 5] = (int) c6;
        long c7 = (c6 >>> 32) + (x_0 * y_6);
        zz[zzOff + 6] = (int) c7;
        long c8 = (c7 >>> 32) + (x_0 * y_7);
        zz[zzOff + 7] = (int) c8;
        zz[zzOff + 8] = (int) (c8 >>> 32);
        for (int i = 1; i < 8; i++) {
            zzOff++;
            long x_i = ((long) x[xOff + i]) & f7063M;
            long c9 = 0 + (x_i * y_0) + (((long) zz[zzOff + 0]) & f7063M);
            zz[zzOff + 0] = (int) c9;
            long c10 = (c9 >>> 32) + (x_i * y_1) + (((long) zz[zzOff + 1]) & f7063M);
            zz[zzOff + 1] = (int) c10;
            long c11 = (c10 >>> 32) + (x_i * y_2) + (((long) zz[zzOff + 2]) & f7063M);
            zz[zzOff + 2] = (int) c11;
            long c12 = (c11 >>> 32) + (x_i * y_3) + (((long) zz[zzOff + 3]) & f7063M);
            zz[zzOff + 3] = (int) c12;
            long c13 = (c12 >>> 32) + (x_i * y_4) + (((long) zz[zzOff + 4]) & f7063M);
            zz[zzOff + 4] = (int) c13;
            long c14 = (c13 >>> 32) + (x_i * y_5) + (((long) zz[zzOff + 5]) & f7063M);
            zz[zzOff + 5] = (int) c14;
            long c15 = (c14 >>> 32) + (x_i * y_6) + (((long) zz[zzOff + 6]) & f7063M);
            zz[zzOff + 6] = (int) c15;
            long c16 = (c15 >>> 32) + (x_i * y_7) + (((long) zz[zzOff + 7]) & f7063M);
            zz[zzOff + 7] = (int) c16;
            zz[zzOff + 8] = (int) (c16 >>> 32);
        }
    }

    public static int mulAddTo(int[] x, int[] y, int[] zz) {
        long y_0 = ((long) y[0]) & f7063M;
        long y_1 = ((long) y[1]) & f7063M;
        long y_2 = ((long) y[2]) & f7063M;
        long y_3 = ((long) y[3]) & f7063M;
        long y_4 = ((long) y[4]) & f7063M;
        long y_5 = ((long) y[5]) & f7063M;
        long y_6 = ((long) y[6]) & f7063M;
        long y_7 = ((long) y[7]) & f7063M;
        long zc = 0;
        for (int i = 0; i < 8; i++) {
            long x_i = ((long) x[i]) & f7063M;
            long c = 0 + (x_i * y_0) + (((long) zz[i + 0]) & f7063M);
            zz[i + 0] = (int) c;
            long c2 = (c >>> 32) + (x_i * y_1) + (((long) zz[i + 1]) & f7063M);
            zz[i + 1] = (int) c2;
            long c3 = (c2 >>> 32) + (x_i * y_2) + (((long) zz[i + 2]) & f7063M);
            zz[i + 2] = (int) c3;
            long c4 = (c3 >>> 32) + (x_i * y_3) + (((long) zz[i + 3]) & f7063M);
            zz[i + 3] = (int) c4;
            long c5 = (c4 >>> 32) + (x_i * y_4) + (((long) zz[i + 4]) & f7063M);
            zz[i + 4] = (int) c5;
            long c6 = (c5 >>> 32) + (x_i * y_5) + (((long) zz[i + 5]) & f7063M);
            zz[i + 5] = (int) c6;
            long c7 = (c6 >>> 32) + (x_i * y_6) + (((long) zz[i + 6]) & f7063M);
            zz[i + 6] = (int) c7;
            long c8 = (c7 >>> 32) + (x_i * y_7) + (((long) zz[i + 7]) & f7063M);
            zz[i + 7] = (int) c8;
            long c9 = (c8 >>> 32) + (((long) zz[i + 8]) & f7063M) + zc;
            zz[i + 8] = (int) c9;
            zc = c9 >>> 32;
        }
        return (int) zc;
    }

    public static int mulAddTo(int[] x, int xOff, int[] y, int yOff, int[] zz, int zzOff) {
        long y_0 = ((long) y[yOff + 0]) & f7063M;
        long y_1 = ((long) y[yOff + 1]) & f7063M;
        long y_2 = ((long) y[yOff + 2]) & f7063M;
        long y_3 = ((long) y[yOff + 3]) & f7063M;
        long y_4 = ((long) y[yOff + 4]) & f7063M;
        long y_5 = ((long) y[yOff + 5]) & f7063M;
        long y_6 = ((long) y[yOff + 6]) & f7063M;
        long y_7 = ((long) y[yOff + 7]) & f7063M;
        long zc = 0;
        for (int i = 0; i < 8; i++) {
            long x_i = ((long) x[xOff + i]) & f7063M;
            long c = 0 + (x_i * y_0) + (((long) zz[zzOff + 0]) & f7063M);
            zz[zzOff + 0] = (int) c;
            long c2 = (c >>> 32) + (x_i * y_1) + (((long) zz[zzOff + 1]) & f7063M);
            zz[zzOff + 1] = (int) c2;
            long c3 = (c2 >>> 32) + (x_i * y_2) + (((long) zz[zzOff + 2]) & f7063M);
            zz[zzOff + 2] = (int) c3;
            long c4 = (c3 >>> 32) + (x_i * y_3) + (((long) zz[zzOff + 3]) & f7063M);
            zz[zzOff + 3] = (int) c4;
            long c5 = (c4 >>> 32) + (x_i * y_4) + (((long) zz[zzOff + 4]) & f7063M);
            zz[zzOff + 4] = (int) c5;
            long c6 = (c5 >>> 32) + (x_i * y_5) + (((long) zz[zzOff + 5]) & f7063M);
            zz[zzOff + 5] = (int) c6;
            long c7 = (c6 >>> 32) + (x_i * y_6) + (((long) zz[zzOff + 6]) & f7063M);
            zz[zzOff + 6] = (int) c7;
            long c8 = (c7 >>> 32) + (x_i * y_7) + (((long) zz[zzOff + 7]) & f7063M);
            zz[zzOff + 7] = (int) c8;
            long c9 = (c8 >>> 32) + (((long) zz[zzOff + 8]) & f7063M) + zc;
            zz[zzOff + 8] = (int) c9;
            zc = c9 >>> 32;
            zzOff++;
        }
        return (int) zc;
    }

    public static long mul33Add(int w, int[] x, int xOff, int[] y, int yOff, int[] z, int zOff) {
        long wVal = ((long) w) & f7063M;
        long x0 = ((long) x[xOff + 0]) & f7063M;
        long c = 0 + (wVal * x0) + (((long) y[yOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long x1 = ((long) x[xOff + 1]) & f7063M;
        long c2 = (c >>> 32) + (wVal * x1) + x0 + (((long) y[yOff + 1]) & f7063M);
        z[zOff + 1] = (int) c2;
        long x2 = ((long) x[xOff + 2]) & f7063M;
        long c3 = (c2 >>> 32) + (wVal * x2) + x1 + (((long) y[yOff + 2]) & f7063M);
        z[zOff + 2] = (int) c3;
        long x3 = ((long) x[xOff + 3]) & f7063M;
        long c4 = (c3 >>> 32) + (wVal * x3) + x2 + (((long) y[yOff + 3]) & f7063M);
        z[zOff + 3] = (int) c4;
        long x4 = ((long) x[xOff + 4]) & f7063M;
        long c5 = (c4 >>> 32) + (wVal * x4) + x3 + (((long) y[yOff + 4]) & f7063M);
        z[zOff + 4] = (int) c5;
        long x5 = ((long) x[xOff + 5]) & f7063M;
        long c6 = (c5 >>> 32) + (wVal * x5) + x4 + (((long) y[yOff + 5]) & f7063M);
        z[zOff + 5] = (int) c6;
        long x6 = ((long) x[xOff + 6]) & f7063M;
        long c7 = (c6 >>> 32) + (wVal * x6) + x5 + (((long) y[yOff + 6]) & f7063M);
        z[zOff + 6] = (int) c7;
        long x7 = ((long) x[xOff + 7]) & f7063M;
        long c8 = (c7 >>> 32) + (wVal * x7) + x6 + (((long) y[yOff + 7]) & f7063M);
        z[zOff + 7] = (int) c8;
        return (c8 >>> 32) + x7;
    }

    public static int mulByWord(int x, int[] z) {
        long xVal = ((long) x) & f7063M;
        long c = 0 + ((((long) z[0]) & f7063M) * xVal);
        z[0] = (int) c;
        long c2 = (c >>> 32) + ((((long) z[1]) & f7063M) * xVal);
        z[1] = (int) c2;
        long c3 = (c2 >>> 32) + ((((long) z[2]) & f7063M) * xVal);
        z[2] = (int) c3;
        long c4 = (c3 >>> 32) + ((((long) z[3]) & f7063M) * xVal);
        z[3] = (int) c4;
        long c5 = (c4 >>> 32) + ((((long) z[4]) & f7063M) * xVal);
        z[4] = (int) c5;
        long c6 = (c5 >>> 32) + ((((long) z[5]) & f7063M) * xVal);
        z[5] = (int) c6;
        long c7 = (c6 >>> 32) + ((((long) z[6]) & f7063M) * xVal);
        z[6] = (int) c7;
        long c8 = (c7 >>> 32) + ((((long) z[7]) & f7063M) * xVal);
        z[7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int mulByWordAddTo(int x, int[] y, int[] z) {
        long xVal = ((long) x) & f7063M;
        long c = 0 + ((((long) z[0]) & f7063M) * xVal) + (((long) y[0]) & f7063M);
        z[0] = (int) c;
        long c2 = (c >>> 32) + ((((long) z[1]) & f7063M) * xVal) + (((long) y[1]) & f7063M);
        z[1] = (int) c2;
        long c3 = (c2 >>> 32) + ((((long) z[2]) & f7063M) * xVal) + (((long) y[2]) & f7063M);
        z[2] = (int) c3;
        long c4 = (c3 >>> 32) + ((((long) z[3]) & f7063M) * xVal) + (((long) y[3]) & f7063M);
        z[3] = (int) c4;
        long c5 = (c4 >>> 32) + ((((long) z[4]) & f7063M) * xVal) + (((long) y[4]) & f7063M);
        z[4] = (int) c5;
        long c6 = (c5 >>> 32) + ((((long) z[5]) & f7063M) * xVal) + (((long) y[5]) & f7063M);
        z[5] = (int) c6;
        long c7 = (c6 >>> 32) + ((((long) z[6]) & f7063M) * xVal) + (((long) y[6]) & f7063M);
        z[6] = (int) c7;
        long c8 = (c7 >>> 32) + ((((long) z[7]) & f7063M) * xVal) + (((long) y[7]) & f7063M);
        z[7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int mulWordAddTo(int x, int[] y, int yOff, int[] z, int zOff) {
        long xVal = ((long) x) & f7063M;
        long c = 0 + ((((long) y[yOff + 0]) & f7063M) * xVal) + (((long) z[zOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long c2 = (c >>> 32) + ((((long) y[yOff + 1]) & f7063M) * xVal) + (((long) z[zOff + 1]) & f7063M);
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + ((((long) y[yOff + 2]) & f7063M) * xVal) + (((long) z[zOff + 2]) & f7063M);
        z[zOff + 2] = (int) c3;
        long c4 = (c3 >>> 32) + ((((long) y[yOff + 3]) & f7063M) * xVal) + (((long) z[zOff + 3]) & f7063M);
        z[zOff + 3] = (int) c4;
        long c5 = (c4 >>> 32) + ((((long) y[yOff + 4]) & f7063M) * xVal) + (((long) z[zOff + 4]) & f7063M);
        z[zOff + 4] = (int) c5;
        long c6 = (c5 >>> 32) + ((((long) y[yOff + 5]) & f7063M) * xVal) + (((long) z[zOff + 5]) & f7063M);
        z[zOff + 5] = (int) c6;
        long c7 = (c6 >>> 32) + ((((long) y[yOff + 6]) & f7063M) * xVal) + (((long) z[zOff + 6]) & f7063M);
        z[zOff + 6] = (int) c7;
        long c8 = (c7 >>> 32) + ((((long) y[yOff + 7]) & f7063M) * xVal) + (((long) z[zOff + 7]) & f7063M);
        z[zOff + 7] = (int) c8;
        return (int) (c8 >>> 32);
    }

    public static int mul33DWordAdd(int x, long y, int[] z, int zOff) {
        long xVal = ((long) x) & f7063M;
        long y00 = y & f7063M;
        long c = 0 + (xVal * y00) + (((long) z[zOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long y01 = y >>> 32;
        long c2 = (c >>> 32) + (xVal * y01) + y00 + (((long) z[zOff + 1]) & f7063M);
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) z[zOff + 2]) & f7063M) + y01;
        z[zOff + 2] = (int) c3;
        long c4 = (c3 >>> 32) + (((long) z[zOff + 3]) & f7063M);
        z[zOff + 3] = (int) c4;
        if ((c4 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, z, zOff, 4);
    }

    public static int mul33WordAdd(int x, int y, int[] z, int zOff) {
        long yVal = ((long) y) & f7063M;
        long c = 0 + (yVal * (((long) x) & f7063M)) + (((long) z[zOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long c2 = (c >>> 32) + (((long) z[zOff + 1]) & f7063M) + yVal;
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) z[zOff + 2]) & f7063M);
        z[zOff + 2] = (int) c3;
        if ((c3 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, z, zOff, 3);
    }

    public static int mulWordDwordAdd(int x, long y, int[] z, int zOff) {
        long xVal = ((long) x) & f7063M;
        long c = 0 + ((y & f7063M) * xVal) + (((long) z[zOff + 0]) & f7063M);
        z[zOff + 0] = (int) c;
        long c2 = (c >>> 32) + ((y >>> 32) * xVal) + (((long) z[zOff + 1]) & f7063M);
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >>> 32) + (((long) z[zOff + 2]) & f7063M);
        z[zOff + 2] = (int) c3;
        if ((c3 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(8, z, zOff, 3);
    }

    public static int mulWord(int x, int[] y, int[] z, int zOff) {
        long c = 0;
        long xVal = ((long) x) & f7063M;
        int i = 0;
        do {
            long c2 = c + ((((long) y[i]) & f7063M) * xVal);
            z[zOff + i] = (int) c2;
            c = c2 >>> 32;
            i++;
        } while (i < 8);
        return (int) c;
    }

    public static void square(int[] x, int[] zz) {
        long x_0 = ((long) x[0]) & f7063M;
        int c = 0;
        int i = 7;
        int j = 16;
        while (true) {
            int i2 = i - 1;
            long xVal = ((long) x[i]) & f7063M;
            long p = xVal * xVal;
            int j2 = j - 1;
            zz[j2] = (c << 31) | ((int) (p >>> 33));
            j = j2 - 1;
            zz[j] = (int) (p >>> 1);
            c = (int) p;
            if (i2 <= 0) {
                long p2 = x_0 * x_0;
                long zz_1 = (((long) (c << 31)) & f7063M) | (p2 >>> 33);
                zz[0] = (int) p2;
                long x_1 = ((long) x[1]) & f7063M;
                long zz_2 = ((long) zz[2]) & f7063M;
                long zz_12 = zz_1 + (x_1 * x_0);
                int w = (int) zz_12;
                zz[1] = (w << 1) | (((int) (p2 >>> 32)) & 1);
                int c2 = w >>> 31;
                long x_2 = ((long) x[2]) & f7063M;
                long zz_3 = ((long) zz[3]) & f7063M;
                long zz_4 = ((long) zz[4]) & f7063M;
                long zz_22 = zz_2 + (zz_12 >>> 32) + (x_2 * x_0);
                int w2 = (int) zz_22;
                zz[2] = (w2 << 1) | c2;
                int c3 = w2 >>> 31;
                long zz_32 = zz_3 + (zz_22 >>> 32) + (x_2 * x_1);
                long zz_42 = zz_4 + (zz_32 >>> 32);
                long x_3 = ((long) x[3]) & f7063M;
                long zz_5 = ((long) zz[5]) & f7063M;
                long zz_6 = ((long) zz[6]) & f7063M;
                long zz_33 = (zz_32 & f7063M) + (x_3 * x_0);
                int w3 = (int) zz_33;
                zz[3] = (w3 << 1) | c3;
                int c4 = w3 >>> 31;
                long zz_43 = zz_42 + (zz_33 >>> 32) + (x_3 * x_1);
                long zz_52 = zz_5 + (zz_43 >>> 32) + (x_3 * x_2);
                long zz_62 = zz_6 + (zz_52 >>> 32);
                long zz_53 = zz_52 & f7063M;
                long x_4 = ((long) x[4]) & f7063M;
                long zz_7 = ((long) zz[7]) & f7063M;
                long zz_8 = ((long) zz[8]) & f7063M;
                long zz_44 = (zz_43 & f7063M) + (x_4 * x_0);
                int w4 = (int) zz_44;
                zz[4] = (w4 << 1) | c4;
                int c5 = w4 >>> 31;
                long zz_54 = zz_53 + (zz_44 >>> 32) + (x_4 * x_1);
                long zz_63 = zz_62 + (zz_54 >>> 32) + (x_4 * x_2);
                long zz_72 = zz_7 + (zz_63 >>> 32) + (x_4 * x_3);
                long zz_64 = zz_63 & f7063M;
                long zz_82 = zz_8 + (zz_72 >>> 32);
                long zz_73 = zz_72 & f7063M;
                long x_5 = ((long) x[5]) & f7063M;
                long zz_9 = ((long) zz[9]) & f7063M;
                long zz_10 = ((long) zz[10]) & f7063M;
                long zz_55 = (zz_54 & f7063M) + (x_5 * x_0);
                int w5 = (int) zz_55;
                zz[5] = (w5 << 1) | c5;
                int c6 = w5 >>> 31;
                long zz_65 = zz_64 + (zz_55 >>> 32) + (x_5 * x_1);
                long zz_74 = zz_73 + (zz_65 >>> 32) + (x_5 * x_2);
                long zz_83 = zz_82 + (zz_74 >>> 32) + (x_5 * x_3);
                long zz_75 = zz_74 & f7063M;
                long zz_92 = zz_9 + (zz_83 >>> 32) + (x_5 * x_4);
                long zz_84 = zz_83 & f7063M;
                long zz_102 = zz_10 + (zz_92 >>> 32);
                long zz_93 = zz_92 & f7063M;
                long x_6 = ((long) x[6]) & f7063M;
                long zz_11 = ((long) zz[11]) & f7063M;
                long zz_122 = ((long) zz[12]) & f7063M;
                long zz_66 = (zz_65 & f7063M) + (x_6 * x_0);
                int w6 = (int) zz_66;
                zz[6] = (w6 << 1) | c6;
                int c7 = w6 >>> 31;
                long zz_76 = zz_75 + (zz_66 >>> 32) + (x_6 * x_1);
                long zz_85 = zz_84 + (zz_76 >>> 32) + (x_6 * x_2);
                long zz_94 = zz_93 + (zz_85 >>> 32) + (x_6 * x_3);
                long zz_86 = zz_85 & f7063M;
                long zz_103 = zz_102 + (zz_94 >>> 32) + (x_6 * x_4);
                long zz_95 = zz_94 & f7063M;
                long zz_112 = zz_11 + (zz_103 >>> 32) + (x_6 * x_5);
                long zz_104 = zz_103 & f7063M;
                long zz_123 = zz_122 + (zz_112 >>> 32);
                long zz_113 = zz_112 & f7063M;
                long x_7 = ((long) x[7]) & f7063M;
                long zz_13 = ((long) zz[13]) & f7063M;
                long zz_14 = ((long) zz[14]) & f7063M;
                long zz_77 = (zz_76 & f7063M) + (x_7 * x_0);
                int w7 = (int) zz_77;
                zz[7] = (w7 << 1) | c7;
                int c8 = w7 >>> 31;
                long zz_87 = zz_86 + (zz_77 >>> 32) + (x_7 * x_1);
                long zz_96 = zz_95 + (zz_87 >>> 32) + (x_7 * x_2);
                long zz_105 = zz_104 + (zz_96 >>> 32) + (x_7 * x_3);
                long zz_114 = zz_113 + (zz_105 >>> 32) + (x_7 * x_4);
                long zz_124 = zz_123 + (zz_114 >>> 32) + (x_7 * x_5);
                long zz_132 = zz_13 + (zz_124 >>> 32) + (x_7 * x_6);
                long zz_142 = zz_14 + (zz_132 >>> 32);
                int w8 = (int) zz_87;
                zz[8] = (w8 << 1) | c8;
                int c9 = w8 >>> 31;
                int w9 = (int) zz_96;
                zz[9] = (w9 << 1) | c9;
                int c10 = w9 >>> 31;
                int w10 = (int) zz_105;
                zz[10] = (w10 << 1) | c10;
                int c11 = w10 >>> 31;
                int w11 = (int) zz_114;
                zz[11] = (w11 << 1) | c11;
                int c12 = w11 >>> 31;
                int w12 = (int) zz_124;
                zz[12] = (w12 << 1) | c12;
                int c13 = w12 >>> 31;
                int w13 = (int) zz_132;
                zz[13] = (w13 << 1) | c13;
                int c14 = w13 >>> 31;
                int w14 = (int) zz_142;
                zz[14] = (w14 << 1) | c14;
                zz[15] = ((zz[15] + ((int) (zz_142 >> 32))) << 1) | (w14 >>> 31);
                return;
            }
            i = i2;
        }
    }

    public static void square(int[] x, int xOff, int[] zz, int zzOff) {
        long x_0 = ((long) x[xOff + 0]) & f7063M;
        int c = 0;
        int i = 7;
        int j = 16;
        while (true) {
            int i2 = i - 1;
            long xVal = ((long) x[xOff + i]) & f7063M;
            long p = xVal * xVal;
            int j2 = j - 1;
            zz[zzOff + j2] = (c << 31) | ((int) (p >>> 33));
            j = j2 - 1;
            zz[zzOff + j] = (int) (p >>> 1);
            c = (int) p;
            if (i2 <= 0) {
                long p2 = x_0 * x_0;
                long zz_1 = (((long) (c << 31)) & f7063M) | (p2 >>> 33);
                zz[zzOff + 0] = (int) p2;
                long x_1 = ((long) x[xOff + 1]) & f7063M;
                long zz_2 = ((long) zz[zzOff + 2]) & f7063M;
                long zz_12 = zz_1 + (x_1 * x_0);
                int w = (int) zz_12;
                zz[zzOff + 1] = (w << 1) | (((int) (p2 >>> 32)) & 1);
                int c2 = w >>> 31;
                long x_2 = ((long) x[xOff + 2]) & f7063M;
                long zz_3 = ((long) zz[zzOff + 3]) & f7063M;
                long zz_4 = ((long) zz[zzOff + 4]) & f7063M;
                long zz_22 = zz_2 + (zz_12 >>> 32) + (x_2 * x_0);
                int w2 = (int) zz_22;
                zz[zzOff + 2] = (w2 << 1) | c2;
                int c3 = w2 >>> 31;
                long zz_32 = zz_3 + (zz_22 >>> 32) + (x_2 * x_1);
                long zz_42 = zz_4 + (zz_32 >>> 32);
                long x_3 = ((long) x[xOff + 3]) & f7063M;
                long zz_5 = ((long) zz[zzOff + 5]) & f7063M;
                long zz_6 = ((long) zz[zzOff + 6]) & f7063M;
                long zz_33 = (zz_32 & f7063M) + (x_3 * x_0);
                int w3 = (int) zz_33;
                zz[zzOff + 3] = (w3 << 1) | c3;
                int c4 = w3 >>> 31;
                long zz_43 = zz_42 + (zz_33 >>> 32) + (x_3 * x_1);
                long zz_52 = zz_5 + (zz_43 >>> 32) + (x_3 * x_2);
                long zz_62 = zz_6 + (zz_52 >>> 32);
                long zz_53 = zz_52 & f7063M;
                long x_4 = ((long) x[xOff + 4]) & f7063M;
                long zz_7 = ((long) zz[zzOff + 7]) & f7063M;
                long zz_8 = ((long) zz[zzOff + 8]) & f7063M;
                long zz_44 = (zz_43 & f7063M) + (x_4 * x_0);
                int w4 = (int) zz_44;
                zz[zzOff + 4] = (w4 << 1) | c4;
                int c5 = w4 >>> 31;
                long zz_54 = zz_53 + (zz_44 >>> 32) + (x_4 * x_1);
                long zz_63 = zz_62 + (zz_54 >>> 32) + (x_4 * x_2);
                long zz_72 = zz_7 + (zz_63 >>> 32) + (x_4 * x_3);
                long zz_64 = zz_63 & f7063M;
                long zz_82 = zz_8 + (zz_72 >>> 32);
                long zz_73 = zz_72 & f7063M;
                long x_5 = ((long) x[xOff + 5]) & f7063M;
                long zz_9 = ((long) zz[zzOff + 9]) & f7063M;
                long zz_10 = ((long) zz[zzOff + 10]) & f7063M;
                long zz_55 = (zz_54 & f7063M) + (x_5 * x_0);
                int w5 = (int) zz_55;
                zz[zzOff + 5] = (w5 << 1) | c5;
                int c6 = w5 >>> 31;
                long zz_65 = zz_64 + (zz_55 >>> 32) + (x_5 * x_1);
                long zz_74 = zz_73 + (zz_65 >>> 32) + (x_5 * x_2);
                long zz_83 = zz_82 + (zz_74 >>> 32) + (x_5 * x_3);
                long zz_75 = zz_74 & f7063M;
                long zz_92 = zz_9 + (zz_83 >>> 32) + (x_5 * x_4);
                long zz_84 = zz_83 & f7063M;
                long zz_102 = zz_10 + (zz_92 >>> 32);
                long zz_93 = zz_92 & f7063M;
                long x_6 = ((long) x[xOff + 6]) & f7063M;
                long zz_11 = ((long) zz[zzOff + 11]) & f7063M;
                long zz_122 = ((long) zz[zzOff + 12]) & f7063M;
                long zz_66 = (zz_65 & f7063M) + (x_6 * x_0);
                int w6 = (int) zz_66;
                zz[zzOff + 6] = (w6 << 1) | c6;
                int c7 = w6 >>> 31;
                long zz_76 = zz_75 + (zz_66 >>> 32) + (x_6 * x_1);
                long zz_85 = zz_84 + (zz_76 >>> 32) + (x_6 * x_2);
                long zz_94 = zz_93 + (zz_85 >>> 32) + (x_6 * x_3);
                long zz_86 = zz_85 & f7063M;
                long zz_103 = zz_102 + (zz_94 >>> 32) + (x_6 * x_4);
                long zz_95 = zz_94 & f7063M;
                long zz_112 = zz_11 + (zz_103 >>> 32) + (x_6 * x_5);
                long zz_104 = zz_103 & f7063M;
                long zz_123 = zz_122 + (zz_112 >>> 32);
                long zz_113 = zz_112 & f7063M;
                long x_7 = ((long) x[xOff + 7]) & f7063M;
                long zz_13 = ((long) zz[zzOff + 13]) & f7063M;
                long zz_14 = ((long) zz[zzOff + 14]) & f7063M;
                long zz_77 = (zz_76 & f7063M) + (x_7 * x_0);
                int w7 = (int) zz_77;
                zz[zzOff + 7] = (w7 << 1) | c7;
                int c8 = w7 >>> 31;
                long zz_87 = zz_86 + (zz_77 >>> 32) + (x_7 * x_1);
                long zz_96 = zz_95 + (zz_87 >>> 32) + (x_7 * x_2);
                long zz_105 = zz_104 + (zz_96 >>> 32) + (x_7 * x_3);
                long zz_114 = zz_113 + (zz_105 >>> 32) + (x_7 * x_4);
                long zz_124 = zz_123 + (zz_114 >>> 32) + (x_7 * x_5);
                long zz_132 = zz_13 + (zz_124 >>> 32) + (x_7 * x_6);
                long zz_142 = zz_14 + (zz_132 >>> 32);
                int w8 = (int) zz_87;
                zz[zzOff + 8] = (w8 << 1) | c8;
                int c9 = w8 >>> 31;
                int w9 = (int) zz_96;
                zz[zzOff + 9] = (w9 << 1) | c9;
                int c10 = w9 >>> 31;
                int w10 = (int) zz_105;
                zz[zzOff + 10] = (w10 << 1) | c10;
                int c11 = w10 >>> 31;
                int w11 = (int) zz_114;
                zz[zzOff + 11] = (w11 << 1) | c11;
                int c12 = w11 >>> 31;
                int w12 = (int) zz_124;
                zz[zzOff + 12] = (w12 << 1) | c12;
                int c13 = w12 >>> 31;
                int w13 = (int) zz_132;
                zz[zzOff + 13] = (w13 << 1) | c13;
                int c14 = w13 >>> 31;
                int w14 = (int) zz_142;
                zz[zzOff + 14] = (w14 << 1) | c14;
                zz[zzOff + 15] = ((zz[zzOff + 15] + ((int) (zz_142 >> 32))) << 1) | (w14 >>> 31);
                return;
            }
            i = i2;
        }
    }

    public static int sub(int[] x, int[] y, int[] z) {
        long c = 0 + ((((long) x[0]) & f7063M) - (((long) y[0]) & f7063M));
        z[0] = (int) c;
        long c2 = (c >> 32) + ((((long) x[1]) & f7063M) - (((long) y[1]) & f7063M));
        z[1] = (int) c2;
        long c3 = (c2 >> 32) + ((((long) x[2]) & f7063M) - (((long) y[2]) & f7063M));
        z[2] = (int) c3;
        long c4 = (c3 >> 32) + ((((long) x[3]) & f7063M) - (((long) y[3]) & f7063M));
        z[3] = (int) c4;
        long c5 = (c4 >> 32) + ((((long) x[4]) & f7063M) - (((long) y[4]) & f7063M));
        z[4] = (int) c5;
        long c6 = (c5 >> 32) + ((((long) x[5]) & f7063M) - (((long) y[5]) & f7063M));
        z[5] = (int) c6;
        long c7 = (c6 >> 32) + ((((long) x[6]) & f7063M) - (((long) y[6]) & f7063M));
        z[6] = (int) c7;
        long c8 = (c7 >> 32) + ((((long) x[7]) & f7063M) - (((long) y[7]) & f7063M));
        z[7] = (int) c8;
        return (int) (c8 >> 32);
    }

    public static int sub(int[] x, int xOff, int[] y, int yOff, int[] z, int zOff) {
        long c = 0 + ((((long) x[xOff + 0]) & f7063M) - (((long) y[yOff + 0]) & f7063M));
        z[zOff + 0] = (int) c;
        long c2 = (c >> 32) + ((((long) x[xOff + 1]) & f7063M) - (((long) y[yOff + 1]) & f7063M));
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >> 32) + ((((long) x[xOff + 2]) & f7063M) - (((long) y[yOff + 2]) & f7063M));
        z[zOff + 2] = (int) c3;
        long c4 = (c3 >> 32) + ((((long) x[xOff + 3]) & f7063M) - (((long) y[yOff + 3]) & f7063M));
        z[zOff + 3] = (int) c4;
        long c5 = (c4 >> 32) + ((((long) x[xOff + 4]) & f7063M) - (((long) y[yOff + 4]) & f7063M));
        z[zOff + 4] = (int) c5;
        long c6 = (c5 >> 32) + ((((long) x[xOff + 5]) & f7063M) - (((long) y[yOff + 5]) & f7063M));
        z[zOff + 5] = (int) c6;
        long c7 = (c6 >> 32) + ((((long) x[xOff + 6]) & f7063M) - (((long) y[yOff + 6]) & f7063M));
        z[zOff + 6] = (int) c7;
        long c8 = (c7 >> 32) + ((((long) x[xOff + 7]) & f7063M) - (((long) y[yOff + 7]) & f7063M));
        z[zOff + 7] = (int) c8;
        return (int) (c8 >> 32);
    }

    public static int subBothFrom(int[] x, int[] y, int[] z) {
        long c = 0 + (((((long) z[0]) & f7063M) - (((long) x[0]) & f7063M)) - (((long) y[0]) & f7063M));
        z[0] = (int) c;
        long c2 = (c >> 32) + (((((long) z[1]) & f7063M) - (((long) x[1]) & f7063M)) - (((long) y[1]) & f7063M));
        z[1] = (int) c2;
        long c3 = (c2 >> 32) + (((((long) z[2]) & f7063M) - (((long) x[2]) & f7063M)) - (((long) y[2]) & f7063M));
        z[2] = (int) c3;
        long c4 = (c3 >> 32) + (((((long) z[3]) & f7063M) - (((long) x[3]) & f7063M)) - (((long) y[3]) & f7063M));
        z[3] = (int) c4;
        long c5 = (c4 >> 32) + (((((long) z[4]) & f7063M) - (((long) x[4]) & f7063M)) - (((long) y[4]) & f7063M));
        z[4] = (int) c5;
        long c6 = (c5 >> 32) + (((((long) z[5]) & f7063M) - (((long) x[5]) & f7063M)) - (((long) y[5]) & f7063M));
        z[5] = (int) c6;
        long c7 = (c6 >> 32) + (((((long) z[6]) & f7063M) - (((long) x[6]) & f7063M)) - (((long) y[6]) & f7063M));
        z[6] = (int) c7;
        long c8 = (c7 >> 32) + (((((long) z[7]) & f7063M) - (((long) x[7]) & f7063M)) - (((long) y[7]) & f7063M));
        z[7] = (int) c8;
        return (int) (c8 >> 32);
    }

    public static int subFrom(int[] x, int[] z) {
        long c = 0 + ((((long) z[0]) & f7063M) - (((long) x[0]) & f7063M));
        z[0] = (int) c;
        long c2 = (c >> 32) + ((((long) z[1]) & f7063M) - (((long) x[1]) & f7063M));
        z[1] = (int) c2;
        long c3 = (c2 >> 32) + ((((long) z[2]) & f7063M) - (((long) x[2]) & f7063M));
        z[2] = (int) c3;
        long c4 = (c3 >> 32) + ((((long) z[3]) & f7063M) - (((long) x[3]) & f7063M));
        z[3] = (int) c4;
        long c5 = (c4 >> 32) + ((((long) z[4]) & f7063M) - (((long) x[4]) & f7063M));
        z[4] = (int) c5;
        long c6 = (c5 >> 32) + ((((long) z[5]) & f7063M) - (((long) x[5]) & f7063M));
        z[5] = (int) c6;
        long c7 = (c6 >> 32) + ((((long) z[6]) & f7063M) - (((long) x[6]) & f7063M));
        z[6] = (int) c7;
        long c8 = (c7 >> 32) + ((((long) z[7]) & f7063M) - (((long) x[7]) & f7063M));
        z[7] = (int) c8;
        return (int) (c8 >> 32);
    }

    public static int subFrom(int[] x, int xOff, int[] z, int zOff) {
        long c = 0 + ((((long) z[zOff + 0]) & f7063M) - (((long) x[xOff + 0]) & f7063M));
        z[zOff + 0] = (int) c;
        long c2 = (c >> 32) + ((((long) z[zOff + 1]) & f7063M) - (((long) x[xOff + 1]) & f7063M));
        z[zOff + 1] = (int) c2;
        long c3 = (c2 >> 32) + ((((long) z[zOff + 2]) & f7063M) - (((long) x[xOff + 2]) & f7063M));
        z[zOff + 2] = (int) c3;
        long c4 = (c3 >> 32) + ((((long) z[zOff + 3]) & f7063M) - (((long) x[xOff + 3]) & f7063M));
        z[zOff + 3] = (int) c4;
        long c5 = (c4 >> 32) + ((((long) z[zOff + 4]) & f7063M) - (((long) x[xOff + 4]) & f7063M));
        z[zOff + 4] = (int) c5;
        long c6 = (c5 >> 32) + ((((long) z[zOff + 5]) & f7063M) - (((long) x[xOff + 5]) & f7063M));
        z[zOff + 5] = (int) c6;
        long c7 = (c6 >> 32) + ((((long) z[zOff + 6]) & f7063M) - (((long) x[xOff + 6]) & f7063M));
        z[zOff + 6] = (int) c7;
        long c8 = (c7 >> 32) + ((((long) z[zOff + 7]) & f7063M) - (((long) x[xOff + 7]) & f7063M));
        z[zOff + 7] = (int) c8;
        return (int) (c8 >> 32);
    }

    public static BigInteger toBigInteger(int[] x) {
        byte[] bs = new byte[32];
        for (int i = 0; i < 8; i++) {
            int x_i = x[i];
            if (x_i != 0) {
                Pack.intToBigEndian(x_i, bs, (7 - i) << 2);
            }
        }
        return new BigInteger(1, bs);
    }

    public static BigInteger toBigInteger64(long[] x) {
        byte[] bs = new byte[32];
        for (int i = 0; i < 4; i++) {
            long x_i = x[i];
            if (x_i != 0) {
                Pack.longToBigEndian(x_i, bs, (3 - i) << 3);
            }
        }
        return new BigInteger(1, bs);
    }

    public static void zero(int[] z) {
        z[0] = 0;
        z[1] = 0;
        z[2] = 0;
        z[3] = 0;
        z[4] = 0;
        z[5] = 0;
        z[6] = 0;
        z[7] = 0;
    }
}
