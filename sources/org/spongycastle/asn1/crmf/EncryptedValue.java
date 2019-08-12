package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedValue extends ASN1Object {
    private DERBitString encSymmKey;
    private DERBitString encValue;
    private AlgorithmIdentifier intendedAlg;
    private AlgorithmIdentifier keyAlg;
    private AlgorithmIdentifier symmAlg;
    private ASN1OctetString valueHint;

    private EncryptedValue(ASN1Sequence seq) {
        int index = 0;
        while (seq.getObjectAt(index) instanceof ASN1TaggedObject) {
            ASN1TaggedObject tObj = (ASN1TaggedObject) seq.getObjectAt(index);
            switch (tObj.getTagNo()) {
                case 0:
                    this.intendedAlg = AlgorithmIdentifier.getInstance(tObj, false);
                    break;
                case 1:
                    this.symmAlg = AlgorithmIdentifier.getInstance(tObj, false);
                    break;
                case 2:
                    this.encSymmKey = DERBitString.getInstance(tObj, false);
                    break;
                case 3:
                    this.keyAlg = AlgorithmIdentifier.getInstance(tObj, false);
                    break;
                case 4:
                    this.valueHint = ASN1OctetString.getInstance(tObj, false);
                    break;
            }
            index++;
        }
        this.encValue = DERBitString.getInstance(seq.getObjectAt(index));
    }

    public static EncryptedValue getInstance(Object o) {
        if (o instanceof EncryptedValue) {
            return (EncryptedValue) o;
        }
        if (o != null) {
            return new EncryptedValue(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public EncryptedValue(AlgorithmIdentifier intendedAlg2, AlgorithmIdentifier symmAlg2, DERBitString encSymmKey2, AlgorithmIdentifier keyAlg2, ASN1OctetString valueHint2, DERBitString encValue2) {
        if (encValue2 == null) {
            throw new IllegalArgumentException("'encValue' cannot be null");
        }
        this.intendedAlg = intendedAlg2;
        this.symmAlg = symmAlg2;
        this.encSymmKey = encSymmKey2;
        this.keyAlg = keyAlg2;
        this.valueHint = valueHint2;
        this.encValue = encValue2;
    }

    public AlgorithmIdentifier getIntendedAlg() {
        return this.intendedAlg;
    }

    public AlgorithmIdentifier getSymmAlg() {
        return this.symmAlg;
    }

    public DERBitString getEncSymmKey() {
        return this.encSymmKey;
    }

    public AlgorithmIdentifier getKeyAlg() {
        return this.keyAlg;
    }

    public ASN1OctetString getValueHint() {
        return this.valueHint;
    }

    public DERBitString getEncValue() {
        return this.encValue;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        addOptional(v, 0, this.intendedAlg);
        addOptional(v, 1, this.symmAlg);
        addOptional(v, 2, this.encSymmKey);
        addOptional(v, 3, this.keyAlg);
        addOptional(v, 4, this.valueHint);
        v.add(this.encValue);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, int tagNo, ASN1Encodable obj) {
        if (obj != null) {
            v.add(new DERTaggedObject(false, tagNo, obj));
        }
    }
}
