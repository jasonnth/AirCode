package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class DSAParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f6422g;

    /* renamed from: p */
    ASN1Integer f6423p;

    /* renamed from: q */
    ASN1Integer f6424q;

    public static DSAParameter getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static DSAParameter getInstance(Object obj) {
        if (obj instanceof DSAParameter) {
            return (DSAParameter) obj;
        }
        if (obj != null) {
            return new DSAParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSAParameter(BigInteger p, BigInteger q, BigInteger g) {
        this.f6423p = new ASN1Integer(p);
        this.f6424q = new ASN1Integer(q);
        this.f6422g = new ASN1Integer(g);
    }

    private DSAParameter(ASN1Sequence seq) {
        if (seq.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        Enumeration e = seq.getObjects();
        this.f6423p = ASN1Integer.getInstance(e.nextElement());
        this.f6424q = ASN1Integer.getInstance(e.nextElement());
        this.f6422g = ASN1Integer.getInstance(e.nextElement());
    }

    public BigInteger getP() {
        return this.f6423p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f6424q.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f6422g.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.f6423p);
        v.add(this.f6424q);
        v.add(this.f6422g);
        return new DERSequence(v);
    }
}
