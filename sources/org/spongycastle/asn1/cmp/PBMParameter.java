package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PBMParameter extends ASN1Object {
    private ASN1Integer iterationCount;
    private AlgorithmIdentifier mac;
    private AlgorithmIdentifier owf;
    private ASN1OctetString salt;

    private PBMParameter(ASN1Sequence seq) {
        this.salt = ASN1OctetString.getInstance(seq.getObjectAt(0));
        this.owf = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
        this.iterationCount = ASN1Integer.getInstance(seq.getObjectAt(2));
        this.mac = AlgorithmIdentifier.getInstance(seq.getObjectAt(3));
    }

    public static PBMParameter getInstance(Object o) {
        if (o instanceof PBMParameter) {
            return (PBMParameter) o;
        }
        if (o != null) {
            return new PBMParameter(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public PBMParameter(byte[] salt2, AlgorithmIdentifier owf2, int iterationCount2, AlgorithmIdentifier mac2) {
        this((ASN1OctetString) new DEROctetString(salt2), owf2, new ASN1Integer((long) iterationCount2), mac2);
    }

    public PBMParameter(ASN1OctetString salt2, AlgorithmIdentifier owf2, ASN1Integer iterationCount2, AlgorithmIdentifier mac2) {
        this.salt = salt2;
        this.owf = owf2;
        this.iterationCount = iterationCount2;
        this.mac = mac2;
    }

    public ASN1OctetString getSalt() {
        return this.salt;
    }

    public AlgorithmIdentifier getOwf() {
        return this.owf;
    }

    public ASN1Integer getIterationCount() {
        return this.iterationCount;
    }

    public AlgorithmIdentifier getMac() {
        return this.mac;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.salt);
        v.add(this.owf);
        v.add(this.iterationCount);
        v.add(this.mac);
        return new DERSequence(v);
    }
}
