package org.spongycastle.asn1.cms;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1OctetStringParser;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.DERIA5String;

public class TimeStampedDataParser {
    private ASN1OctetStringParser content;
    private DERIA5String dataUri;
    private MetaData metaData;
    private ASN1SequenceParser parser;
    private Evidence temporalEvidence;
    private ASN1Integer version;

    private TimeStampedDataParser(ASN1SequenceParser parser2) throws IOException {
        this.parser = parser2;
        this.version = ASN1Integer.getInstance(parser2.readObject());
        ASN1Encodable obj = parser2.readObject();
        if (obj instanceof DERIA5String) {
            this.dataUri = DERIA5String.getInstance(obj);
            obj = parser2.readObject();
        }
        if ((obj instanceof MetaData) || (obj instanceof ASN1SequenceParser)) {
            this.metaData = MetaData.getInstance(obj.toASN1Primitive());
            obj = parser2.readObject();
        }
        if (obj instanceof ASN1OctetStringParser) {
            this.content = (ASN1OctetStringParser) obj;
        }
    }

    public static TimeStampedDataParser getInstance(Object obj) throws IOException {
        if (obj instanceof ASN1Sequence) {
            return new TimeStampedDataParser(((ASN1Sequence) obj).parser());
        }
        if (obj instanceof ASN1SequenceParser) {
            return new TimeStampedDataParser((ASN1SequenceParser) obj);
        }
        return null;
    }

    public DERIA5String getDataUri() {
        return this.dataUri;
    }

    public MetaData getMetaData() {
        return this.metaData;
    }

    public ASN1OctetStringParser getContent() {
        return this.content;
    }

    public Evidence getTemporalEvidence() throws IOException {
        if (this.temporalEvidence == null) {
            this.temporalEvidence = Evidence.getInstance(this.parser.readObject().toASN1Primitive());
        }
        return this.temporalEvidence;
    }
}
