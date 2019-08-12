package com.alipay.sdk.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Utils {

    public static class AlipayPkg {
        public byte[] sign;
        public int versionCode;
    }

    static String getPublicKey(byte[] signature) {
        try {
            String publickey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature))).getPublicKey().toString();
            if (publickey.indexOf("modulus") != -1) {
                return publickey.substring(publickey.indexOf("modulus") + 8, publickey.lastIndexOf(",")).trim();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static AlipayPkg getSign(Context context, String packageName) {
        for (PackageInfo info : context.getPackageManager().getInstalledPackages(64)) {
            if (info.packageName.equals(packageName)) {
                AlipayPkg pkg = new AlipayPkg();
                pkg.sign = info.signatures[0].toByteArray();
                pkg.versionCode = info.versionCode;
                return pkg;
            }
        }
        return null;
    }
}
