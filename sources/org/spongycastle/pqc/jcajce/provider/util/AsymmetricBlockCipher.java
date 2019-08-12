package org.spongycastle.pqc.jcajce.provider.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

public abstract class AsymmetricBlockCipher extends CipherSpiExt {
    protected ByteArrayOutputStream buf = new ByteArrayOutputStream();
    protected int cipherTextSize;
    protected int maxPlainTextSize;
    protected AlgorithmParameterSpec paramSpec;

    /* access modifiers changed from: protected */
    public abstract void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException;

    /* access modifiers changed from: protected */
    public abstract void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException;

    /* access modifiers changed from: protected */
    public abstract byte[] messageDecrypt(byte[] bArr) throws IllegalBlockSizeException, BadPaddingException;

    /* access modifiers changed from: protected */
    public abstract byte[] messageEncrypt(byte[] bArr) throws IllegalBlockSizeException, BadPaddingException;

    public final int getBlockSize() {
        return this.opMode == 1 ? this.maxPlainTextSize : this.cipherTextSize;
    }

    public final byte[] getIV() {
        return null;
    }

    public final int getOutputSize(int inLen) {
        int totalLen = inLen + this.buf.size();
        int maxLen = getBlockSize();
        if (totalLen > maxLen) {
            return 0;
        }
        return maxLen;
    }

    public final AlgorithmParameterSpec getParameters() {
        return this.paramSpec;
    }

    public final void initEncrypt(Key key) throws InvalidKeyException {
        try {
            initEncrypt(key, null, new SecureRandom());
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, SecureRandom random) throws InvalidKeyException {
        try {
            initEncrypt(key, null, random);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
        initEncrypt(key, params, new SecureRandom());
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec params, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.opMode = 1;
        initCipherEncrypt(key, params, secureRandom);
    }

    public final void initDecrypt(Key key) throws InvalidKeyException {
        try {
            initDecrypt(key, null);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initDecrypt(Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
        this.opMode = 2;
        initCipherDecrypt(key, params);
    }

    public final byte[] update(byte[] input, int inOff, int inLen) {
        if (inLen != 0) {
            this.buf.write(input, inOff, inLen);
        }
        return new byte[0];
    }

    public final int update(byte[] input, int inOff, int inLen, byte[] output, int outOff) {
        update(input, inOff, inLen);
        return 0;
    }

    public final byte[] doFinal(byte[] input, int inOff, int inLen) throws IllegalBlockSizeException, BadPaddingException {
        checkLength(inLen);
        update(input, inOff, inLen);
        byte[] mBytes = this.buf.toByteArray();
        this.buf.reset();
        switch (this.opMode) {
            case 1:
                return messageEncrypt(mBytes);
            case 2:
                return messageDecrypt(mBytes);
            default:
                return null;
        }
    }

    public final int doFinal(byte[] input, int inOff, int inLen, byte[] output, int outOff) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        if (output.length < getOutputSize(inLen)) {
            throw new ShortBufferException("Output buffer too short.");
        }
        byte[] out = doFinal(input, inOff, inLen);
        System.arraycopy(out, 0, output, outOff, out.length);
        return out.length;
    }

    /* access modifiers changed from: protected */
    public final void setMode(String modeName) {
    }

    /* access modifiers changed from: protected */
    public final void setPadding(String paddingName) {
    }

    /* access modifiers changed from: protected */
    public void checkLength(int inLen) throws IllegalBlockSizeException {
        int inLength = inLen + this.buf.size();
        if (this.opMode == 1) {
            if (inLength > this.maxPlainTextSize) {
                throw new IllegalBlockSizeException("The length of the plaintext (" + inLength + " bytes) is not supported by " + "the cipher (max. " + this.maxPlainTextSize + " bytes).");
            }
        } else if (this.opMode == 2 && inLength != this.cipherTextSize) {
            throw new IllegalBlockSizeException("Illegal ciphertext length (expected " + this.cipherTextSize + " bytes, was " + inLength + " bytes).");
        }
    }
}
