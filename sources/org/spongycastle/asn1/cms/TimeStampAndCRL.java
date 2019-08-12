package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.CertificateList;

public class TimeStampAndCRL extends ASN1Object {
    private CertificateList crl;
    private ContentInfo timeStamp;

    public TimeStampAndCRL(ContentInfo timeStamp2) {
        this.timeStamp = timeStamp2;
    }

    private TimeStampAndCRL(ASN1Sequence seq) {
        this.timeStamp = ContentInfo.getInstance(seq.getObjectAt(0));
        if (seq.size() == 2) {
            this.crl = CertificateList.getInstance(seq.getObjectAt(1));
        }
    }

    public static TimeStampAndCRL getInstance(Object obj) {
        if (obj instanceof TimeStampAndCRL) {
            return (TimeStampAndCRL) obj;
        }
        if (obj != null) {
            return new TimeStampAndCRL(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ContentInfo getTimeStampToken() {
        return this.timeStamp;
    }

    public CertificateList getCertificateList() {
        return this.crl;
    }

    public CertificateList getCRL() {
        return this.crl;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.timeStamp);
        if (this.crl != null) {
            v.add(this.crl);
        }
        return new DERSequence(v);
    }
}
