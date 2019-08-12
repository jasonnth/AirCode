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

/* renamed from: org.spongycastle.asn1.x9.DomainParameters */
public class DomainParameters extends ASN1Object {

    /* renamed from: g */
    private final ASN1Integer f6442g;

    /* renamed from: j */
    private final ASN1Integer f6443j;

    /* renamed from: p */
    private final ASN1Integer f6444p;

    /* renamed from: q */
    private final ASN1Integer f6445q;
    private final ValidationParams validationParams;

    public static DomainParameters getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static DomainParameters getInstance(Object obj) {
        if (obj instanceof DomainParameters) {
            return (DomainParameters) obj;
        }
        if (obj != null) {
            return new DomainParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DomainParameters(BigInteger p, BigInteger g, BigInteger q, BigInteger j, ValidationParams validationParams2) {
        if (p == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        } else if (g == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        } else if (q == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        } else {
            this.f6444p = new ASN1Integer(p);
            this.f6442g = new ASN1Integer(g);
            this.f6445q = new ASN1Integer(q);
            if (j != null) {
                this.f6443j = new ASN1Integer(j);
            } else {
                this.f6443j = null;
            }
            this.validationParams = validationParams2;
        }
    }

    private DomainParameters(ASN1Sequence seq) {
        if (seq.size() < 3 || seq.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        Enumeration e = seq.getObjects();
        this.f6444p = ASN1Integer.getInstance(e.nextElement());
        this.f6442g = ASN1Integer.getInstance(e.nextElement());
        this.f6445q = ASN1Integer.getInstance(e.nextElement());
        ASN1Encodable next = getNext(e);
        if (next == null || !(next instanceof ASN1Integer)) {
            this.f6443j = null;
        } else {
            this.f6443j = ASN1Integer.getInstance(next);
            next = getNext(e);
        }
        if (next != null) {
            this.validationParams = ValidationParams.getInstance(next.toASN1Primitive());
        } else {
            this.validationParams = null;
        }
    }

    private static ASN1Encodable getNext(Enumeration e) {
        if (e.hasMoreElements()) {
            return (ASN1Encodable) e.nextElement();
        }
        return null;
    }

    public BigInteger getP() {
        return this.f6444p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f6442g.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f6445q.getPositiveValue();
    }

    public BigInteger getJ() {
        if (this.f6443j == null) {
            return null;
        }
        return this.f6443j.getPositiveValue();
    }

    public ValidationParams getValidationParams() {
        return this.validationParams;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.f6444p);
        v.add(this.f6442g);
        v.add(this.f6445q);
        if (this.f6443j != null) {
            v.add(this.f6443j);
        }
        if (this.validationParams != null) {
            v.add(this.validationParams);
        }
        return new DERSequence(v);
    }
}
