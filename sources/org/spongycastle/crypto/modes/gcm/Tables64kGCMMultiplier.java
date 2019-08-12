package org.spongycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class Tables64kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f6800H;

    /* renamed from: M */
    private int[][][] f6801M;

    public void init(byte[] H) {
        if (this.f6801M == null) {
            this.f6801M = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{16, 256, 4});
        } else if (Arrays.areEqual(this.f6800H, H)) {
            return;
        }
        this.f6800H = Arrays.clone(H);
        GCMUtil.asInts(H, this.f6801M[0][128]);
        for (int j = 64; j >= 1; j >>= 1) {
            GCMUtil.multiplyP(this.f6801M[0][j + j], this.f6801M[0][j]);
        }
        int i = 0;
        while (true) {
            for (int j2 = 2; j2 < 256; j2 += j2) {
                for (int k = 1; k < j2; k++) {
                    GCMUtil.xor(this.f6801M[i][j2], this.f6801M[i][k], this.f6801M[i][j2 + k]);
                }
            }
            i++;
            if (i != 16) {
                for (int j3 = 128; j3 > 0; j3 >>= 1) {
                    GCMUtil.multiplyP8(this.f6801M[i - 1][j3], this.f6801M[i][j3]);
                }
            } else {
                return;
            }
        }
    }

    public void multiplyH(byte[] x) {
        int[] z = new int[4];
        for (int i = 15; i >= 0; i--) {
            int[] m = this.f6801M[i][x[i] & 255];
            z[0] = z[0] ^ m[0];
            z[1] = z[1] ^ m[1];
            z[2] = z[2] ^ m[2];
            z[3] = z[3] ^ m[3];
        }
        Pack.intToBigEndian(z, x, 0);
    }
}
