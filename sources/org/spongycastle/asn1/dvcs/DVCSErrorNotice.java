package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.cmp.PKIStatusInfo;
import org.spongycastle.asn1.x509.GeneralName;

public class DVCSErrorNotice extends ASN1Object {
    private GeneralName transactionIdentifier;
    private PKIStatusInfo transactionStatus;

    public DVCSErrorNotice(PKIStatusInfo status) {
        this(status, null);
    }

    public DVCSErrorNotice(PKIStatusInfo status, GeneralName transactionIdentifier2) {
        this.transactionStatus = status;
        this.transactionIdentifier = transactionIdentifier2;
    }

    private DVCSErrorNotice(ASN1Sequence seq) {
        this.transactionStatus = PKIStatusInfo.getInstance(seq.getObjectAt(0));
        if (seq.size() > 1) {
            this.transactionIdentifier = GeneralName.getInstance(seq.getObjectAt(1));
        }
    }

    public static DVCSErrorNotice getInstance(Object obj) {
        if (obj instanceof DVCSErrorNotice) {
            return (DVCSErrorNotice) obj;
        }
        if (obj != null) {
            return new DVCSErrorNotice(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DVCSErrorNotice getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.transactionStatus);
        if (this.transactionIdentifier != null) {
            v.add(this.transactionIdentifier);
        }
        return new DERSequence(v);
    }

    public String toString() {
        return "DVCSErrorNotice {\ntransactionStatus: " + this.transactionStatus + "\n" + (this.transactionIdentifier != null ? "transactionIdentifier: " + this.transactionIdentifier + "\n" : "") + "}\n";
    }

    public PKIStatusInfo getTransactionStatus() {
        return this.transactionStatus;
    }

    public GeneralName getTransactionIdentifier() {
        return this.transactionIdentifier;
    }
}
