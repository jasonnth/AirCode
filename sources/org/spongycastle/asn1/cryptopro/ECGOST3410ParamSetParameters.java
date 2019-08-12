package org.spongycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class ECGOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f6362a;

    /* renamed from: b */
    ASN1Integer f6363b;

    /* renamed from: p */
    ASN1Integer f6364p;

    /* renamed from: q */
    ASN1Integer f6365q;

    /* renamed from: x */
    ASN1Integer f6366x;

    /* renamed from: y */
    ASN1Integer f6367y;

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public ECGOST3410ParamSetParameters(BigInteger a, BigInteger b, BigInteger p, BigInteger q, int x, BigInteger y) {
        this.f6362a = new ASN1Integer(a);
        this.f6363b = new ASN1Integer(b);
        this.f6364p = new ASN1Integer(p);
        this.f6365q = new ASN1Integer(q);
        this.f6366x = new ASN1Integer((long) x);
        this.f6367y = new ASN1Integer(y);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.f6362a = (ASN1Integer) e.nextElement();
        this.f6363b = (ASN1Integer) e.nextElement();
        this.f6364p = (ASN1Integer) e.nextElement();
        this.f6365q = (ASN1Integer) e.nextElement();
        this.f6366x = (ASN1Integer) e.nextElement();
        this.f6367y = (ASN1Integer) e.nextElement();
    }

    public BigInteger getP() {
        return this.f6364p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f6365q.getPositiveValue();
    }

    public BigInteger getA() {
        return this.f6362a.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.f6362a);
        v.add(this.f6363b);
        v.add(this.f6364p);
        v.add(this.f6365q);
        v.add(this.f6366x);
        v.add(this.f6367y);
        return new DERSequence(v);
    }
}
