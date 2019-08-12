package com.microsoft.thrifty.protocol;

public final class SetMetadata {
    public final byte elementTypeId;
    public final int size;

    public SetMetadata(byte elementTypeId2, int size2) {
        this.elementTypeId = elementTypeId2;
        this.size = size2;
    }
}
