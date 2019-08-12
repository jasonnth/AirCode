package com.microsoft.thrifty.protocol;

public class MapMetadata {
    public final byte keyTypeId;
    public final int size;
    public final byte valueTypeId;

    public MapMetadata(byte keyTypeId2, byte valueTypeId2, int size2) {
        this.keyTypeId = keyTypeId2;
        this.valueTypeId = valueTypeId2;
        this.size = size2;
    }
}
