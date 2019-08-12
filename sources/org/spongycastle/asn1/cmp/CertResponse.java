package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class CertResponse extends ASN1Object {
    private ASN1Integer certReqId;
    private CertifiedKeyPair certifiedKeyPair;
    private ASN1OctetString rspInfo;
    private PKIStatusInfo status;

    private CertResponse(ASN1Sequence seq) {
        this.certReqId = ASN1Integer.getInstance(seq.getObjectAt(0));
        this.status = PKIStatusInfo.getInstance(seq.getObjectAt(1));
        if (seq.size() < 3) {
            return;
        }
        if (seq.size() == 3) {
            ASN1Encodable o = seq.getObjectAt(2);
            if (o instanceof ASN1OctetString) {
                this.rspInfo = ASN1OctetString.getInstance(o);
            } else {
                this.certifiedKeyPair = CertifiedKeyPair.getInstance(o);
            }
        } else {
            this.certifiedKeyPair = CertifiedKeyPair.getInstance(seq.getObjectAt(2));
            this.rspInfo = ASN1OctetString.getInstance(seq.getObjectAt(3));
        }
    }

    public static CertResponse getInstance(Object o) {
        if (o instanceof CertResponse) {
            return (CertResponse) o;
        }
        if (o != null) {
            return new CertResponse(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public CertResponse(ASN1Integer certReqId2, PKIStatusInfo status2) {
        this(certReqId2, status2, null, null);
    }

    public CertResponse(ASN1Integer certReqId2, PKIStatusInfo status2, CertifiedKeyPair certifiedKeyPair2, ASN1OctetString rspInfo2) {
        if (certReqId2 == null) {
            throw new IllegalArgumentException("'certReqId' cannot be null");
        } else if (status2 == null) {
            throw new IllegalArgumentException("'status' cannot be null");
        } else {
            this.certReqId = certReqId2;
            this.status = status2;
            this.certifiedKeyPair = certifiedKeyPair2;
            this.rspInfo = rspInfo2;
        }
    }

    public ASN1Integer getCertReqId() {
        return this.certReqId;
    }

    public PKIStatusInfo getStatus() {
        return this.status;
    }

    public CertifiedKeyPair getCertifiedKeyPair() {
        return this.certifiedKeyPair;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.certReqId);
        v.add(this.status);
        if (this.certifiedKeyPair != null) {
            v.add(this.certifiedKeyPair);
        }
        if (this.rspInfo != null) {
            v.add(this.rspInfo);
        }
        return new DERSequence(v);
    }
}
