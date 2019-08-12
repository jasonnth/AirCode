package org.ejbca.cvc;

public class AuthorizationRoleRawValue implements AuthorizationRole {
    private static final String EXCEPTION_MSG = "Authorization Role object does not know its type/OID yet. This is a bug.";
    private final byte value;

    AuthorizationRoleRawValue(byte b) {
        this.value = b;
    }

    public boolean isCVCA() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isDomesticDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isForeignDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isAccreditationBodyDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isCertificationServiceProviderDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isIS() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isAuthenticationTerminal() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public boolean isSignatureTerminal() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    public byte getValue() {
        return this.value;
    }

    public String name() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }
}
