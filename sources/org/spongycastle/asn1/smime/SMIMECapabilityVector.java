package org.spongycastle.asn1.smime;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class SMIMECapabilityVector {
    private ASN1EncodableVector capabilities = new ASN1EncodableVector();

    public void addCapability(ASN1ObjectIdentifier capability) {
        this.capabilities.add(new DERSequence((ASN1Encodable) capability));
    }

    public void addCapability(ASN1ObjectIdentifier capability, int value) {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(capability);
        v.add(new ASN1Integer((long) value));
        this.capabilities.add(new DERSequence(v));
    }

    public void addCapability(ASN1ObjectIdentifier capability, ASN1Encodable params) {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(capability);
        v.add(params);
        this.capabilities.add(new DERSequence(v));
    }

    public ASN1EncodableVector toASN1EncodableVector() {
        return this.capabilities;
    }
}
