package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

public class KDFParameters implements DerivationParameters {

    /* renamed from: iv */
    byte[] f6857iv;
    byte[] shared;

    public KDFParameters(byte[] shared2, byte[] iv) {
        this.shared = shared2;
        this.f6857iv = iv;
    }

    public byte[] getSharedSecret() {
        return this.shared;
    }

    public byte[] getIV() {
        return this.f6857iv;
    }
}
