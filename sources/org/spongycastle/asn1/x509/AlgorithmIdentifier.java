package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class AlgorithmIdentifier extends ASN1Object {
    private ASN1ObjectIdentifier algorithm;
    private ASN1Encodable parameters;

    public static AlgorithmIdentifier getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AlgorithmIdentifier getInstance(Object obj) {
        if (obj instanceof AlgorithmIdentifier) {
            return (AlgorithmIdentifier) obj;
        }
        if (obj != null) {
            return new AlgorithmIdentifier(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier algorithm2) {
        this.algorithm = algorithm2;
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier algorithm2, ASN1Encodable parameters2) {
        this.algorithm = algorithm2;
        this.parameters = parameters2;
    }

    private AlgorithmIdentifier(ASN1Sequence seq) {
        if (seq.size() < 1 || seq.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        this.algorithm = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        if (seq.size() == 2) {
            this.parameters = seq.getObjectAt(1);
        } else {
            this.parameters = null;
        }
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return this.algorithm;
    }

    public ASN1Encodable getParameters() {
        return this.parameters;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.algorithm);
        if (this.parameters != null) {
            v.add(this.parameters);
        }
        return new DERSequence(v);
    }
}
