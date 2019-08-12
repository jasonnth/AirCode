package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.crmf.CertTemplate;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.X509Extensions;

public class RevDetails extends ASN1Object {
    private CertTemplate certDetails;
    private Extensions crlEntryDetails;

    private RevDetails(ASN1Sequence seq) {
        this.certDetails = CertTemplate.getInstance(seq.getObjectAt(0));
        if (seq.size() > 1) {
            this.crlEntryDetails = Extensions.getInstance(seq.getObjectAt(1));
        }
    }

    public static RevDetails getInstance(Object o) {
        if (o instanceof RevDetails) {
            return (RevDetails) o;
        }
        if (o != null) {
            return new RevDetails(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public RevDetails(CertTemplate certDetails2) {
        this.certDetails = certDetails2;
    }

    public RevDetails(CertTemplate certDetails2, X509Extensions crlEntryDetails2) {
        this.certDetails = certDetails2;
        this.crlEntryDetails = Extensions.getInstance(crlEntryDetails2.toASN1Primitive());
    }

    public RevDetails(CertTemplate certDetails2, Extensions crlEntryDetails2) {
        this.certDetails = certDetails2;
        this.crlEntryDetails = crlEntryDetails2;
    }

    public CertTemplate getCertDetails() {
        return this.certDetails;
    }

    public Extensions getCrlEntryDetails() {
        return this.crlEntryDetails;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.certDetails);
        if (this.crlEntryDetails != null) {
            v.add(this.crlEntryDetails);
        }
        return new DERSequence(v);
    }
}
