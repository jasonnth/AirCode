package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class DHParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f6388g;

    /* renamed from: l */
    ASN1Integer f6389l;

    /* renamed from: p */
    ASN1Integer f6390p;

    public DHParameter(BigInteger p, BigInteger g, int l) {
        this.f6390p = new ASN1Integer(p);
        this.f6388g = new ASN1Integer(g);
        if (l != 0) {
            this.f6389l = new ASN1Integer((long) l);
        } else {
            this.f6389l = null;
        }
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private DHParameter(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.f6390p = ASN1Integer.getInstance(e.nextElement());
        this.f6388g = ASN1Integer.getInstance(e.nextElement());
        if (e.hasMoreElements()) {
            this.f6389l = (ASN1Integer) e.nextElement();
        } else {
            this.f6389l = null;
        }
    }

    public BigInteger getP() {
        return this.f6390p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f6388g.getPositiveValue();
    }

    public BigInteger getL() {
        if (this.f6389l == null) {
            return null;
        }
        return this.f6389l.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.f6390p);
        v.add(this.f6388g);
        if (getL() != null) {
            v.add(this.f6389l);
        }
        return new DERSequence(v);
    }
}
