package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;

public class PKIConfirmContent extends ASN1Object {
    private ASN1Null val;

    private PKIConfirmContent(ASN1Null val2) {
        this.val = val2;
    }

    public static PKIConfirmContent getInstance(Object o) {
        if (o == null || (o instanceof PKIConfirmContent)) {
            return (PKIConfirmContent) o;
        }
        if (o instanceof ASN1Null) {
            return new PKIConfirmContent((ASN1Null) o);
        }
        throw new IllegalArgumentException("Invalid object: " + o.getClass().getName());
    }

    public PKIConfirmContent() {
        this.val = DERNull.INSTANCE;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.val;
    }
}
