package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.Xof;

public class SHAKEDigest extends KeccakDigest implements Xof {
    private static int checkBitLength(int bitLength) {
        switch (bitLength) {
            case 128:
            case 256:
                return bitLength;
            default:
                throw new IllegalArgumentException("'bitLength' " + bitLength + " not supported for SHAKE");
        }
    }

    public SHAKEDigest() {
        this(128);
    }

    public SHAKEDigest(int bitLength) {
        super(checkBitLength(bitLength));
    }

    public SHAKEDigest(SHAKEDigest source) {
        super((KeccakDigest) source);
    }

    public String getAlgorithmName() {
        return "SHAKE" + this.fixedOutputLength;
    }

    public int doFinal(byte[] out, int outOff) {
        return doFinal(out, outOff, getDigestSize());
    }

    public int doFinal(byte[] out, int outOff, int outLen) {
        absorb(new byte[]{15}, 0, 4);
        squeeze(out, outOff, ((long) outLen) * 8);
        reset();
        return outLen;
    }

    /* access modifiers changed from: protected */
    public int doFinal(byte[] out, int outOff, byte partialByte, int partialBits) {
        return doFinal(out, outOff, getDigestSize(), partialByte, partialBits);
    }

    /* access modifiers changed from: protected */
    public int doFinal(byte[] out, int outOff, int outLen, byte partialByte, int partialBits) {
        if (partialBits < 0 || partialBits > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int finalInput = (((1 << partialBits) - 1) & partialByte) | (15 << partialBits);
        int finalBits = partialBits + 4;
        if (finalBits >= 8) {
            this.oneByte[0] = (byte) finalInput;
            absorb(this.oneByte, 0, 8);
            finalBits -= 8;
            finalInput >>>= 8;
        }
        if (finalBits > 0) {
            this.oneByte[0] = (byte) finalInput;
            absorb(this.oneByte, 0, (long) finalBits);
        }
        squeeze(out, outOff, ((long) outLen) * 8);
        reset();
        return outLen;
    }
}
