package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class ParametersWithIV implements CipherParameters {

    /* renamed from: iv */
    private byte[] f6860iv;
    private CipherParameters parameters;

    public ParametersWithIV(CipherParameters parameters2, byte[] iv) {
        this(parameters2, iv, 0, iv.length);
    }

    public ParametersWithIV(CipherParameters parameters2, byte[] iv, int ivOff, int ivLen) {
        this.f6860iv = new byte[ivLen];
        this.parameters = parameters2;
        System.arraycopy(iv, ivOff, this.f6860iv, 0, ivLen);
    }

    public byte[] getIV() {
        return this.f6860iv;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
