package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.DERIA5String;

public class TimeStampedData extends ASN1Object {
    private ASN1OctetString content;
    private DERIA5String dataUri;
    private MetaData metaData;
    private Evidence temporalEvidence;
    private ASN1Integer version;

    public TimeStampedData(DERIA5String dataUri2, MetaData metaData2, ASN1OctetString content2, Evidence temporalEvidence2) {
        this.version = new ASN1Integer(1);
        this.dataUri = dataUri2;
        this.metaData = metaData2;
        this.content = content2;
        this.temporalEvidence = temporalEvidence2;
    }

    private TimeStampedData(ASN1Sequence seq) {
        this.version = ASN1Integer.getInstance(seq.getObjectAt(0));
        int index = 1;
        if (seq.getObjectAt(1) instanceof DERIA5String) {
            int index2 = 1 + 1;
            this.dataUri = DERIA5String.getInstance(seq.getObjectAt(1));
            index = index2;
        }
        if ((seq.getObjectAt(index) instanceof MetaData) || (seq.getObjectAt(index) instanceof ASN1Sequence)) {
            int index3 = index + 1;
            this.metaData = MetaData.getInstance(seq.getObjectAt(index));
            index = index3;
        }
        if (seq.getObjectAt(index) instanceof ASN1OctetString) {
            int index4 = index + 1;
            this.content = ASN1OctetString.getInstance(seq.getObjectAt(index));
            index = index4;
        }
        this.temporalEvidence = Evidence.getInstance(seq.getObjectAt(index));
    }

    public static TimeStampedData getInstance(Object obj) {
        if (obj == null || (obj instanceof TimeStampedData)) {
            return (TimeStampedData) obj;
        }
        return new TimeStampedData(ASN1Sequence.getInstance(obj));
    }

    public DERIA5String getDataUri() {
        return this.dataUri;
    }

    public MetaData getMetaData() {
        return this.metaData;
    }

    public ASN1OctetString getContent() {
        return this.content;
    }

    public Evidence getTemporalEvidence() {
        return this.temporalEvidence;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.version);
        if (this.dataUri != null) {
            v.add(this.dataUri);
        }
        if (this.metaData != null) {
            v.add(this.metaData);
        }
        if (this.content != null) {
            v.add(this.content);
        }
        v.add(this.temporalEvidence);
        return new BERSequence(v);
    }
}
