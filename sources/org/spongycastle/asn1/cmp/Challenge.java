package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class Challenge extends ASN1Object {
    private ASN1OctetString challenge;
    private AlgorithmIdentifier owf;
    private ASN1OctetString witness;

    private Challenge(ASN1Sequence seq) {
        int index = 0;
        if (seq.size() == 3) {
            int index2 = 0 + 1;
            this.owf = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
            index = index2;
        }
        int index3 = index + 1;
        this.witness = ASN1OctetString.getInstance(seq.getObjectAt(index));
        this.challenge = ASN1OctetString.getInstance(seq.getObjectAt(index3));
    }

    public static Challenge getInstance(Object o) {
        if (o instanceof Challenge) {
            return (Challenge) o;
        }
        if (o != null) {
            return new Challenge(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public Challenge(byte[] witness2, byte[] challenge2) {
        this(null, witness2, challenge2);
    }

    public Challenge(AlgorithmIdentifier owf2, byte[] witness2, byte[] challenge2) {
        this.owf = owf2;
        this.witness = new DEROctetString(witness2);
        this.challenge = new DEROctetString(challenge2);
    }

    public AlgorithmIdentifier getOwf() {
        return this.owf;
    }

    public byte[] getWitness() {
        return this.witness.getOctets();
    }

    public byte[] getChallenge() {
        return this.challenge.getOctets();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        addOptional(v, this.owf);
        v.add(this.witness);
        v.add(this.challenge);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, ASN1Encodable obj) {
        if (obj != null) {
            v.add(obj);
        }
    }
}
