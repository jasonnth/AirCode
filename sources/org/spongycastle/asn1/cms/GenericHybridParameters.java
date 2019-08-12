package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class GenericHybridParameters extends ASN1Object {
    private final AlgorithmIdentifier dem;
    private final AlgorithmIdentifier kem;

    private GenericHybridParameters(ASN1Sequence sequence) {
        if (sequence.size() != 2) {
            throw new IllegalArgumentException("ASN.1 SEQUENCE should be of length 2");
        }
        this.kem = AlgorithmIdentifier.getInstance(sequence.getObjectAt(0));
        this.dem = AlgorithmIdentifier.getInstance(sequence.getObjectAt(1));
    }

    public static GenericHybridParameters getInstance(Object o) {
        if (o instanceof GenericHybridParameters) {
            return (GenericHybridParameters) o;
        }
        if (o != null) {
            return new GenericHybridParameters(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public GenericHybridParameters(AlgorithmIdentifier kem2, AlgorithmIdentifier dem2) {
        this.kem = kem2;
        this.dem = dem2;
    }

    public AlgorithmIdentifier getDem() {
        return this.dem;
    }

    public AlgorithmIdentifier getKem() {
        return this.kem;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.kem);
        v.add(this.dem);
        return new DERSequence(v);
    }
}
