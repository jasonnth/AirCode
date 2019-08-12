package com.tencent.p313mm.sdk.channel.compatible;

/* renamed from: com.tencent.mm.sdk.channel.compatible.MMessageUtil */
public class MMessageUtil {
    public static byte[] genCheckSum(String content, int sdkVersion, String packageName) {
        StringBuffer sb = new StringBuffer();
        if (content != null) {
            sb.append(content);
        }
        sb.append(sdkVersion);
        sb.append(packageName);
        sb.append("mMcShCsTr");
        return MD5.getMessageDigest(sb.toString().substring(1, 9).getBytes()).getBytes();
    }
}
