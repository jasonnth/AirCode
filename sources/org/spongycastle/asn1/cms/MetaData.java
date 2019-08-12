package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERUTF8String;

public class MetaData extends ASN1Object {
    private DERUTF8String fileName;
    private ASN1Boolean hashProtected;
    private DERIA5String mediaType;
    private Attributes otherMetaData;

    public MetaData(ASN1Boolean hashProtected2, DERUTF8String fileName2, DERIA5String mediaType2, Attributes otherMetaData2) {
        this.hashProtected = hashProtected2;
        this.fileName = fileName2;
        this.mediaType = mediaType2;
        this.otherMetaData = otherMetaData2;
    }

    private MetaData(ASN1Sequence seq) {
        this.hashProtected = ASN1Boolean.getInstance((Object) seq.getObjectAt(0));
        int index = 1;
        if (1 < seq.size() && (seq.getObjectAt(1) instanceof DERUTF8String)) {
            int index2 = 1 + 1;
            this.fileName = DERUTF8String.getInstance(seq.getObjectAt(1));
            index = index2;
        }
        if (index < seq.size() && (seq.getObjectAt(index) instanceof DERIA5String)) {
            int index3 = index + 1;
            this.mediaType = DERIA5String.getInstance(seq.getObjectAt(index));
            index = index3;
        }
        if (index < seq.size()) {
            int index4 = index + 1;
            this.otherMetaData = Attributes.getInstance(seq.getObjectAt(index));
            int i = index4;
        }
    }

    public static MetaData getInstance(Object obj) {
        if (obj instanceof MetaData) {
            return (MetaData) obj;
        }
        if (obj != null) {
            return new MetaData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.hashProtected);
        if (this.fileName != null) {
            v.add(this.fileName);
        }
        if (this.mediaType != null) {
            v.add(this.mediaType);
        }
        if (this.otherMetaData != null) {
            v.add(this.otherMetaData);
        }
        return new DERSequence(v);
    }

    public boolean isHashProtected() {
        return this.hashProtected.isTrue();
    }

    public DERUTF8String getFileName() {
        return this.fileName;
    }

    public DERIA5String getMediaType() {
        return this.mediaType;
    }

    public Attributes getOtherMetaData() {
        return this.otherMetaData;
    }
}
