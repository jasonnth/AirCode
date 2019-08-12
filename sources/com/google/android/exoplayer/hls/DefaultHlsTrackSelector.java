package com.google.android.exoplayer.hls;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.exoplayer.chunk.VideoFormatSelectorUtil;
import com.google.android.exoplayer.hls.HlsTrackSelector.Output;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class DefaultHlsTrackSelector implements HlsTrackSelector {
    private final Context context;
    private final int type;

    public static DefaultHlsTrackSelector newDefaultInstance(Context context2) {
        return new DefaultHlsTrackSelector(context2, 0);
    }

    public static DefaultHlsTrackSelector newAudioInstance() {
        return new DefaultHlsTrackSelector(null, 1);
    }

    public static DefaultHlsTrackSelector newSubtitleInstance() {
        return new DefaultHlsTrackSelector(null, 2);
    }

    private DefaultHlsTrackSelector(Context context2, int type2) {
        this.context = context2;
        this.type = type2;
    }

    public void selectTracks(HlsMasterPlaylist playlist, Output output) throws IOException {
        if (this.type == 1 || this.type == 2) {
            List<Variant> variants = this.type == 1 ? playlist.audios : playlist.subtitles;
            if (variants != null && !variants.isEmpty()) {
                for (int i = 0; i < variants.size(); i++) {
                    output.fixedTrack(playlist, (Variant) variants.get(i));
                }
                return;
            }
            return;
        }
        ArrayList<Variant> enabledVariantList = new ArrayList<>();
        int[] variantIndices = VideoFormatSelectorUtil.selectVideoFormatsForDefaultDisplay(this.context, playlist.variants, null, false);
        for (int i2 : variantIndices) {
            enabledVariantList.add(playlist.variants.get(i2));
        }
        ArrayList<Variant> definiteVideoVariants = new ArrayList<>();
        ArrayList<Variant> definiteAudioOnlyVariants = new ArrayList<>();
        for (int i3 = 0; i3 < enabledVariantList.size(); i3++) {
            Variant variant = (Variant) enabledVariantList.get(i3);
            if (variant.format.height > 0 || variantHasExplicitCodecWithPrefix(variant, "avc")) {
                definiteVideoVariants.add(variant);
            } else if (variantHasExplicitCodecWithPrefix(variant, "mp4a")) {
                definiteAudioOnlyVariants.add(variant);
            }
        }
        if (!definiteVideoVariants.isEmpty()) {
            enabledVariantList = definiteVideoVariants;
        } else if (definiteAudioOnlyVariants.size() < enabledVariantList.size()) {
            enabledVariantList.removeAll(definiteAudioOnlyVariants);
        }
        if (enabledVariantList.size() > 1) {
            Variant[] enabledVariants = new Variant[enabledVariantList.size()];
            enabledVariantList.toArray(enabledVariants);
            output.adaptiveTrack(playlist, enabledVariants);
        }
        for (int i4 = 0; i4 < enabledVariantList.size(); i4++) {
            output.fixedTrack(playlist, (Variant) enabledVariantList.get(i4));
        }
    }

    private static boolean variantHasExplicitCodecWithPrefix(Variant variant, String prefix) {
        String codecs = variant.format.codecs;
        if (TextUtils.isEmpty(codecs)) {
            return false;
        }
        String[] codecArray = codecs.split("(\\s*,\\s*)|(\\s*$)");
        for (String startsWith : codecArray) {
            if (startsWith.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
