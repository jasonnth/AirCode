package org.spongycastle.asn1.cms.ecc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.util.Arrays;

public class ECCCMSSharedInfo extends ASN1Object {
    private final byte[] entityUInfo;
    private final AlgorithmIdentifier keyInfo;
    private final byte[] suppPubInfo;

    public ECCCMSSharedInfo(AlgorithmIdentifier keyInfo2, byte[] entityUInfo2, byte[] suppPubInfo2) {
        this.keyInfo = keyInfo2;
        this.entityUInfo = Arrays.clone(entityUInfo2);
        this.suppPubInfo = Arrays.clone(suppPubInfo2);
    }

    public ECCCMSSharedInfo(AlgorithmIdentifier keyInfo2, byte[] suppPubInfo2) {
        this.keyInfo = keyInfo2;
        this.entityUInfo = null;
        this.suppPubInfo = Arrays.clone(suppPubInfo2);
    }

    private ECCCMSSharedInfo(ASN1Sequence seq) {
        this.keyInfo = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
        if (seq.size() == 2) {
            this.entityUInfo = null;
            this.suppPubInfo = ASN1OctetString.getInstance((ASN1TaggedObject) seq.getObjectAt(1), true).getOctets();
            return;
        }
        this.entityUInfo = ASN1OctetString.getInstance((ASN1TaggedObject) seq.getObjectAt(1), true).getOctets();
        this.suppPubInfo = ASN1OctetString.getInstance((ASN1TaggedObject) seq.getObjectAt(2), true).getOctets();
    }

    public static ECCCMSSharedInfo getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static ECCCMSSharedInfo getInstance(Object obj) {
        if (obj instanceof ECCCMSSharedInfo) {
            return (ECCCMSSharedInfo) obj;
        }
        if (obj != null) {
            return new ECCCMSSharedInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.keyInfo);
        if (this.entityUInfo != null) {
            v.add(new DERTaggedObject(true, 0, new DEROctetString(this.entityUInfo)));
        }
        v.add(new DERTaggedObject(true, 2, new DEROctetString(this.suppPubInfo)));
        return new DERSequence(v);
    }
}
