package org.ejbca.cvc;

public enum AuthorizationRoleSignTermEnum implements AuthorizationRole {
    CVCA(192),
    DV_AB(128),
    DV_CSP(64),
    SIGNTERM(0);
    
    private byte value;

    private AuthorizationRoleSignTermEnum(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return this.value;
    }

    public boolean isCVCA() {
        return this == CVCA;
    }

    public boolean isDV() {
        return this == DV_AB || this == DV_CSP;
    }

    public boolean isDomesticDV() {
        return false;
    }

    public boolean isForeignDV() {
        return false;
    }

    public boolean isAccreditationBodyDV() {
        return this == DV_AB;
    }

    public boolean isCertificationServiceProviderDV() {
        return this == DV_CSP;
    }

    public boolean isIS() {
        return false;
    }

    public boolean isAuthenticationTerminal() {
        return false;
    }

    public boolean isSignatureTerminal() {
        return this == SIGNTERM;
    }

    public String toString() {
        switch (this) {
            case CVCA:
                return "CVCA";
            case DV_AB:
                return "DV-Accreditation-Body";
            case DV_CSP:
                return "DV-Certification-Service-Provider";
            case SIGNTERM:
                return "Signature-Terminal";
            default:
                throw new IllegalStateException("Enum case not handled");
        }
    }
}
