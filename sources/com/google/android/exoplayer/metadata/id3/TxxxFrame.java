package com.google.android.exoplayer.metadata.id3;

public final class TxxxFrame extends Id3Frame {
    public final String description;
    public final String value;

    public TxxxFrame(String description2, String value2) {
        super("TXXX");
        this.description = description2;
        this.value = value2;
    }
}
