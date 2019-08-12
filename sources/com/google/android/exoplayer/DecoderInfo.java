package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo.CodecCapabilities;
import com.google.android.exoplayer.util.Util;

@TargetApi(16)
public final class DecoderInfo {
    public final boolean adaptive;
    public final CodecCapabilities capabilities;
    public final String name;

    DecoderInfo(String name2, CodecCapabilities capabilities2) {
        this.name = name2;
        this.capabilities = capabilities2;
        this.adaptive = isAdaptive(capabilities2);
    }

    private static boolean isAdaptive(CodecCapabilities capabilities2) {
        return capabilities2 != null && Util.SDK_INT >= 19 && isAdaptiveV19(capabilities2);
    }

    @TargetApi(19)
    private static boolean isAdaptiveV19(CodecCapabilities capabilities2) {
        return capabilities2.isFeatureSupported("adaptive-playback");
    }
}
