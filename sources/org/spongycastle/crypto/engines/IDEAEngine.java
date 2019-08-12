package org.spongycastle.crypto.engines;

import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class IDEAEngine implements BlockCipher {
    private static final int BASE = 65537;
    protected static final int BLOCK_SIZE = 8;
    private static final int MASK = 65535;
    private int[] workingKey = null;

    public void init(boolean forEncryption, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(forEncryption, ((KeyParameter) params).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + params.getClass().getName());
    }

    public String getAlgorithmName() {
        return "IDEA";
    }

    public int getBlockSize() {
        return 8;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.workingKey == null) {
            throw new IllegalStateException("IDEA engine not initialised");
        } else if (inOff + 8 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 8 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            ideaFunc(this.workingKey, in, inOff, out, outOff);
            return 8;
        }
    }

    public void reset() {
    }

    private int bytesToWord(byte[] in, int inOff) {
        return ((in[inOff] << 8) & 65280) + (in[inOff + 1] & 255);
    }

    private void wordToBytes(int word, byte[] out, int outOff) {
        out[outOff] = (byte) (word >>> 8);
        out[outOff + 1] = (byte) word;
    }

    private int mul(int x, int y) {
        int x2;
        if (x == 0) {
            x2 = 65537 - y;
        } else if (y == 0) {
            x2 = 65537 - x;
        } else {
            int p = x * y;
            int y2 = p & 65535;
            int x3 = p >>> 16;
            x2 = (y2 - x3) + (y2 < x3 ? 1 : 0);
        }
        return x2 & 65535;
    }

    private void ideaFunc(int[] workingKey2, byte[] in, int inOff, byte[] out, int outOff) {
        int x0 = bytesToWord(in, inOff);
        int x1 = bytesToWord(in, inOff + 2);
        int x2 = bytesToWord(in, inOff + 4);
        int x3 = bytesToWord(in, inOff + 6);
        int keyOff = 0;
        for (int round = 0; round < 8; round++) {
            int keyOff2 = keyOff + 1;
            int x02 = mul(x0, workingKey2[keyOff]);
            int keyOff3 = keyOff2 + 1;
            int x12 = (x1 + workingKey2[keyOff2]) & 65535;
            int keyOff4 = keyOff3 + 1;
            int x22 = (x2 + workingKey2[keyOff3]) & 65535;
            int keyOff5 = keyOff4 + 1;
            int x32 = mul(x3, workingKey2[keyOff4]);
            int t0 = x12;
            int t1 = x22;
            int x13 = x12 ^ x32;
            int keyOff6 = keyOff5 + 1;
            int x23 = mul(x22 ^ x02, workingKey2[keyOff5]);
            keyOff = keyOff6 + 1;
            int x14 = mul((x13 + x23) & 65535, workingKey2[keyOff6]);
            int x24 = (x23 + x14) & 65535;
            x0 = x02 ^ x14;
            x3 = x32 ^ x24;
            x1 = x14 ^ t1;
            x2 = x24 ^ t0;
        }
        int keyOff7 = keyOff + 1;
        wordToBytes(mul(x0, workingKey2[keyOff]), out, outOff);
        int keyOff8 = keyOff7 + 1;
        wordToBytes(workingKey2[keyOff7] + x2, out, outOff + 2);
        int keyOff9 = keyOff8 + 1;
        wordToBytes(workingKey2[keyOff8] + x1, out, outOff + 4);
        wordToBytes(mul(x3, workingKey2[keyOff9]), out, outOff + 6);
    }

    private int[] expandKey(byte[] uKey) {
        int[] key = new int[52];
        if (uKey.length < 16) {
            byte[] tmp = new byte[16];
            System.arraycopy(uKey, 0, tmp, tmp.length - uKey.length, uKey.length);
            uKey = tmp;
        }
        for (int i = 0; i < 8; i++) {
            key[i] = bytesToWord(uKey, i * 2);
        }
        for (int i2 = 8; i2 < 52; i2++) {
            if ((i2 & 7) < 6) {
                key[i2] = (((key[i2 - 7] & CertificateBody.profileType) << 9) | (key[i2 - 6] >> 7)) & 65535;
            } else if ((i2 & 7) == 6) {
                key[i2] = (((key[i2 - 7] & CertificateBody.profileType) << 9) | (key[i2 - 14] >> 7)) & 65535;
            } else {
                key[i2] = (((key[i2 - 15] & CertificateBody.profileType) << 9) | (key[i2 - 14] >> 7)) & 65535;
            }
        }
        return key;
    }

    private int mulInv(int x) {
        if (x < 2) {
            return x;
        }
        int t0 = 1;
        int t1 = 65537 / x;
        int y = 65537 % x;
        while (y != 1) {
            int q = x / y;
            x %= y;
            t0 = ((t1 * q) + t0) & 65535;
            if (x == 1) {
                return t0;
            }
            int q2 = y / x;
            y %= x;
            t1 = ((t0 * q2) + t1) & 65535;
        }
        return (1 - t1) & 65535;
    }

    /* access modifiers changed from: 0000 */
    public int addInv(int x) {
        return (0 - x) & 65535;
    }

    private int[] invertKey(int[] inKey) {
        int[] key = new int[52];
        int inOff = 0 + 1;
        int t1 = mulInv(inKey[0]);
        int inOff2 = inOff + 1;
        int t2 = addInv(inKey[inOff]);
        int inOff3 = inOff2 + 1;
        int t3 = addInv(inKey[inOff2]);
        int inOff4 = inOff3 + 1;
        int p = 52 - 1;
        key[p] = mulInv(inKey[inOff3]);
        int p2 = p - 1;
        key[p2] = t3;
        int p3 = p2 - 1;
        key[p3] = t2;
        int p4 = p3 - 1;
        key[p4] = t1;
        int inOff5 = inOff4;
        for (int round = 1; round < 8; round++) {
            int inOff6 = inOff5 + 1;
            int t12 = inKey[inOff5];
            int inOff7 = inOff6 + 1;
            int p5 = p4 - 1;
            key[p5] = inKey[inOff6];
            int p6 = p5 - 1;
            key[p6] = t12;
            int inOff8 = inOff7 + 1;
            int t13 = mulInv(inKey[inOff7]);
            int inOff9 = inOff8 + 1;
            int t22 = addInv(inKey[inOff8]);
            int inOff10 = inOff9 + 1;
            int t32 = addInv(inKey[inOff9]);
            inOff5 = inOff10 + 1;
            int p7 = p6 - 1;
            key[p7] = mulInv(inKey[inOff10]);
            int p8 = p7 - 1;
            key[p8] = t22;
            int p9 = p8 - 1;
            key[p9] = t32;
            p4 = p9 - 1;
            key[p4] = t13;
        }
        int inOff11 = inOff5 + 1;
        int t14 = inKey[inOff5];
        int inOff12 = inOff11 + 1;
        int p10 = p4 - 1;
        key[p10] = inKey[inOff11];
        int p11 = p10 - 1;
        key[p11] = t14;
        int inOff13 = inOff12 + 1;
        int t15 = mulInv(inKey[inOff12]);
        int inOff14 = inOff13 + 1;
        int t23 = addInv(inKey[inOff13]);
        int inOff15 = inOff14 + 1;
        int t33 = addInv(inKey[inOff14]);
        int p12 = p11 - 1;
        key[p12] = mulInv(inKey[inOff15]);
        int p13 = p12 - 1;
        key[p13] = t33;
        int p14 = p13 - 1;
        key[p14] = t23;
        key[p14 - 1] = t15;
        return key;
    }

    private int[] generateWorkingKey(boolean forEncryption, byte[] userKey) {
        if (forEncryption) {
            return expandKey(userKey);
        }
        return invertKey(expandKey(userKey));
    }
}
