package org.spongycastle.crypto.digests;

public class SHA3Digest extends KeccakDigest {
    private static int checkBitLength(int bitLength) {
        switch (bitLength) {
            case 224:
            case 256:
            case 384:
            case 512:
                return bitLength;
            default:
                throw new IllegalArgumentException("'bitLength' " + bitLength + " not supported for SHA-3");
        }
    }

    public SHA3Digest() {
        this(256);
    }

    public SHA3Digest(int bitLength) {
        super(checkBitLength(bitLength));
    }

    public SHA3Digest(SHA3Digest source) {
        super((KeccakDigest) source);
    }

    public String getAlgorithmName() {
        return "SHA3-" + this.fixedOutputLength;
    }

    public int doFinal(byte[] out, int outOff) {
        absorb(new byte[]{2}, 0, 2);
        return super.doFinal(out, outOff);
    }

    /* access modifiers changed from: protected */
    public int doFinal(byte[] out, int outOff, byte partialByte, int partialBits) {
        if (partialBits < 0 || partialBits > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int finalInput = (((1 << partialBits) - 1) & partialByte) | (2 << partialBits);
        int finalBits = partialBits + 2;
        if (finalBits >= 8) {
            this.oneByte[0] = (byte) finalInput;
            absorb(this.oneByte, 0, 8);
            finalBits -= 8;
            finalInput >>>= 8;
        }
        return super.doFinal(out, outOff, (byte) finalInput, finalBits);
    }
}
