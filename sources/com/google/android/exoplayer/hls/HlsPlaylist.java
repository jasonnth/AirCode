package com.google.android.exoplayer.hls;

public abstract class HlsPlaylist {
    public final String baseUri;
    public final int type;

    protected HlsPlaylist(String baseUri2, int type2) {
        this.baseUri = baseUri2;
        this.type = type2;
    }
}
