package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class MQVPrivateParameters implements CipherParameters {
    private ECPrivateKeyParameters ephemeralPrivateKey;
    private ECPublicKeyParameters ephemeralPublicKey;
    private ECPrivateKeyParameters staticPrivateKey;

    public MQVPrivateParameters(ECPrivateKeyParameters staticPrivateKey2, ECPrivateKeyParameters ephemeralPrivateKey2) {
        this(staticPrivateKey2, ephemeralPrivateKey2, null);
    }

    public MQVPrivateParameters(ECPrivateKeyParameters staticPrivateKey2, ECPrivateKeyParameters ephemeralPrivateKey2, ECPublicKeyParameters ephemeralPublicKey2) {
        this.staticPrivateKey = staticPrivateKey2;
        this.ephemeralPrivateKey = ephemeralPrivateKey2;
        this.ephemeralPublicKey = ephemeralPublicKey2;
    }

    public ECPrivateKeyParameters getStaticPrivateKey() {
        return this.staticPrivateKey;
    }

    public ECPrivateKeyParameters getEphemeralPrivateKey() {
        return this.ephemeralPrivateKey;
    }

    public ECPublicKeyParameters getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }
}
