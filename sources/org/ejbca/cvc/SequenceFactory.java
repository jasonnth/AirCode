package org.ejbca.cvc;

public class SequenceFactory {
    public static AbstractSequence createSequence(CVCTagEnum cVCTagEnum) {
        if (!cVCTagEnum.isSequence()) {
            throw new IllegalArgumentException("Tag " + cVCTagEnum + " is not a sequence");
        }
        switch (cVCTagEnum) {
            case CV_CERTIFICATE:
                return new CVCertificate();
            case CERTIFICATE_BODY:
                return new CVCertificateBody();
            case PUBLIC_KEY:
                return new GenericPublicKeyField();
            case HOLDER_AUTH_TEMPLATE:
                return new CVCAuthorizationTemplate();
            case REQ_AUTHENTICATION:
                return new CVCAuthenticatedRequest();
            default:
                throw new IllegalArgumentException("Unsupported type " + cVCTagEnum);
        }
    }
}
