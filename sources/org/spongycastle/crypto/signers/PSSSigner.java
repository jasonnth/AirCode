package org.spongycastle.crypto.signers;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSABlindingParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class PSSSigner implements Signer {
    public static final byte TRAILER_IMPLICIT = -68;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest contentDigest;
    private int emBits;
    private int hLen;
    private byte[] mDash;
    private Digest mgfDigest;
    private int mgfhLen;
    private SecureRandom random;
    private int sLen;
    private boolean sSet;
    private byte[] salt;
    private byte trailer;

    public PSSSigner(AsymmetricBlockCipher cipher2, Digest digest, int sLen2) {
        this(cipher2, digest, sLen2, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher cipher2, Digest contentDigest2, Digest mgfDigest2, int sLen2) {
        this(cipher2, contentDigest2, mgfDigest2, sLen2, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher cipher2, Digest digest, int sLen2, byte trailer2) {
        this(cipher2, digest, digest, sLen2, trailer2);
    }

    public PSSSigner(AsymmetricBlockCipher cipher2, Digest contentDigest2, Digest mgfDigest2, int sLen2, byte trailer2) {
        this.cipher = cipher2;
        this.contentDigest = contentDigest2;
        this.mgfDigest = mgfDigest2;
        this.hLen = contentDigest2.getDigestSize();
        this.mgfhLen = mgfDigest2.getDigestSize();
        this.sSet = false;
        this.sLen = sLen2;
        this.salt = new byte[sLen2];
        this.mDash = new byte[(sLen2 + 8 + this.hLen)];
        this.trailer = trailer2;
    }

    public PSSSigner(AsymmetricBlockCipher cipher2, Digest digest, byte[] salt2) {
        this(cipher2, digest, digest, salt2, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher cipher2, Digest contentDigest2, Digest mgfDigest2, byte[] salt2) {
        this(cipher2, contentDigest2, mgfDigest2, salt2, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher cipher2, Digest contentDigest2, Digest mgfDigest2, byte[] salt2, byte trailer2) {
        this.cipher = cipher2;
        this.contentDigest = contentDigest2;
        this.mgfDigest = mgfDigest2;
        this.hLen = contentDigest2.getDigestSize();
        this.mgfhLen = mgfDigest2.getDigestSize();
        this.sSet = true;
        this.sLen = salt2.length;
        this.salt = salt2;
        this.mDash = new byte[(this.sLen + 8 + this.hLen)];
        this.trailer = trailer2;
    }

    public void init(boolean forSigning, CipherParameters param) {
        CipherParameters params;
        RSAKeyParameters kParam;
        if (param instanceof ParametersWithRandom) {
            ParametersWithRandom p = (ParametersWithRandom) param;
            params = p.getParameters();
            this.random = p.getRandom();
        } else {
            params = param;
            if (forSigning) {
                this.random = new SecureRandom();
            }
        }
        if (params instanceof RSABlindingParameters) {
            kParam = ((RSABlindingParameters) params).getPublicKey();
            this.cipher.init(forSigning, param);
        } else {
            kParam = (RSAKeyParameters) params;
            this.cipher.init(forSigning, params);
        }
        this.emBits = kParam.getModulus().bitLength() - 1;
        if (this.emBits < (this.hLen * 8) + (this.sLen * 8) + 9) {
            throw new IllegalArgumentException("key too small for specified hash and salt lengths");
        }
        this.block = new byte[((this.emBits + 7) / 8)];
        reset();
    }

    private void clearBlock(byte[] block2) {
        for (int i = 0; i != block2.length; i++) {
            block2[i] = 0;
        }
    }

    public void update(byte b) {
        this.contentDigest.update(b);
    }

    public void update(byte[] in, int off, int len) {
        this.contentDigest.update(in, off, len);
    }

    public void reset() {
        this.contentDigest.reset();
    }

    public byte[] generateSignature() throws CryptoException, DataLengthException {
        this.contentDigest.doFinal(this.mDash, (this.mDash.length - this.hLen) - this.sLen);
        if (this.sLen != 0) {
            if (!this.sSet) {
                this.random.nextBytes(this.salt);
            }
            System.arraycopy(this.salt, 0, this.mDash, this.mDash.length - this.sLen, this.sLen);
        }
        byte[] h = new byte[this.hLen];
        this.contentDigest.update(this.mDash, 0, this.mDash.length);
        this.contentDigest.doFinal(h, 0);
        this.block[(((this.block.length - this.sLen) - 1) - this.hLen) - 1] = 1;
        System.arraycopy(this.salt, 0, this.block, ((this.block.length - this.sLen) - this.hLen) - 1, this.sLen);
        byte[] dbMask = maskGeneratorFunction1(h, 0, h.length, (this.block.length - this.hLen) - 1);
        for (int i = 0; i != dbMask.length; i++) {
            byte[] bArr = this.block;
            bArr[i] = (byte) (bArr[i] ^ dbMask[i]);
        }
        byte[] bArr2 = this.block;
        bArr2[0] = (byte) (bArr2[0] & (255 >> ((this.block.length * 8) - this.emBits)));
        System.arraycopy(h, 0, this.block, (this.block.length - this.hLen) - 1, this.hLen);
        this.block[this.block.length - 1] = this.trailer;
        byte[] b = this.cipher.processBlock(this.block, 0, this.block.length);
        clearBlock(this.block);
        return b;
    }

    public boolean verifySignature(byte[] signature) {
        this.contentDigest.doFinal(this.mDash, (this.mDash.length - this.hLen) - this.sLen);
        try {
            byte[] b = this.cipher.processBlock(signature, 0, signature.length);
            System.arraycopy(b, 0, this.block, this.block.length - b.length, b.length);
            if (this.block[this.block.length - 1] != this.trailer) {
                clearBlock(this.block);
                return false;
            }
            byte[] dbMask = maskGeneratorFunction1(this.block, (this.block.length - this.hLen) - 1, this.hLen, (this.block.length - this.hLen) - 1);
            for (int i = 0; i != dbMask.length; i++) {
                byte[] bArr = this.block;
                bArr[i] = (byte) (bArr[i] ^ dbMask[i]);
            }
            byte[] bArr2 = this.block;
            bArr2[0] = (byte) (bArr2[0] & (255 >> ((this.block.length * 8) - this.emBits)));
            for (int i2 = 0; i2 != ((this.block.length - this.hLen) - this.sLen) - 2; i2++) {
                if (this.block[i2] != 0) {
                    clearBlock(this.block);
                    return false;
                }
            }
            if (this.block[((this.block.length - this.hLen) - this.sLen) - 2] != 1) {
                clearBlock(this.block);
                return false;
            }
            if (this.sSet) {
                System.arraycopy(this.salt, 0, this.mDash, this.mDash.length - this.sLen, this.sLen);
            } else {
                System.arraycopy(this.block, ((this.block.length - this.sLen) - this.hLen) - 1, this.mDash, this.mDash.length - this.sLen, this.sLen);
            }
            this.contentDigest.update(this.mDash, 0, this.mDash.length);
            this.contentDigest.doFinal(this.mDash, this.mDash.length - this.hLen);
            int i3 = (this.block.length - this.hLen) - 1;
            for (int j = this.mDash.length - this.hLen; j != this.mDash.length; j++) {
                if ((this.block[i3] ^ this.mDash[j]) != 0) {
                    clearBlock(this.mDash);
                    clearBlock(this.block);
                    return false;
                }
                i3++;
            }
            clearBlock(this.mDash);
            clearBlock(this.block);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void ItoOSP(int i, byte[] sp) {
        sp[0] = (byte) (i >>> 24);
        sp[1] = (byte) (i >>> 16);
        sp[2] = (byte) (i >>> 8);
        sp[3] = (byte) (i >>> 0);
    }

    private byte[] maskGeneratorFunction1(byte[] Z, int zOff, int zLen, int length) {
        byte[] mask = new byte[length];
        byte[] hashBuf = new byte[this.mgfhLen];
        byte[] C = new byte[4];
        int counter = 0;
        this.mgfDigest.reset();
        while (counter < length / this.mgfhLen) {
            ItoOSP(counter, C);
            this.mgfDigest.update(Z, zOff, zLen);
            this.mgfDigest.update(C, 0, C.length);
            this.mgfDigest.doFinal(hashBuf, 0);
            System.arraycopy(hashBuf, 0, mask, this.mgfhLen * counter, this.mgfhLen);
            counter++;
        }
        if (this.mgfhLen * counter < length) {
            ItoOSP(counter, C);
            this.mgfDigest.update(Z, zOff, zLen);
            this.mgfDigest.update(C, 0, C.length);
            this.mgfDigest.doFinal(hashBuf, 0);
            System.arraycopy(hashBuf, 0, mask, this.mgfhLen * counter, mask.length - (this.mgfhLen * counter));
        }
        return mask;
    }
}
