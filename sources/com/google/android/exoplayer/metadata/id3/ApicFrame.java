package com.google.android.exoplayer.metadata.id3;

public final class ApicFrame extends Id3Frame {
    public final String description;
    public final String mimeType;
    public final byte[] pictureData;
    public final int pictureType;

    public ApicFrame(String mimeType2, String description2, int pictureType2, byte[] pictureData2) {
        super("APIC");
        this.mimeType = mimeType2;
        this.description = description2;
        this.pictureType = pictureType2;
        this.pictureData = pictureData2;
    }
}
