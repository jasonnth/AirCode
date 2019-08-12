package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class AuthEnvelopedData extends ASN1Object {
    private ASN1Set authAttrs;
    private EncryptedContentInfo authEncryptedContentInfo;
    private ASN1OctetString mac;
    private OriginatorInfo originatorInfo;
    private ASN1Set recipientInfos;
    private ASN1Set unauthAttrs;
    private ASN1Integer version;

    public AuthEnvelopedData(OriginatorInfo originatorInfo2, ASN1Set recipientInfos2, EncryptedContentInfo authEncryptedContentInfo2, ASN1Set authAttrs2, ASN1OctetString mac2, ASN1Set unauthAttrs2) {
        this.version = new ASN1Integer(0);
        this.originatorInfo = originatorInfo2;
        this.recipientInfos = recipientInfos2;
        if (this.recipientInfos.size() == 0) {
            throw new IllegalArgumentException("AuthEnvelopedData requires at least 1 RecipientInfo");
        }
        this.authEncryptedContentInfo = authEncryptedContentInfo2;
        this.authAttrs = authAttrs2;
        if (authEncryptedContentInfo2.getContentType().equals(CMSObjectIdentifiers.data) || !(authAttrs2 == null || authAttrs2.size() == 0)) {
            this.mac = mac2;
            this.unauthAttrs = unauthAttrs2;
            return;
        }
        throw new IllegalArgumentException("authAttrs must be present with non-data content");
    }

    private AuthEnvelopedData(ASN1Sequence seq) {
        int index = 0 + 1;
        this.version = (ASN1Integer) seq.getObjectAt(0).toASN1Primitive();
        if (this.version.getValue().intValue() != 0) {
            throw new IllegalArgumentException("AuthEnvelopedData version number must be 0");
        }
        int index2 = index + 1;
        ASN1Primitive tmp = seq.getObjectAt(index).toASN1Primitive();
        if (tmp instanceof ASN1TaggedObject) {
            this.originatorInfo = OriginatorInfo.getInstance((ASN1TaggedObject) tmp, false);
            int index3 = index2 + 1;
            tmp = seq.getObjectAt(index2).toASN1Primitive();
            index2 = index3;
        }
        this.recipientInfos = ASN1Set.getInstance(tmp);
        if (this.recipientInfos.size() == 0) {
            throw new IllegalArgumentException("AuthEnvelopedData requires at least 1 RecipientInfo");
        }
        int index4 = index2 + 1;
        this.authEncryptedContentInfo = EncryptedContentInfo.getInstance(seq.getObjectAt(index2).toASN1Primitive());
        int index5 = index4 + 1;
        ASN1Primitive tmp2 = seq.getObjectAt(index4).toASN1Primitive();
        if (tmp2 instanceof ASN1TaggedObject) {
            this.authAttrs = ASN1Set.getInstance((ASN1TaggedObject) tmp2, false);
            int index6 = index5 + 1;
            tmp2 = seq.getObjectAt(index5).toASN1Primitive();
            index5 = index6;
        } else if (!this.authEncryptedContentInfo.getContentType().equals(CMSObjectIdentifiers.data) && (this.authAttrs == null || this.authAttrs.size() == 0)) {
            throw new IllegalArgumentException("authAttrs must be present with non-data content");
        }
        this.mac = ASN1OctetString.getInstance(tmp2);
        if (seq.size() > index5) {
            this.unauthAttrs = ASN1Set.getInstance((ASN1TaggedObject) seq.getObjectAt(index5).toASN1Primitive(), false);
        }
    }

    public static AuthEnvelopedData getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AuthEnvelopedData getInstance(Object obj) {
        if (obj == null || (obj instanceof AuthEnvelopedData)) {
            return (AuthEnvelopedData) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new AuthEnvelopedData((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid AuthEnvelopedData: " + obj.getClass().getName());
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public OriginatorInfo getOriginatorInfo() {
        return this.originatorInfo;
    }

    public ASN1Set getRecipientInfos() {
        return this.recipientInfos;
    }

    public EncryptedContentInfo getAuthEncryptedContentInfo() {
        return this.authEncryptedContentInfo;
    }

    public ASN1Set getAuthAttrs() {
        return this.authAttrs;
    }

    public ASN1OctetString getMac() {
        return this.mac;
    }

    public ASN1Set getUnauthAttrs() {
        return this.unauthAttrs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.version);
        if (this.originatorInfo != null) {
            v.add(new DERTaggedObject(false, 0, this.originatorInfo));
        }
        v.add(this.recipientInfos);
        v.add(this.authEncryptedContentInfo);
        if (this.authAttrs != null) {
            v.add(new DERTaggedObject(false, 1, this.authAttrs));
        }
        v.add(this.mac);
        if (this.unauthAttrs != null) {
            v.add(new DERTaggedObject(false, 2, this.unauthAttrs));
        }
        return new BERSequence(v);
    }
}
