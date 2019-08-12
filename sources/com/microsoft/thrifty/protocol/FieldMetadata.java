package com.microsoft.thrifty.protocol;

public final class FieldMetadata {
    public final short fieldId;
    public final String name;
    public final byte typeId;

    public FieldMetadata(String name2, byte typeId2, short fieldId2) {
        this.name = name2;
        this.typeId = typeId2;
        this.fieldId = fieldId2;
    }
}
