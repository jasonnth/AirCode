package com.threatmetrix.TrustDefender;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.threatmetrix.TrustDefender.ai */
class C4770ai {

    /* renamed from: a */
    private static final String f4215a = C4834w.m2892a(C4770ai.class);

    /* renamed from: b */
    private static final char[] f4216b = "0123456789abcdef".toCharArray();

    /* renamed from: c */
    private static final MessageDigest f4217c;

    /* renamed from: d */
    private static final MessageDigest f4218d;

    /* renamed from: e */
    private static final Pattern f4219e = Pattern.compile("^[a-zA-Z0-9]{8}$");

    C4770ai() {
    }

    static {
        MessageDigest temp = null;
        C4834w.m2901c(f4215a, "Getting SHA1 digest");
        try {
            temp = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            String str = f4215a;
        }
        f4218d = temp;
        MessageDigest temp2 = null;
        if (!NativeGatherer.m2512a().mo45864b()) {
            C4834w.m2901c(f4215a, "Getting MD5 digest");
            try {
                temp2 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e2) {
                String str2 = f4215a;
            }
        }
        f4217c = temp2;
    }

    /* renamed from: a */
    static String m2622a(String in) throws InterruptedException {
        if (NativeGatherer.m2512a().mo45864b()) {
            return NativeGatherer.m2512a().mo45869e(in);
        }
        try {
            return URLEncoder.encode(in, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            String str = f4215a;
            return null;
        }
    }

    /* renamed from: b */
    static String m2628b(String inputStr) throws InterruptedException {
        String a;
        if (inputStr == null || inputStr.isEmpty()) {
            return "";
        }
        if (NativeGatherer.m2512a().mo45864b()) {
            String result = NativeGatherer.m2512a().mo45861b(inputStr);
            if (result == null) {
                return "";
            }
            return result;
        } else if (f4217c == null) {
            return "";
        } else {
            synchronized (f4217c) {
                f4217c.update(inputStr.getBytes());
                byte[] outputData = f4217c.digest();
                f4217c.reset();
                a = m2626a(outputData);
            }
            return a;
        }
    }

    /* renamed from: k */
    private static String m2638k(String inputStr) throws InterruptedException {
        String a;
        if (inputStr == null || inputStr.isEmpty()) {
            return "";
        }
        if (NativeGatherer.m2512a().mo45864b()) {
            String result = NativeGatherer.m2512a().mo45868d(inputStr);
            if (result == null) {
                return "";
            }
            return result;
        } else if (f4218d == null) {
            return "";
        } else {
            synchronized (f4218d) {
                f4218d.update(inputStr.getBytes());
                byte[] outputData = f4218d.digest();
                f4218d.reset();
                a = m2626a(outputData);
            }
            return a;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0035=Splitter:B:22:0x0035, B:36:0x0058=Splitter:B:36:0x0058} */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String m2630c(java.lang.String r9) {
        /*
            boolean r6 = m2632e(r9)
            if (r6 == 0) goto L_0x000a
            java.lang.String r0 = ""
        L_0x0009:
            return r0
        L_0x000a:
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0064 }
            r3.<init>(r9)     // Catch:{ FileNotFoundException -> 0x0064 }
            r2 = r3
        L_0x0011:
            java.security.MessageDigest r6 = f4218d
            if (r6 == 0) goto L_0x0060
            if (r2 == 0) goto L_0x0060
            java.lang.String r6 = f4215a
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r6]
            java.security.MessageDigest r7 = f4218d
            monitor-enter(r7)
            r5 = 0
        L_0x0021:
            int r5 = r2.read(r1)     // Catch:{ IOException -> 0x002f }
            r6 = -1
            if (r5 == r6) goto L_0x0048
            java.security.MessageDigest r6 = f4218d     // Catch:{ IOException -> 0x002f }
            r8 = 0
            r6.update(r1, r8, r5)     // Catch:{ IOException -> 0x002f }
            goto L_0x0021
        L_0x002f:
            r6 = move-exception
            java.lang.String r6 = f4215a     // Catch:{ all -> 0x0054 }
            r2.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0035:
            java.security.MessageDigest r6 = f4218d     // Catch:{ all -> 0x0059 }
            byte[] r4 = r6.digest()     // Catch:{ all -> 0x0059 }
            java.security.MessageDigest r6 = f4218d     // Catch:{ all -> 0x0059 }
            r6.reset()     // Catch:{ all -> 0x0059 }
            monitor-exit(r7)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = m2626a(r4)
            java.lang.String r6 = f4215a
            goto L_0x0009
        L_0x0048:
            r2.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x0035
        L_0x004c:
            r6 = move-exception
            java.lang.String r6 = f4215a     // Catch:{ all -> 0x0059 }
            goto L_0x0035
        L_0x0050:
            r6 = move-exception
            java.lang.String r6 = f4215a     // Catch:{ all -> 0x0059 }
            goto L_0x0035
        L_0x0054:
            r6 = move-exception
            r2.close()     // Catch:{ IOException -> 0x005c }
        L_0x0058:
            throw r6     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r6 = move-exception
            monitor-exit(r7)
            throw r6
        L_0x005c:
            r8 = move-exception
            java.lang.String r8 = f4215a     // Catch:{ all -> 0x0059 }
            goto L_0x0058
        L_0x0060:
            java.lang.String r0 = ""
            goto L_0x0009
        L_0x0064:
            r6 = move-exception
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4770ai.m2630c(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private static String m2626a(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = f4216b[v >>> 4];
            hexChars[(j * 2) + 1] = f4216b[v & 15];
        }
        return new String(hexChars);
    }

    /* renamed from: a */
    static String m2623a(String input, String key) throws InterruptedException {
        int index;
        if (NativeGatherer.m2512a().mo45864b()) {
            return NativeGatherer.m2512a().mo45862b(input, key);
        }
        String lengthString = input.length() + "&";
        byte[] buffer = new byte[(input.length() + lengthString.length())];
        int k_len = key.length();
        int index2 = 0;
        int c = 0;
        int i = 0;
        while (i < lengthString.length()) {
            int c2 = c + 1;
            int index3 = index2 + 1;
            buffer[c] = (byte) (((byte) lengthString.charAt(i)) ^ (((byte) key.charAt(index2)) & 10));
            if (index3 >= k_len) {
                index2 = 0;
            } else {
                index2 = index3;
            }
            i++;
            c = c2;
        }
        int i2 = 0;
        while (i2 < input.length()) {
            int c3 = c + 1;
            int index4 = index2 + 1;
            buffer[c] = (byte) (((byte) input.charAt(i2)) ^ (((byte) key.charAt(index2)) & 10));
            if (index4 >= k_len) {
                index = 0;
            } else {
                index = index4;
            }
            i2++;
            c = c3;
        }
        return m2626a(buffer);
    }

    /* renamed from: a */
    static String m2621a() throws InterruptedException {
        String str = f4215a;
        if (NativeGatherer.m2512a().mo45864b()) {
            return NativeGatherer.m2512a().mo45857a(32);
        }
        return UUID.randomUUID().toString().toLowerCase(Locale.US).replaceAll("-", "");
    }

    /* renamed from: b */
    static List<String> m2629b(String input, String delim) {
        List<String> l = new ArrayList<>();
        while (true) {
            int index = input.indexOf(delim);
            if (index == -1) {
                break;
            }
            if (index > 1) {
                l.add(input.substring(0, index));
            }
            input = input.substring(delim.length() + index);
        }
        if (!input.isEmpty()) {
            l.add(input);
        }
        return l;
    }

    /* renamed from: d */
    static Map<String, String> m2631d(String query) {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        for (String pair : m2629b(query, "&")) {
            int idx = pair.indexOf("=");
            try {
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), JPushConstants.ENCODING_UTF_8), URLDecoder.decode(pair.substring(idx + 1), JPushConstants.ENCODING_UTF_8));
            } catch (UnsupportedEncodingException e) {
                String str = f4215a;
            }
        }
        return query_pairs;
    }

    /* renamed from: a */
    static String m2624a(List<String> list, String separator) {
        return m2625a(list, separator, false);
    }

    /* renamed from: a */
    static String m2625a(List<String> list, String separator, boolean addTrailingSep) {
        StringBuilder sb = new StringBuilder();
        for (String l : list) {
            if (!l.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(separator);
                }
                sb.append(l);
            }
        }
        if (addTrailingSep && sb.length() > 0) {
            sb.append(separator);
        }
        return sb.toString();
    }

    /* renamed from: e */
    static boolean m2632e(String input) {
        return input == null || input.isEmpty();
    }

    /* renamed from: f */
    static boolean m2633f(String input) {
        return input != null && !input.isEmpty();
    }

    /* renamed from: g */
    static String m2634g(String sessionId) {
        StringBuilder result = new StringBuilder();
        int len = 0;
        for (int i = 0; i < sessionId.length() && len < 128; i++) {
            char ch = sessionId.charAt(i);
            if ((ch >= '0' && ch <= '9') || ((ch >= 'a' && ch <= 'z') || ch == '_' || ch == '/' || ch == '-')) {
                result.append(ch);
                len++;
            } else if (ch >= 'A' && ch <= 'Z') {
                result.append(Character.toLowerCase(ch));
                len++;
            }
        }
        return result.toString();
    }

    /* renamed from: h */
    static String m2635h(String plain) {
        if (!m2633f(plain)) {
            return "";
        }
        try {
            return m2638k(plain);
        } catch (InterruptedException e) {
            return "";
        }
    }

    /* renamed from: a */
    static void m2627a(String input, boolean hashable, String keyName, Map<String, String> result) {
        if (hashable) {
            input = m2635h(input);
        }
        if (m2633f(input)) {
            result.put(keyName, input);
        }
    }

    /* renamed from: i */
    static String m2636i(String userAgent) {
        if (m2632e(userAgent)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        byte[] bytes = userAgent.getBytes(Charset.forName("UTF8"));
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 32 || bytes[i] > Byte.MAX_VALUE) {
                result.append("\\x").append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
            } else {
                result.append((char) bytes[i]);
            }
        }
        return result.toString();
    }

    /* renamed from: j */
    static boolean m2637j(String orgId) {
        return m2633f(orgId) && f4219e.matcher(orgId).find();
    }
}
