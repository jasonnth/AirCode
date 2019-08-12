package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.crmf.EncryptedValue;

public class CertOrEncCert extends ASN1Object implements ASN1Choice {
    private CMPCertificate certificate;
    private EncryptedValue encryptedCert;

    private CertOrEncCert(ASN1TaggedObject tagged) {
        if (tagged.getTagNo() == 0) {
            this.certificate = CMPCertificate.getInstance(tagged.getObject());
        } else if (tagged.getTagNo() == 1) {
            this.encryptedCert = EncryptedValue.getInstance(tagged.getObject());
        } else {
            throw new IllegalArgumentException("unknown tag: " + tagged.getTagNo());
        }
    }

    public static CertOrEncCert getInstance(Object o) {
        if (o instanceof CertOrEncCert) {
            return (CertOrEncCert) o;
        }
        if (o instanceof ASN1TaggedObject) {
            return new CertOrEncCert((ASN1TaggedObject) o);
        }
        return null;
    }

    public CertOrEncCert(CMPCertificate certificate2) {
        if (certificate2 == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        }
        this.certificate = certificate2;
    }

    public CertOrEncCert(EncryptedValue encryptedCert2) {
        if (encryptedCert2 == null) {
            throw new IllegalArgumentException("'encryptedCert' cannot be null");
        }
        this.encryptedCert = encryptedCert2;
    }

    public CMPCertificate getCertificate() {
        return this.certificate;
    }

    public EncryptedValue getEncryptedCert() {
        return this.encryptedCert;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.certificate != null) {
            return new DERTaggedObject(true, 0, this.certificate);
        }
        return new DERTaggedObject(true, 1, this.encryptedCert);
    }
}
