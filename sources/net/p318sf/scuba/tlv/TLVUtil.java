package net.p318sf.scuba.tlv;

import java.io.ByteArrayOutputStream;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;

/* renamed from: net.sf.scuba.tlv.TLVUtil */
public class TLVUtil implements ASN1Constants {
    private TLVUtil() {
    }

    public static byte[] getLengthAsBytes(int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i < 128) {
            byteArrayOutputStream.write(i);
        } else {
            int log = log(i, 256);
            byteArrayOutputStream.write(log | 128);
            for (int i2 = 0; i2 < log; i2++) {
                int i3 = ((log - i2) - 1) * 8;
                byteArrayOutputStream.write(((255 << i3) & i) >> i3);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int getLengthLength(int i) {
        return getLengthAsBytes(i).length;
    }

    public static byte[] getTagAsBytes(int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int log = ((int) (Math.log((double) i) / Math.log(256.0d))) + 1;
        for (int i2 = 0; i2 < log; i2++) {
            int i3 = ((log - i2) - 1) * 8;
            byteArrayOutputStream.write(((255 << i3) & i) >> i3);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        switch (getTagClass(i)) {
            case 1:
                byteArray[0] = (byte) (byteArray[0] | 64);
                break;
            case 2:
                byteArray[0] = (byte) (byteArray[0] | ISOFileInfo.DATA_BYTES1);
                break;
            case 3:
                byteArray[0] = (byte) (byteArray[0] | ISO7816.INS_GET_RESPONSE);
                break;
        }
        if (!isPrimitive(i)) {
            byteArray[0] = (byte) (byteArray[0] | ISO7816.INS_VERIFY);
        }
        return byteArray;
    }

    static int getTagClass(int i) {
        int i2 = 3;
        while (i2 >= 0 && ((255 << (i2 * 8)) & i) == 0) {
            i2--;
        }
        switch ((((255 << (i2 * 8)) & i) >> (i2 * 8)) & 255 & 192) {
            case 0:
                return 0;
            case 64:
                return 1;
            case 128:
                return 2;
            default:
                return 3;
        }
    }

    public static int getTagLength(int i) {
        return getTagAsBytes(i).length;
    }

    public static boolean isPrimitive(int i) {
        int i2 = 3;
        while (i2 >= 0 && ((255 << (i2 * 8)) & i) == 0) {
            i2--;
        }
        return (((((255 << (i2 * 8)) & i) >> (i2 * 8)) & 255) & 32) == 0;
    }

    private static int log(int i, int i2) {
        int i3 = 0;
        while (i > 0) {
            i /= i2;
            i3++;
        }
        return i3;
    }
}
