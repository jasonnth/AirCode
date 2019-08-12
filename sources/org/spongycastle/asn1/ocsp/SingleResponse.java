package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.X509Extensions;

public class SingleResponse extends ASN1Object {
    private CertID certID;
    private CertStatus certStatus;
    private ASN1GeneralizedTime nextUpdate;
    private Extensions singleExtensions;
    private ASN1GeneralizedTime thisUpdate;

    public SingleResponse(CertID certID2, CertStatus certStatus2, ASN1GeneralizedTime thisUpdate2, ASN1GeneralizedTime nextUpdate2, X509Extensions singleExtensions2) {
        this(certID2, certStatus2, thisUpdate2, nextUpdate2, Extensions.getInstance(singleExtensions2));
    }

    public SingleResponse(CertID certID2, CertStatus certStatus2, ASN1GeneralizedTime thisUpdate2, ASN1GeneralizedTime nextUpdate2, Extensions singleExtensions2) {
        this.certID = certID2;
        this.certStatus = certStatus2;
        this.thisUpdate = thisUpdate2;
        this.nextUpdate = nextUpdate2;
        this.singleExtensions = singleExtensions2;
    }

    private SingleResponse(ASN1Sequence seq) {
        this.certID = CertID.getInstance(seq.getObjectAt(0));
        this.certStatus = CertStatus.getInstance(seq.getObjectAt(1));
        this.thisUpdate = ASN1GeneralizedTime.getInstance(seq.getObjectAt(2));
        if (seq.size() > 4) {
            this.nextUpdate = ASN1GeneralizedTime.getInstance((ASN1TaggedObject) seq.getObjectAt(3), true);
            this.singleExtensions = Extensions.getInstance((ASN1TaggedObject) seq.getObjectAt(4), true);
        } else if (seq.size() > 3) {
            ASN1TaggedObject o = (ASN1TaggedObject) seq.getObjectAt(3);
            if (o.getTagNo() == 0) {
                this.nextUpdate = ASN1GeneralizedTime.getInstance(o, true);
            } else {
                this.singleExtensions = Extensions.getInstance(o, true);
            }
        }
    }

    public static SingleResponse getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static SingleResponse getInstance(Object obj) {
        if (obj instanceof SingleResponse) {
            return (SingleResponse) obj;
        }
        if (obj != null) {
            return new SingleResponse(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertID getCertID() {
        return this.certID;
    }

    public CertStatus getCertStatus() {
        return this.certStatus;
    }

    public ASN1GeneralizedTime getThisUpdate() {
        return this.thisUpdate;
    }

    public ASN1GeneralizedTime getNextUpdate() {
        return this.nextUpdate;
    }

    public Extensions getSingleExtensions() {
        return this.singleExtensions;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.certID);
        v.add(this.certStatus);
        v.add(this.thisUpdate);
        if (this.nextUpdate != null) {
            v.add(new DERTaggedObject(true, 0, this.nextUpdate));
        }
        if (this.singleExtensions != null) {
            v.add(new DERTaggedObject(true, 1, this.singleExtensions));
        }
        return new DERSequence(v);
    }
}
