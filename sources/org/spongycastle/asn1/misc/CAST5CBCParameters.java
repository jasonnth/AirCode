package org.spongycastle.asn1.misc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class CAST5CBCParameters extends ASN1Object {

    /* renamed from: iv */
    ASN1OctetString f6382iv;
    ASN1Integer keyLength;

    public static CAST5CBCParameters getInstance(Object o) {
        if (o instanceof CAST5CBCParameters) {
            return (CAST5CBCParameters) o;
        }
        if (o != null) {
            return new CAST5CBCParameters(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public CAST5CBCParameters(byte[] iv, int keyLength2) {
        this.f6382iv = new DEROctetString(iv);
        this.keyLength = new ASN1Integer((long) keyLength2);
    }

    public CAST5CBCParameters(ASN1Sequence seq) {
        this.f6382iv = (ASN1OctetString) seq.getObjectAt(0);
        this.keyLength = (ASN1Integer) seq.getObjectAt(1);
    }

    public byte[] getIV() {
        return this.f6382iv.getOctets();
    }

    public int getKeyLength() {
        return this.keyLength.getValue().intValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.f6382iv);
        v.add(this.keyLength);
        return new DERSequence(v);
    }
}
