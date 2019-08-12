package com.airbnb.jitney.event.logging.IdentityVerification.p125v1;

/* renamed from: com.airbnb.jitney.event.logging.IdentityVerification.v1.IdentityVerificationType */
public enum IdentityVerificationType {
    PHOTO_WITH_FACE(1),
    GOVERNMENT_ID(2),
    SELFIE(3),
    ID_SELFIE_MATCH(4),
    EMAIL(5),
    PHONE_NUMBER(6);
    
    public final int value;

    private IdentityVerificationType(int value2) {
        this.value = value2;
    }
}
