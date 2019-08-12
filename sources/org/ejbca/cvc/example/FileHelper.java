package org.ejbca.cvc.example;

import java.io.File;
import java.io.IOException;

public final class FileHelper {
    private FileHelper() {
    }

    public static byte[] loadFile(String str) throws IOException {
        return loadFile(new File(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047 A[SYNTHETIC, Splitter:B:22:0x0047] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] loadFile(java.io.File r8) throws java.io.IOException {
        /*
            r1 = 1
            r2 = 0
            r4 = 0
            long r6 = r8.length()     // Catch:{ all -> 0x0043 }
            int r5 = (int) r6     // Catch:{ all -> 0x0043 }
            byte[] r6 = new byte[r5]     // Catch:{ all -> 0x0043 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0043 }
            r3.<init>(r8)     // Catch:{ all -> 0x0043 }
            r0 = r1
            r4 = r2
        L_0x0011:
            if (r0 == 0) goto L_0x0022
            int r0 = r5 - r4
            int r0 = r3.read(r6, r4, r0)     // Catch:{ all -> 0x0066 }
            int r4 = r4 + r0
            if (r0 <= 0) goto L_0x0020
            if (r4 == r5) goto L_0x0020
            r0 = r1
            goto L_0x0011
        L_0x0020:
            r0 = r2
            goto L_0x0011
        L_0x0022:
            if (r3 == 0) goto L_0x0027
            r3.close()     // Catch:{ IOException -> 0x0028 }
        L_0x0027:
            return r6
        L_0x0028:
            r0 = move-exception
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "loadFile - error when closing: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.println(r0)
            goto L_0x0027
        L_0x0043:
            r0 = move-exception
            r1 = r4
        L_0x0045:
            if (r1 == 0) goto L_0x004a
            r1.close()     // Catch:{ IOException -> 0x004b }
        L_0x004a:
            throw r0
        L_0x004b:
            r1 = move-exception
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "loadFile - error when closing: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r2.println(r1)
            goto L_0x004a
        L_0x0066:
            r0 = move-exception
            r1 = r3
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ejbca.cvc.example.FileHelper.loadFile(java.io.File):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeFile(java.io.File r4, byte[] r5) throws java.io.IOException {
        /*
            r2 = 0
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x0016 }
            r0.<init>(r4)     // Catch:{ all -> 0x0016 }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0016 }
            r3 = 1000(0x3e8, float:1.401E-42)
            r1.<init>(r0, r3)     // Catch:{ all -> 0x0016 }
            r1.write(r5)     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x0015
            r1.close()
        L_0x0015:
            return
        L_0x0016:
            r0 = move-exception
            r1 = r2
        L_0x0018:
            if (r1 == 0) goto L_0x001d
            r1.close()
        L_0x001d:
            throw r0
        L_0x001e:
            r0 = move-exception
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ejbca.cvc.example.FileHelper.writeFile(java.io.File, byte[]):void");
    }
}
