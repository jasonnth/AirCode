package org.spongycastle.asn1.ess;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERUTF8String;

public class ContentHints extends ASN1Object {
    private DERUTF8String contentDescription;
    private ASN1ObjectIdentifier contentType;

    public static ContentHints getInstance(Object o) {
        if (o instanceof ContentHints) {
            return (ContentHints) o;
        }
        if (o != null) {
            return new ContentHints(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    private ContentHints(ASN1Sequence seq) {
        ASN1Encodable field = seq.getObjectAt(0);
        if (field.toASN1Primitive() instanceof DERUTF8String) {
            this.contentDescription = DERUTF8String.getInstance(field);
            this.contentType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(1));
            return;
        }
        this.contentType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
    }

    public ContentHints(ASN1ObjectIdentifier contentType2) {
        this.contentType = contentType2;
        this.contentDescription = null;
    }

    public ContentHints(ASN1ObjectIdentifier contentType2, DERUTF8String contentDescription2) {
        this.contentType = contentType2;
        this.contentDescription = contentDescription2;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public DERUTF8String getContentDescription() {
        return this.contentDescription;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.contentDescription != null) {
            v.add(this.contentDescription);
        }
        v.add(this.contentType);
        return new DERSequence(v);
    }
}
