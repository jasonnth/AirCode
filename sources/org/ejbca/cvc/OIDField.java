package org.ejbca.cvc;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;

public class OIDField extends AbstractDataField {

    /* renamed from: id */
    private String f6331id;

    OIDField() {
        super(CVCTagEnum.OID);
    }

    OIDField(String str) {
        this();
        this.f6331id = str;
    }

    OIDField(byte[] bArr) {
        this();
        this.f6331id = ASN1ObjectIdentifier.getInstance(new DERTaggedObject(true, 0, new DEROctetString(bArr)), false).getId();
    }

    public String getValue() {
        return this.f6331id;
    }

    /* access modifiers changed from: protected */
    public byte[] getEncoded() {
        try {
            byte[] encoded = new DERObjectIdentifier(this.f6331id).getEncoded();
            byte[] bArr = new byte[(encoded.length - 2)];
            System.arraycopy(encoded, 2, bArr, 0, bArr.length);
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public String valueAsText() {
        return this.f6331id;
    }

    public String toString() {
        return getValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof OIDField) {
            return this.f6331id.equals(((OIDField) obj).getValue());
        }
        return false;
    }
}
