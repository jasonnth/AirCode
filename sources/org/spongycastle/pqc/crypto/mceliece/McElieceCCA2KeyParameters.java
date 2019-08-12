package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public class McElieceCCA2KeyParameters extends AsymmetricKeyParameter {
    private McElieceCCA2Parameters params;

    public McElieceCCA2KeyParameters(boolean isPrivate, McElieceCCA2Parameters params2) {
        super(isPrivate);
        this.params = params2;
    }

    public McElieceCCA2Parameters getParameters() {
        return this.params;
    }
}
