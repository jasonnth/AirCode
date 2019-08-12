package org.spongycastle.asn1.ess;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;

public class ContentIdentifier extends ASN1Object {
    ASN1OctetString value;

    public static ContentIdentifier getInstance(Object o) {
        if (o instanceof ContentIdentifier) {
            return (ContentIdentifier) o;
        }
        if (o != null) {
            return new ContentIdentifier(ASN1OctetString.getInstance(o));
        }
        return null;
    }

    private ContentIdentifier(ASN1OctetString value2) {
        this.value = value2;
    }

    public ContentIdentifier(byte[] value2) {
        this((ASN1OctetString) new DEROctetString(value2));
    }

    public ASN1OctetString getValue() {
        return this.value;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.value;
    }
}
