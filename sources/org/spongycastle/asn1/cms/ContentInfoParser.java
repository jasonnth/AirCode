package org.spongycastle.asn1.cms;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.ASN1TaggedObjectParser;

public class ContentInfoParser {
    private ASN1TaggedObjectParser content;
    private ASN1ObjectIdentifier contentType;

    public ContentInfoParser(ASN1SequenceParser seq) throws IOException {
        this.contentType = (ASN1ObjectIdentifier) seq.readObject();
        this.content = (ASN1TaggedObjectParser) seq.readObject();
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public ASN1Encodable getContent(int tag) throws IOException {
        if (this.content != null) {
            return this.content.getObjectParser(tag, true);
        }
        return null;
    }
}
