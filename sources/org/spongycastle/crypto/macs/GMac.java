package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class GMac implements Mac {
    private final GCMBlockCipher cipher;
    private final int macSizeBits;

    public GMac(GCMBlockCipher cipher2) {
        this.cipher = cipher2;
        this.macSizeBits = 128;
    }

    public GMac(GCMBlockCipher cipher2, int macSizeBits2) {
        this.cipher = cipher2;
        this.macSizeBits = macSizeBits2;
    }

    public void init(CipherParameters params) throws IllegalArgumentException {
        if (params instanceof ParametersWithIV) {
            ParametersWithIV param = (ParametersWithIV) params;
            KeyParameter keyParam = (KeyParameter) param.getParameters();
            this.cipher.init(true, new AEADParameters(keyParam, this.macSizeBits, param.getIV()));
            return;
        }
        throw new IllegalArgumentException("GMAC requires ParametersWithIV");
    }

    public String getAlgorithmName() {
        return this.cipher.getUnderlyingCipher().getAlgorithmName() + "-GMAC";
    }

    public int getMacSize() {
        return this.macSizeBits / 8;
    }

    public void update(byte in) throws IllegalStateException {
        this.cipher.processAADByte(in);
    }

    public void update(byte[] in, int inOff, int len) throws DataLengthException, IllegalStateException {
        this.cipher.processAADBytes(in, inOff, len);
    }

    public int doFinal(byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        try {
            return this.cipher.doFinal(out, outOff);
        } catch (InvalidCipherTextException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    public void reset() {
        this.cipher.reset();
    }
}
