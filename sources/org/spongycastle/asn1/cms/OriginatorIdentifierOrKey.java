package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.SubjectKeyIdentifier;

public class OriginatorIdentifierOrKey extends ASN1Object implements ASN1Choice {

    /* renamed from: id */
    private ASN1Encodable f6359id;

    public OriginatorIdentifierOrKey(IssuerAndSerialNumber id) {
        this.f6359id = id;
    }

    public OriginatorIdentifierOrKey(ASN1OctetString id) {
        this(new SubjectKeyIdentifier(id.getOctets()));
    }

    public OriginatorIdentifierOrKey(SubjectKeyIdentifier id) {
        this.f6359id = new DERTaggedObject(false, 0, id);
    }

    public OriginatorIdentifierOrKey(OriginatorPublicKey id) {
        this.f6359id = new DERTaggedObject(false, 1, id);
    }

    public OriginatorIdentifierOrKey(ASN1Primitive id) {
        this.f6359id = id;
    }

    public static OriginatorIdentifierOrKey getInstance(ASN1TaggedObject o, boolean explicit) {
        if (explicit) {
            return getInstance(o.getObject());
        }
        throw new IllegalArgumentException("Can't implicitly tag OriginatorIdentifierOrKey");
    }

    public static OriginatorIdentifierOrKey getInstance(Object o) {
        if (o == null || (o instanceof OriginatorIdentifierOrKey)) {
            return (OriginatorIdentifierOrKey) o;
        }
        if ((o instanceof IssuerAndSerialNumber) || (o instanceof ASN1Sequence)) {
            return new OriginatorIdentifierOrKey(IssuerAndSerialNumber.getInstance(o));
        }
        if (o instanceof ASN1TaggedObject) {
            ASN1TaggedObject tagged = (ASN1TaggedObject) o;
            if (tagged.getTagNo() == 0) {
                return new OriginatorIdentifierOrKey(SubjectKeyIdentifier.getInstance(tagged, false));
            }
            if (tagged.getTagNo() == 1) {
                return new OriginatorIdentifierOrKey(OriginatorPublicKey.getInstance(tagged, false));
            }
        }
        throw new IllegalArgumentException("Invalid OriginatorIdentifierOrKey: " + o.getClass().getName());
    }

    public ASN1Encodable getId() {
        return this.f6359id;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber() {
        if (this.f6359id instanceof IssuerAndSerialNumber) {
            return (IssuerAndSerialNumber) this.f6359id;
        }
        return null;
    }

    public SubjectKeyIdentifier getSubjectKeyIdentifier() {
        if (!(this.f6359id instanceof ASN1TaggedObject) || ((ASN1TaggedObject) this.f6359id).getTagNo() != 0) {
            return null;
        }
        return SubjectKeyIdentifier.getInstance((ASN1TaggedObject) this.f6359id, false);
    }

    public OriginatorPublicKey getOriginatorKey() {
        if (!(this.f6359id instanceof ASN1TaggedObject) || ((ASN1TaggedObject) this.f6359id).getTagNo() != 1) {
            return null;
        }
        return OriginatorPublicKey.getInstance((ASN1TaggedObject) this.f6359id, false);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.f6359id.toASN1Primitive();
    }
}
