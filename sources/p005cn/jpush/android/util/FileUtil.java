package p005cn.jpush.android.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import p005cn.jpush.android.JPushConstants;

/* renamed from: cn.jpush.android.util.FileUtil */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static void deepDeleteFile(String filePath) {
        File[] files;
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    deepDeleteFile(f.getAbsolutePath());
                    f.delete();
                }
            }
            file.delete();
        }
    }

    public static ArrayList<String> readLines(InputStream is) {
        ArrayList<String> data = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(is, JPushConstants.ENCODING_UTF_8);
            BufferedReader br = new BufferedReader(isr, 2048);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String line2 = line.trim();
                if (!"".equals(line2)) {
                    data.add(line2);
                }
            }
            if (isr != null) {
                isr.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            Logger.m1420e(TAG, e.getMessage());
        }
        return data;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0067 A[Catch:{ IOException -> 0x006b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean createHtmlFile(java.lang.String r7, java.lang.String r8, android.content.Context r9) {
        /*
            if (r9 != 0) goto L_0x000b
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "NULL context"
            r4.<init>(r5)
            throw r4
        L_0x000b:
            java.lang.String r4 = "FileUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "action:createHtmlFile - filePath:"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r6 = ", content:"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r5 = r5.toString()
            p005cn.jpush.android.util.Logger.m1428v(r4, r5)
            boolean r4 = android.text.TextUtils.isEmpty(r7)
            if (r4 != 0) goto L_0x0075
            boolean r4 = android.text.TextUtils.isEmpty(r8)
            if (r4 != 0) goto L_0x0075
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x006b }
            r1.<init>(r7)     // Catch:{ IOException -> 0x006b }
            boolean r4 = r1.exists()     // Catch:{ IOException -> 0x006b }
            if (r4 != 0) goto L_0x004a
            r1.createNewFile()     // Catch:{ IOException -> 0x006b }
        L_0x004a:
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0064 }
            r3.<init>(r1)     // Catch:{ all -> 0x0064 }
            java.lang.String r4 = "UTF-8"
            byte[] r4 = r8.getBytes(r4)     // Catch:{ all -> 0x0077 }
            r3.write(r4)     // Catch:{ all -> 0x0077 }
            r3.flush()     // Catch:{ all -> 0x0077 }
            if (r3 == 0) goto L_0x0062
            r3.close()     // Catch:{ IOException -> 0x006b }
        L_0x0062:
            r4 = 1
        L_0x0063:
            return r4
        L_0x0064:
            r4 = move-exception
        L_0x0065:
            if (r2 == 0) goto L_0x006a
            r2.close()     // Catch:{ IOException -> 0x006b }
        L_0x006a:
            throw r4     // Catch:{ IOException -> 0x006b }
        L_0x006b:
            r0 = move-exception
            java.lang.String r4 = "FileUtil"
            java.lang.String r5 = ""
            p005cn.jpush.android.util.Logger.m1417d(r4, r5, r0)
        L_0x0075:
            r4 = 0
            goto L_0x0063
        L_0x0077:
            r4 = move-exception
            r2 = r3
            goto L_0x0065
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.FileUtil.createHtmlFile(java.lang.String, java.lang.String, android.content.Context):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean createImgFile(java.lang.String r4, byte[] r5, android.content.Context r6) throws java.io.IOException {
        /*
            boolean r3 = android.text.TextUtils.isEmpty(r4)
            if (r3 != 0) goto L_0x0031
            int r3 = r5.length
            if (r3 <= 0) goto L_0x0031
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            boolean r3 = r0.exists()
            if (r3 != 0) goto L_0x0017
            r0.createNewFile()
        L_0x0017:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x002a }
            r2.<init>(r0)     // Catch:{ all -> 0x002a }
            r2.write(r5)     // Catch:{ all -> 0x0033 }
            r2.flush()     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0028
            r2.close()
        L_0x0028:
            r3 = 1
        L_0x0029:
            return r3
        L_0x002a:
            r3 = move-exception
        L_0x002b:
            if (r1 == 0) goto L_0x0030
            r1.close()
        L_0x0030:
            throw r3
        L_0x0031:
            r3 = 0
            goto L_0x0029
        L_0x0033:
            r3 = move-exception
            r1 = r2
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.FileUtil.createImgFile(java.lang.String, byte[], android.content.Context):boolean");
    }

    private static String getExtName(String s, String split) {
        if (StringUtils.isEmpty(s)) {
            return "";
        }
        int i = s.lastIndexOf(split);
        int leg = s.length();
        if (i > 0) {
            return i + 1 == leg ? "" : s.substring(i, s.length());
        }
        return "";
    }

    public static String getExtName(String fileName) {
        return getExtName(fileName, ".");
    }

    public static String getFileNameFromUrl(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
    }
}
