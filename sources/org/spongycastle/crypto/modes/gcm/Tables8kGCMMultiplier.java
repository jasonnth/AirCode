package org.spongycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class Tables8kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f6802H;

    /* renamed from: M */
    private int[][][] f6803M;

    public void init(byte[] H) {
        if (this.f6803M == null) {
            this.f6803M = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{32, 16, 4});
        } else if (Arrays.areEqual(this.f6802H, H)) {
            return;
        }
        this.f6802H = Arrays.clone(H);
        GCMUtil.asInts(H, this.f6803M[1][8]);
        for (int j = 4; j >= 1; j >>= 1) {
            GCMUtil.multiplyP(this.f6803M[1][j + j], this.f6803M[1][j]);
        }
        GCMUtil.multiplyP(this.f6803M[1][1], this.f6803M[0][8]);
        for (int j2 = 4; j2 >= 1; j2 >>= 1) {
            GCMUtil.multiplyP(this.f6803M[0][j2 + j2], this.f6803M[0][j2]);
        }
        int i = 0;
        while (true) {
            for (int j3 = 2; j3 < 16; j3 += j3) {
                for (int k = 1; k < j3; k++) {
                    GCMUtil.xor(this.f6803M[i][j3], this.f6803M[i][k], this.f6803M[i][j3 + k]);
                }
            }
            i++;
            if (i == 32) {
                return;
            }
            if (i > 1) {
                for (int j4 = 8; j4 > 0; j4 >>= 1) {
                    GCMUtil.multiplyP8(this.f6803M[i - 2][j4], this.f6803M[i][j4]);
                }
            }
        }
    }

    public void multiplyH(byte[] x) {
        int[] z = new int[4];
        for (int i = 15; i >= 0; i--) {
            int[] m = this.f6803M[i + i][x[i] & 15];
            z[0] = z[0] ^ m[0];
            z[1] = z[1] ^ m[1];
            z[2] = z[2] ^ m[2];
            z[3] = z[3] ^ m[3];
            int[] m2 = this.f6803M[i + i + 1][(x[i] & 240) >>> 4];
            z[0] = z[0] ^ m2[0];
            z[1] = z[1] ^ m2[1];
            z[2] = z[2] ^ m2[2];
            z[3] = z[3] ^ m2[3];
        }
        Pack.intToBigEndian(z, x, 0);
    }
}
