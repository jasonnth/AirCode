package org.spongycastle.asn1.cmp;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ErrorMsgContent extends ASN1Object {
    private ASN1Integer errorCode;
    private PKIFreeText errorDetails;
    private PKIStatusInfo pkiStatusInfo;

    private ErrorMsgContent(ASN1Sequence seq) {
        Enumeration en = seq.getObjects();
        this.pkiStatusInfo = PKIStatusInfo.getInstance(en.nextElement());
        while (en.hasMoreElements()) {
            Object o = en.nextElement();
            if (o instanceof ASN1Integer) {
                this.errorCode = ASN1Integer.getInstance(o);
            } else {
                this.errorDetails = PKIFreeText.getInstance(o);
            }
        }
    }

    public static ErrorMsgContent getInstance(Object o) {
        if (o instanceof ErrorMsgContent) {
            return (ErrorMsgContent) o;
        }
        if (o != null) {
            return new ErrorMsgContent(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public ErrorMsgContent(PKIStatusInfo pkiStatusInfo2) {
        this(pkiStatusInfo2, null, null);
    }

    public ErrorMsgContent(PKIStatusInfo pkiStatusInfo2, ASN1Integer errorCode2, PKIFreeText errorDetails2) {
        if (pkiStatusInfo2 == null) {
            throw new IllegalArgumentException("'pkiStatusInfo' cannot be null");
        }
        this.pkiStatusInfo = pkiStatusInfo2;
        this.errorCode = errorCode2;
        this.errorDetails = errorDetails2;
    }

    public PKIStatusInfo getPKIStatusInfo() {
        return this.pkiStatusInfo;
    }

    public ASN1Integer getErrorCode() {
        return this.errorCode;
    }

    public PKIFreeText getErrorDetails() {
        return this.errorDetails;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.pkiStatusInfo);
        addOptional(v, this.errorCode);
        addOptional(v, this.errorDetails);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, ASN1Encodable obj) {
        if (obj != null) {
            v.add(obj);
        }
    }
}
