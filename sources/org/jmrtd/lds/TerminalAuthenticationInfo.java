package org.jmrtd.lds;

import java.util.logging.Logger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DLSequence;
import org.spongycastle.asn1.eac.EACTags;

public class TerminalAuthenticationInfo extends SecurityInfo {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int VERSION_NUM = 1;
    private static final long serialVersionUID = 6220506985707094044L;
    private ASN1Sequence efCVCA;
    private String oid;
    private int version;

    TerminalAuthenticationInfo(String str, int i, ASN1Sequence aSN1Sequence) {
        this.oid = str;
        this.version = i;
        this.efCVCA = aSN1Sequence;
        checkFields();
    }

    TerminalAuthenticationInfo(String str, int i) {
        this(str, i, null);
    }

    public TerminalAuthenticationInfo() {
        this(ID_TA_OID, 1);
    }

    public TerminalAuthenticationInfo(short s, byte b) {
        this(ID_TA_OID, 1, constructEFCVCA(s, b));
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.oid));
        aSN1EncodableVector.add(new ASN1Integer((long) this.version));
        if (this.efCVCA != null) {
            aSN1EncodableVector.add(this.efCVCA);
        }
        return new DLSequence(aSN1EncodableVector);
    }

    public String getObjectIdentifier() {
        return this.oid;
    }

    public int getFileId() {
        return getFileId(this.efCVCA);
    }

    public byte getShortFileId() {
        return getShortFileId(this.efCVCA);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("TerminalAuthenticationInfo");
        stringBuffer.append("[");
        stringBuffer.append("fileID = " + getFileId());
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
        int i = (this.version * 5) + (hashCode * 7) + EACTags.SECURITY_ENVIRONMENT_TEMPLATE;
        if (this.efCVCA == null) {
            hashCode2 = 1;
        } else {
            hashCode2 = this.efCVCA.hashCode();
        }
        return (hashCode2 * 3) + i;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!TerminalAuthenticationInfo.class.equals(obj.getClass())) {
            return false;
        }
        TerminalAuthenticationInfo terminalAuthenticationInfo = (TerminalAuthenticationInfo) obj;
        if (this.efCVCA == null && terminalAuthenticationInfo.efCVCA != null) {
            return false;
        }
        if (this.efCVCA == null || terminalAuthenticationInfo.efCVCA != null) {
            return getDERObject().equals(terminalAuthenticationInfo.getDERObject());
        }
        return false;
    }

    static boolean checkRequiredIdentifier(String str) {
        return ID_TA_OID.equals(str);
    }

    private void checkFields() {
        try {
            if (!checkRequiredIdentifier(this.oid)) {
                throw new IllegalArgumentException("Wrong identifier: " + this.oid);
            } else if (this.version != 1) {
                throw new IllegalArgumentException("Wrong version");
            } else if (this.efCVCA == null) {
            } else {
                if (((DEROctetString) this.efCVCA.getObjectAt(0)).getOctets().length != 2) {
                    throw new IllegalArgumentException("Malformed FID.");
                } else if (this.efCVCA.size() == 2 && ((DEROctetString) this.efCVCA.getObjectAt(1)).getOctets().length != 1) {
                    throw new IllegalArgumentException("Malformed SFI.");
                }
            }
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalArgumentException("Malformed TerminalAuthenticationInfo.");
        }
    }

    private static ASN1Sequence constructEFCVCA(short s, byte b) {
        if (b != -1) {
            return new DLSequence(new ASN1Encodable[]{new DEROctetString(new byte[]{(byte) ((65280 & s) >> 8), (byte) (s & 255)}), new DEROctetString(new byte[]{(byte) (b & 255)})});
        }
        return new DLSequence(new ASN1Encodable[]{new DEROctetString(new byte[]{(byte) ((65280 & s) >> 8), (byte) (s & 255)})});
    }

    private static short getFileId(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence == null) {
            return -1;
        }
        byte[] octets = ((DEROctetString) aSN1Sequence.getObjectAt(0)).getOctets();
        return (short) ((octets[1] & 255) | ((octets[0] & 255) << 8));
    }

    private static byte getShortFileId(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence != null && aSN1Sequence.size() == 2) {
            return ((DEROctetString) aSN1Sequence.getObjectAt(1)).getOctets()[0];
        }
        return -1;
    }
}
