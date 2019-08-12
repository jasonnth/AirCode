package org.spongycastle.math.raw;

import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import com.facebook.soloader.MinElf;

public class Interleave {
    private static final long M32 = 1431655765;
    private static final long M64 = 6148914691236517205L;

    public static int expand8to16(int x) {
        int x2 = x & 255;
        int x3 = ((x2 << 4) | x2) & 3855;
        int x4 = ((x3 << 2) | x3) & 13107;
        return ((x4 << 1) | x4) & 21845;
    }

    public static int expand16to32(int x) {
        int x2 = x & MinElf.PN_XNUM;
        int x3 = ((x2 << 8) | x2) & 16711935;
        int x4 = ((x3 << 4) | x3) & 252645135;
        int x5 = ((x4 << 2) | x4) & 858993459;
        return ((x5 << 1) | x5) & 1431655765;
    }

    public static long expand32to64(int x) {
        int t = ((x >>> 8) ^ x) & ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK;
        int x2 = x ^ ((t << 8) ^ t);
        int t2 = ((x2 >>> 4) ^ x2) & 15728880;
        int x3 = x2 ^ ((t2 << 4) ^ t2);
        int t3 = ((x3 >>> 2) ^ x3) & 202116108;
        int x4 = x3 ^ ((t3 << 2) ^ t3);
        int t4 = ((x4 >>> 1) ^ x4) & 572662306;
        int x5 = x4 ^ ((t4 << 1) ^ t4);
        return ((((long) (x5 >>> 1)) & M32) << 32) | (((long) x5) & M32);
    }

    public static void expand64To128(long x, long[] z, int zOff) {
        long t = ((x >>> 16) ^ x) & 4294901760L;
        long x2 = x ^ ((t << 16) ^ t);
        long t2 = ((x2 >>> 8) ^ x2) & 280375465148160L;
        long x3 = x2 ^ ((t2 << 8) ^ t2);
        long t3 = ((x3 >>> 4) ^ x3) & 67555025218437360L;
        long x4 = x3 ^ ((t3 << 4) ^ t3);
        long t4 = ((x4 >>> 2) ^ x4) & 868082074056920076L;
        long x5 = x4 ^ ((t4 << 2) ^ t4);
        long t5 = ((x5 >>> 1) ^ x5) & 2459565876494606882L;
        long x6 = x5 ^ ((t5 << 1) ^ t5);
        z[zOff] = M64 & x6;
        z[zOff + 1] = (x6 >>> 1) & M64;
    }

    public static long unshuffle(long x) {
        long t = ((x >>> 1) ^ x) & 2459565876494606882L;
        long x2 = x ^ ((t << 1) ^ t);
        long t2 = ((x2 >>> 2) ^ x2) & 868082074056920076L;
        long x3 = x2 ^ ((t2 << 2) ^ t2);
        long t3 = ((x3 >>> 4) ^ x3) & 67555025218437360L;
        long x4 = x3 ^ ((t3 << 4) ^ t3);
        long t4 = ((x4 >>> 8) ^ x4) & 280375465148160L;
        long x5 = x4 ^ ((t4 << 8) ^ t4);
        long t5 = ((x5 >>> 16) ^ x5) & 4294901760L;
        return x5 ^ ((t5 << 16) ^ t5);
    }
}
