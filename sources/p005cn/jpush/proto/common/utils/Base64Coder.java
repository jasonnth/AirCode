package p005cn.jpush.proto.common.utils;

/* renamed from: cn.jpush.proto.common.utils.Base64Coder */
public class Base64Coder {
    private static char[] map1 = new char[64];
    private static byte[] map2 = new byte[128];

    static {
        int i;
        int i2 = 0;
        char c = 'A';
        while (true) {
            i = i2;
            if (c > 'Z') {
                break;
            }
            i2 = i + 1;
            map1[i] = c;
            c = (char) (c + 1);
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            int i3 = i + 1;
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i = i3;
        }
        char c3 = '0';
        while (c3 <= '9') {
            int i4 = i + 1;
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i = i4;
        }
        int i5 = i + 1;
        map1[i] = '+';
        int i6 = i5 + 1;
        map1[i5] = '/';
        for (int i7 = 0; i7 < map2.length; i7++) {
            map2[i7] = -1;
        }
        for (int i8 = 0; i8 < 64; i8++) {
            map2[map1[i8]] = (byte) i8;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r8v2, types: [char] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r9v2, types: [char] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decode(char[] r22) {
        /*
            r0 = r22
            int r10 = r0.length
            int r20 = r10 % 4
            if (r20 == 0) goto L_0x0010
            java.lang.IllegalArgumentException r20 = new java.lang.IllegalArgumentException
            java.lang.String r21 = "Length of Base64 encoded input string is not a multiple of 4."
            r20.<init>(r21)
            throw r20
        L_0x0010:
            if (r10 <= 0) goto L_0x0021
            int r20 = r10 + -1
            char r20 = r22[r20]
            r21 = 61
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0021
            int r10 = r10 + -1
            goto L_0x0010
        L_0x0021:
            int r20 = r10 * 3
            int r16 = r20 / 4
            r0 = r16
            byte[] r0 = new byte[r0]
            r19 = r0
            r11 = 0
            r17 = 0
            r18 = r17
            r12 = r11
        L_0x0031:
            if (r12 >= r10) goto L_0x00cb
            int r11 = r12 + 1
            char r6 = r22[r12]
            int r12 = r11 + 1
            char r7 = r22[r11]
            if (r12 >= r10) goto L_0x0069
            int r11 = r12 + 1
            char r8 = r22[r12]
            r12 = r11
        L_0x0042:
            if (r12 >= r10) goto L_0x006c
            int r11 = r12 + 1
            char r9 = r22[r12]
        L_0x0048:
            r20 = 127(0x7f, float:1.78E-43)
            r0 = r20
            if (r6 > r0) goto L_0x0060
            r20 = 127(0x7f, float:1.78E-43)
            r0 = r20
            if (r7 > r0) goto L_0x0060
            r20 = 127(0x7f, float:1.78E-43)
            r0 = r20
            if (r8 > r0) goto L_0x0060
            r20 = 127(0x7f, float:1.78E-43)
            r0 = r20
            if (r9 <= r0) goto L_0x0070
        L_0x0060:
            java.lang.IllegalArgumentException r20 = new java.lang.IllegalArgumentException
            java.lang.String r21 = "Illegal character in Base64 encoded data."
            r20.<init>(r21)
            throw r20
        L_0x0069:
            r8 = 65
            goto L_0x0042
        L_0x006c:
            r9 = 65
            r11 = r12
            goto L_0x0048
        L_0x0070:
            byte[] r20 = map2
            byte r2 = r20[r6]
            byte[] r20 = map2
            byte r3 = r20[r7]
            byte[] r20 = map2
            byte r4 = r20[r8]
            byte[] r20 = map2
            byte r5 = r20[r9]
            if (r2 < 0) goto L_0x0088
            if (r3 < 0) goto L_0x0088
            if (r4 < 0) goto L_0x0088
            if (r5 >= 0) goto L_0x0091
        L_0x0088:
            java.lang.IllegalArgumentException r20 = new java.lang.IllegalArgumentException
            java.lang.String r21 = "Illegal character in Base64 encoded data."
            r20.<init>(r21)
            throw r20
        L_0x0091:
            int r20 = r2 << 2
            int r21 = r3 >>> 4
            r13 = r20 | r21
            r20 = r3 & 15
            int r20 = r20 << 4
            int r21 = r4 >>> 2
            r14 = r20 | r21
            r20 = r4 & 3
            int r20 = r20 << 6
            r15 = r20 | r5
            int r17 = r18 + 1
            byte r0 = (byte) r13
            r20 = r0
            r19[r18] = r20
            r0 = r17
            r1 = r16
            if (r0 >= r1) goto L_0x00cf
            int r18 = r17 + 1
            byte r0 = (byte) r14
            r20 = r0
            r19[r17] = r20
        L_0x00b9:
            r0 = r18
            r1 = r16
            if (r0 >= r1) goto L_0x00cc
            int r17 = r18 + 1
            byte r0 = (byte) r15
            r20 = r0
            r19[r18] = r20
        L_0x00c6:
            r18 = r17
            r12 = r11
            goto L_0x0031
        L_0x00cb:
            return r19
        L_0x00cc:
            r17 = r18
            goto L_0x00c6
        L_0x00cf:
            r18 = r17
            goto L_0x00b9
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.proto.common.utils.Base64Coder.decode(char[]):byte[]");
    }

    public static byte[] decode(String s) {
        return decode(s.toCharArray());
    }

    public static String decodeString(String s) {
        return new String(toChar(decode(s)));
    }

    public static char[] encode(byte[] in) {
        return encode(in, in.length);
    }

    public static char[] encode(byte[] in, int iLen) {
        int i1;
        int ip;
        int i2;
        int oDataLen = ((iLen * 4) + 2) / 3;
        char[] out = new char[(((iLen + 2) / 3) * 4)];
        int ip2 = 0;
        int op = 0;
        while (true) {
            int op2 = op;
            int ip3 = ip2;
            if (ip3 >= iLen) {
                return out;
            }
            int ip4 = ip3 + 1;
            int i0 = in[ip3] & 255;
            if (ip4 < iLen) {
                ip = ip4 + 1;
                i1 = in[ip4] & 255;
            } else {
                i1 = 0;
                ip = ip4;
            }
            if (ip < iLen) {
                ip2 = ip + 1;
                i2 = in[ip] & 255;
            } else {
                i2 = 0;
                ip2 = ip;
            }
            int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
            int o2 = ((i1 & 15) << 2) | (i2 >>> 6);
            int o3 = i2 & 63;
            int op3 = op2 + 1;
            out[op2] = map1[i0 >>> 2];
            int op4 = op3 + 1;
            out[op3] = map1[o1];
            out[op4] = op4 < oDataLen ? map1[o2] : '=';
            int op5 = op4 + 1;
            out[op5] = op5 < oDataLen ? map1[o3] : '=';
            op = op5 + 1;
        }
    }

    public static String encodeString(String s) {
        return new String(encode(toByte(s.toCharArray())));
    }

    private static byte[] toByte(char[] chars) {
        byte[] bytes = new byte[chars.length];
        for (int index = 0; index < chars.length; index++) {
            bytes[index] = (byte) chars[index];
        }
        return bytes;
    }

    private static char[] toChar(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int index = 0; index < chars.length; index++) {
            chars[index] = (char) bytes[index];
        }
        return chars;
    }

    private Base64Coder() {
    }
}
