package org.spongycastle.asn1.p324ua;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.asn1.ua.DSTU4145ECBinary */
public class DSTU4145ECBinary extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f6397a;

    /* renamed from: b */
    ASN1OctetString f6398b;

    /* renamed from: bp */
    ASN1OctetString f6399bp;

    /* renamed from: f */
    DSTU4145BinaryField f6400f;

    /* renamed from: n */
    ASN1Integer f6401n;
    BigInteger version = BigInteger.valueOf(0);

    public DSTU4145ECBinary(ECDomainParameters params) {
        ECCurve curve = params.getCurve();
        if (!ECAlgorithms.isF2mCurve(curve)) {
            throw new IllegalArgumentException("only binary domain is possible");
        }
        int[] exponents = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
        if (exponents.length == 3) {
            this.f6400f = new DSTU4145BinaryField(exponents[2], exponents[1]);
        } else if (exponents.length == 5) {
            this.f6400f = new DSTU4145BinaryField(exponents[4], exponents[1], exponents[2], exponents[3]);
        } else {
            throw new IllegalArgumentException("curve must have a trinomial or pentanomial basis");
        }
        this.f6397a = new ASN1Integer(curve.getA().toBigInteger());
        this.f6398b = new DEROctetString(curve.getB().getEncoded());
        this.f6401n = new ASN1Integer(params.getN());
        this.f6399bp = new DEROctetString(DSTU4145PointEncoder.encodePoint(params.getG()));
    }

    private DSTU4145ECBinary(ASN1Sequence seq) {
        int index = 0;
        if (seq.getObjectAt(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject taggedVersion = (ASN1TaggedObject) seq.getObjectAt(0);
            if (!taggedVersion.isExplicit() || taggedVersion.getTagNo() != 0) {
                throw new IllegalArgumentException("object parse error");
            }
            this.version = ASN1Integer.getInstance(taggedVersion.getLoadedObject()).getValue();
            index = 0 + 1;
        }
        this.f6400f = DSTU4145BinaryField.getInstance(seq.getObjectAt(index));
        int index2 = index + 1;
        this.f6397a = ASN1Integer.getInstance(seq.getObjectAt(index2));
        int index3 = index2 + 1;
        this.f6398b = ASN1OctetString.getInstance(seq.getObjectAt(index3));
        int index4 = index3 + 1;
        this.f6401n = ASN1Integer.getInstance(seq.getObjectAt(index4));
        this.f6399bp = ASN1OctetString.getInstance(seq.getObjectAt(index4 + 1));
    }

    public static DSTU4145ECBinary getInstance(Object obj) {
        if (obj instanceof DSTU4145ECBinary) {
            return (DSTU4145ECBinary) obj;
        }
        if (obj != null) {
            return new DSTU4145ECBinary(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSTU4145BinaryField getField() {
        return this.f6400f;
    }

    public BigInteger getA() {
        return this.f6397a.getValue();
    }

    public byte[] getB() {
        return Arrays.clone(this.f6398b.getOctets());
    }

    public BigInteger getN() {
        return this.f6401n.getValue();
    }

    public byte[] getG() {
        return Arrays.clone(this.f6399bp.getOctets());
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.version.compareTo(BigInteger.valueOf(0)) != 0) {
            v.add(new DERTaggedObject(true, 0, new ASN1Integer(this.version)));
        }
        v.add(this.f6400f);
        v.add(this.f6397a);
        v.add(this.f6398b);
        v.add(this.f6401n);
        v.add(this.f6399bp);
        return new DERSequence(v);
    }
}
