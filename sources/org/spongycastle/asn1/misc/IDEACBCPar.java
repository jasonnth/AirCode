package org.spongycastle.asn1.misc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class IDEACBCPar extends ASN1Object {

    /* renamed from: iv */
    ASN1OctetString f6383iv;

    public static IDEACBCPar getInstance(Object o) {
        if (o instanceof IDEACBCPar) {
            return (IDEACBCPar) o;
        }
        if (o != null) {
            return new IDEACBCPar(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public IDEACBCPar(byte[] iv) {
        this.f6383iv = new DEROctetString(iv);
    }

    public IDEACBCPar(ASN1Sequence seq) {
        if (seq.size() == 1) {
            this.f6383iv = (ASN1OctetString) seq.getObjectAt(0);
        } else {
            this.f6383iv = null;
        }
    }

    public byte[] getIV() {
        if (this.f6383iv != null) {
            return this.f6383iv.getOctets();
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.f6383iv != null) {
            v.add(this.f6383iv);
        }
        return new DERSequence(v);
    }
}
