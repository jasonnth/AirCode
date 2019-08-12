package com.tencent.p313mm.sdk.channel.compatible;

import java.security.MessageDigest;

/* renamed from: com.tencent.mm.sdk.channel.compatible.MD5 */
public class MD5 {
    public static final String getMessageDigest(byte[] buffer) {
        byte[] md;
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            char[] str = new char[(j * 2)];
            int k = 0;
            for (byte byte0 : mdTemp.digest()) {
                int k2 = k + 1;
                str[k] = hexDigits[(byte0 >>> 4) & 15];
                k = k2 + 1;
                str[k2] = hexDigits[byte0 & 15];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
