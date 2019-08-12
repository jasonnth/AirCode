package org.spongycastle.crypto.p326ec;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.math.p332ec.ECPoint;

/* renamed from: org.spongycastle.crypto.ec.ECElGamalDecryptor */
public class ECElGamalDecryptor implements ECDecryptor {
    private ECPrivateKeyParameters key;

    public void init(CipherParameters param) {
        if (!(param instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("ECPrivateKeyParameters are required for decryption.");
        }
        this.key = (ECPrivateKeyParameters) param;
    }

    public ECPoint decrypt(ECPair pair) {
        if (this.key == null) {
            throw new IllegalStateException("ECElGamalDecryptor not initialised");
        }
        return pair.getY().subtract(pair.getX().multiply(this.key.getD())).normalize();
    }
}
