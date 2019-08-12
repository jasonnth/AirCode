package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;

public class CertPolicyId extends ASN1Object {

    /* renamed from: id */
    private ASN1ObjectIdentifier f6421id;

    private CertPolicyId(ASN1ObjectIdentifier id) {
        this.f6421id = id;
    }

    public static CertPolicyId getInstance(Object o) {
        if (o instanceof CertPolicyId) {
            return (CertPolicyId) o;
        }
        if (o != null) {
            return new CertPolicyId(ASN1ObjectIdentifier.getInstance(o));
        }
        return null;
    }

    public String getId() {
        return this.f6421id.getId();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.f6421id;
    }
}
