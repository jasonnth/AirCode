package org.spongycastle.asn1.cms;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class RsaKemParameters extends ASN1Object {
    private final AlgorithmIdentifier keyDerivationFunction;
    private final BigInteger keyLength;

    private RsaKemParameters(ASN1Sequence sequence) {
        if (sequence.size() != 2) {
            throw new IllegalArgumentException("ASN.1 SEQUENCE should be of length 2");
        }
        this.keyDerivationFunction = AlgorithmIdentifier.getInstance(sequence.getObjectAt(0));
        this.keyLength = ASN1Integer.getInstance(sequence.getObjectAt(1)).getValue();
    }

    public static RsaKemParameters getInstance(Object o) {
        if (o instanceof RsaKemParameters) {
            return (RsaKemParameters) o;
        }
        if (o != null) {
            return new RsaKemParameters(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public RsaKemParameters(AlgorithmIdentifier keyDerivationFunction2, int keyLength2) {
        this.keyDerivationFunction = keyDerivationFunction2;
        this.keyLength = BigInteger.valueOf((long) keyLength2);
    }

    public AlgorithmIdentifier getKeyDerivationFunction() {
        return this.keyDerivationFunction;
    }

    public BigInteger getKeyLength() {
        return this.keyLength;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.keyDerivationFunction);
        v.add(new ASN1Integer(this.keyLength));
        return new DERSequence(v);
    }
}
