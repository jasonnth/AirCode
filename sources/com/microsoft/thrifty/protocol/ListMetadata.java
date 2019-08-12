package com.microsoft.thrifty.protocol;

public final class ListMetadata {
    public final byte elementTypeId;
    public final int size;

    public ListMetadata(byte elementTypeId2, int size2) {
        this.elementTypeId = elementTypeId2;
        this.size = size2;
    }
}
