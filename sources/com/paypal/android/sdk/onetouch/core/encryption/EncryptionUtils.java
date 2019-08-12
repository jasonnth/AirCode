package com.paypal.android.sdk.onetouch.core.encryption;

import android.util.Base64;
import com.facebook.appevents.AppEventsConstants;
import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class EncryptionUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    static {
        PRNGFixes.apply();
    }

    public static byte[] generateRandomData(int size) {
        byte[] output = new byte[size];
        RANDOM.nextBytes(output);
        return output;
    }

    public static X509Certificate getX509CertificateFromBase64String(String certificateBase64) throws CertificateException {
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(certificateBase64, 0)));
    }

    public static String byteArrayToHexString(byte[] array) {
        if (array == null) {
            return null;
        }
        StringBuilder hexString = new StringBuilder();
        for (byte b : array) {
            int intVal = b & 255;
            if (intVal < 16) {
                hexString.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            hexString.append(Integer.toHexString(intVal));
        }
        return hexString.toString().toUpperCase();
    }

    static boolean isEqual(byte[] arrayOne, byte[] arrayTwo) {
        if (arrayOne.length != arrayTwo.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < arrayOne.length; i++) {
            result |= arrayOne[i] ^ arrayTwo[i];
        }
        if (result == 0) {
            return true;
        }
        return false;
    }
}
