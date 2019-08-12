package p005cn.jpush.android.util;

import java.io.File;

/* renamed from: cn.jpush.android.util.CheckSumUntil */
public class CheckSumUntil {
    private static final String TAG = "CheckSumUntil";

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043 A[SYNTHETIC, Splitter:B:22:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055 A[SYNTHETIC, Splitter:B:28:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] createChecksum(java.io.File r10) {
        /*
            r7 = 0
            r4 = 0
            r1 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0037 }
            r5.<init>(r10)     // Catch:{ Exception -> 0x0037 }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r8]     // Catch:{ Exception -> 0x0067, all -> 0x0064 }
            java.lang.String r8 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r8)     // Catch:{ Exception -> 0x0067, all -> 0x0064 }
        L_0x0013:
            int r6 = r5.read(r0)     // Catch:{ Exception -> 0x0067, all -> 0x0064 }
            if (r6 <= 0) goto L_0x001d
            r8 = 0
            r1.update(r0, r8, r6)     // Catch:{ Exception -> 0x0067, all -> 0x0064 }
        L_0x001d:
            r8 = -1
            if (r6 != r8) goto L_0x0013
            if (r5 == 0) goto L_0x0025
            r5.close()     // Catch:{ IOException -> 0x002b }
        L_0x0025:
            byte[] r7 = r1.digest()
            r4 = r5
        L_0x002a:
            return r7
        L_0x002b:
            r3 = move-exception
            java.lang.String r8 = "CheckSumUntil"
            java.lang.String r9 = "Get Check sum error"
            p005cn.jpush.android.util.Logger.m1417d(r8, r9, r3)
            r4 = r5
            goto L_0x002a
        L_0x0037:
            r2 = move-exception
        L_0x0038:
            java.lang.String r8 = "CheckSumUntil"
            java.lang.String r9 = "Get Check sum error"
            p005cn.jpush.android.util.Logger.m1417d(r8, r9, r2)     // Catch:{ all -> 0x0052 }
            if (r4 == 0) goto L_0x002a
            r4.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x002a
        L_0x0047:
            r3 = move-exception
            java.lang.String r8 = "CheckSumUntil"
            java.lang.String r9 = "Get Check sum error"
            p005cn.jpush.android.util.Logger.m1417d(r8, r9, r3)
            goto L_0x002a
        L_0x0052:
            r8 = move-exception
        L_0x0053:
            if (r4 == 0) goto L_0x0058
            r4.close()     // Catch:{ IOException -> 0x0059 }
        L_0x0058:
            throw r8
        L_0x0059:
            r3 = move-exception
            java.lang.String r8 = "CheckSumUntil"
            java.lang.String r9 = "Get Check sum error"
            p005cn.jpush.android.util.Logger.m1417d(r8, r9, r3)
            goto L_0x002a
        L_0x0064:
            r8 = move-exception
            r4 = r5
            goto L_0x0053
        L_0x0067:
            r2 = move-exception
            r4 = r5
            goto L_0x0038
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.CheckSumUntil.createChecksum(java.io.File):byte[]");
    }

    public static String getMD5Checksum(File filename) {
        byte[] b = createChecksum(filename);
        String result = "";
        if (b != null && b.length > 0) {
            for (byte b2 : b) {
                result = result + Integer.toString((b2 & 255) + 256, 16).substring(1);
            }
        }
        return result;
    }

    public static boolean checkMd5(String md5, File file) {
        Logger.m1416d(TAG, "md5 from server side: " + md5);
        if (md5 == null || "".equals(md5)) {
            Logger.m1416d(TAG, "the md5 from server is: " + md5 + " do not need check MD5 code, return true");
            return true;
        } else if (!file.exists() || file.length() == 0) {
            return false;
        } else {
            String md5Str = getMD5Checksum(file);
            Logger.m1416d(TAG, "md5 in the cliet file: " + md5Str);
            if (md5Str == null || "".equals(md5Str) || !md5Str.equals(md5)) {
                Logger.m1416d(TAG, "Check MD5 code failed");
                return false;
            }
            Logger.m1416d(TAG, "Check MD5 code successful");
            return true;
        }
    }
}
