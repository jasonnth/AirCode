package com.jumio.core.util;

import android.content.Context;
import com.jumio.core.environment.CpuInfo;
import com.jumio.core.environment.Environment;

public class DeviceUtil {
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b1, code lost:
        r1 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDeviceId(android.content.Context r12) {
        /*
            r9 = 0
            if (r12 != 0) goto L_0x0005
            r1 = r9
        L_0x0004:
            return r1
        L_0x0005:
            r1 = 0
            r8 = 0
            java.lang.String r10 = "UniqueID"
            r11 = 0
            android.content.SharedPreferences r8 = r12.getSharedPreferences(r10, r11)
            if (r8 == 0) goto L_0x0018
            java.lang.String r10 = "deviceId"
            java.lang.String r1 = r8.getString(r10, r9)
        L_0x0018:
            if (r1 == 0) goto L_0x0023
            java.lang.String r9 = ""
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x0045
        L_0x0023:
            android.content.ContentResolver r9 = r12.getContentResolver()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r10 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r9, r10)     // Catch:{ Exception -> 0x00b0 }
            if (r0 == 0) goto L_0x0045
            int r9 = r0.length()     // Catch:{ Exception -> 0x00b0 }
            if (r9 <= 0) goto L_0x0045
            java.lang.String r9 = "9774d56d682e549c"
            boolean r9 = r0.equals(r9)     // Catch:{ Exception -> 0x00b0 }
            if (r9 != 0) goto L_0x0045
            java.util.Locale r9 = java.util.Locale.GERMAN     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r1 = r0.toUpperCase(r9)     // Catch:{ Exception -> 0x00b0 }
        L_0x0045:
            if (r1 == 0) goto L_0x0050
            java.lang.String r9 = ""
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x0058
        L_0x0050:
            java.lang.String r9 = android.os.Build.SERIAL     // Catch:{ Exception -> 0x00b3 }
            java.util.Locale r10 = java.util.Locale.GERMAN     // Catch:{ Exception -> 0x00b3 }
            java.lang.String r1 = r9.toUpperCase(r10)     // Catch:{ Exception -> 0x00b3 }
        L_0x0058:
            if (r1 == 0) goto L_0x0063
            java.lang.String r9 = ""
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x0073
        L_0x0063:
            java.util.UUID r9 = java.util.UUID.randomUUID()
            java.lang.String r9 = r9.toString()
            r10 = 45
            r11 = 48
            java.lang.String r1 = r9.replace(r10, r11)
        L_0x0073:
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x00cb }
            r5.<init>()     // Catch:{ Exception -> 0x00cb }
            java.lang.String r9 = "SHA-1"
            java.security.MessageDigest r7 = java.security.MessageDigest.getInstance(r9)     // Catch:{ Exception -> 0x00cb }
            r7.reset()     // Catch:{ Exception -> 0x00cb }
            java.lang.String r9 = "UTF-8"
            byte[] r9 = r1.getBytes(r9)     // Catch:{ Exception -> 0x00cb }
            r7.update(r9)     // Catch:{ Exception -> 0x00cb }
            byte[] r4 = r7.digest()     // Catch:{ Exception -> 0x00cb }
            r6 = 0
        L_0x0091:
            int r9 = r4.length     // Catch:{ Exception -> 0x00cb }
            if (r6 >= r9) goto L_0x00b6
            byte r9 = r4[r6]     // Catch:{ Exception -> 0x00cb }
            r9 = r9 & 255(0xff, float:3.57E-43)
            r10 = 16
            if (r9 >= r10) goto L_0x00a2
            java.lang.String r9 = "0"
            r5.append(r9)     // Catch:{ Exception -> 0x00cb }
        L_0x00a2:
            byte r9 = r4[r6]     // Catch:{ Exception -> 0x00cb }
            r9 = r9 & 255(0xff, float:3.57E-43)
            java.lang.String r9 = java.lang.Integer.toHexString(r9)     // Catch:{ Exception -> 0x00cb }
            r5.append(r9)     // Catch:{ Exception -> 0x00cb }
            int r6 = r6 + 1
            goto L_0x0091
        L_0x00b0:
            r2 = move-exception
            r1 = 0
            goto L_0x0045
        L_0x00b3:
            r2 = move-exception
            r1 = 0
            goto L_0x0058
        L_0x00b6:
            java.lang.String r1 = r5.toString()     // Catch:{ Exception -> 0x00cb }
        L_0x00ba:
            if (r8 == 0) goto L_0x0004
            android.content.SharedPreferences$Editor r3 = r8.edit()
            java.lang.String r9 = "deviceId"
            r3.putString(r9, r1)
            r3.commit()
            goto L_0x0004
        L_0x00cb:
            r2 = move-exception
            java.lang.String r1 = "0000000000000000"
            goto L_0x00ba
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.core.util.DeviceUtil.getDeviceId(android.content.Context):java.lang.String");
    }

    public static boolean isSupportedPlatform(boolean useIntel) {
        boolean ARMv7;
        boolean x86;
        boolean x86_64;
        Environment.loadCpuInfoLib();
        String cpuFeatures = getCpuFeatures();
        String cpuFamily = getCpuFamily();
        if (!cpuFamily.equals("ARM") || !cpuFeatures.contains("ARMv7") || !cpuFeatures.contains("NEON")) {
            ARMv7 = false;
        } else {
            ARMv7 = true;
        }
        boolean ARMv8 = cpuFamily.equals("ARM64");
        if (!cpuFamily.equals("X86") || !useIntel) {
            x86 = false;
        } else {
            x86 = true;
        }
        if (!cpuFamily.equals("X86_64") || !useIntel) {
            x86_64 = false;
        } else {
            x86_64 = true;
        }
        if (ARMv7 || ARMv8 || x86 || x86_64) {
            return true;
        }
        return false;
    }

    public static String getCpuFamily() {
        int cpuFamily = CpuInfo.getCpuFamily();
        switch (cpuFamily) {
            case 1:
                return "ARM";
            case 2:
                return "X86";
            case 3:
                return "MIPS";
            case 4:
                return "ARM64";
            case 5:
                return "X86_64";
            case 6:
                return "MIPS64";
            default:
                return "UNKNOWN (" + cpuFamily + ")";
        }
    }

    public static String getCpuFeatures() {
        int cpuFamily = CpuInfo.getCpuFamily();
        int cpuFeatures = CpuInfo.getCpuFeatures();
        String platform = "";
        switch (cpuFamily) {
            case 1:
                if ((cpuFeatures & 1) == 1) {
                    platform = platform + "ARMv7 ";
                }
                if ((cpuFeatures & 2) == 2) {
                    platform = platform + "VFPv3 ";
                }
                if ((cpuFeatures & 4) == 4) {
                    platform = platform + "NEON ";
                }
                if ((cpuFeatures & 8) == 8) {
                    platform = platform + "LDREX_STREX ";
                }
                if ((cpuFeatures & 16) == 16) {
                    platform = platform + "VFPv2 ";
                }
                if ((cpuFeatures & 32) == 32) {
                    platform = platform + "VFP_D32 ";
                }
                if ((cpuFeatures & 64) == 64) {
                    platform = platform + "VFP_FP16 ";
                }
                if ((cpuFeatures & 128) == 128) {
                    platform = platform + "VFP_FMA ";
                }
                if ((cpuFeatures & 256) == 256) {
                    platform = platform + "NEON_FMA ";
                }
                if ((cpuFeatures & 512) == 512) {
                    platform = platform + "IDIV_ARM ";
                }
                if ((cpuFeatures & 1024) == 1024) {
                    platform = platform + "IDIV_THUMB2 ";
                }
                if ((cpuFeatures & 2048) == 2048) {
                    platform = platform + "iWMMXt ";
                }
                if ((cpuFeatures & 4096) == 4096) {
                    platform = platform + "AES ";
                }
                if ((cpuFeatures & 8192) == 8192) {
                    platform = platform + "PMULL ";
                }
                if ((cpuFeatures & 16384) == 16384) {
                    platform = platform + "SHA1 ";
                }
                if ((32768 & cpuFeatures) == 32768) {
                    platform = platform + "SHA2 ";
                }
                if ((65536 & cpuFeatures) == 65536) {
                    platform = platform + "CRC32 ";
                }
                return platform;
            case 2:
                if ((cpuFeatures & 1) == 1) {
                    platform = platform + "SSSE3 ";
                }
                if ((cpuFeatures & 2) == 2) {
                    platform = platform + "POPCNT ";
                }
                if ((cpuFeatures & 4) == 4) {
                    platform = platform + "MOVBE ";
                }
                return platform;
            case 4:
                if ((cpuFeatures & 1) == 1) {
                    platform = platform + "FP ";
                }
                if ((cpuFeatures & 2) == 2) {
                    platform = platform + "ASIMD ";
                }
                if ((cpuFeatures & 4) == 4) {
                    platform = platform + "AES ";
                }
                if ((cpuFeatures & 8) == 8) {
                    platform = platform + "PMULL ";
                }
                if ((cpuFeatures & 16) == 16) {
                    platform = platform + "SHA1 ";
                }
                if ((cpuFeatures & 32) == 32) {
                    platform = platform + "SHA2 ";
                }
                if ((cpuFeatures & 64) == 64) {
                    platform = platform + "CRC32 ";
                }
                return platform;
            default:
                return "";
        }
    }

    public static String getCpuCount(Context context) {
        return Integer.toString(CpuInfo.getCpuCount());
    }
}
