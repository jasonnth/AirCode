package org.spongycastle.jce.spec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.spongycastle.jce.interfaces.IESKey;

public class IEKeySpec implements KeySpec, IESKey {
    private PrivateKey privKey;
    private PublicKey pubKey;

    public IEKeySpec(PrivateKey privKey2, PublicKey pubKey2) {
        this.privKey = privKey2;
        this.pubKey = pubKey2;
    }

    public PublicKey getPublic() {
        return this.pubKey;
    }

    public PrivateKey getPrivate() {
        return this.privKey;
    }

    public String getAlgorithm() {
        return "IES";
    }

    public String getFormat() {
        return null;
    }

    public byte[] getEncoded() {
        return null;
    }
}
