package net.p318sf.scuba.tlv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.p318sf.scuba.util.Hex;

/* renamed from: net.sf.scuba.tlv.ASN1Util */
class ASN1Util implements ASN1Constants {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyMMddhhmmss'Z'");

    ASN1Util() {
    }

    static Object interpretPrimitiveValue(int i, byte[] bArr) {
        if (TLVUtil.getTagClass(i) != 0) {
            return bArr;
        }
        switch (i) {
            case 12:
            case 19:
            case 20:
            case 22:
            case 26:
            case 27:
            case 28:
            case 30:
                return new String(bArr);
            case 23:
                try {
                    return SDF.parse(new String(bArr));
                } catch (ParseException e) {
                    return bArr;
                }
            default:
                return bArr;
        }
    }

    static String tagToString(int i) {
        if (TLVUtil.getTagClass(i) == 0) {
            if (TLVUtil.isPrimitive(i)) {
                switch (i & 31) {
                    case 1:
                        return "BOOLEAN";
                    case 2:
                        return "INTEGER";
                    case 3:
                        return "BIT_STRING";
                    case 4:
                        return "OCTET_STRING";
                    case 5:
                        return "NULL";
                    case 6:
                        return "OBJECT_IDENTIFIER";
                    case 9:
                        return "REAL";
                    case 12:
                        return "UTF_STRING";
                    case 19:
                        return "PRINTABLE_STRING";
                    case 20:
                        return "T61_STRING";
                    case 22:
                        return "IA5_STRING";
                    case 23:
                        return "UTC_TIME";
                    case 24:
                        return "GENERAL_TIME";
                    case 26:
                        return "VISIBLE_STRING";
                    case 27:
                        return "GENERAL_STRING";
                    case 28:
                        return "UNIVERSAL_STRING";
                    case 30:
                        return "BMP_STRING";
                }
            } else {
                switch (i & 31) {
                    case 10:
                        return "ENUMERATED";
                    case 16:
                        return "SEQUENCE";
                    case 17:
                        return "SET";
                }
            }
        }
        return "'0x" + Hex.intToHexString(i) + "'";
    }
}
