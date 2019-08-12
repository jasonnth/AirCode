package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.GeneralName;

public class EncKeyWithID extends ASN1Object {
    private final ASN1Encodable identifier;
    private final PrivateKeyInfo privKeyInfo;

    public static EncKeyWithID getInstance(Object o) {
        if (o instanceof EncKeyWithID) {
            return (EncKeyWithID) o;
        }
        if (o != null) {
            return new EncKeyWithID(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    private EncKeyWithID(ASN1Sequence seq) {
        this.privKeyInfo = PrivateKeyInfo.getInstance(seq.getObjectAt(0));
        if (seq.size() <= 1) {
            this.identifier = null;
        } else if (!(seq.getObjectAt(1) instanceof DERUTF8String)) {
            this.identifier = GeneralName.getInstance(seq.getObjectAt(1));
        } else {
            this.identifier = seq.getObjectAt(1);
        }
    }

    public EncKeyWithID(PrivateKeyInfo privKeyInfo2) {
        this.privKeyInfo = privKeyInfo2;
        this.identifier = null;
    }

    public EncKeyWithID(PrivateKeyInfo privKeyInfo2, DERUTF8String str) {
        this.privKeyInfo = privKeyInfo2;
        this.identifier = str;
    }

    public EncKeyWithID(PrivateKeyInfo privKeyInfo2, GeneralName generalName) {
        this.privKeyInfo = privKeyInfo2;
        this.identifier = generalName;
    }

    public PrivateKeyInfo getPrivateKey() {
        return this.privKeyInfo;
    }

    public boolean hasIdentifier() {
        return this.identifier != null;
    }

    public boolean isIdentifierUTF8String() {
        return this.identifier instanceof DERUTF8String;
    }

    public ASN1Encodable getIdentifier() {
        return this.identifier;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.privKeyInfo);
        if (this.identifier != null) {
            v.add(this.identifier);
        }
        return new DERSequence(v);
    }
}
