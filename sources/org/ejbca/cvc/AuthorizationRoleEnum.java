package org.ejbca.cvc;

public enum AuthorizationRoleEnum implements AuthorizationRole {
    CVCA(192),
    DV_D(128),
    DV_F(64),
    IS(0);
    
    private byte value;

    private AuthorizationRoleEnum(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return this.value;
    }

    public boolean isCVCA() {
        return this == CVCA;
    }

    public boolean isDV() {
        return this == DV_D || this == DV_F;
    }

    public boolean isDomesticDV() {
        return this == DV_D;
    }

    public boolean isForeignDV() {
        return this == DV_F;
    }

    public boolean isAccreditationBodyDV() {
        return false;
    }

    public boolean isCertificationServiceProviderDV() {
        return false;
    }

    public boolean isIS() {
        return this == IS;
    }

    public boolean isAuthenticationTerminal() {
        return false;
    }

    public boolean isSignatureTerminal() {
        return false;
    }

    public String toString() {
        switch (this) {
            case CVCA:
                return "CVCA";
            case DV_D:
                return "DV-domestic";
            case DV_F:
                return "DV-foreign";
            case IS:
                return "IS";
            default:
                throw new IllegalStateException("Enum case not handled");
        }
    }
}
