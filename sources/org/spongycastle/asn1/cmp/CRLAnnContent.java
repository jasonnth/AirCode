package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.CertificateList;

public class CRLAnnContent extends ASN1Object {
    private ASN1Sequence content;

    private CRLAnnContent(ASN1Sequence seq) {
        this.content = seq;
    }

    public static CRLAnnContent getInstance(Object o) {
        if (o instanceof CRLAnnContent) {
            return (CRLAnnContent) o;
        }
        if (o != null) {
            return new CRLAnnContent(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public CRLAnnContent(CertificateList crl) {
        this.content = new DERSequence((ASN1Encodable) crl);
    }

    public CertificateList[] getCertificateLists() {
        CertificateList[] result = new CertificateList[this.content.size()];
        for (int i = 0; i != result.length; i++) {
            result[i] = CertificateList.getInstance(this.content.getObjectAt(i));
        }
        return result;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}
