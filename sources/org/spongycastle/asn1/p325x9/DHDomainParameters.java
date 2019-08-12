package org.spongycastle.asn1.p325x9;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

/* renamed from: org.spongycastle.asn1.x9.DHDomainParameters */
public class DHDomainParameters extends ASN1Object {

    /* renamed from: g */
    private ASN1Integer f6437g;

    /* renamed from: j */
    private ASN1Integer f6438j;

    /* renamed from: p */
    private ASN1Integer f6439p;

    /* renamed from: q */
    private ASN1Integer f6440q;
    private DHValidationParms validationParms;

    public static DHDomainParameters getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static DHDomainParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof DHDomainParameters)) {
            return (DHDomainParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new DHDomainParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid DHDomainParameters: " + obj.getClass().getName());
    }

    public DHDomainParameters(BigInteger p, BigInteger g, BigInteger q, BigInteger j, DHValidationParms validationParms2) {
        if (p == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        } else if (g == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        } else if (q == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        } else {
            this.f6439p = new ASN1Integer(p);
            this.f6437g = new ASN1Integer(g);
            this.f6440q = new ASN1Integer(q);
            this.f6438j = new ASN1Integer(j);
            this.validationParms = validationParms2;
        }
    }

    public DHDomainParameters(ASN1Integer p, ASN1Integer g, ASN1Integer q, ASN1Integer j, DHValidationParms validationParms2) {
        if (p == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        } else if (g == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        } else if (q == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        } else {
            this.f6439p = p;
            this.f6437g = g;
            this.f6440q = q;
            this.f6438j = j;
            this.validationParms = validationParms2;
        }
    }

    private DHDomainParameters(ASN1Sequence seq) {
        if (seq.size() < 3 || seq.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        Enumeration e = seq.getObjects();
        this.f6439p = ASN1Integer.getInstance(e.nextElement());
        this.f6437g = ASN1Integer.getInstance(e.nextElement());
        this.f6440q = ASN1Integer.getInstance(e.nextElement());
        ASN1Encodable next = getNext(e);
        if (next != null && (next instanceof ASN1Integer)) {
            this.f6438j = ASN1Integer.getInstance(next);
            next = getNext(e);
        }
        if (next != null) {
            this.validationParms = DHValidationParms.getInstance(next.toASN1Primitive());
        }
    }

    private static ASN1Encodable getNext(Enumeration e) {
        if (e.hasMoreElements()) {
            return (ASN1Encodable) e.nextElement();
        }
        return null;
    }

    public ASN1Integer getP() {
        return this.f6439p;
    }

    public ASN1Integer getG() {
        return this.f6437g;
    }

    public ASN1Integer getQ() {
        return this.f6440q;
    }

    public ASN1Integer getJ() {
        return this.f6438j;
    }

    public DHValidationParms getValidationParms() {
        return this.validationParms;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.f6439p);
        v.add(this.f6437g);
        v.add(this.f6440q);
        if (this.f6438j != null) {
            v.add(this.f6438j);
        }
        if (this.validationParms != null) {
            v.add(this.validationParms);
        }
        return new DERSequence(v);
    }
}
