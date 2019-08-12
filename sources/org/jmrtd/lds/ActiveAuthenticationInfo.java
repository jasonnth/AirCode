package org.jmrtd.lds;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DLSequence;

public class ActiveAuthenticationInfo extends SecurityInfo {
    public static final String ECDSA_PLAIN_RIPEMD160_OID = "0.4.0.127.0.7.1.1.4.1.6";
    public static final String ECDSA_PLAIN_SHA1_OID = "0.4.0.127.0.7.1.1.4.1.1";
    public static final String ECDSA_PLAIN_SHA224_OID = "0.4.0.127.0.7.1.1.4.1.2";
    public static final String ECDSA_PLAIN_SHA256_OID = "0.4.0.127.0.7.1.1.4.1.3";
    public static final String ECDSA_PLAIN_SHA384_OID = "0.4.0.127.0.7.1.1.4.1.4";
    public static final String ECDSA_PLAIN_SHA512_OID = "0.4.0.127.0.7.1.1.4.1.5";
    public static final String ECDSA_PLAIN_SIGNATURES = "0.4.0.127.0.7.1.1.4.1";
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int VERSION_NUM = 1;
    private static final long serialVersionUID = 6830847342039845308L;
    private String oid;
    private String signatureAlgorithmOID;
    private int version;

    ActiveAuthenticationInfo(String str, int i, String str2) {
        this.oid = str;
        this.version = i;
        this.signatureAlgorithmOID = str2;
        checkFields();
    }

    public ActiveAuthenticationInfo(String str) {
        this(SecurityInfo.ID_AA_OID, 1, str);
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.oid));
        aSN1EncodableVector.add(new ASN1Integer((long) this.version));
        if (this.signatureAlgorithmOID != null) {
            aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.signatureAlgorithmOID));
        }
        return new DLSequence(aSN1EncodableVector);
    }

    public String getObjectIdentifier() {
        return this.oid;
    }

    public String getSignatureAlgorithmOID() {
        return this.signatureAlgorithmOID;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ActiveAuthenticationInfo");
        stringBuffer.append("[");
        stringBuffer.append("signatureAlgorithmOID = " + getSignatureAlgorithmOID());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public int hashCode() {
        int hashCode;
        int hashCode2;
        if (this.oid == null) {
            hashCode = 0;
        } else {
            hashCode = this.oid.hashCode();
        }
        int i = (this.version * 5) + (hashCode * 3) + 12345;
        if (this.signatureAlgorithmOID == null) {
            hashCode2 = 1;
        } else {
            hashCode2 = this.signatureAlgorithmOID.hashCode();
        }
        return (hashCode2 * 11) + i;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!ActiveAuthenticationInfo.class.equals(obj.getClass())) {
            return false;
        }
        return getDERObject().equals(((ActiveAuthenticationInfo) obj).getDERObject());
    }

    static boolean checkRequiredIdentifier(String str) {
        return SecurityInfo.ID_AA_OID.equals(str);
    }

    private void checkFields() {
        try {
            if (!checkRequiredIdentifier(this.oid)) {
                throw new IllegalArgumentException("Wrong identifier: " + this.oid);
            } else if (this.version != 1) {
                throw new IllegalArgumentException("Wrong version: " + this.version);
            } else if (!ECDSA_PLAIN_SHA1_OID.equals(this.signatureAlgorithmOID) && !ECDSA_PLAIN_SHA224_OID.equals(this.signatureAlgorithmOID) && !ECDSA_PLAIN_SHA256_OID.equals(this.signatureAlgorithmOID) && !ECDSA_PLAIN_SHA384_OID.equals(this.signatureAlgorithmOID) && !ECDSA_PLAIN_SHA512_OID.equals(this.signatureAlgorithmOID) && !ECDSA_PLAIN_RIPEMD160_OID.equals(this.signatureAlgorithmOID)) {
                throw new IllegalArgumentException("Wrong signature algorithm OID: " + this.signatureAlgorithmOID);
            }
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalArgumentException("Malformed ActiveAuthenticationInfo.");
        }
    }

    public static String lookupMnemonicByOID(String str) throws NoSuchAlgorithmException {
        if (ECDSA_PLAIN_SHA1_OID.equals(str)) {
            return "SHA1withECDSA";
        }
        if (ECDSA_PLAIN_SHA224_OID.equals(str)) {
            return "SHA224withECDSA";
        }
        if (ECDSA_PLAIN_SHA256_OID.equals(str)) {
            return "SHA256withECDSA";
        }
        if (ECDSA_PLAIN_SHA384_OID.equals(str)) {
            return "SHA384withECDSA";
        }
        if (ECDSA_PLAIN_SHA512_OID.equals(str)) {
            return "SHA512withECDSA";
        }
        if (ECDSA_PLAIN_RIPEMD160_OID.equals(str)) {
            return "RIPEMD160withECDSA";
        }
        throw new NoSuchAlgorithmException("Unknown OID " + str);
    }
}
