package com.threatmetrix.TrustDefender;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.threatmetrix.TrustDefender.a */
class C4759a {

    /* renamed from: a */
    private static final String f4158a = C4834w.m2892a(C4759a.class);

    C4759a() {
    }

    /* renamed from: a */
    private static String m2572a(String path) throws InterruptedException {
        if (NativeGatherer.m2512a().mo45864b()) {
            return NativeGatherer.m2512a().mo45858a(path);
        }
        String str = f4158a;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            try {
                InputStream is = new FileInputStream(path);
                String md5String = null;
                byte[] buffer = new byte[8192];
                while (true) {
                    try {
                        int read = is.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        digest.update(buffer, 0, read);
                    } catch (IOException e) {
                        String str2 = f4158a;
                        try {
                        } catch (IOException e2) {
                            String str3 = f4158a;
                        }
                    } finally {
                        try {
                            is.close();
                        } catch (IOException e3) {
                            String str4 = f4158a;
                        }
                    }
                }
                md5String = String.format("%32s", new Object[]{new BigInteger(1, digest.digest()).toString(16)}).replace(' ', '0');
                try {
                } catch (IOException e4) {
                    String str5 = f4158a;
                }
                String str6 = f4158a;
                new StringBuilder("Got : ").append(md5String);
                return md5String;
            } catch (FileNotFoundException e5) {
                String str7 = f4158a;
                return null;
            }
        } catch (NoSuchAlgorithmException e6) {
            String str8 = f4158a;
            return null;
        }
    }

    /* renamed from: a */
    static String m2571a(Context context) throws InterruptedException {
        String sourceDir = new C4798a(context).mo46042b();
        C4834w.m2901c(f4158a, "sourceDir: " + sourceDir);
        if (C4770ai.m2633f(sourceDir)) {
            return m2572a(sourceDir);
        }
        return null;
    }
}
