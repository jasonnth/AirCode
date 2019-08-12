package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class CertificatePolicies extends ASN1Object {
    private final PolicyInformation[] policyInformation;

    public static CertificatePolicies getInstance(Object obj) {
        if (obj instanceof CertificatePolicies) {
            return (CertificatePolicies) obj;
        }
        if (obj != null) {
            return new CertificatePolicies(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CertificatePolicies getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static CertificatePolicies fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.certificatePolicies));
    }

    public CertificatePolicies(PolicyInformation name) {
        this.policyInformation = new PolicyInformation[]{name};
    }

    public CertificatePolicies(PolicyInformation[] policyInformation2) {
        this.policyInformation = policyInformation2;
    }

    private CertificatePolicies(ASN1Sequence seq) {
        this.policyInformation = new PolicyInformation[seq.size()];
        for (int i = 0; i != seq.size(); i++) {
            this.policyInformation[i] = PolicyInformation.getInstance(seq.getObjectAt(i));
        }
    }

    public PolicyInformation[] getPolicyInformation() {
        PolicyInformation[] tmp = new PolicyInformation[this.policyInformation.length];
        System.arraycopy(this.policyInformation, 0, tmp, 0, this.policyInformation.length);
        return tmp;
    }

    public PolicyInformation getPolicyInformation(ASN1ObjectIdentifier policyIdentifier) {
        for (int i = 0; i != this.policyInformation.length; i++) {
            if (policyIdentifier.equals(this.policyInformation[i].getPolicyIdentifier())) {
                return this.policyInformation[i];
            }
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence((ASN1Encodable[]) this.policyInformation);
    }

    public String toString() {
        StringBuffer p = new StringBuffer();
        for (PolicyInformation append : this.policyInformation) {
            if (p.length() != 0) {
                p.append(", ");
            }
            p.append(append);
        }
        return "CertificatePolicies: [" + p + "]";
    }
}
