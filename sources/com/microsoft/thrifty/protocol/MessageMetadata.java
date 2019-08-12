package com.microsoft.thrifty.protocol;

public class MessageMetadata {
    public final String name;
    public final int seqId;
    public final byte type;

    public MessageMetadata(String name2, byte type2, int seqId2) {
        if (name2 == null) {
            name2 = "";
        }
        this.name = name2;
        this.type = type2;
        this.seqId = seqId2;
    }
}
