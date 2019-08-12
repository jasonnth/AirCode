package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ReactMarker {
    private static MarkerListener sMarkerListener = null;

    public interface MarkerListener {
        void logMarker(String str);
    }

    public static void setMarkerListener(MarkerListener listener) {
        sMarkerListener = listener;
    }

    @DoNotStrip
    public static void logMarker(String name) {
        if (sMarkerListener != null) {
            sMarkerListener.logMarker(name);
        }
    }
}
