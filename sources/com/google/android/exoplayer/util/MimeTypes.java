package com.google.android.exoplayer.util;

import com.facebook.internal.AnalyticsEvents;

public final class MimeTypes {
    public static boolean isAudio(String mimeType) {
        return getTopLevelType(mimeType).equals("audio");
    }

    public static boolean isVideo(String mimeType) {
        return getTopLevelType(mimeType).equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
    }

    public static boolean isText(String mimeType) {
        return getTopLevelType(mimeType).equals("text");
    }

    private static String getTopLevelType(String mimeType) {
        int indexOfSlash = mimeType.indexOf(47);
        if (indexOfSlash != -1) {
            return mimeType.substring(0, indexOfSlash);
        }
        throw new IllegalArgumentException("Invalid mime type: " + mimeType);
    }

    public static String getVideoMediaMimeType(String codecs) {
        if (codecs == null) {
            return "video/x-unknown";
        }
        for (String codec : codecs.split(",")) {
            String codec2 = codec.trim();
            if (codec2.startsWith("avc1") || codec2.startsWith("avc3")) {
                return "video/avc";
            }
            if (codec2.startsWith("hev1") || codec2.startsWith("hvc1")) {
                return "video/hevc";
            }
            if (codec2.startsWith("vp9")) {
                return "video/x-vnd.on2.vp9";
            }
            if (codec2.startsWith("vp8")) {
                return "video/x-vnd.on2.vp8";
            }
        }
        return "video/x-unknown";
    }

    public static String getAudioMediaMimeType(String codecs) {
        if (codecs == null) {
            return "audio/x-unknown";
        }
        for (String codec : codecs.split(",")) {
            String codec2 = codec.trim();
            if (codec2.startsWith("mp4a")) {
                return "audio/mp4a-latm";
            }
            if (codec2.startsWith("ac-3") || codec2.startsWith("dac3")) {
                return "audio/ac3";
            }
            if (codec2.startsWith("ec-3") || codec2.startsWith("dec3")) {
                return "audio/eac3";
            }
            if (codec2.startsWith("dtsc")) {
                return "audio/vnd.dts";
            }
            if (codec2.startsWith("dtsh") || codec2.startsWith("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (codec2.startsWith("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (codec2.startsWith("opus")) {
                return "audio/opus";
            }
            if (codec2.startsWith("vorbis")) {
                return "audio/vorbis";
            }
        }
        return "audio/x-unknown";
    }
}
