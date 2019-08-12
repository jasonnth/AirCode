package com.devbrackets.android.exomedia.util;

import android.net.Uri;
import com.devbrackets.android.exomedia.type.MediaSourceType;

public class MediaSourceUtil {
    public static MediaSourceType getType(Uri uri) {
        String extension = getExtension(uri);
        if (extension != null) {
            return MediaSourceType.getByExtension(extension);
        }
        return MediaSourceType.getByLooseComparison(uri);
    }

    public static String getExtension(Uri uri) {
        String path = uri.getLastPathSegment();
        if (path == null) {
            return null;
        }
        int periodIndex = path.lastIndexOf(46);
        if (periodIndex == -1 && uri.getPathSegments().size() > 1) {
            path = (String) uri.getPathSegments().get(uri.getPathSegments().size() - 2);
            periodIndex = path.lastIndexOf(46);
        }
        if (periodIndex == -1) {
            periodIndex = 0;
            path = "." + uri.getLastPathSegment();
        }
        return path.substring(periodIndex).toLowerCase();
    }
}
