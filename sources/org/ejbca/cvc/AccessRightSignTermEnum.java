package org.ejbca.cvc;

public enum AccessRightSignTermEnum implements AccessRights {
    ACCESS_NONE(0),
    ACCESS_SIGN(1),
    ACCESS_QUALSIGN(2),
    ACCESS_SIGN_AND_QUALSIGN(3);
    
    private byte value;

    private AccessRightSignTermEnum(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return this.value;
    }

    public boolean allowsSignature() {
        return (this.value & ACCESS_SIGN.value) != 0;
    }

    public boolean allowsQualifiedSignature() {
        return (this.value & ACCESS_QUALSIGN.value) != 0;
    }

    public byte[] getEncoded() {
        return new byte[]{this.value};
    }

    public String toString() {
        switch (this) {
            case ACCESS_SIGN:
                return "Signature";
            case ACCESS_QUALSIGN:
                return "Qualified_Signature";
            case ACCESS_SIGN_AND_QUALSIGN:
                return "Signature_and_Qualified_Signature";
            case ACCESS_NONE:
                return "none";
            default:
                throw new IllegalStateException("Enum case not handled");
        }
    }
}
