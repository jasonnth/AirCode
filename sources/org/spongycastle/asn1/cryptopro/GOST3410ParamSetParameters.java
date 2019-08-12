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

public class GOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f6369a;
    int keySize;

    /* renamed from: p */
    ASN1Integer f6370p;

    /* renamed from: q */
    ASN1Integer f6371q;

    public static GOST3410ParamSetParameters getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static GOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof GOST3410ParamSetParameters)) {
            return (GOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new GOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public GOST3410ParamSetParameters(int keySize2, BigInteger p, BigInteger q, BigInteger a) {
        this.keySize = keySize2;
        this.f6370p = new ASN1Integer(p);
        this.f6371q = new ASN1Integer(q);
        this.f6369a = new ASN1Integer(a);
    }

    public GOST3410ParamSetParameters(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.keySize = ((ASN1Integer) e.nextElement()).getValue().intValue();
        this.f6370p = (ASN1Integer) e.nextElement();
        this.f6371q = (ASN1Integer) e.nextElement();
        this.f6369a = (ASN1Integer) e.nextElement();
    }

    public int getLKeySize() {
        return this.keySize;
    }

    public int getKeySize() {
        return this.keySize;
    }

    public BigInteger getP() {
        return this.f6370p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f6371q.getPositiveValue();
    }

    public BigInteger getA() {
        return this.f6369a.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer((long) this.keySize));
        v.add(this.f6370p);
        v.add(this.f6371q);
        v.add(this.f6369a);
        return new DERSequence(v);
    }
}
