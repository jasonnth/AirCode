package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public class X931Signer implements Signer {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA224 = 14540;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest digest;
    private RSAKeyParameters kParam;
    private int keyBits;
    private int trailer;

    public X931Signer(AsymmetricBlockCipher cipher2, Digest digest2, boolean implicit) {
        this.cipher = cipher2;
        this.digest = digest2;
        if (implicit) {
            this.trailer = 188;
            return;
        }
        Integer trailerObj = ISOTrailers.getTrailer(digest2);
        if (trailerObj != null) {
            this.trailer = trailerObj.intValue();
            return;
        }
        throw new IllegalArgumentException("no valid trailer for digest: " + digest2.getAlgorithmName());
    }

    public X931Signer(AsymmetricBlockCipher cipher2, Digest digest2) {
        this(cipher2, digest2, false);
    }

    public void init(boolean forSigning, CipherParameters param) {
        this.kParam = (RSAKeyParameters) param;
        this.cipher.init(forSigning, this.kParam);
        this.keyBits = this.kParam.getModulus().bitLength();
        this.block = new byte[((this.keyBits + 7) / 8)];
        reset();
    }

    private void clearBlock(byte[] block2) {
        for (int i = 0; i != block2.length; i++) {
            block2[i] = 0;
        }
    }

    public void update(byte b) {
        this.digest.update(b);
    }

    public void update(byte[] in, int off, int len) {
        this.digest.update(in, off, len);
    }

    public void reset() {
        this.digest.reset();
    }

    public byte[] generateSignature() throws CryptoException {
        createSignatureBlock();
        BigInteger t = new BigInteger(1, this.cipher.processBlock(this.block, 0, this.block.length));
        clearBlock(this.block);
        return BigIntegers.asUnsignedByteArray((this.kParam.getModulus().bitLength() + 7) / 8, t.min(this.kParam.getModulus().subtract(t)));
    }

    private void createSignatureBlock() {
        int delta;
        int digSize = this.digest.getDigestSize();
        if (this.trailer == 188) {
            delta = (this.block.length - digSize) - 1;
            this.digest.doFinal(this.block, delta);
            this.block[this.block.length - 1] = PSSSigner.TRAILER_IMPLICIT;
        } else {
            delta = (this.block.length - digSize) - 2;
            this.digest.doFinal(this.block, delta);
            this.block[this.block.length - 2] = (byte) (this.trailer >>> 8);
            this.block[this.block.length - 1] = (byte) this.trailer;
        }
        this.block[0] = 107;
        for (int i = delta - 2; i != 0; i--) {
            this.block[i] = -69;
        }
        this.block[delta - 1] = -70;
    }

    public boolean verifySignature(byte[] signature) {
        BigInteger f;
        try {
            this.block = this.cipher.processBlock(signature, 0, signature.length);
            BigInteger t = new BigInteger(1, this.block);
            if ((t.intValue() & 15) == 12) {
                f = t;
            } else {
                BigInteger t2 = this.kParam.getModulus().subtract(t);
                if ((t2.intValue() & 15) != 12) {
                    return false;
                }
                f = t2;
            }
            createSignatureBlock();
            byte[] fBlock = BigIntegers.asUnsignedByteArray(this.block.length, f);
            boolean rv = Arrays.constantTimeAreEqual(this.block, fBlock);
            clearBlock(this.block);
            clearBlock(fBlock);
            return rv;
        } catch (Exception e) {
            return false;
        }
    }
}
