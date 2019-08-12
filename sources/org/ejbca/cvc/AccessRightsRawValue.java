package org.ejbca.cvc;

public class AccessRightsRawValue implements AccessRights {
    private static final String EXCEPTION_MSG = "Access Right object does not know its type/OID yet. This is a bug.";
    private final byte[] bytes;

    AccessRightsRawValue(byte[] bArr) {
        this.bytes = bArr;
    }

    public byte[] getEncoded() {
        return this.bytes;
    }

    public String name() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }
}
