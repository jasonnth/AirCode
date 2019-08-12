package com.google.firebase.appindexing.internal;

import android.net.Uri;
import android.webkit.URLUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.appindexing.FirebaseAppIndexingException;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseAppIndexingTooManyArgumentsException;

public class zzs {
    public static String zzE(String str, int i) {
        if (str.length() <= i) {
            return str;
        }
        if (i <= 0) {
            return "";
        }
        if (Character.isHighSurrogate(str.charAt(i - 1)) && Character.isLowSurrogate(str.charAt(i))) {
            i--;
        }
        return str.substring(0, i);
    }

    public static FirebaseAppIndexingException zzb(Status status, String str) {
        zzac.zzw(status);
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null && !statusMessage.isEmpty()) {
            str = statusMessage;
        }
        switch (status.getStatusCode()) {
            case 17510:
                return new FirebaseAppIndexingInvalidArgumentException(str);
            case 17511:
                return new FirebaseAppIndexingTooManyArgumentsException(str);
            default:
                return new FirebaseAppIndexingException(str);
        }
    }

    public static void zziu(String str) throws FirebaseAppIndexingInvalidArgumentException {
        zzac.zzw(str);
        Uri parse = Uri.parse(str);
        if (parse == null || !parse.isAbsolute()) {
            throw new FirebaseAppIndexingInvalidArgumentException(new StringBuilder(String.valueOf(str).length() + 33).append("Invalid String passed as url: '").append(str).append("'.").toString());
        }
    }

    public static void zziv(String str) throws FirebaseAppIndexingInvalidArgumentException {
        zzac.zzw(str);
        if (!URLUtil.isHttpUrl(str) && !URLUtil.isHttpsUrl(str)) {
            throw new FirebaseAppIndexingInvalidArgumentException(new StringBuilder(String.valueOf(str).length() + 46).append("Web url must be with http or https scheme: '").append(str).append("'.").toString());
        }
    }
}
