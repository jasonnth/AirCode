package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.X509Extensions;

public class TBSRequest extends ASN1Object {

    /* renamed from: V1 */
    private static final ASN1Integer f6385V1 = new ASN1Integer(0);
    Extensions requestExtensions;
    ASN1Sequence requestList;
    GeneralName requestorName;
    ASN1Integer version;
    boolean versionSet;

    public TBSRequest(GeneralName requestorName2, ASN1Sequence requestList2, X509Extensions requestExtensions2) {
        this.version = f6385V1;
        this.requestorName = requestorName2;
        this.requestList = requestList2;
        this.requestExtensions = Extensions.getInstance(requestExtensions2);
    }

    public TBSRequest(GeneralName requestorName2, ASN1Sequence requestList2, Extensions requestExtensions2) {
        this.version = f6385V1;
        this.requestorName = requestorName2;
        this.requestList = requestList2;
        this.requestExtensions = requestExtensions2;
    }

    private TBSRequest(ASN1Sequence seq) {
        int index = 0;
        if (!(seq.getObjectAt(0) instanceof ASN1TaggedObject)) {
            this.version = f6385V1;
        } else if (((ASN1TaggedObject) seq.getObjectAt(0)).getTagNo() == 0) {
            this.versionSet = true;
            this.version = ASN1Integer.getInstance((ASN1TaggedObject) seq.getObjectAt(0), true);
            index = 0 + 1;
        } else {
            this.version = f6385V1;
        }
        if (seq.getObjectAt(index) instanceof ASN1TaggedObject) {
            int index2 = index + 1;
            this.requestorName = GeneralName.getInstance((ASN1TaggedObject) seq.getObjectAt(index), true);
            index = index2;
        }
        int index3 = index + 1;
        this.requestList = (ASN1Sequence) seq.getObjectAt(index);
        if (seq.size() == index3 + 1) {
            this.requestExtensions = Extensions.getInstance((ASN1TaggedObject) seq.getObjectAt(index3), true);
        }
    }

    public static TBSRequest getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static TBSRequest getInstance(Object obj) {
        if (obj instanceof TBSRequest) {
            return (TBSRequest) obj;
        }
        if (obj != null) {
            return new TBSRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public GeneralName getRequestorName() {
        return this.requestorName;
    }

    public ASN1Sequence getRequestList() {
        return this.requestList;
    }

    public Extensions getRequestExtensions() {
        return this.requestExtensions;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (!this.version.equals(f6385V1) || this.versionSet) {
            v.add(new DERTaggedObject(true, 0, this.version));
        }
        if (this.requestorName != null) {
            v.add(new DERTaggedObject(true, 1, this.requestorName));
        }
        v.add(this.requestList);
        if (this.requestExtensions != null) {
            v.add(new DERTaggedObject(true, 2, this.requestExtensions));
        }
        return new DERSequence(v);
    }
}
