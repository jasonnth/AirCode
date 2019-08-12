package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.engines.Salsa20Engine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class SCrypt {
    public static byte[] generate(byte[] P, byte[] S, int N, int r, int p, int dkLen) {
        if (P == null) {
            throw new IllegalArgumentException("Passphrase P must be provided.");
        } else if (S == null) {
            throw new IllegalArgumentException("Salt S must be provided.");
        } else if (N <= 1) {
            throw new IllegalArgumentException("Cost parameter N must be > 1.");
        } else if (r == 1 && N > 65536) {
            throw new IllegalArgumentException("Cost parameter N must be > 1 and < 65536.");
        } else if (r < 1) {
            throw new IllegalArgumentException("Block size r must be >= 1.");
        } else {
            int maxParallel = Integer.MAX_VALUE / ((r * 128) * 8);
            if (p < 1 || p > maxParallel) {
                throw new IllegalArgumentException("Parallelisation parameter p must be >= 1 and <= " + maxParallel + " (based on block size r of " + r + ")");
            } else if (dkLen >= 1) {
                return MFcrypt(P, S, N, r, p, dkLen);
            } else {
                throw new IllegalArgumentException("Generated key length dkLen must be >= 1.");
            }
        }
    }

    private static byte[] MFcrypt(byte[] P, byte[] S, int N, int r, int p, int dkLen) {
        int MFLenBytes = r * 128;
        byte[] bytes = SingleIterationPBKDF2(P, S, p * MFLenBytes);
        int[] B = null;
        try {
            int BLen = bytes.length >>> 2;
            B = new int[BLen];
            Pack.littleEndianToInt(bytes, 0, B);
            int MFLenWords = MFLenBytes >>> 2;
            for (int BOff = 0; BOff < BLen; BOff += MFLenWords) {
                SMix(B, BOff, N, r);
            }
            Pack.intToLittleEndian(B, bytes, 0);
            return SingleIterationPBKDF2(P, bytes, dkLen);
        } finally {
            Clear(bytes);
            Clear(B);
        }
    }

    private static byte[] SingleIterationPBKDF2(byte[] P, byte[] S, int dkLen) {
        PBEParametersGenerator pGen = new PKCS5S2ParametersGenerator(new SHA256Digest());
        pGen.init(P, S, 1);
        return ((KeyParameter) pGen.generateDerivedMacParameters(dkLen * 8)).getKey();
    }

    private static void SMix(int[] B, int BOff, int N, int r) {
        int BCount = r * 32;
        int[] blockX1 = new int[16];
        int[] blockX2 = new int[16];
        int[] blockY = new int[BCount];
        int[] X = new int[BCount];
        int[][] V = new int[N][];
        try {
            System.arraycopy(B, BOff, X, 0, BCount);
            for (int i = 0; i < N; i++) {
                V[i] = Arrays.clone(X);
                BlockMix(X, blockX1, blockX2, blockY, r);
            }
            int mask = N - 1;
            for (int i2 = 0; i2 < N; i2++) {
                Xor(X, V[X[BCount - 16] & mask], 0, X);
                BlockMix(X, blockX1, blockX2, blockY, r);
            }
            System.arraycopy(X, 0, B, BOff, BCount);
        } finally {
            ClearAll(V);
            ClearAll(new int[][]{X, blockX1, blockX2, blockY});
        }
    }

    private static void BlockMix(int[] B, int[] X1, int[] X2, int[] Y, int r) {
        System.arraycopy(B, B.length - 16, X1, 0, 16);
        int BOff = 0;
        int YOff = 0;
        int halfLen = B.length >>> 1;
        for (int i = r * 2; i > 0; i--) {
            Xor(X1, B, BOff, X2);
            Salsa20Engine.salsaCore(8, X2, X1);
            System.arraycopy(X1, 0, Y, YOff, 16);
            YOff = (halfLen + BOff) - YOff;
            BOff += 16;
        }
        System.arraycopy(Y, 0, B, 0, Y.length);
    }

    private static void Xor(int[] a, int[] b, int bOff, int[] output) {
        for (int i = output.length - 1; i >= 0; i--) {
            output[i] = a[i] ^ b[bOff + i];
        }
    }

    private static void Clear(byte[] array) {
        if (array != null) {
            Arrays.fill(array, 0);
        }
    }

    private static void Clear(int[] array) {
        if (array != null) {
            Arrays.fill(array, 0);
        }
    }

    private static void ClearAll(int[][] arrays) {
        for (int[] Clear : arrays) {
            Clear(Clear);
        }
    }
}
