package org.spongycastle.asn1.esf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class OcspListID extends ASN1Object {
    private ASN1Sequence ocspResponses;

    public static OcspListID getInstance(Object obj) {
        if (obj instanceof OcspListID) {
            return (OcspListID) obj;
        }
        if (obj != null) {
            return new OcspListID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OcspListID(ASN1Sequence seq) {
        if (seq.size() != 1) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        this.ocspResponses = (ASN1Sequence) seq.getObjectAt(0);
        Enumeration e = this.ocspResponses.getObjects();
        while (e.hasMoreElements()) {
            OcspResponsesID.getInstance(e.nextElement());
        }
    }

    public OcspListID(OcspResponsesID[] ocspResponses2) {
        this.ocspResponses = new DERSequence((ASN1Encodable[]) ocspResponses2);
    }

    public OcspResponsesID[] getOcspResponses() {
        OcspResponsesID[] result = new OcspResponsesID[this.ocspResponses.size()];
        for (int idx = 0; idx < result.length; idx++) {
            result[idx] = OcspResponsesID.getInstance(this.ocspResponses.getObjectAt(idx));
        }
        return result;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence((ASN1Encodable) this.ocspResponses);
    }
}
