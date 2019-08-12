package org.ejbca.cvc;

public class CAReferenceField extends ReferenceField {
    public CAReferenceField(String str, String str2, String str3) {
        super(CVCTagEnum.CA_REFERENCE, str, str2, str3);
    }

    public CAReferenceField(byte[] bArr) {
        super(CVCTagEnum.CA_REFERENCE, bArr);
    }
}
