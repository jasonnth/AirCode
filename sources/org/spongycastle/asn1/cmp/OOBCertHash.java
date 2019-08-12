package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.crmf.CertId;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class OOBCertHash extends ASN1Object {
    private CertId certId;
    private AlgorithmIdentifier hashAlg;
    private DERBitString hashVal;

    private OOBCertHash(ASN1Sequence seq) {
        int index = seq.size() - 1;
        int index2 = index - 1;
        this.hashVal = DERBitString.getInstance(seq.getObjectAt(index));
        for (int i = index2; i >= 0; i--) {
            ASN1TaggedObject tObj = (ASN1TaggedObject) seq.getObjectAt(i);
            if (tObj.getTagNo() == 0) {
                this.hashAlg = AlgorithmIdentifier.getInstance(tObj, true);
            } else {
                this.certId = CertId.getInstance(tObj, true);
            }
        }
    }

    public static OOBCertHash getInstance(Object o) {
        if (o instanceof OOBCertHash) {
            return (OOBCertHash) o;
        }
        if (o != null) {
            return new OOBCertHash(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public OOBCertHash(AlgorithmIdentifier hashAlg2, CertId certId2, byte[] hashVal2) {
        this(hashAlg2, certId2, new DERBitString(hashVal2));
    }

    public OOBCertHash(AlgorithmIdentifier hashAlg2, CertId certId2, DERBitString hashVal2) {
        this.hashAlg = hashAlg2;
        this.certId = certId2;
        this.hashVal = hashVal2;
    }

    public AlgorithmIdentifier getHashAlg() {
        return this.hashAlg;
    }

    public CertId getCertId() {
        return this.certId;
    }

    public DERBitString getHashVal() {
        return this.hashVal;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        addOptional(v, 0, this.hashAlg);
        addOptional(v, 1, this.certId);
        v.add(this.hashVal);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, int tagNo, ASN1Encodable obj) {
        if (obj != null) {
            v.add(new DERTaggedObject(true, tagNo, obj));
        }
    }
}
