package com.devbrackets.android.exomedia.util;

import android.net.Uri;

public class MediaUtil {
    public static String getUriWithProtocol(String uri) {
        if (uri == null) {
            return "";
        }
        if (Uri.parse(uri).getScheme() == null) {
            return "file://" + uri;
        }
        return uri;
    }
}
