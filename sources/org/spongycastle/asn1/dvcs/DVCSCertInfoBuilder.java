package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cmp.PKIStatusInfo;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.PolicyInformation;

public class DVCSCertInfoBuilder {
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_CERTS = 3;
    private static final int TAG_DV_STATUS = 0;
    private static final int TAG_POLICY = 1;
    private static final int TAG_REQ_SIGNATURE = 2;
    private ASN1Sequence certs;
    private DVCSRequestInformation dvReqInfo;
    private PKIStatusInfo dvStatus;
    private Extensions extensions;
    private DigestInfo messageImprint;
    private PolicyInformation policy;
    private ASN1Set reqSignature;
    private DVCSTime responseTime;
    private ASN1Integer serialNumber;
    private int version = 1;

    public DVCSCertInfoBuilder(DVCSRequestInformation dvReqInfo2, DigestInfo messageImprint2, ASN1Integer serialNumber2, DVCSTime responseTime2) {
        this.dvReqInfo = dvReqInfo2;
        this.messageImprint = messageImprint2;
        this.serialNumber = serialNumber2;
        this.responseTime = responseTime2;
    }

    public DVCSCertInfo build() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.version != 1) {
            v.add(new ASN1Integer((long) this.version));
        }
        v.add(this.dvReqInfo);
        v.add(this.messageImprint);
        v.add(this.serialNumber);
        v.add(this.responseTime);
        if (this.dvStatus != null) {
            v.add(new DERTaggedObject(false, 0, this.dvStatus));
        }
        if (this.policy != null) {
            v.add(new DERTaggedObject(false, 1, this.policy));
        }
        if (this.reqSignature != null) {
            v.add(new DERTaggedObject(false, 2, this.reqSignature));
        }
        if (this.certs != null) {
            v.add(new DERTaggedObject(false, 3, this.certs));
        }
        if (this.extensions != null) {
            v.add(this.extensions);
        }
        return DVCSCertInfo.getInstance(new DERSequence(v));
    }

    public void setVersion(int version2) {
        this.version = version2;
    }

    public void setDvReqInfo(DVCSRequestInformation dvReqInfo2) {
        this.dvReqInfo = dvReqInfo2;
    }

    public void setMessageImprint(DigestInfo messageImprint2) {
        this.messageImprint = messageImprint2;
    }

    public void setSerialNumber(ASN1Integer serialNumber2) {
        this.serialNumber = serialNumber2;
    }

    public void setResponseTime(DVCSTime responseTime2) {
        this.responseTime = responseTime2;
    }

    public void setDvStatus(PKIStatusInfo dvStatus2) {
        this.dvStatus = dvStatus2;
    }

    public void setPolicy(PolicyInformation policy2) {
        this.policy = policy2;
    }

    public void setReqSignature(ASN1Set reqSignature2) {
        this.reqSignature = reqSignature2;
    }

    public void setCerts(TargetEtcChain[] certs2) {
        this.certs = new DERSequence((ASN1Encodable[]) certs2);
    }

    public void setExtensions(Extensions extensions2) {
        this.extensions = extensions2;
    }
}
