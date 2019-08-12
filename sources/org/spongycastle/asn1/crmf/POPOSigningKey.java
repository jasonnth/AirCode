package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class POPOSigningKey extends ASN1Object {
    private AlgorithmIdentifier algorithmIdentifier;
    private POPOSigningKeyInput poposkInput;
    private DERBitString signature;

    private POPOSigningKey(ASN1Sequence seq) {
        int index = 0;
        if (seq.getObjectAt(0) instanceof ASN1TaggedObject) {
            int index2 = 0 + 1;
            ASN1TaggedObject tagObj = (ASN1TaggedObject) seq.getObjectAt(0);
            if (tagObj.getTagNo() != 0) {
                throw new IllegalArgumentException("Unknown POPOSigningKeyInput tag: " + tagObj.getTagNo());
            }
            this.poposkInput = POPOSigningKeyInput.getInstance(tagObj.getObject());
            index = index2;
        }
        int index3 = index + 1;
        this.algorithmIdentifier = AlgorithmIdentifier.getInstance(seq.getObjectAt(index));
        this.signature = DERBitString.getInstance(seq.getObjectAt(index3));
    }

    public static POPOSigningKey getInstance(Object o) {
        if (o instanceof POPOSigningKey) {
            return (POPOSigningKey) o;
        }
        if (o != null) {
            return new POPOSigningKey(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public static POPOSigningKey getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public POPOSigningKey(POPOSigningKeyInput poposkIn, AlgorithmIdentifier aid, DERBitString signature2) {
        this.poposkInput = poposkIn;
        this.algorithmIdentifier = aid;
        this.signature = signature2;
    }

    public POPOSigningKeyInput getPoposkInput() {
        return this.poposkInput;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    public DERBitString getSignature() {
        return this.signature;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.poposkInput != null) {
            v.add(new DERTaggedObject(false, 0, this.poposkInput));
        }
        v.add(this.algorithmIdentifier);
        v.add(this.signature);
        return new DERSequence(v);
    }
}
