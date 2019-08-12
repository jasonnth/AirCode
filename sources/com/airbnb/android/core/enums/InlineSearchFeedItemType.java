package com.airbnb.android.core.enums;

public enum InlineSearchFeedItemType {
    Filter("filter"),
    Message("message");
    
    private final String type;

    private InlineSearchFeedItemType(String type2) {
        this.type = type2;
    }

    public String getType() {
        return this.type;
    }

    public static InlineSearchFeedItemType fromType(String type2) {
        InlineSearchFeedItemType[] values;
        for (InlineSearchFeedItemType t : values()) {
            if (t.type.equals(type2)) {
                return t;
            }
        }
        return null;
    }
}
