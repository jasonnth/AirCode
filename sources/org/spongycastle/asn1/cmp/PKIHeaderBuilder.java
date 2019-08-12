package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.GeneralName;

public class PKIHeaderBuilder {
    private PKIFreeText freeText;
    private ASN1Sequence generalInfo;
    private ASN1GeneralizedTime messageTime;
    private AlgorithmIdentifier protectionAlg;
    private ASN1Integer pvno;
    private ASN1OctetString recipKID;
    private ASN1OctetString recipNonce;
    private GeneralName recipient;
    private GeneralName sender;
    private ASN1OctetString senderKID;
    private ASN1OctetString senderNonce;
    private ASN1OctetString transactionID;

    public PKIHeaderBuilder(int pvno2, GeneralName sender2, GeneralName recipient2) {
        this(new ASN1Integer((long) pvno2), sender2, recipient2);
    }

    private PKIHeaderBuilder(ASN1Integer pvno2, GeneralName sender2, GeneralName recipient2) {
        this.pvno = pvno2;
        this.sender = sender2;
        this.recipient = recipient2;
    }

    public PKIHeaderBuilder setMessageTime(ASN1GeneralizedTime time) {
        this.messageTime = time;
        return this;
    }

    public PKIHeaderBuilder setProtectionAlg(AlgorithmIdentifier aid) {
        this.protectionAlg = aid;
        return this;
    }

    public PKIHeaderBuilder setSenderKID(byte[] kid) {
        return setSenderKID((ASN1OctetString) kid == null ? null : new DEROctetString(kid));
    }

    public PKIHeaderBuilder setSenderKID(ASN1OctetString kid) {
        this.senderKID = kid;
        return this;
    }

    public PKIHeaderBuilder setRecipKID(byte[] kid) {
        return setRecipKID(kid == null ? null : new DEROctetString(kid));
    }

    public PKIHeaderBuilder setRecipKID(DEROctetString kid) {
        this.recipKID = kid;
        return this;
    }

    public PKIHeaderBuilder setTransactionID(byte[] tid) {
        return setTransactionID((ASN1OctetString) tid == null ? null : new DEROctetString(tid));
    }

    public PKIHeaderBuilder setTransactionID(ASN1OctetString tid) {
        this.transactionID = tid;
        return this;
    }

    public PKIHeaderBuilder setSenderNonce(byte[] nonce) {
        return setSenderNonce((ASN1OctetString) nonce == null ? null : new DEROctetString(nonce));
    }

    public PKIHeaderBuilder setSenderNonce(ASN1OctetString nonce) {
        this.senderNonce = nonce;
        return this;
    }

    public PKIHeaderBuilder setRecipNonce(byte[] nonce) {
        return setRecipNonce((ASN1OctetString) nonce == null ? null : new DEROctetString(nonce));
    }

    public PKIHeaderBuilder setRecipNonce(ASN1OctetString nonce) {
        this.recipNonce = nonce;
        return this;
    }

    public PKIHeaderBuilder setFreeText(PKIFreeText text) {
        this.freeText = text;
        return this;
    }

    public PKIHeaderBuilder setGeneralInfo(InfoTypeAndValue genInfo) {
        return setGeneralInfo(makeGeneralInfoSeq(genInfo));
    }

    public PKIHeaderBuilder setGeneralInfo(InfoTypeAndValue[] genInfos) {
        return setGeneralInfo(makeGeneralInfoSeq(genInfos));
    }

    public PKIHeaderBuilder setGeneralInfo(ASN1Sequence seqOfInfoTypeAndValue) {
        this.generalInfo = seqOfInfoTypeAndValue;
        return this;
    }

    private static ASN1Sequence makeGeneralInfoSeq(InfoTypeAndValue generalInfo2) {
        return new DERSequence((ASN1Encodable) generalInfo2);
    }

    private static ASN1Sequence makeGeneralInfoSeq(InfoTypeAndValue[] generalInfos) {
        if (generalInfos == null) {
            return null;
        }
        ASN1EncodableVector v = new ASN1EncodableVector();
        for (InfoTypeAndValue add : generalInfos) {
            v.add(add);
        }
        return new DERSequence(v);
    }

    public PKIHeader build() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.pvno);
        v.add(this.sender);
        v.add(this.recipient);
        addOptional(v, 0, this.messageTime);
        addOptional(v, 1, this.protectionAlg);
        addOptional(v, 2, this.senderKID);
        addOptional(v, 3, this.recipKID);
        addOptional(v, 4, this.transactionID);
        addOptional(v, 5, this.senderNonce);
        addOptional(v, 6, this.recipNonce);
        addOptional(v, 7, this.freeText);
        addOptional(v, 8, this.generalInfo);
        this.messageTime = null;
        this.protectionAlg = null;
        this.senderKID = null;
        this.recipKID = null;
        this.transactionID = null;
        this.senderNonce = null;
        this.recipNonce = null;
        this.freeText = null;
        this.generalInfo = null;
        return PKIHeader.getInstance(new DERSequence(v));
    }

    private void addOptional(ASN1EncodableVector v, int tagNo, ASN1Encodable obj) {
        if (obj != null) {
            v.add(new DERTaggedObject(true, tagNo, obj));
        }
    }
}
