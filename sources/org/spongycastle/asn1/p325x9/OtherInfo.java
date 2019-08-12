package org.spongycastle.asn1.p325x9;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

/* renamed from: org.spongycastle.asn1.x9.OtherInfo */
public class OtherInfo extends ASN1Object {
    private KeySpecificInfo keyInfo;
    private ASN1OctetString partyAInfo;
    private ASN1OctetString suppPubInfo;

    public OtherInfo(KeySpecificInfo keyInfo2, ASN1OctetString partyAInfo2, ASN1OctetString suppPubInfo2) {
        this.keyInfo = keyInfo2;
        this.partyAInfo = partyAInfo2;
        this.suppPubInfo = suppPubInfo2;
    }

    public static OtherInfo getInstance(Object obj) {
        if (obj instanceof OtherInfo) {
            return (OtherInfo) obj;
        }
        if (obj != null) {
            return new OtherInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OtherInfo(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.keyInfo = KeySpecificInfo.getInstance(e.nextElement());
        while (e.hasMoreElements()) {
            DERTaggedObject o = (DERTaggedObject) e.nextElement();
            if (o.getTagNo() == 0) {
                this.partyAInfo = (ASN1OctetString) o.getObject();
            } else if (o.getTagNo() == 2) {
                this.suppPubInfo = (ASN1OctetString) o.getObject();
            }
        }
    }

    public KeySpecificInfo getKeyInfo() {
        return this.keyInfo;
    }

    public ASN1OctetString getPartyAInfo() {
        return this.partyAInfo;
    }

    public ASN1OctetString getSuppPubInfo() {
        return this.suppPubInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.keyInfo);
        if (this.partyAInfo != null) {
            v.add(new DERTaggedObject(0, this.partyAInfo));
        }
        v.add(new DERTaggedObject(2, this.suppPubInfo));
        return new DERSequence(v);
    }
}
