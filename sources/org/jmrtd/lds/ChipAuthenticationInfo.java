package org.jmrtd.lds;

import java.math.BigInteger;
import java.util.logging.Logger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DLSequence;

public class ChipAuthenticationInfo extends SecurityInfo {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int VERSION_NUM = 1;
    private static final long serialVersionUID = 5591988305059068535L;
    private BigInteger keyId;
    private String oid;
    private int version;

    public ChipAuthenticationInfo(String str, int i, BigInteger bigInteger) {
        this.oid = str;
        this.version = i;
        this.keyId = bigInteger;
        checkFields();
    }

    public ChipAuthenticationInfo(String str, int i) {
        this(str, i, BigInteger.valueOf(-1));
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.oid));
        aSN1EncodableVector.add(new ASN1Integer((long) this.version));
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

    /* access modifiers changed from: protected */
    public void checkFields() {
        try {
            if (!checkRequiredIdentifier(this.oid)) {
                throw new IllegalArgumentException("Wrong identifier: " + this.oid);
            } else if (this.version != 1) {
                throw new IllegalArgumentException("Wrong version");
            }
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalArgumentException("Malformed ChipAuthenticationInfo.");
        }
    }

    static boolean checkRequiredIdentifier(String str) {
        return ID_CA_DH_3DES_CBC_CBC_OID.equals(str) || ID_CA_ECDH_3DES_CBC_CBC_OID.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_128_OID.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_192_OID.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_256_OID.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_128_OID.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_192_OID.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_256_OID.equals(str);
    }

    public String toString() {
        return "ChipAuthenticationInfo [oid = " + this.oid + ", version = " + this.version + ", keyId = " + this.keyId + "]";
    }

    public int hashCode() {
        return ((this.oid == null ? 0 : this.oid.hashCode()) * 11) + 3 + (this.version * 61) + (this.keyId.hashCode() * 1991);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!ChipAuthenticationInfo.class.equals(obj.getClass())) {
            return false;
        }
        ChipAuthenticationInfo chipAuthenticationInfo = (ChipAuthenticationInfo) obj;
        if (!this.oid.equals(chipAuthenticationInfo.oid) || this.version != chipAuthenticationInfo.version || !this.keyId.equals(chipAuthenticationInfo.keyId)) {
            z = false;
        }
        return z;
    }
}
