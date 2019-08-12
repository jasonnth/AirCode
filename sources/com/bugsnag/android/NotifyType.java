package com.bugsnag.android;

public enum NotifyType {
    ALL(Integer.valueOf(1)),
    USER(Integer.valueOf(2)),
    APP(Integer.valueOf(3)),
    DEVICE(Integer.valueOf(4)),
    CONTEXT(Integer.valueOf(5)),
    RELEASE_STAGES(Integer.valueOf(6)),
    FILTERS(Integer.valueOf(7)),
    BREADCRUMB(Integer.valueOf(8)),
    META(Integer.valueOf(9));
    
    private Integer intValue;

    private NotifyType(Integer intValue2) {
        this.intValue = intValue2;
    }

    public Integer getValue() {
        return this.intValue;
    }

    public static NotifyType fromInt(Integer intValue2) {
        NotifyType[] values;
        if (intValue2 != null) {
            for (NotifyType type : values()) {
                if (intValue2.equals(type.getValue())) {
                    return type;
                }
            }
        }
        return null;
    }
}
