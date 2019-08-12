package org.ejbca.cvc;

import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.crypto.tls.CipherSuite;

public enum CVCTagEnum {
    CV_CERTIFICATE(EACTags.CV_CERTIFICATE, true),
    CERTIFICATE_BODY(32590, true),
    PROFILE_IDENTIFIER(24361),
    PUBLIC_KEY(32585, true),
    HOLDER_REFERENCE(24352),
    HOLDER_AUTH_TEMPLATE(32588, true),
    EFFECTIVE_DATE(24357),
    EXPIRATION_DATE(24356),
    SIGNATURE(EACTags.SIGNATURE),
    OID(6),
    CA_REFERENCE(66),
    REQ_AUTHENTICATION(103, true),
    ROLE_AND_ACCESS_RIGHTS(83),
    MODULUS(129),
    EXPONENT(ISO781611.BIOMETRIC_SUBTYPE_TAG),
    COEFFICIENT_A(ISO781611.BIOMETRIC_SUBTYPE_TAG),
    COEFFICIENT_B(ISO781611.CREATION_DATE_AND_TIME_TAG),
    BASE_POINT_G(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA),
    BASE_POINT_R_ORDER(133),
    PUBLIC_POINT_Y(134),
    COFACTOR_F(135);
    
    private boolean isSequence;
    private int value;

    private CVCTagEnum(int i) {
        this(r2, r3, i, false);
    }

    private CVCTagEnum(int i, boolean z) {
        this.value = i;
        this.isSequence = z;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isSequence() {
        return this.isSequence;
    }
}
