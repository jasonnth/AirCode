package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.HKDFParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class HKDFBytesGenerator implements DerivationFunction {
    private byte[] currentT;
    private int generatedBytes;
    private HMac hMacHash;
    private int hashLen;
    private byte[] info;

    public HKDFBytesGenerator(Digest hash) {
        this.hMacHash = new HMac(hash);
        this.hashLen = hash.getDigestSize();
    }

    public void init(DerivationParameters param) {
        if (!(param instanceof HKDFParameters)) {
            throw new IllegalArgumentException("HKDF parameters required for HKDFBytesGenerator");
        }
        HKDFParameters params = (HKDFParameters) param;
        if (params.skipExtract()) {
            this.hMacHash.init(new KeyParameter(params.getIKM()));
        } else {
            this.hMacHash.init(extract(params.getSalt(), params.getIKM()));
        }
        this.info = params.getInfo();
        this.generatedBytes = 0;
        this.currentT = new byte[this.hashLen];
    }

    private KeyParameter extract(byte[] salt, byte[] ikm) {
        this.hMacHash.init(new KeyParameter(ikm));
        if (salt == null) {
            this.hMacHash.init(new KeyParameter(new byte[this.hashLen]));
        } else {
            this.hMacHash.init(new KeyParameter(salt));
        }
        this.hMacHash.update(ikm, 0, ikm.length);
        byte[] prk = new byte[this.hashLen];
        this.hMacHash.doFinal(prk, 0);
        return new KeyParameter(prk);
    }

    private void expandNext() throws DataLengthException {
        int n = (this.generatedBytes / this.hashLen) + 1;
        if (n >= 256) {
            throw new DataLengthException("HKDF cannot generate more than 255 blocks of HashLen size");
        }
        if (this.generatedBytes != 0) {
            this.hMacHash.update(this.currentT, 0, this.hashLen);
        }
        this.hMacHash.update(this.info, 0, this.info.length);
        this.hMacHash.update((byte) n);
        this.hMacHash.doFinal(this.currentT, 0);
    }

    public Digest getDigest() {
        return this.hMacHash.getUnderlyingDigest();
    }

    public int generateBytes(byte[] out, int outOff, int len) throws DataLengthException, IllegalArgumentException {
        if (this.generatedBytes + len > this.hashLen * 255) {
            throw new DataLengthException("HKDF may only be used for 255 * HashLen bytes of output");
        }
        if (this.generatedBytes % this.hashLen == 0) {
            expandNext();
        }
        int toGenerate = len;
        int posInT = this.generatedBytes % this.hashLen;
        int toCopy = Math.min(this.hashLen - (this.generatedBytes % this.hashLen), toGenerate);
        System.arraycopy(this.currentT, posInT, out, outOff, toCopy);
        this.generatedBytes += toCopy;
        int toGenerate2 = toGenerate - toCopy;
        int outOff2 = outOff + toCopy;
        while (toGenerate2 > 0) {
            expandNext();
            int toCopy2 = Math.min(this.hashLen, toGenerate2);
            System.arraycopy(this.currentT, 0, out, outOff2, toCopy2);
            this.generatedBytes += toCopy2;
            toGenerate2 -= toCopy2;
            outOff2 += toCopy2;
        }
        return len;
    }
}
