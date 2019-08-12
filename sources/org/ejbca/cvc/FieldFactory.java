package org.ejbca.cvc;

import java.io.IOException;

public class FieldFactory {
    public static AbstractDataField decodeField(CVCTagEnum cVCTagEnum, byte[] bArr) throws IOException {
        if (cVCTagEnum.isSequence()) {
            throw new IllegalArgumentException("Tag " + cVCTagEnum + " is a sequence");
        }
        switch (cVCTagEnum) {
            case EFFECTIVE_DATE:
                return new DateField(cVCTagEnum, bArr);
            case EXPIRATION_DATE:
                return new DateField(cVCTagEnum, bArr);
            case CA_REFERENCE:
                return new CAReferenceField(bArr);
            case HOLDER_REFERENCE:
                return new HolderReferenceField(bArr);
            case OID:
                return new OIDField(bArr);
            case ROLE_AND_ACCESS_RIGHTS:
                return new AuthorizationField(bArr);
            case PROFILE_IDENTIFIER:
                return new IntegerField(cVCTagEnum, bArr);
            case COFACTOR_F:
                return new IntegerField(cVCTagEnum, bArr);
            default:
                return new ByteField(cVCTagEnum, bArr);
        }
    }
}
