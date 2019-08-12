package org.ejbca.cvc;

import java.io.IOException;
import java.security.PublicKey;

public abstract class CVCPublicKey extends AbstractSequence implements PublicKey {
    CVCPublicKey() {
        super(CVCTagEnum.PUBLIC_KEY);
    }

    public byte[] getEncoded() {
        boolean z = false;
        try {
            return getDEREncoded();
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    public OIDField getObjectIdentifier() throws NoSuchFieldException {
        return (OIDField) getSubfield(CVCTagEnum.OID);
    }
}
