package org.ejbca.cvc;

import java.io.Serializable;
import org.ejbca.cvc.util.StringConverter;

public class AccessRightAuthTerm implements Serializable, AccessRights {
    public static final int BIT_AGE_VERIFICATION = 0;
    public static final int BIT_CAN_ALLOWED = 4;
    public static final int BIT_COMMUNITY_ID_VERIFICATION = 1;
    public static final int BIT_INSTALL_CERT = 6;
    public static final int BIT_INSTALL_QUALIFIED_CERT = 7;
    public static final int BIT_PIN_MANAGEMENT = 5;
    public static final int BIT_PRIVILEGED_TERMINAL = 3;
    public static final int BIT_READ_DG1 = 8;
    public static final int BIT_READ_DG10 = 17;
    public static final int BIT_READ_DG11 = 18;
    public static final int BIT_READ_DG12 = 19;
    public static final int BIT_READ_DG13 = 20;
    public static final int BIT_READ_DG14 = 21;
    public static final int BIT_READ_DG15 = 22;
    public static final int BIT_READ_DG16 = 23;
    public static final int BIT_READ_DG17 = 24;
    public static final int BIT_READ_DG18 = 25;
    public static final int BIT_READ_DG19 = 26;
    public static final int BIT_READ_DG2 = 9;
    public static final int BIT_READ_DG20 = 27;
    public static final int BIT_READ_DG21 = 28;
    public static final int BIT_READ_DG3 = 10;
    public static final int BIT_READ_DG4 = 11;
    public static final int BIT_READ_DG5 = 12;
    public static final int BIT_READ_DG6 = 13;
    public static final int BIT_READ_DG7 = 14;
    public static final int BIT_READ_DG8 = 15;
    public static final int BIT_READ_DG9 = 16;
    public static final int BIT_RESTRICTED_IDENTIFICATION = 2;
    public static final int BIT_WRITE_DG17 = 37;
    public static final int BIT_WRITE_DG18 = 36;
    public static final int BIT_WRITE_DG19 = 35;
    public static final int BIT_WRITE_DG20 = 34;
    public static final int BIT_WRITE_DG21 = 33;
    private static final int MAX_BIT = 37;
    private static final long serialVersionUID = 1;
    private final byte[] bytes;

    public AccessRightAuthTerm() {
        this.bytes = new byte[5];
    }

    public AccessRightAuthTerm(byte[] bArr) {
        if (bArr.length != 5) {
            throw new IllegalArgumentException("byte array length must be 5, was " + bArr.length);
        }
        this.bytes = (byte[]) bArr.clone();
        byte[] bArr2 = this.bytes;
        bArr2[0] = (byte) (bArr2[0] & -193);
    }

    public boolean getFlag(int i) {
        if (i < 0 || i > 37) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        if (((this.bytes[(this.bytes.length - 1) - (i >> 3)] >> (i & 7)) & 1) == 1) {
            return true;
        }
        return false;
    }

    public void setFlag(int i, boolean z) {
        if (i < 0 || i > 37) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        int length = (this.bytes.length - 1) - (i >> 3);
        int i2 = 1 << (i & 7);
        byte[] bArr = this.bytes;
        bArr[length] = (byte) (bArr[length] & (i2 ^ -1));
        byte[] bArr2 = this.bytes;
        byte b = bArr2[length];
        if (!z) {
            i2 = 0;
        }
        bArr2[length] = (byte) (i2 | b);
    }

    public byte[] getEncoded() {
        return (byte[]) this.bytes.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 37; i++) {
            if (getFlag(i)) {
                sb.append(flagToString(i));
                sb.append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    private static String flagToString(int i) {
        if (i < 0 || i > 37) {
            throw new ArrayIndexOutOfBoundsException(i);
        } else if (i >= 33) {
            return "W-DG" + (54 - i);
        } else {
            if (i > 28) {
                return "RFU-" + i;
            }
            if (i >= 8) {
                return "R-DG" + ((i - 8) + 1);
            }
            switch (i) {
                case 0:
                    return "Age Verification";
                case 1:
                    return "Community ID Verification";
                case 2:
                    return "Restricted Identification";
                case 3:
                    return "Privileged Terminal";
                case 4:
                    return "CAN Allowed";
                case 5:
                    return "PIN Management";
                case 6:
                    return "Install Certificate";
                case 7:
                    return "Install Qualified Certificate";
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public String name() {
        return "ACCESS_RIGHT_AT_" + StringConverter.byteToHex(this.bytes);
    }
}
