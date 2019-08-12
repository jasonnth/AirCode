package com.google.android.exoplayer.metadata.id3;

public final class PrivFrame extends Id3Frame {
    public final String owner;
    public final byte[] privateData;

    public PrivFrame(String owner2, byte[] privateData2) {
        super("PRIV");
        this.owner = owner2;
        this.privateData = privateData2;
    }
}
