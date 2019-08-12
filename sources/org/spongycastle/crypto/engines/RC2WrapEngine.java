package org.spongycastle.crypto.engines;

import java.security.SecureRandom;
import net.p318sf.scuba.smartcards.ISO7816;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class RC2WrapEngine implements Wrapper {
    private static final byte[] IV2 = {74, -35, -94, ISO7816.INS_UNBLOCK_CHV, 121, -24, 33, 5};
    byte[] digest = new byte[20];
    private CBCBlockCipher engine;
    private boolean forWrapping;

    /* renamed from: iv */
    private byte[] f6684iv;
    private CipherParameters param;
    private ParametersWithIV paramPlusIV;
    Digest sha1 = new SHA1Digest();

    /* renamed from: sr */
    private SecureRandom f6685sr;

    public void init(boolean forWrapping2, CipherParameters param2) {
        this.forWrapping = forWrapping2;
        this.engine = new CBCBlockCipher(new RC2Engine());
        if (param2 instanceof ParametersWithRandom) {
            ParametersWithRandom pWithR = (ParametersWithRandom) param2;
            this.f6685sr = pWithR.getRandom();
            param2 = pWithR.getParameters();
        } else {
            this.f6685sr = new SecureRandom();
        }
        if (param2 instanceof ParametersWithIV) {
            this.paramPlusIV = (ParametersWithIV) param2;
            this.f6684iv = this.paramPlusIV.getIV();
            this.param = this.paramPlusIV.getParameters();
            if (!this.forWrapping) {
                throw new IllegalArgumentException("You should not supply an IV for unwrapping");
            } else if (this.f6684iv == null || this.f6684iv.length != 8) {
                throw new IllegalArgumentException("IV is not 8 octets");
            }
        } else {
            this.param = param2;
            if (this.forWrapping) {
                this.f6684iv = new byte[8];
                this.f6685sr.nextBytes(this.f6684iv);
                this.paramPlusIV = new ParametersWithIV(this.param, this.f6684iv);
            }
        }
    }

    public String getAlgorithmName() {
        return "RC2";
    }

    public byte[] wrap(byte[] in, int inOff, int inLen) {
        if (!this.forWrapping) {
            throw new IllegalStateException("Not initialized for wrapping");
        }
        int length = inLen + 1;
        if (length % 8 != 0) {
            length += 8 - (length % 8);
        }
        byte[] keyToBeWrapped = new byte[length];
        keyToBeWrapped[0] = (byte) inLen;
        System.arraycopy(in, inOff, keyToBeWrapped, 1, inLen);
        byte[] pad = new byte[((keyToBeWrapped.length - inLen) - 1)];
        if (pad.length > 0) {
            this.f6685sr.nextBytes(pad);
            System.arraycopy(pad, 0, keyToBeWrapped, inLen + 1, pad.length);
        }
        byte[] CKS = calculateCMSKeyChecksum(keyToBeWrapped);
        byte[] WKCKS = new byte[(keyToBeWrapped.length + CKS.length)];
        System.arraycopy(keyToBeWrapped, 0, WKCKS, 0, keyToBeWrapped.length);
        System.arraycopy(CKS, 0, WKCKS, keyToBeWrapped.length, CKS.length);
        byte[] TEMP1 = new byte[WKCKS.length];
        System.arraycopy(WKCKS, 0, TEMP1, 0, WKCKS.length);
        int noOfBlocks = WKCKS.length / this.engine.getBlockSize();
        if (WKCKS.length % this.engine.getBlockSize() != 0) {
            throw new IllegalStateException("Not multiple of block length");
        }
        this.engine.init(true, this.paramPlusIV);
        for (int i = 0; i < noOfBlocks; i++) {
            int currentBytePos = i * this.engine.getBlockSize();
            this.engine.processBlock(TEMP1, currentBytePos, TEMP1, currentBytePos);
        }
        byte[] TEMP2 = new byte[(this.f6684iv.length + TEMP1.length)];
        System.arraycopy(this.f6684iv, 0, TEMP2, 0, this.f6684iv.length);
        System.arraycopy(TEMP1, 0, TEMP2, this.f6684iv.length, TEMP1.length);
        byte[] TEMP3 = new byte[TEMP2.length];
        for (int i2 = 0; i2 < TEMP2.length; i2++) {
            TEMP3[i2] = TEMP2[TEMP2.length - (i2 + 1)];
        }
        this.engine.init(true, new ParametersWithIV(this.param, IV2));
        for (int i3 = 0; i3 < noOfBlocks + 1; i3++) {
            int currentBytePos2 = i3 * this.engine.getBlockSize();
            this.engine.processBlock(TEMP3, currentBytePos2, TEMP3, currentBytePos2);
        }
        return TEMP3;
    }

    public byte[] unwrap(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("Not set for unwrapping");
        } else if (in == null) {
            throw new InvalidCipherTextException("Null pointer as ciphertext");
        } else if (inLen % this.engine.getBlockSize() != 0) {
            throw new InvalidCipherTextException("Ciphertext not multiple of " + this.engine.getBlockSize());
        } else {
            this.engine.init(false, new ParametersWithIV(this.param, IV2));
            byte[] TEMP3 = new byte[inLen];
            System.arraycopy(in, inOff, TEMP3, 0, inLen);
            for (int i = 0; i < TEMP3.length / this.engine.getBlockSize(); i++) {
                int currentBytePos = i * this.engine.getBlockSize();
                this.engine.processBlock(TEMP3, currentBytePos, TEMP3, currentBytePos);
            }
            byte[] TEMP2 = new byte[TEMP3.length];
            for (int i2 = 0; i2 < TEMP3.length; i2++) {
                TEMP2[i2] = TEMP3[TEMP3.length - (i2 + 1)];
            }
            this.f6684iv = new byte[8];
            byte[] TEMP1 = new byte[(TEMP2.length - 8)];
            System.arraycopy(TEMP2, 0, this.f6684iv, 0, 8);
            System.arraycopy(TEMP2, 8, TEMP1, 0, TEMP2.length - 8);
            this.paramPlusIV = new ParametersWithIV(this.param, this.f6684iv);
            this.engine.init(false, this.paramPlusIV);
            byte[] LCEKPADICV = new byte[TEMP1.length];
            System.arraycopy(TEMP1, 0, LCEKPADICV, 0, TEMP1.length);
            for (int i3 = 0; i3 < LCEKPADICV.length / this.engine.getBlockSize(); i3++) {
                int currentBytePos2 = i3 * this.engine.getBlockSize();
                this.engine.processBlock(LCEKPADICV, currentBytePos2, LCEKPADICV, currentBytePos2);
            }
            byte[] result = new byte[(LCEKPADICV.length - 8)];
            byte[] CKStoBeVerified = new byte[8];
            System.arraycopy(LCEKPADICV, 0, result, 0, LCEKPADICV.length - 8);
            System.arraycopy(LCEKPADICV, LCEKPADICV.length - 8, CKStoBeVerified, 0, 8);
            if (!checkCMSKeyChecksum(result, CKStoBeVerified)) {
                throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
            } else if (result.length - ((result[0] & 255) + 1) > 7) {
                throw new InvalidCipherTextException("too many pad bytes (" + (result.length - ((result[0] & 255) + 1)) + ")");
            } else {
                byte[] CEK = new byte[result[0]];
                System.arraycopy(result, 1, CEK, 0, CEK.length);
                return CEK;
            }
        }
    }

    private byte[] calculateCMSKeyChecksum(byte[] key) {
        byte[] result = new byte[8];
        this.sha1.update(key, 0, key.length);
        this.sha1.doFinal(this.digest, 0);
        System.arraycopy(this.digest, 0, result, 0, 8);
        return result;
    }

    private boolean checkCMSKeyChecksum(byte[] key, byte[] checksum) {
        return Arrays.constantTimeAreEqual(calculateCMSKeyChecksum(key), checksum);
    }
}
