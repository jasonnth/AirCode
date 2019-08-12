package org.spongycastle.asn1.oiw;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ElGamalParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f6386g;

    /* renamed from: p */
    ASN1Integer f6387p;

    public ElGamalParameter(BigInteger p, BigInteger g) {
        this.f6387p = new ASN1Integer(p);
        this.f6386g = new ASN1Integer(g);
    }

    private ElGamalParameter(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.f6387p = (ASN1Integer) e.nextElement();
        this.f6386g = (ASN1Integer) e.nextElement();
    }

    public static ElGamalParameter getInstance(Object o) {
        if (o instanceof ElGamalParameter) {
            return (ElGamalParameter) o;
        }
        if (o != null) {
            return new ElGamalParameter(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public BigInteger getP() {
        return this.f6387p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f6386g.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.f6387p);
        v.add(this.f6386g);
        return new DERSequence(v);
    }
}
