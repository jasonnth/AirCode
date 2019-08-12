package org.spongycastle.asn1.cms.ecc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cms.OriginatorPublicKey;

public class MQVuserKeyingMaterial extends ASN1Object {
    private ASN1OctetString addedukm;
    private OriginatorPublicKey ephemeralPublicKey;

    public MQVuserKeyingMaterial(OriginatorPublicKey ephemeralPublicKey2, ASN1OctetString addedukm2) {
        if (ephemeralPublicKey2 == null) {
            throw new IllegalArgumentException("Ephemeral public key cannot be null");
        }
        this.ephemeralPublicKey = ephemeralPublicKey2;
        this.addedukm = addedukm2;
    }

    private MQVuserKeyingMaterial(ASN1Sequence seq) {
        if (seq.size() == 1 || seq.size() == 2) {
            this.ephemeralPublicKey = OriginatorPublicKey.getInstance(seq.getObjectAt(0));
            if (seq.size() > 1) {
                this.addedukm = ASN1OctetString.getInstance((ASN1TaggedObject) seq.getObjectAt(1), true);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Sequence has incorrect number of elements");
    }

    public static MQVuserKeyingMaterial getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static MQVuserKeyingMaterial getInstance(Object obj) {
        if (obj instanceof MQVuserKeyingMaterial) {
            return (MQVuserKeyingMaterial) obj;
        }
        if (obj != null) {
            return new MQVuserKeyingMaterial(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public OriginatorPublicKey getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    public ASN1OctetString getAddedukm() {
        return this.addedukm;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.ephemeralPublicKey);
        if (this.addedukm != null) {
            v.add(new DERTaggedObject(true, 0, this.addedukm));
        }
        return new DERSequence(v);
    }
}
