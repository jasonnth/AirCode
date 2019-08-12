package org.spongycastle.crypto.p326ec;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.math.p332ec.ECPoint;

/* renamed from: org.spongycastle.crypto.ec.ECEncryptor */
public interface ECEncryptor {
    ECPair encrypt(ECPoint eCPoint);

    void init(CipherParameters cipherParameters);
}
