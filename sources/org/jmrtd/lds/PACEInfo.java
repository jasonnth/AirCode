package org.jmrtd.lds;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import org.jmrtd.Util;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DLSequence;
import org.spongycastle.crypto.agreement.DHStandardGroups;
import org.spongycastle.jce.ECNamedCurveTable;

public class PACEInfo extends SecurityInfo {
    private static final ECParameterSpec PARAMS_ECP_BRAINPOOL_P192_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("brainpoolp192r1"));
    private static final ECParameterSpec PARAMS_ECP_BRAINPOOL_P224_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("brainpoolp224r1"));
    private static final ECParameterSpec PARAMS_ECP_BRAINPOOL_P256_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("brainpoolp256r1"));
    private static final ECParameterSpec PARAMS_ECP_BRAINPOOL_P320_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("brainpoolp320r1"));
    private static final ECParameterSpec PARAMS_ECP_BRAINPOOL_P384_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("brainpoolp384r1"));
    private static final ECParameterSpec PARAMS_ECP_BRAINPOOL_P512_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("brainpoolp512r1"));
    private static final ECParameterSpec PARAMS_ECP_NIST_P192_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("secp192r1"));
    private static final ECParameterSpec PARAMS_ECP_NIST_P224_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("secp224r1"));
    private static final ECParameterSpec PARAMS_ECP_NIST_P256_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("secp256r1"));
    private static final ECParameterSpec PARAMS_ECP_NIST_P384_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("secp384r1"));
    private static final ECParameterSpec PARAMS_ECP_NIST_P521_R1 = Util.toExplicitECParameterSpec(ECNamedCurveTable.getParameterSpec("secp521r1"));
    private static final DHParameterSpec PARAMS_GFP_1024_160 = Util.toExplicitDHParameterSpec(DHStandardGroups.rfc5114_1024_160);
    private static final DHParameterSpec PARAMS_GFP_2048_224 = Util.toExplicitDHParameterSpec(DHStandardGroups.rfc5114_2048_224);
    private static final DHParameterSpec PARAMS_GFP_2048_256 = Util.toExplicitDHParameterSpec(DHStandardGroups.rfc5114_2048_256);
    public static final int PARAM_ID_ECP_BRAINPOOL_P192_R1 = 9;
    public static final int PARAM_ID_ECP_BRAINPOOL_P224_R1 = 11;
    public static final int PARAM_ID_ECP_BRAINPOOL_P256_R1 = 13;
    public static final int PARAM_ID_ECP_BRAINPOOL_P320_R1 = 14;
    public static final int PARAM_ID_ECP_BRAINPOOL_P384_R1 = 16;
    public static final int PARAM_ID_ECP_BRAINPOOL_P512_R1 = 17;
    public static final int PARAM_ID_ECP_NIST_P192_R1 = 8;
    public static final int PARAM_ID_ECP_NIST_P224_R1 = 10;
    public static final int PARAM_ID_ECP_NIST_P384_R1 = 15;
    public static final int PARAM_ID_ECP_NIST_P521_R1 = 18;
    public static final int PARAM_ID_ECP_NST_P256_R1 = 12;
    public static final int PARAM_ID_GFP_1024_160 = 0;
    public static final int PARAM_ID_GFP_2048_224 = 1;
    public static final int PARAM_ID_GFP_2048_256 = 2;
    private static final long serialVersionUID = 7960925013249578359L;
    private int parameterId;
    private String protocolOID;
    private int version;

    public enum MappingType {
        GM,
        IM
    }

    public PACEInfo(String str, int i, int i2) {
        if (!checkRequiredIdentifier(str)) {
            throw new IllegalArgumentException("Invalid OID");
        } else if (i != 2) {
            throw new IllegalArgumentException("Invalid version, must be 2");
        } else {
            this.protocolOID = str;
            this.version = i;
            this.parameterId = i2;
        }
    }

    public static PACEInfo createPACEInfo(byte[] bArr) {
        ASN1Sequence instance = ASN1Sequence.getInstance(bArr);
        String id = ((ASN1ObjectIdentifier) instance.getObjectAt(0)).getId();
        ASN1Primitive aSN1Primitive = instance.getObjectAt(1).toASN1Primitive();
        ASN1Primitive aSN1Primitive2 = null;
        if (instance.size() == 3) {
            aSN1Primitive2 = instance.getObjectAt(2).toASN1Primitive();
        }
        int intValue = ((ASN1Integer) aSN1Primitive).getValue().intValue();
        int i = -1;
        if (aSN1Primitive2 != null) {
            i = ((ASN1Integer) aSN1Primitive2).getValue().intValue();
        }
        return new PACEInfo(id, intValue, i);
    }

    public String getObjectIdentifier() {
        return this.protocolOID;
    }

    public int getVersion() {
        return this.version;
    }

    public int getParameterId() {
        return this.parameterId;
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.protocolOID));
        aSN1EncodableVector.add(new ASN1Integer((long) this.version));
        if (this.parameterId >= 0) {
            aSN1EncodableVector.add(new ASN1Integer((long) this.parameterId));
        }
        return new DLSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PaceInfo");
        stringBuffer.append("[");
        stringBuffer.append("protocol: " + toProtocolOIDString(this.protocolOID));
        stringBuffer.append(", version: " + this.version);
        if (this.parameterId >= 0) {
            stringBuffer.append(", parameterId: " + toStandardizedParamIdString(this.parameterId));
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public int hashCode() {
        return 1234567891 + (this.protocolOID.hashCode() * 7) + (this.version * 5) + (this.parameterId * 3);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!PACEInfo.class.equals(obj.getClass())) {
            return false;
        }
        return getDERObject().equals(((PACEInfo) obj).getDERObject());
    }

    public static boolean checkRequiredIdentifier(String str) {
        return toMappingType(str) != null;
    }

    public static MappingType toMappingType(String str) {
        if (SecurityInfo.ID_PACE_DH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_256.equals(str)) {
            return MappingType.GM;
        }
        if (SecurityInfo.ID_PACE_DH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_256.equals(str)) {
            return MappingType.IM;
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static String toKeyAgreementAlgorithm(String str) {
        if (SecurityInfo.ID_PACE_DH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_DH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_256.equals(str)) {
            return "DH";
        }
        if (SecurityInfo.ID_PACE_ECDH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_256.equals(str)) {
            return "ECDH";
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static String toCipherAlgorithm(String str) {
        if (SecurityInfo.ID_PACE_DH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_3DES_CBC_CBC.equals(str)) {
            return "DESede";
        }
        if (SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_256.equals(str)) {
            return "AES";
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static String toDigestAlgorithm(String str) {
        if (SecurityInfo.ID_PACE_DH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_128.equals(str)) {
            return "SHA-1";
        }
        if (SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_256.equals(str)) {
            return "SHA-256";
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static int toKeyLength(String str) {
        if (SecurityInfo.ID_PACE_DH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_128.equals(str)) {
            return 128;
        }
        if (SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_192.equals(str)) {
            return 192;
        }
        if (SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_256.equals(str)) {
            return 256;
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static AlgorithmParameterSpec toParameterSpec(int i) {
        switch (i) {
            case 0:
                return PARAMS_GFP_1024_160;
            case 1:
                return PARAMS_GFP_2048_224;
            case 2:
                return PARAMS_GFP_2048_256;
            case 8:
                return PARAMS_ECP_NIST_P192_R1;
            case 9:
                return PARAMS_ECP_BRAINPOOL_P192_R1;
            case 10:
                return PARAMS_ECP_NIST_P224_R1;
            case 11:
                return PARAMS_ECP_BRAINPOOL_P224_R1;
            case 12:
                return PARAMS_ECP_NIST_P256_R1;
            case 13:
                return PARAMS_ECP_BRAINPOOL_P256_R1;
            case 14:
                return PARAMS_ECP_BRAINPOOL_P320_R1;
            case 15:
                return PARAMS_ECP_NIST_P384_R1;
            case 16:
                return PARAMS_ECP_BRAINPOOL_P384_R1;
            case 17:
                return PARAMS_ECP_BRAINPOOL_P512_R1;
            case 18:
                return PARAMS_ECP_NIST_P521_R1;
            default:
                throw new NumberFormatException("Unknown standardized domain parameters " + i);
        }
    }

    private String toStandardizedParamIdString(int i) {
        switch (i) {
            case 0:
                return "1024-bit MODP Group with 160-bit Prime Order Subgroup";
            case 1:
                return "2048-bit MODP Group with 224-bit Prime Order Subgroup";
            case 2:
                return "2048-bit MODP Group with 256-bit Prime Order Subgroup";
            case 8:
                return "NIST P-192 (secp192r1)";
            case 9:
                return "BrainpoolP192r1";
            case 10:
                return "NIST P-224 (secp224r1)";
            case 11:
                return "BrainpoolP224r1";
            case 12:
                return "NIST P-256 (secp256r1)";
            case 13:
                return "BrainpoolP256r1";
            case 14:
                return "BrainpoolP320r1";
            case 15:
                return "NIST P-384 (secp384r1)";
            case 16:
                return "BrainpoolP384r1";
            case 17:
                return "BrainpoolP512r1";
            case 18:
                return "NIST P-521 (secp521r1)";
            default:
                return Integer.toString(i);
        }
    }

    private String toProtocolOIDString(String str) {
        if (SecurityInfo.ID_PACE_DH_GM_3DES_CBC_CBC.equals(str)) {
            return "id-PACE-DH-GM-3DES-CBC-CBC";
        }
        if (SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_128.equals(str)) {
            return "id-PACE-DH-GM-AES-CBC-CMAC-128";
        }
        if (SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_192.equals(str)) {
            return "id-PACE-DH-GM-AES-CBC-CMAC-192";
        }
        if (SecurityInfo.ID_PACE_DH_GM_AES_CBC_CMAC_256.equals(str)) {
            return "id-PACE-DH-GM-AES-CBC-CMAC-256";
        }
        if (SecurityInfo.ID_PACE_DH_IM_3DES_CBC_CBC.equals(str)) {
            return "id-PACE-DH-IM-3DES-CBC-CBC";
        }
        if (SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_128.equals(str)) {
            return "id-PACE-DH-IM-AES-CBC-CMAC-128";
        }
        if (SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_192.equals(str)) {
            return "id-PACE-DH-IM-AES-CBC-CMAC-192";
        }
        if (SecurityInfo.ID_PACE_DH_IM_AES_CBC_CMAC_256.equals(str)) {
            return "id-PACE_DH-IM-AES-CBC-CMAC-256";
        }
        if (SecurityInfo.ID_PACE_ECDH_GM_3DES_CBC_CBC.equals(str)) {
            return "id-PACE_ECDH-GM-3DES-CBC-CBC";
        }
        if (SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_128.equals(str)) {
            return "id-PACE-ECDH-GM-AES-CBC-CMAC-128";
        }
        if (SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_192.equals(str)) {
            return "id-PACE-ECDH-GM-AES-CBC-CMAC-192";
        }
        if (SecurityInfo.ID_PACE_ECDH_GM_AES_CBC_CMAC_256.equals(str)) {
            return "id-PACE-ECDH-GM-AES-CBC-CMAC-256";
        }
        if (SecurityInfo.ID_PACE_ECDH_IM_3DES_CBC_CBC.equals(str)) {
            return "id-PACE-ECDH-IM_3DES-CBC-CBC";
        }
        if (SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_128.equals(str)) {
            return "id-PACE-ECDH-IM-AES-CBC-CMAC-128";
        }
        if (SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_192.equals(str)) {
            return "id-PACE-ECDH-IM-AES-CBC-CMAC-192";
        }
        if (SecurityInfo.ID_PACE_ECDH_IM_AES_CBC_CMAC_256.equals(str)) {
            return "id-PACE-ECDH-IM-AES-CBC-CMAC-256";
        }
        return str;
    }
}
