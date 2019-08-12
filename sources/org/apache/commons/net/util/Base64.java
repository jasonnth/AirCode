package org.apache.commons.net.util;

import java.io.UnsupportedEncodingException;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.asn1.eac.EACTags;

public class Base64 {
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final byte[] STANDARD_ENCODE_TABLE = {65, CVCAFile.CAR_TAG, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, ISOFileInfo.FCP_BYTE, 99, ISOFileInfo.FMD_BYTE, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, ISOFileInfo.FCI_BYTE, ISO7816.INS_MANAGE_CHANNEL, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ISO7816.INS_DECREASE, 49, ISO7816.INS_INCREASE, 51, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, CVCAFile.CAR_TAG, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, ISOFileInfo.FCP_BYTE, 99, ISOFileInfo.FMD_BYTE, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, ISOFileInfo.FCI_BYTE, ISO7816.INS_MANAGE_CHANNEL, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, ISO7816.INS_DECREASE, 49, ISO7816.INS_INCREASE, 51, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57, 45, 95};
    private byte[] buffer;
    private int currentLinePos;
    private final int decodeSize;
    private final int encodeSize;
    private final byte[] encodeTable;
    private boolean eof;
    private final int lineLength;
    private final byte[] lineSeparator;
    private int modulus;
    private int pos;
    private int readPos;

    /* renamed from: x */
    private int f6327x;

    static {
        byte[] bArr = new byte[EACTags.SECURITY_ENVIRONMENT_TEMPLATE];
        // fill-array-data instruction
        bArr[0] = -1;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = -1;
        bArr[4] = -1;
        bArr[5] = -1;
        bArr[6] = -1;
        bArr[7] = -1;
        bArr[8] = -1;
        bArr[9] = -1;
        bArr[10] = -1;
        bArr[11] = -1;
        bArr[12] = -1;
        bArr[13] = -1;
        bArr[14] = -1;
        bArr[15] = -1;
        bArr[16] = -1;
        bArr[17] = -1;
        bArr[18] = -1;
        bArr[19] = -1;
        bArr[20] = -1;
        bArr[21] = -1;
        bArr[22] = -1;
        bArr[23] = -1;
        bArr[24] = -1;
        bArr[25] = -1;
        bArr[26] = -1;
        bArr[27] = -1;
        bArr[28] = -1;
        bArr[29] = -1;
        bArr[30] = -1;
        bArr[31] = -1;
        bArr[32] = -1;
        bArr[33] = -1;
        bArr[34] = -1;
        bArr[35] = -1;
        bArr[36] = -1;
        bArr[37] = -1;
        bArr[38] = -1;
        bArr[39] = -1;
        bArr[40] = -1;
        bArr[41] = -1;
        bArr[42] = -1;
        bArr[43] = 62;
        bArr[44] = -1;
        bArr[45] = 62;
        bArr[46] = -1;
        bArr[47] = 63;
        bArr[48] = 52;
        bArr[49] = 53;
        bArr[50] = 54;
        bArr[51] = 55;
        bArr[52] = 56;
        bArr[53] = 57;
        bArr[54] = 58;
        bArr[55] = 59;
        bArr[56] = 60;
        bArr[57] = 61;
        bArr[58] = -1;
        bArr[59] = -1;
        bArr[60] = -1;
        bArr[61] = -1;
        bArr[62] = -1;
        bArr[63] = -1;
        bArr[64] = -1;
        bArr[65] = 0;
        bArr[66] = 1;
        bArr[67] = 2;
        bArr[68] = 3;
        bArr[69] = 4;
        bArr[70] = 5;
        bArr[71] = 6;
        bArr[72] = 7;
        bArr[73] = 8;
        bArr[74] = 9;
        bArr[75] = 10;
        bArr[76] = 11;
        bArr[77] = 12;
        bArr[78] = 13;
        bArr[79] = 14;
        bArr[80] = 15;
        bArr[81] = 16;
        bArr[82] = 17;
        bArr[83] = 18;
        bArr[84] = 19;
        bArr[85] = 20;
        bArr[86] = 21;
        bArr[87] = 22;
        bArr[88] = 23;
        bArr[89] = 24;
        bArr[90] = 25;
        bArr[91] = -1;
        bArr[92] = -1;
        bArr[93] = -1;
        bArr[94] = -1;
        bArr[95] = 63;
        bArr[96] = -1;
        bArr[97] = 26;
        bArr[98] = 27;
        bArr[99] = 28;
        bArr[100] = 29;
        bArr[101] = 30;
        bArr[102] = 31;
        bArr[103] = 32;
        bArr[104] = 33;
        bArr[105] = 34;
        bArr[106] = 35;
        bArr[107] = 36;
        bArr[108] = 37;
        bArr[109] = 38;
        bArr[110] = 39;
        bArr[111] = 40;
        bArr[112] = 41;
        bArr[113] = 42;
        bArr[114] = 43;
        bArr[115] = 44;
        bArr[116] = 45;
        bArr[117] = 46;
        bArr[118] = 47;
        bArr[119] = 48;
        bArr[120] = 49;
        bArr[121] = 50;
        bArr[122] = 51;
        DECODE_TABLE = bArr;
    }

    public Base64() {
        this(false);
    }

    public Base64(boolean urlSafe) {
        this(76, CHUNK_SEPARATOR, urlSafe);
    }

    public Base64(int lineLength2, byte[] lineSeparator2, boolean urlSafe) {
        int i;
        if (lineSeparator2 == null) {
            lineLength2 = 0;
            lineSeparator2 = EMPTY_BYTE_ARRAY;
        }
        if (lineLength2 > 0) {
            i = (lineLength2 / 4) * 4;
        } else {
            i = 0;
        }
        this.lineLength = i;
        this.lineSeparator = new byte[lineSeparator2.length];
        System.arraycopy(lineSeparator2, 0, this.lineSeparator, 0, lineSeparator2.length);
        if (lineLength2 > 0) {
            this.encodeSize = lineSeparator2.length + 4;
        } else {
            this.encodeSize = 4;
        }
        this.decodeSize = this.encodeSize - 1;
        if (containsBase64Byte(lineSeparator2)) {
            throw new IllegalArgumentException("lineSeperator must not contain base64 characters: [" + newStringUtf8(lineSeparator2) + "]");
        }
        this.encodeTable = urlSafe ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    /* access modifiers changed from: 0000 */
    public int avail() {
        if (this.buffer != null) {
            return this.pos - this.readPos;
        }
        return 0;
    }

    private void resizeBuffer() {
        if (this.buffer == null) {
            this.buffer = new byte[8192];
            this.pos = 0;
            this.readPos = 0;
            return;
        }
        byte[] b = new byte[(this.buffer.length * 2)];
        System.arraycopy(this.buffer, 0, b, 0, this.buffer.length);
        this.buffer = b;
    }

    /* access modifiers changed from: 0000 */
    public int readResults(byte[] b, int bPos, int bAvail) {
        if (this.buffer != null) {
            int len = Math.min(avail(), bAvail);
            if (this.buffer != b) {
                System.arraycopy(this.buffer, this.readPos, b, bPos, len);
                this.readPos += len;
                if (this.readPos < this.pos) {
                    return len;
                }
                this.buffer = null;
                return len;
            }
            this.buffer = null;
            return len;
        }
        return this.eof ? -1 : 0;
    }

    /* access modifiers changed from: 0000 */
    public void setInitialBuffer(byte[] out, int outPos, int outAvail) {
        if (out != null && out.length == outAvail) {
            this.buffer = out;
            this.pos = outPos;
            this.readPos = outPos;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v0, types: [byte, int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(byte[] r10, int r11, int r12) {
        /*
            r9 = this;
            r8 = 61
            r7 = 0
            boolean r3 = r9.eof
            if (r3 == 0) goto L_0x0008
        L_0x0007:
            return
        L_0x0008:
            if (r12 >= 0) goto L_0x00d1
            r3 = 1
            r9.eof = r3
            byte[] r3 = r9.buffer
            if (r3 == 0) goto L_0x001b
            byte[] r3 = r9.buffer
            int r3 = r3.length
            int r4 = r9.pos
            int r3 = r3 - r4
            int r4 = r9.encodeSize
            if (r3 >= r4) goto L_0x001e
        L_0x001b:
            r9.resizeBuffer()
        L_0x001e:
            int r3 = r9.modulus
            switch(r3) {
                case 1: goto L_0x0040;
                case 2: goto L_0x0083;
                default: goto L_0x0023;
            }
        L_0x0023:
            int r3 = r9.lineLength
            if (r3 <= 0) goto L_0x0007
            int r3 = r9.pos
            if (r3 <= 0) goto L_0x0007
            byte[] r3 = r9.lineSeparator
            byte[] r4 = r9.buffer
            int r5 = r9.pos
            byte[] r6 = r9.lineSeparator
            int r6 = r6.length
            java.lang.System.arraycopy(r3, r7, r4, r5, r6)
            int r3 = r9.pos
            byte[] r4 = r9.lineSeparator
            int r4 = r4.length
            int r3 = r3 + r4
            r9.pos = r3
            goto L_0x0007
        L_0x0040:
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 >> 2
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 << 4
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.encodeTable
            byte[] r4 = STANDARD_ENCODE_TABLE
            if (r3 != r4) goto L_0x0023
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            r3[r4] = r8
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            r3[r4] = r8
            goto L_0x0023
        L_0x0083:
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 >> 10
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 >> 4
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 << 2
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.encodeTable
            byte[] r4 = STANDARD_ENCODE_TABLE
            if (r3 != r4) goto L_0x0023
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            r3[r4] = r8
            goto L_0x0023
        L_0x00d1:
            r1 = 0
            r2 = r11
        L_0x00d3:
            if (r1 >= r12) goto L_0x017c
            byte[] r3 = r9.buffer
            if (r3 == 0) goto L_0x00e3
            byte[] r3 = r9.buffer
            int r3 = r3.length
            int r4 = r9.pos
            int r3 = r3 - r4
            int r4 = r9.encodeSize
            if (r3 >= r4) goto L_0x00e6
        L_0x00e3:
            r9.resizeBuffer()
        L_0x00e6:
            int r3 = r9.modulus
            int r3 = r3 + 1
            r9.modulus = r3
            int r3 = r3 % 3
            r9.modulus = r3
            int r11 = r2 + 1
            byte r0 = r10[r2]
            if (r0 >= 0) goto L_0x00f8
            int r0 = r0 + 256
        L_0x00f8:
            int r3 = r9.f6327x
            int r3 = r3 << 8
            int r3 = r3 + r0
            r9.f6327x = r3
            int r3 = r9.modulus
            if (r3 != 0) goto L_0x0177
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 >> 18
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 >> 12
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            int r6 = r6 >> 6
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            byte[] r3 = r9.buffer
            int r4 = r9.pos
            int r5 = r4 + 1
            r9.pos = r5
            byte[] r5 = r9.encodeTable
            int r6 = r9.f6327x
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r4] = r5
            int r3 = r9.currentLinePos
            int r3 = r3 + 4
            r9.currentLinePos = r3
            int r3 = r9.lineLength
            if (r3 <= 0) goto L_0x0177
            int r3 = r9.lineLength
            int r4 = r9.currentLinePos
            if (r3 > r4) goto L_0x0177
            byte[] r3 = r9.lineSeparator
            byte[] r4 = r9.buffer
            int r5 = r9.pos
            byte[] r6 = r9.lineSeparator
            int r6 = r6.length
            java.lang.System.arraycopy(r3, r7, r4, r5, r6)
            int r3 = r9.pos
            byte[] r4 = r9.lineSeparator
            int r4 = r4.length
            int r3 = r3 + r4
            r9.pos = r3
            r9.currentLinePos = r7
        L_0x0177:
            int r1 = r1 + 1
            r2 = r11
            goto L_0x00d3
        L_0x017c:
            r11 = r2
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.util.Base64.encode(byte[], int, int):void");
    }

    public static boolean isBase64(byte octet) {
        return octet == 61 || (octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != -1);
    }

    private static boolean containsBase64Byte(byte[] arrayOctet) {
        for (byte element : arrayOctet) {
            if (isBase64(element)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] encodeBase64(byte[] binaryData) {
        return encodeBase64(binaryData, false);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        return encodeBase64(binaryData, isChunked, false);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe) {
        return encodeBase64(binaryData, isChunked, urlSafe, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe, int maxResultSize) {
        int i;
        if (binaryData == null || binaryData.length == 0) {
            return binaryData;
        }
        if (isChunked) {
            i = 76;
        } else {
            i = 0;
        }
        long len = getEncodeLength(binaryData, i, isChunked ? CHUNK_SEPARATOR : EMPTY_BYTE_ARRAY);
        if (len > ((long) maxResultSize)) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + len + ") than the specified maxium size of " + maxResultSize);
        }
        return (isChunked ? new Base64(urlSafe) : new Base64(0, CHUNK_SEPARATOR, urlSafe)).encode(binaryData);
    }

    private static String newStringUtf8(byte[] encode) {
        try {
            return new String(encode, "UTF8");
        } catch (UnsupportedEncodingException ue) {
            throw new RuntimeException(ue);
        }
    }

    public byte[] encode(byte[] pArray) {
        reset();
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        byte[] buf = new byte[((int) getEncodeLength(pArray, this.lineLength, this.lineSeparator))];
        setInitialBuffer(buf, 0, buf.length);
        encode(pArray, 0, pArray.length);
        encode(pArray, 0, -1);
        if (this.buffer != buf) {
            readResults(buf, 0, buf.length);
        }
        if (!isUrlSafe() || this.pos >= buf.length) {
            return buf;
        }
        byte[] smallerBuf = new byte[this.pos];
        System.arraycopy(buf, 0, smallerBuf, 0, this.pos);
        return smallerBuf;
    }

    private static long getEncodeLength(byte[] pArray, int chunkSize, byte[] chunkSeparator) {
        int chunkSize2 = (chunkSize / 4) * 4;
        long len = (long) ((pArray.length * 4) / 3);
        long mod = len % 4;
        if (mod != 0) {
            len += 4 - mod;
        }
        if (chunkSize2 <= 0) {
            return len;
        }
        boolean lenChunksPerfectly = len % ((long) chunkSize2) == 0;
        long len2 = len + ((len / ((long) chunkSize2)) * ((long) chunkSeparator.length));
        if (!lenChunksPerfectly) {
            return len2 + ((long) chunkSeparator.length);
        }
        return len2;
    }

    private void reset() {
        this.buffer = null;
        this.pos = 0;
        this.readPos = 0;
        this.currentLinePos = 0;
        this.modulus = 0;
        this.eof = false;
    }
}
