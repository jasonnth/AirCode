package org.spongycastle.jce.provider;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

/* compiled from: BrokenPBE */
class OldPKCS12ParametersGenerator extends PBEParametersGenerator {
    public static final int IV_MATERIAL = 2;
    public static final int KEY_MATERIAL = 1;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;

    /* renamed from: u */
    private int f6933u;

    /* renamed from: v */
    private int f6934v;

    public OldPKCS12ParametersGenerator(Digest digest2) {
        this.digest = digest2;
        if (digest2 instanceof MD5Digest) {
            this.f6933u = 16;
            this.f6934v = 64;
        } else if (digest2 instanceof SHA1Digest) {
            this.f6933u = 20;
            this.f6934v = 64;
        } else if (digest2 instanceof RIPEMD160Digest) {
            this.f6933u = 20;
            this.f6934v = 64;
        } else {
            throw new IllegalArgumentException("Digest " + digest2.getAlgorithmName() + " unsupported");
        }
    }

    private void adjust(byte[] a, int aOff, byte[] b) {
        int x = (b[b.length - 1] & 255) + (a[(b.length + aOff) - 1] & 255) + 1;
        a[(b.length + aOff) - 1] = (byte) x;
        int x2 = x >>> 8;
        for (int i = b.length - 2; i >= 0; i--) {
            int x3 = x2 + (b[i] & 255) + (a[aOff + i] & 255);
            a[aOff + i] = (byte) x3;
            x2 = x3 >>> 8;
        }
    }

    private byte[] generateDerivedKey(int idByte, int n) {
        byte[] S;
        byte[] P;
        byte[] D = new byte[this.f6934v];
        byte[] dKey = new byte[n];
        for (int i = 0; i != D.length; i++) {
            D[i] = (byte) idByte;
        }
        if (this.salt == null || this.salt.length == 0) {
            S = new byte[0];
        } else {
            S = new byte[(this.f6934v * (((this.salt.length + this.f6934v) - 1) / this.f6934v))];
            for (int i2 = 0; i2 != S.length; i2++) {
                S[i2] = this.salt[i2 % this.salt.length];
            }
        }
        if (this.password == null || this.password.length == 0) {
            P = new byte[0];
        } else {
            P = new byte[(this.f6934v * (((this.password.length + this.f6934v) - 1) / this.f6934v))];
            for (int i3 = 0; i3 != P.length; i3++) {
                P[i3] = this.password[i3 % this.password.length];
            }
        }
        byte[] I = new byte[(S.length + P.length)];
        System.arraycopy(S, 0, I, 0, S.length);
        System.arraycopy(P, 0, I, S.length, P.length);
        byte[] B = new byte[this.f6934v];
        int c = ((this.f6933u + n) - 1) / this.f6933u;
        for (int i4 = 1; i4 <= c; i4++) {
            byte[] A = new byte[this.f6933u];
            this.digest.update(D, 0, D.length);
            this.digest.update(I, 0, I.length);
            this.digest.doFinal(A, 0);
            for (int j = 1; j != this.iterationCount; j++) {
                this.digest.update(A, 0, A.length);
                this.digest.doFinal(A, 0);
            }
            for (int j2 = 0; j2 != B.length; j2++) {
                B[i4] = A[j2 % A.length];
            }
            for (int j3 = 0; j3 != I.length / this.f6934v; j3++) {
                adjust(I, this.f6934v * j3, B);
            }
            if (i4 == c) {
                System.arraycopy(A, 0, dKey, (i4 - 1) * this.f6933u, dKey.length - ((i4 - 1) * this.f6933u));
            } else {
                System.arraycopy(A, 0, dKey, (i4 - 1) * this.f6933u, A.length);
            }
        }
        return dKey;
    }

    public CipherParameters generateDerivedParameters(int keySize) {
        int keySize2 = keySize / 8;
        return new KeyParameter(generateDerivedKey(1, keySize2), 0, keySize2);
    }

    public CipherParameters generateDerivedParameters(int keySize, int ivSize) {
        int keySize2 = keySize / 8;
        int ivSize2 = ivSize / 8;
        byte[] dKey = generateDerivedKey(1, keySize2);
        return new ParametersWithIV(new KeyParameter(dKey, 0, keySize2), generateDerivedKey(2, ivSize2), 0, ivSize2);
    }

    public CipherParameters generateDerivedMacParameters(int keySize) {
        int keySize2 = keySize / 8;
        return new KeyParameter(generateDerivedKey(3, keySize2), 0, keySize2);
    }
}
