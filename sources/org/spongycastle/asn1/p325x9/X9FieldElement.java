package org.spongycastle.asn1.p325x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECFieldElement.C5398Fp;
import org.spongycastle.math.p332ec.ECFieldElement.F2m;

/* renamed from: org.spongycastle.asn1.x9.X9FieldElement */
public class X9FieldElement extends ASN1Object {
    private static X9IntegerConverter converter = new X9IntegerConverter();

    /* renamed from: f */
    protected ECFieldElement f6451f;

    public X9FieldElement(ECFieldElement f) {
        this.f6451f = f;
    }

    public X9FieldElement(BigInteger p, ASN1OctetString s) {
        this(new C5398Fp(p, new BigInteger(1, s.getOctets())));
    }

    public X9FieldElement(int m, int k1, int k2, int k3, ASN1OctetString s) {
        this(new F2m(m, k1, k2, k3, new BigInteger(1, s.getOctets())));
    }

    public ECFieldElement getValue() {
        return this.f6451f;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(converter.integerToBytes(this.f6451f.toBigInteger(), converter.getByteLength(this.f6451f)));
    }
}
