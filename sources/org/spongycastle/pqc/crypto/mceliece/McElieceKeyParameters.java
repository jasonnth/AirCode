package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public class McElieceKeyParameters extends AsymmetricKeyParameter {
    private McElieceParameters params;

    public McElieceKeyParameters(boolean isPrivate, McElieceParameters params2) {
        super(isPrivate);
        this.params = params2;
    }

    public McElieceParameters getParameters() {
        return this.params;
    }
}
