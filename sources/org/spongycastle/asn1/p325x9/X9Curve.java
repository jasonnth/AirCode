package org.spongycastle.asn1.p325x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.C5397Fp;
import org.spongycastle.math.p332ec.ECCurve.F2m;

/* renamed from: org.spongycastle.asn1.x9.X9Curve */
public class X9Curve extends ASN1Object implements X9ObjectIdentifiers {
    private ECCurve curve;
    private ASN1ObjectIdentifier fieldIdentifier = null;
    private byte[] seed;

    public X9Curve(ECCurve curve2) {
        this.curve = curve2;
        this.seed = null;
        setFieldIdentifier();
    }

    public X9Curve(ECCurve curve2, byte[] seed2) {
        this.curve = curve2;
        this.seed = seed2;
        setFieldIdentifier();
    }

    public X9Curve(X9FieldID fieldID, ASN1Sequence seq) {
        int k1;
        this.fieldIdentifier = fieldID.getIdentifier();
        if (this.fieldIdentifier.equals(prime_field)) {
            BigInteger p = ((ASN1Integer) fieldID.getParameters()).getValue();
            this.curve = new C5397Fp(p, new X9FieldElement(p, (ASN1OctetString) seq.getObjectAt(0)).getValue().toBigInteger(), new X9FieldElement(p, (ASN1OctetString) seq.getObjectAt(1)).getValue().toBigInteger());
        } else if (this.fieldIdentifier.equals(characteristic_two_field)) {
            ASN1Sequence parameters = ASN1Sequence.getInstance(fieldID.getParameters());
            int m = ((ASN1Integer) parameters.getObjectAt(0)).getValue().intValue();
            ASN1ObjectIdentifier representation = (ASN1ObjectIdentifier) parameters.getObjectAt(1);
            int k2 = 0;
            int k3 = 0;
            if (representation.equals(tpBasis)) {
                k1 = ASN1Integer.getInstance(parameters.getObjectAt(2)).getValue().intValue();
            } else {
                if (representation.equals(ppBasis)) {
                    ASN1Sequence pentanomial = ASN1Sequence.getInstance(parameters.getObjectAt(2));
                    k1 = ASN1Integer.getInstance(pentanomial.getObjectAt(0)).getValue().intValue();
                    k2 = ASN1Integer.getInstance(pentanomial.getObjectAt(1)).getValue().intValue();
                    k3 = ASN1Integer.getInstance(pentanomial.getObjectAt(2)).getValue().intValue();
                } else {
                    throw new IllegalArgumentException("This type of EC basis is not implemented");
                }
            }
            X9FieldElement x9A = new X9FieldElement(m, k1, k2, k3, (ASN1OctetString) seq.getObjectAt(0));
            X9FieldElement x9B = new X9FieldElement(m, k1, k2, k3, (ASN1OctetString) seq.getObjectAt(1));
            this.curve = new F2m(m, k1, k2, k3, x9A.getValue().toBigInteger(), x9B.getValue().toBigInteger());
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
        if (seq.size() == 3) {
            this.seed = ((DERBitString) seq.getObjectAt(2)).getBytes();
        }
    }

    private void setFieldIdentifier() {
        if (ECAlgorithms.isFpCurve(this.curve)) {
            this.fieldIdentifier = prime_field;
        } else if (ECAlgorithms.isF2mCurve(this.curve)) {
            this.fieldIdentifier = characteristic_two_field;
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.fieldIdentifier.equals(prime_field)) {
            v.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
            v.add(new X9FieldElement(this.curve.getB()).toASN1Primitive());
        } else if (this.fieldIdentifier.equals(characteristic_two_field)) {
            v.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
            v.add(new X9FieldElement(this.curve.getB()).toASN1Primitive());
        }
        if (this.seed != null) {
            v.add(new DERBitString(this.seed));
        }
        return new DERSequence(v);
    }
}
