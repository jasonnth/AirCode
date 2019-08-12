package org.spongycastle.crypto.generators;

import com.facebook.appevents.AppEventsConstants;
import java.io.ByteArrayOutputStream;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class OpenBSDBCrypt {
    private static final byte[] decodingTable = new byte[128];
    private static final byte[] encodingTable = {46, 47, 65, CVCAFile.CAR_TAG, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, ISOFileInfo.FCP_BYTE, 99, ISOFileInfo.FMD_BYTE, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, ISOFileInfo.FCI_BYTE, ISO7816.INS_MANAGE_CHANNEL, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ISO7816.INS_DECREASE, 49, ISO7816.INS_INCREASE, 51, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57};
    private static final String version = "2a";

    static {
        for (int i = 0; i < decodingTable.length; i++) {
            decodingTable[i] = -1;
        }
        for (int i2 = 0; i2 < encodingTable.length; i2++) {
            decodingTable[encodingTable[i2]] = (byte) i2;
        }
    }

    private static String createBcryptString(byte[] password, byte[] salt, int cost) {
        StringBuffer sb = new StringBuffer(60);
        sb.append('$');
        sb.append(version);
        sb.append('$');
        sb.append(cost < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + cost : Integer.toString(cost));
        sb.append('$');
        sb.append(encodeData(salt));
        sb.append(encodeData(BCrypt.generate(password, salt, cost)));
        return sb.toString();
    }

    public static String generate(char[] password, byte[] salt, int cost) {
        int i = 72;
        if (password == null) {
            throw new IllegalArgumentException("Password required.");
        } else if (salt == null) {
            throw new IllegalArgumentException("Salt required.");
        } else if (salt.length != 16) {
            throw new DataLengthException("16 byte salt required: " + salt.length);
        } else if (cost < 4 || cost > 31) {
            throw new IllegalArgumentException("Invalid cost factor.");
        } else {
            byte[] psw = Strings.toUTF8ByteArray(password);
            if (psw.length < 72) {
                i = psw.length + 1;
            }
            byte[] tmp = new byte[i];
            if (tmp.length > psw.length) {
                System.arraycopy(psw, 0, tmp, 0, psw.length);
            } else {
                System.arraycopy(psw, 0, tmp, 0, tmp.length);
            }
            Arrays.fill(psw, 0);
            String rv = createBcryptString(tmp, salt, cost);
            Arrays.fill(tmp, 0);
            return rv;
        }
    }

    public static boolean checkPassword(String bcryptString, char[] password) {
        if (bcryptString.length() != 60) {
            throw new DataLengthException("Bcrypt String length: " + bcryptString.length() + ", 60 required.");
        } else if (bcryptString.charAt(0) != '$' || bcryptString.charAt(3) != '$' || bcryptString.charAt(6) != '$') {
            throw new IllegalArgumentException("Invalid Bcrypt String format.");
        } else if (!bcryptString.substring(1, 3).equals(version)) {
            throw new IllegalArgumentException("Wrong Bcrypt version, 2a expected.");
        } else {
            try {
                int cost = Integer.parseInt(bcryptString.substring(4, 6));
                if (cost < 4 || cost > 31) {
                    throw new IllegalArgumentException("Invalid cost factor: " + cost + ", 4 < cost < 31 expected.");
                } else if (password != null) {
                    return bcryptString.equals(generate(password, decodeSaltString(bcryptString.substring(bcryptString.lastIndexOf(36) + 1, bcryptString.length() - 31)), cost));
                } else {
                    throw new IllegalArgumentException("Missing password.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid cost factor:" + bcryptString.substring(4, 6));
            }
        }
    }

    private static String encodeData(byte[] data) {
        if (data.length == 24 || data.length == 16) {
            boolean salt = false;
            if (data.length == 16) {
                salt = true;
                byte[] tmp = new byte[18];
                System.arraycopy(data, 0, tmp, 0, data.length);
                data = tmp;
            } else {
                data[data.length - 1] = 0;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = data.length;
            for (int i = 0; i < len; i += 3) {
                int a1 = data[i] & 255;
                int a2 = data[i + 1] & 255;
                int a3 = data[i + 2] & 255;
                out.write(encodingTable[(a1 >>> 2) & 63]);
                out.write(encodingTable[((a1 << 4) | (a2 >>> 4)) & 63]);
                out.write(encodingTable[((a2 << 2) | (a3 >>> 6)) & 63]);
                out.write(encodingTable[a3 & 63]);
            }
            String result = Strings.fromByteArray(out.toByteArray());
            if (salt) {
                return result.substring(0, 22);
            }
            return result.substring(0, result.length() - 1);
        }
        throw new DataLengthException("Invalid length: " + data.length + ", 24 for key or 16 for salt expected");
    }

    private static byte[] decodeSaltString(String saltString) {
        char[] saltChars = saltString.toCharArray();
        ByteArrayOutputStream out = new ByteArrayOutputStream(16);
        if (saltChars.length != 22) {
            throw new DataLengthException("Invalid base64 salt length: " + saltChars.length + " , 22 required.");
        }
        for (char value : saltChars) {
            if (value > 'z' || value < '.' || (value > '9' && value < 'A')) {
                throw new IllegalArgumentException("Salt string contains invalid character: " + value);
            }
        }
        char[] tmp = new char[24];
        System.arraycopy(saltChars, 0, tmp, 0, saltChars.length);
        char[] saltChars2 = tmp;
        int len = saltChars2.length;
        for (int i = 0; i < len; i += 4) {
            byte b1 = decodingTable[saltChars2[i]];
            byte b2 = decodingTable[saltChars2[i + 1]];
            byte b3 = decodingTable[saltChars2[i + 2]];
            byte b4 = decodingTable[saltChars2[i + 3]];
            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
            out.write((b3 << 6) | b4);
        }
        byte[] tmpSalt = new byte[16];
        System.arraycopy(out.toByteArray(), 0, tmpSalt, 0, tmpSalt.length);
        return tmpSalt;
    }
}
