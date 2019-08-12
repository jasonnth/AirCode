package com.devbrackets.android.exomedia.type;

import android.net.Uri;

public enum MediaSourceType {
    AAC(".aac", null),
    MP4(".mp4", null),
    MP3(".mp3", null),
    M4A(".m4a", null),
    FMP4(".fmp4", null),
    TS(".ts", null),
    WEBM(".webm", null),
    MKV(".mkv", null),
    _3GP(".3gp", null),
    HLS(".m3u8", ".*m3u8.*"),
    DASH(".mpd", ".*mpd.*"),
    SMOOTH_STREAM(".ism", ".*ism.*"),
    UNKNOWN(null, null);
    
    private String extension;
    private String looseComparisonRegex;

    private MediaSourceType(String extension2, String looseComparisonRegex2) {
        this.extension = extension2;
        this.looseComparisonRegex = looseComparisonRegex2;
    }

    public String getExtension() {
        return this.extension;
    }

    public String getLooseComparisonRegex() {
        return this.looseComparisonRegex;
    }

    public static MediaSourceType getByExtension(String extension2) {
        MediaSourceType[] values;
        for (MediaSourceType type : values()) {
            if (type.getExtension() != null && type.getExtension().equalsIgnoreCase(extension2)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static MediaSourceType getByLooseComparison(Uri uri) {
        MediaSourceType[] values;
        for (MediaSourceType type : values()) {
            if (type.getLooseComparisonRegex() != null && uri.toString().matches(type.getLooseComparisonRegex())) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
