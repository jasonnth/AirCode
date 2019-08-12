package org.spongycastle.asn1.p325x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECPoint;

/* renamed from: org.spongycastle.asn1.x9.X9ECParameters */
public class X9ECParameters extends ASN1Object implements X9ObjectIdentifiers {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private ECCurve curve;
    private X9FieldID fieldID;

    /* renamed from: g */
    private X9ECPoint f6446g;

    /* renamed from: h */
    private BigInteger f6447h;

    /* renamed from: n */
    private BigInteger f6448n;
    private byte[] seed;

    private X9ECParameters(ASN1Sequence seq) {
        if (!(seq.getObjectAt(0) instanceof ASN1Integer) || !((ASN1Integer) seq.getObjectAt(0)).getValue().equals(ONE)) {
            throw new IllegalArgumentException("bad version in X9ECParameters");
        }
        X9Curve x9c = new X9Curve(X9FieldID.getInstance(seq.getObjectAt(1)), ASN1Sequence.getInstance(seq.getObjectAt(2)));
        this.curve = x9c.getCurve();
        ASN1Encodable p = seq.getObjectAt(3);
        if (p instanceof X9ECPoint) {
            this.f6446g = (X9ECPoint) p;
        } else {
            this.f6446g = new X9ECPoint(this.curve, (ASN1OctetString) p);
        }
        this.f6448n = ((ASN1Integer) seq.getObjectAt(4)).getValue();
        this.seed = x9c.getSeed();
        if (seq.size() == 6) {
            this.f6447h = ((ASN1Integer) seq.getObjectAt(5)).getValue();
        }
    }

    public static X9ECParameters getInstance(Object obj) {
        if (obj instanceof X9ECParameters) {
            return (X9ECParameters) obj;
        }
        if (obj != null) {
            return new X9ECParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public X9ECParameters(ECCurve curve2, ECPoint g, BigInteger n) {
        this(curve2, g, n, (BigInteger) null, (byte[]) null);
    }

    public X9ECParameters(ECCurve curve2, X9ECPoint g, BigInteger n, BigInteger h) {
        this(curve2, g, n, h, (byte[]) null);
    }

    public X9ECParameters(ECCurve curve2, ECPoint g, BigInteger n, BigInteger h) {
        this(curve2, g, n, h, (byte[]) null);
    }

    public X9ECParameters(ECCurve curve2, ECPoint g, BigInteger n, BigInteger h, byte[] seed2) {
        this(curve2, new X9ECPoint(g), n, h, seed2);
    }

    public X9ECParameters(ECCurve curve2, X9ECPoint g, BigInteger n, BigInteger h, byte[] seed2) {
        this.curve = curve2;
        this.f6446g = g;
        this.f6448n = n;
        this.f6447h = h;
        this.seed = seed2;
        if (ECAlgorithms.isFpCurve(curve2)) {
            this.fieldID = new X9FieldID(curve2.getField().getCharacteristic());
        } else if (ECAlgorithms.isF2mCurve(curve2)) {
            int[] exponents = ((PolynomialExtensionField) curve2.getField()).getMinimalPolynomial().getExponentsPresent();
            if (exponents.length == 3) {
                this.fieldID = new X9FieldID(exponents[2], exponents[1]);
            } else if (exponents.length == 5) {
                this.fieldID = new X9FieldID(exponents[4], exponents[1], exponents[2], exponents[3]);
            } else {
                throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
            }
        } else {
            throw new IllegalArgumentException("'curve' is of an unsupported type");
        }
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f6446g.getPoint();
    }

    public BigInteger getN() {
        return this.f6448n;
    }

    public BigInteger getH() {
        return this.f6447h;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public X9Curve getCurveEntry() {
        return new X9Curve(this.curve, this.seed);
    }

    public X9FieldID getFieldIDEntry() {
        return this.fieldID;
    }

    public X9ECPoint getBaseEntry() {
        return this.f6446g;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(ONE));
        v.add(this.fieldID);
        v.add(new X9Curve(this.curve, this.seed));
        v.add(this.f6446g);
        v.add(new ASN1Integer(this.f6448n));
        if (this.f6447h != null) {
            v.add(new ASN1Integer(this.f6447h));
        }
        return new DERSequence(v);
    }
}
