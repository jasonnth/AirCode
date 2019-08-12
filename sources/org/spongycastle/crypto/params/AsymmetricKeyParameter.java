package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class AsymmetricKeyParameter implements CipherParameters {
    boolean privateKey;

    public AsymmetricKeyParameter(boolean privateKey2) {
        this.privateKey = privateKey2;
    }

    public boolean isPrivate() {
        return this.privateKey;
    }
}
