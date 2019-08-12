package org.spongycastle.asn1.mozilla;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;

public class PublicKeyAndChallenge extends ASN1Object {
    private DERIA5String challenge;
    private ASN1Sequence pkacSeq;
    private SubjectPublicKeyInfo spki;

    public static PublicKeyAndChallenge getInstance(Object obj) {
        if (obj instanceof PublicKeyAndChallenge) {
            return (PublicKeyAndChallenge) obj;
        }
        if (obj != null) {
            return new PublicKeyAndChallenge(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private PublicKeyAndChallenge(ASN1Sequence seq) {
        this.pkacSeq = seq;
        this.spki = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(0));
        this.challenge = DERIA5String.getInstance(seq.getObjectAt(1));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.pkacSeq;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.spki;
    }

    public DERIA5String getChallenge() {
        return this.challenge;
    }
}
