package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class BasicConstraints extends ASN1Object {

    /* renamed from: cA */
    ASN1Boolean f6420cA;
    ASN1Integer pathLenConstraint;

    public static BasicConstraints getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static BasicConstraints getInstance(Object obj) {
        if (obj instanceof BasicConstraints) {
            return (BasicConstraints) obj;
        }
        if (obj instanceof X509Extension) {
            return getInstance(X509Extension.convertValueToObject((X509Extension) obj));
        }
        if (obj != null) {
            return new BasicConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static BasicConstraints fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.basicConstraints));
    }

    private BasicConstraints(ASN1Sequence seq) {
        this.f6420cA = ASN1Boolean.getInstance(false);
        this.pathLenConstraint = null;
        if (seq.size() == 0) {
            this.f6420cA = null;
            this.pathLenConstraint = null;
            return;
        }
        if (seq.getObjectAt(0) instanceof ASN1Boolean) {
            this.f6420cA = ASN1Boolean.getInstance((Object) seq.getObjectAt(0));
        } else {
            this.f6420cA = null;
            this.pathLenConstraint = ASN1Integer.getInstance(seq.getObjectAt(0));
        }
        if (seq.size() <= 1) {
            return;
        }
        if (this.f6420cA != null) {
            this.pathLenConstraint = ASN1Integer.getInstance(seq.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("wrong sequence in constructor");
    }

    public BasicConstraints(boolean cA) {
        this.f6420cA = ASN1Boolean.getInstance(false);
        this.pathLenConstraint = null;
        if (cA) {
            this.f6420cA = ASN1Boolean.getInstance(true);
        } else {
            this.f6420cA = null;
        }
        this.pathLenConstraint = null;
    }

    public BasicConstraints(int pathLenConstraint2) {
        this.f6420cA = ASN1Boolean.getInstance(false);
        this.pathLenConstraint = null;
        this.f6420cA = ASN1Boolean.getInstance(true);
        this.pathLenConstraint = new ASN1Integer((long) pathLenConstraint2);
    }

    public boolean isCA() {
        return this.f6420cA != null && this.f6420cA.isTrue();
    }

    public BigInteger getPathLenConstraint() {
        if (this.pathLenConstraint != null) {
            return this.pathLenConstraint.getValue();
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.f6420cA != null) {
            v.add(this.f6420cA);
        }
        if (this.pathLenConstraint != null) {
            v.add(this.pathLenConstraint);
        }
        return new DERSequence(v);
    }

    public String toString() {
        if (this.pathLenConstraint != null) {
            return "BasicConstraints: isCa(" + isCA() + "), pathLenConstraint = " + this.pathLenConstraint.getValue();
        }
        if (this.f6420cA == null) {
            return "BasicConstraints: isCa(false)";
        }
        return "BasicConstraints: isCa(" + isCA() + ")";
    }
}
