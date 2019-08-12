package org.ejbca.cvc;

public class HolderReferenceField extends ReferenceField {
    public HolderReferenceField(String str, String str2, String str3) {
        super(CVCTagEnum.HOLDER_REFERENCE, str, str2, str3);
    }

    public HolderReferenceField(byte[] bArr) {
        super(CVCTagEnum.HOLDER_REFERENCE, bArr);
    }
}
