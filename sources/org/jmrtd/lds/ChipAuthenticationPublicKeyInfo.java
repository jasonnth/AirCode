package org.jmrtd.lds;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.util.logging.Logger;
import org.jmrtd.JMRTDSecurityProvider;
import org.jmrtd.Util;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DLSequence;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;

public class ChipAuthenticationPublicKeyInfo extends SecurityInfo {
    private static final Provider BC_PROVIDER = JMRTDSecurityProvider.getBouncyCastleProvider();
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = 5687291829854501771L;
    private BigInteger keyId;
    private String oid;
    private SubjectPublicKeyInfo subjectPublicKeyInfo;

    ChipAuthenticationPublicKeyInfo(String str, SubjectPublicKeyInfo subjectPublicKeyInfo2, BigInteger bigInteger) {
        this.oid = str;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo2;
        this.keyId = bigInteger;
        checkFields();
    }

    ChipAuthenticationPublicKeyInfo(String str, SubjectPublicKeyInfo subjectPublicKeyInfo2) {
        this(str, subjectPublicKeyInfo2, BigInteger.valueOf(-1));
    }

    public ChipAuthenticationPublicKeyInfo(PublicKey publicKey, BigInteger bigInteger) {
        this(Util.inferProtocolIdentifier(publicKey), Util.toSubjectPublicKeyInfo(Util.reconstructPublicKey(publicKey)), bigInteger);
    }

    public ChipAuthenticationPublicKeyInfo(PublicKey publicKey) {
        this(publicKey, BigInteger.valueOf(-1));
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.oid));
        aSN1EncodableVector.add((ASN1Sequence) this.subjectPublicKeyInfo.toASN1Primitive());
        if (this.keyId.compareTo(BigInteger.ZERO) >= 0) {
            aSN1EncodableVector.add(new ASN1Integer(this.keyId));
        }
        return new DLSequence(aSN1EncodableVector);
    }

    public String getObjectIdentifier() {
        return this.oid;
    }

    public BigInteger getKeyId() {
        return this.keyId;
    }

    public PublicKey getSubjectPublicKey() {
        return Util.toPublicKey(this.subjectPublicKeyInfo);
    }

    /* access modifiers changed from: protected */
    public void checkFields() {
        try {
            if (!checkRequiredIdentifier(this.oid)) {
                throw new IllegalArgumentException("Wrong identifier: " + this.oid);
            }
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalArgumentException("Malformed ChipAuthenticationInfo.");
        }
    }

    public static boolean checkRequiredIdentifier(String str) {
        return ID_PK_DH_OID.equals(str) || ID_PK_ECDH_OID.equals(str);
    }

    public String toString() {
        String str = this.oid;
        try {
            str = lookupMnemonicByOID(this.oid);
        } catch (NoSuchAlgorithmException e) {
        }
        return "ChipAuthenticationPublicKeyInfo [protocol = " + str + ", " + "chipAuthenticationPublicKey = " + getSubjectPublicKey().toString() + ", " + "keyId = " + getKeyId().toString() + "]";
    }

    public int hashCode() {
        return ((this.oid.hashCode() + this.keyId.hashCode() + this.subjectPublicKeyInfo.hashCode()) * 1337) + EACTags.SECURITY_ENVIRONMENT_TEMPLATE;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!ChipAuthenticationPublicKeyInfo.class.equals(obj.getClass())) {
            return false;
        }
        ChipAuthenticationPublicKeyInfo chipAuthenticationPublicKeyInfo = (ChipAuthenticationPublicKeyInfo) obj;
        if (!this.oid.equals(chipAuthenticationPublicKeyInfo.oid) || !this.keyId.equals(chipAuthenticationPublicKeyInfo.keyId) || !this.subjectPublicKeyInfo.equals(chipAuthenticationPublicKeyInfo.subjectPublicKeyInfo)) {
            z = false;
        }
        return z;
    }
}
