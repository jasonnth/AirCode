package org.jmrtd.lds;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DLSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PACEDomainParameterInfo extends SecurityInfo {
    private static final String ID_DH_PUBLIC_NUMBER = "1.2.840.10046.2.1";
    private static final String ID_EC_PUBLIC_KEY = "1.2.840.10045.2.1";
    private static final long serialVersionUID = -5851251908152594728L;
    private AlgorithmIdentifier domainParameter;
    private int parameterId;
    private String protocolOID;

    public PACEDomainParameterInfo(String str, ASN1Encodable aSN1Encodable) {
        this(str, aSN1Encodable, -1);
    }

    public PACEDomainParameterInfo(String str, ASN1Encodable aSN1Encodable, int i) {
        this(str, toAlgorithmIdentifier(str, aSN1Encodable), i);
    }

    private PACEDomainParameterInfo(String str, AlgorithmIdentifier algorithmIdentifier, int i) {
        if (!checkRequiredIdentifier(str)) {
            throw new IllegalArgumentException("Invalid protocol id: " + str);
        }
        this.protocolOID = str;
        this.domainParameter = algorithmIdentifier;
        this.parameterId = i;
    }

    public String getObjectIdentifier() {
        return this.protocolOID;
    }

    public int getParameterId() {
        return this.parameterId;
    }

    public ASN1Encodable getParameters() {
        return this.domainParameter.getParameters();
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.protocolOID));
        aSN1EncodableVector.add(this.domainParameter);
        if (this.parameterId >= 0) {
            aSN1EncodableVector.add(new ASN1Integer((long) this.parameterId));
        }
        return new DLSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PaceInfo");
        stringBuffer.append("[");
        stringBuffer.append("protocol: " + this.protocolOID);
        stringBuffer.append(", domainParameter: " + this.domainParameter.toString());
        if (this.parameterId >= 0) {
            stringBuffer.append(", parameterId: " + this.parameterId);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public int hashCode() {
        return 111111111 + (this.protocolOID.hashCode() * 7) + (this.domainParameter.hashCode() * 5) + (this.parameterId * 3);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!PACEDomainParameterInfo.class.equals(obj.getClass())) {
            return false;
        }
        return getDERObject().equals(((PACEDomainParameterInfo) obj).getDERObject());
    }

    public static boolean checkRequiredIdentifier(String str) {
        return SecurityInfo.ID_PACE_DH_GM.equals(str) || SecurityInfo.ID_PACE_ECDH_GM.equals(str) || SecurityInfo.ID_PACE_DH_IM.equals(str) || SecurityInfo.ID_PACE_ECDH_IM.equals(str);
    }

    private static AlgorithmIdentifier toAlgorithmIdentifier(String str, ASN1Encodable aSN1Encodable) {
        if (SecurityInfo.ID_PACE_DH_GM.equals(str) || SecurityInfo.ID_PACE_DH_IM.equals(str)) {
            return new AlgorithmIdentifier(new ASN1ObjectIdentifier(ID_DH_PUBLIC_NUMBER), aSN1Encodable);
        }
        if (SecurityInfo.ID_PACE_ECDH_GM.equals(str) || SecurityInfo.ID_PACE_ECDH_IM.equals(str)) {
            return new AlgorithmIdentifier(new ASN1ObjectIdentifier(ID_EC_PUBLIC_KEY), aSN1Encodable);
        }
        throw new IllegalArgumentException("Cannot infer algorithm OID from protocol OID: " + str);
    }
}
