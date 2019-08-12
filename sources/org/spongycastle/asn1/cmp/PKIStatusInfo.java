package org.spongycastle.asn1.cmp;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;

public class PKIStatusInfo extends ASN1Object {
    DERBitString failInfo;
    ASN1Integer status;
    PKIFreeText statusString;

    public static PKIStatusInfo getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static PKIStatusInfo getInstance(Object obj) {
        if (obj instanceof PKIStatusInfo) {
            return (PKIStatusInfo) obj;
        }
        if (obj != null) {
            return new PKIStatusInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private PKIStatusInfo(ASN1Sequence seq) {
        this.status = ASN1Integer.getInstance(seq.getObjectAt(0));
        this.statusString = null;
        this.failInfo = null;
        if (seq.size() > 2) {
            this.statusString = PKIFreeText.getInstance(seq.getObjectAt(1));
            this.failInfo = DERBitString.getInstance(seq.getObjectAt(2));
        } else if (seq.size() > 1) {
            ASN1Encodable obj = seq.getObjectAt(1);
            if (obj instanceof DERBitString) {
                this.failInfo = DERBitString.getInstance(obj);
            } else {
                this.statusString = PKIFreeText.getInstance(obj);
            }
        }
    }

    public PKIStatusInfo(PKIStatus status2) {
        this.status = ASN1Integer.getInstance(status2.toASN1Primitive());
    }

    public PKIStatusInfo(PKIStatus status2, PKIFreeText statusString2) {
        this.status = ASN1Integer.getInstance(status2.toASN1Primitive());
        this.statusString = statusString2;
    }

    public PKIStatusInfo(PKIStatus status2, PKIFreeText statusString2, PKIFailureInfo failInfo2) {
        this.status = ASN1Integer.getInstance(status2.toASN1Primitive());
        this.statusString = statusString2;
        this.failInfo = failInfo2;
    }

    public BigInteger getStatus() {
        return this.status.getValue();
    }

    public PKIFreeText getStatusString() {
        return this.statusString;
    }

    public DERBitString getFailInfo() {
        return this.failInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.status);
        if (this.statusString != null) {
            v.add(this.statusString);
        }
        if (this.failInfo != null) {
            v.add(this.failInfo);
        }
        return new DERSequence(v);
    }
}
