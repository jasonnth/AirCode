package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class BasicOCSPResponse extends ASN1Object {
    private ASN1Sequence certs;
    private DERBitString signature;
    private AlgorithmIdentifier signatureAlgorithm;
    private ResponseData tbsResponseData;

    public BasicOCSPResponse(ResponseData tbsResponseData2, AlgorithmIdentifier signatureAlgorithm2, DERBitString signature2, ASN1Sequence certs2) {
        this.tbsResponseData = tbsResponseData2;
        this.signatureAlgorithm = signatureAlgorithm2;
        this.signature = signature2;
        this.certs = certs2;
    }

    private BasicOCSPResponse(ASN1Sequence seq) {
        this.tbsResponseData = ResponseData.getInstance(seq.getObjectAt(0));
        this.signatureAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
        this.signature = (DERBitString) seq.getObjectAt(2);
        if (seq.size() > 3) {
            this.certs = ASN1Sequence.getInstance((ASN1TaggedObject) seq.getObjectAt(3), true);
        }
    }

    public static BasicOCSPResponse getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static BasicOCSPResponse getInstance(Object obj) {
        if (obj instanceof BasicOCSPResponse) {
            return (BasicOCSPResponse) obj;
        }
        if (obj != null) {
            return new BasicOCSPResponse(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ResponseData getTbsResponseData() {
        return this.tbsResponseData;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public DERBitString getSignature() {
        return this.signature;
    }

    public ASN1Sequence getCerts() {
        return this.certs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.tbsResponseData);
        v.add(this.signatureAlgorithm);
        v.add(this.signature);
        if (this.certs != null) {
            v.add(new DERTaggedObject(true, 0, this.certs));
        }
        return new DERSequence(v);
    }
}
