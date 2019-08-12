package org.ejbca.cvc;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import org.ejbca.cvc.exception.ConstructionException;

public class PublicKeyRSA extends CVCPublicKey implements RSAPublicKey {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.OID, CVCTagEnum.MODULUS, CVCTagEnum.EXPONENT};

    /* access modifiers changed from: protected */
    public CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    PublicKeyRSA(GenericPublicKeyField genericPublicKeyField) throws ConstructionException, NoSuchFieldException {
        ByteField byteField = (ByteField) genericPublicKeyField.getSubfield(CVCTagEnum.MODULUS);
        byteField.setShowBitLength(true);
        addSubfield(genericPublicKeyField.getSubfield(CVCTagEnum.OID));
        addSubfield(byteField);
        addSubfield(genericPublicKeyField.getSubfield(CVCTagEnum.EXPONENT));
    }

    PublicKeyRSA(OIDField oIDField, RSAPublicKey rSAPublicKey) throws ConstructionException {
        addSubfield(oIDField);
        addSubfield(new ByteField(CVCTagEnum.MODULUS, trimByteArray(rSAPublicKey.getModulus().toByteArray()), true));
        addSubfield(new ByteField(CVCTagEnum.EXPONENT, trimByteArray(rSAPublicKey.getPublicExponent().toByteArray())));
    }

    public String getAlgorithm() {
        return "RSA";
    }

    public String getFormat() {
        return "CVC";
    }

    public BigInteger getPublicExponent() {
        try {
            return new BigInteger(1, ((ByteField) getSubfield(CVCTagEnum.EXPONENT)).getData());
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    public BigInteger getModulus() {
        try {
            return new BigInteger(1, ((ByteField) getSubfield(CVCTagEnum.MODULUS)).getData());
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }
}
