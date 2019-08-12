package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.crmf.CertId;
import org.spongycastle.asn1.x509.CertificateList;

public class RevRepContentBuilder {
    private ASN1EncodableVector crls = new ASN1EncodableVector();
    private ASN1EncodableVector revCerts = new ASN1EncodableVector();
    private ASN1EncodableVector status = new ASN1EncodableVector();

    public RevRepContentBuilder add(PKIStatusInfo status2) {
        this.status.add(status2);
        return this;
    }

    public RevRepContentBuilder add(PKIStatusInfo status2, CertId certId) {
        if (this.status.size() != this.revCerts.size()) {
            throw new IllegalStateException("status and revCerts sequence must be in common order");
        }
        this.status.add(status2);
        this.revCerts.add(certId);
        return this;
    }

    public RevRepContentBuilder addCrl(CertificateList crl) {
        this.crls.add(crl);
        return this;
    }

    public RevRepContent build() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new DERSequence(this.status));
        if (this.revCerts.size() != 0) {
            v.add(new DERTaggedObject(true, 0, new DERSequence(this.revCerts)));
        }
        if (this.crls.size() != 0) {
            v.add(new DERTaggedObject(true, 1, new DERSequence(this.crls)));
        }
        return RevRepContent.getInstance(new DERSequence(v));
    }
}
