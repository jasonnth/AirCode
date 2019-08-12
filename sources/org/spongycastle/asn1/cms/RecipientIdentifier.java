package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

public class RecipientIdentifier extends ASN1Object implements ASN1Choice {

    /* renamed from: id */
    private ASN1Encodable f6360id;

    public RecipientIdentifier(IssuerAndSerialNumber id) {
        this.f6360id = id;
    }

    public RecipientIdentifier(ASN1OctetString id) {
        this.f6360id = new DERTaggedObject(false, 0, id);
    }

    public RecipientIdentifier(ASN1Primitive id) {
        this.f6360id = id;
    }

    public static RecipientIdentifier getInstance(Object o) {
        if (o == null || (o instanceof RecipientIdentifier)) {
            return (RecipientIdentifier) o;
        }
        if (o instanceof IssuerAndSerialNumber) {
            return new RecipientIdentifier((IssuerAndSerialNumber) o);
        }
        if (o instanceof ASN1OctetString) {
            return new RecipientIdentifier((ASN1OctetString) o);
        }
        if (o instanceof ASN1Primitive) {
            return new RecipientIdentifier((ASN1Primitive) o);
        }
        throw new IllegalArgumentException("Illegal object in RecipientIdentifier: " + o.getClass().getName());
    }

    public boolean isTagged() {
        return this.f6360id instanceof ASN1TaggedObject;
    }

    public ASN1Encodable getId() {
        if (this.f6360id instanceof ASN1TaggedObject) {
            return ASN1OctetString.getInstance((ASN1TaggedObject) this.f6360id, false);
        }
        return IssuerAndSerialNumber.getInstance(this.f6360id);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.f6360id.toASN1Primitive();
    }
}
